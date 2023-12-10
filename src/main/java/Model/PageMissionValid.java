package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Network.Network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageMissionValid extends JFrame{
    private JTable missionTable;
    public DefaultTableModel tableModel;
    

    public PageMissionValid(){
         // création de la fenêtre
         setTitle("Listes de Toutes les missions ");
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
        
        // Charger toutes les missions des utilisateurs depuis la base de données 
        Connection connection = null;
        try {
            connection =Network.Connect();

            // Exécuter la requête SQL pour récupérer les missions de l'utilisateur
            String sql = "SELECT id, mission_description, date, States, Commentaire FROM Mission ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Parcourir les résultats et ajouter les lignes au modèle de tableau
                    while (resultSet.next()) {
                        int id = tableModel.getRowCount() + 1;
                        String desc = resultSet.getString("mission_description");
                        Date date =  resultSet.getDate("date");
                        String state = resultSet.getString("States");
                        String c = resultSet.getString("Commentaire");
                        
                        
                        tableModel.addRow(new Object[]{id, desc, date, state, c});
                    }
                }
            }
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
    JButton button1 = new JButton("Refuser Mission");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefuserMission();

        }
    });
        buttonPanel.add(button);
        buttonPanel.add(button1);
        add(buttonPanel, BorderLayout.SOUTH);
         // Configurer la fenêtre
        setSize(1000, 600);
        setLocationRelativeTo(null); // Centre la fenêtre
        setVisible(true);

    }
    public void RefuserMission() {
         // Créer une boîte de dialogue pour saisir les informations de la mission
         JTextField id = new JTextField();
         JTextField comm = new JTextField();

         JPanel myPanel = new JPanel();
         myPanel.setLayout(new GridLayout(5, 2));
         myPanel.add(new JLabel("Id de la mission :"));
         myPanel.add(id);
         myPanel.add(new JLabel("Commentaire :"));
         myPanel.add(comm);
         String missdescr="";
         String Comment="";
        
         
         int result = JOptionPane.showConfirmDialog(null, myPanel,"Veuillez saisir l'id de la mission et commentaire", JOptionPane.OK_CANCEL_OPTION);
         
         // Si l'utilisateur clique sur OK, Changer la mission
         if (result == JOptionPane.OK_OPTION) {
             int idmission = Integer.parseInt(id.getText());
             Comment=comm.getText();
             int i=0;
             for ( i =0;i< tableModel.getRowCount();i++) { 
                int val = Integer.valueOf(tableModel.getValueAt(i,0).toString());
                if (val == idmission) {
                tableModel.setValueAt("REFUSED", i, 3);
                tableModel.setValueAt(Comment, i, 4);
                missdescr = tableModel.getValueAt(i, 1).toString()  ;
                }     
         }
         Connection connection = null;
         try {
             // Établir la connexion à la base de données
             connection =Network.Connect();

             // Exécuter la requête SQL pour update la nouvelle mission
             String sql = "UPDATE Mission SET States = ?, Commentaire = ? WHERE mission_description=? ";
             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                 preparedStatement.setString(1, "REFUSED");
                 preparedStatement.setString(2,Comment); 
                 preparedStatement.setString(3,missdescr); 
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
public void ChoisirMission() {
         // Créer une boîte de dialogue pour saisir les informations de la mission
         JTextField id = new JTextField();

         JPanel myPanel = new JPanel();
         myPanel.setLayout(new GridLayout(5, 2));
         myPanel.add(new JLabel("Id de la mission :"));
         myPanel.add(id);
         String missdescr="";
        
         
         int result = JOptionPane.showConfirmDialog(null, myPanel,
                 "Veuillez saisir l'id de la mission", JOptionPane.OK_CANCEL_OPTION);
         
         // Si l'utilisateur clique sur OK, Changer la mission
         if (result == JOptionPane.OK_OPTION) {
             int idmission = Integer.parseInt(id.getText());
             int i=0;
             for ( i =0;i< tableModel.getRowCount();i++) { 
                int val = Integer.valueOf(tableModel.getValueAt(i,0).toString());
                if (val == idmission) {
                tableModel.setValueAt("IN PROGRESS", i, 3);
                missdescr = tableModel.getValueAt(i, 1).toString()  ;
                }     
         }
         Connection connection = null;
         try {
             // Établir la connexion à la base de données
             connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037", "projet_gei_037", "ook5ue9R");

             // Exécuter la requête SQL pour update la nouvelle mission
             String sql = "UPDATE Mission SET States = ? WHERE mission_description=? ";
             try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                 preparedStatement.setString(1, "IN PROGRESS");
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
        new PageMissionValid();
    }
    
}
