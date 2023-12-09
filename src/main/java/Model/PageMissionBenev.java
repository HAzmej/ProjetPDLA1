package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Network.Network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PageMissionBenev extends JFrame {
    private JTable missionTable;
    private DefaultTableModel tableModel;
    Connection connection = null;
   

    public PageMissionBenev(){
         // création de la fenêtre
        setTitle("Listes de Toutes les missions Validées");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // Créer un tableau de noms de colonnes
        Object[] columnNames = {"id", "mission_description","date","States","Commentaire"};
        tableModel = new DefaultTableModel(columnNames,0);
        missionTable= new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(missionTable);

         // Ajuster la taille des colonnes
        missionTable.setRowHeight(40);
        missionTable.getColumnModel().getColumn(0).setPreferredWidth(30);  // id
        missionTable.getColumnModel().getColumn(1).setPreferredWidth(200); // mission_description
        missionTable.getColumnModel().getColumn(2).setPreferredWidth(100); // date
        missionTable.getColumnModel().getColumn(3).setPreferredWidth(100); // States
        missionTable.getColumnModel().getColumn(4).setPreferredWidth(200); // Commentaire  
         // Ajouter le JScrollPane à la fenêtre
        add(scrollPane, BorderLayout.CENTER);

        try {
            connection =Network.Connect();
            // Exécuter la requête SQL pour récupérer les missions de l'utilisateur
            Network.tableauShowMissionBenev(tableModel,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion à la base de données
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Choix Mission");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChoisirMission();

        }
    });

        buttonPanel.add(button);
        add(buttonPanel, BorderLayout.SOUTH);
         // Configurer la fenêtre
        setSize(1000, 600);
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true);
    }
   
    void ChoisirMission() {
         // Créer une boîte de dialogue pour saisir les informations de la mission
         JTextField id = new JTextField();

         JPanel myPanel = new JPanel();
         myPanel.setLayout(new GridLayout(5, 2));
         myPanel.add(new JLabel("Id de la mission :"));
         myPanel.add(id);
         String missdescr="";
        
         int result = JOptionPane.showConfirmDialog(null, myPanel,
                 "Veuillez saisir l'id de la mission", JOptionPane.OK_CANCEL_OPTION);
         String aa="";
         Object obj;
         // Si l'utilisateur clique sur OK, Changer la mission
         if (result == JOptionPane.OK_OPTION) {
             int idmission = Integer.parseInt(id.getText());
             int i=0;

             for ( i =0;i< tableModel.getRowCount();i++) { 
                int val = Integer.valueOf(tableModel.getValueAt(i,0).toString());
                if (val == idmission) { if (tableModel.getValueAt(i,3)=="ACCEPTED") {aa="FINISHED";} else {aa="ACCEPTED";}
                obj= (Object) aa;
                tableModel.setValueAt(obj, i, 3);
                missdescr = tableModel.getValueAt(i, 1).toString()  ;
                }     
         }
         Connection connection = null;
         try {
            connection =Network.Connect();
            
             // Exécuter la requête SQL pour update la nouvelle mission
             String sql = "UPDATE Mission SET States = ? WHERE mission_description=? ";
             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                 preparedStatement.setString(1, aa);
                 preparedStatement.setString(2,missdescr); 
                 preparedStatement.executeUpdate();
             }
         } catch (SQLException ex) {
             ex.printStackTrace();
         } finally {
             // Fermer la connexion à la base de données
             if (connection != null) {
                 try {
                     connection.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
             }
         }


    }
}
    public static void main(String[] args) {
        new PageMissionBenev();
    }
}

