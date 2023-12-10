package Model;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Network.Network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PageMission  {
    private JFrame frame = new JFrame();
    private JTable missionTable;
    public DefaultTableModel tableModel;
  

    public PageMission(int idUtilisateur) throws SQLException{

        // création de la fenêtre
        frame.setTitle("Voici Vos Missions ajoutées");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Créer un tableau de noms de colonnes
        Object[] columnNames = {"id", "mission_description","date","States","Commentaire"};
       
        
        tableModel = new DefaultTableModel(columnNames,0);
        missionTable= new JTable(tableModel);
        missionTable.setRowHeight(40);
        missionTable.getColumnModel().getColumn(0).setPreferredWidth(30);  // id
        missionTable.getColumnModel().getColumn(1).setPreferredWidth(200); // mission_description
        missionTable.getColumnModel().getColumn(2).setPreferredWidth(100); // date
        missionTable.getColumnModel().getColumn(3).setPreferredWidth(100); // States
        missionTable.getColumnModel().getColumn(4).setPreferredWidth(200); // Commentaire

        JScrollPane scrollPane = new JScrollPane(missionTable);

        // Ajouter le JScrollPane à la fenêtre
        frame.add(scrollPane, BorderLayout.CENTER);

        // Charger les missions de l'utilisateur depuis la base de données 
        Connection connection = null;
        
        connection =Network.Connect();

            // Exécuter la requête SQL pour récupérer les missions de l'utilisateur
        Network.tableauShowMissionID(tableModel,connection,idUtilisateur);
        
        //Bouton Ajout de mission
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Ajouter");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterMission(idUtilisateur);

        }
    });
        
        JButton button1 = new JButton("Supprimer");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletemission();
        }
    });
         // Configurer la fenêtre
        buttonPanel.add(button);
        buttonPanel.add(button1);
        // Ajouter le panneau du bouton en bas de la fenêtre
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null); // Centre la fenêtre
        frame.setVisible(true);

    }
    
    
    public void deletemission(){
         Connection connection = null;
            try {
                 // Établir la connexion à la base de données
                connection =Network.Connect();

                // Exécuter la requête SQL pour supprimer la derniere mission
                String sql = "DELETE FROM Mission WHERE mission_description = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1,tableModel.getValueAt(tableModel.getRowCount()-1,1).toString());
                    preparedStatement.executeUpdate();
                    System.out.println(String.valueOf(tableModel.getValueAt(tableModel.getRowCount()-1,1)));
                    tableModel.removeRow(tableModel.getRowCount()-1);
                }
            }catch (SQLException ex) {
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
    
    public boolean ajouterMission(int idUser) {
        // Créer une boîte de dialogue pour saisir les informations de la mission
        JTextField descriptionField = new JTextField();
    
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(new JLabel("Description:"));
        myPanel.add(descriptionField);
        
        int result = JOptionPane.showConfirmDialog(null, myPanel,"Veuillez saisir les informations de la mission", JOptionPane.OK_CANCEL_OPTION);
        
        // Si l'utilisateur clique sur OK, ajouter la nouvelle mission
        if (result == JOptionPane.OK_OPTION) {
            String nouvelleDescription = descriptionField.getText();
            long mms = System.currentTimeMillis();
            java.sql.Date nouvelleDate = new Date(mms);
            String nouvelEtat ="Waiting";
            
            String nouveauCommentaire = "Pas de Commentaire";

            // Ajouter la nouvelle mission au modèle de tableau
            int nouvelId = tableModel.getRowCount() + 1; 
            tableModel.addRow(new Object[]{nouvelId, nouvelleDescription, nouvelleDate, nouvelEtat, nouveauCommentaire});

            // Insérer la nouvelle mission dans la base de données
            Connection connection = null;
            try {
                 // Établir la connexion à la base de données
                connection =Network.Connect();

                // Exécuter la requête SQL pour insérer la nouvelle mission
                String sql = "INSERT INTO Mission (id_user, mission_description, States, Commentaire,date) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setInt(1, idUser);
                    preparedStatement.setString(2, nouvelleDescription);
                    preparedStatement.setDate(5, nouvelleDate);
                    preparedStatement.setString(3, nouvelEtat);
                    preparedStatement.setString(4, nouveauCommentaire);
                    preparedStatement.executeUpdate();
                    return true;
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
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        new PageMission(1);
    }
}
