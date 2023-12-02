package Model;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageMission  {
    private JFrame frame = new JFrame();
    private JTable missionTable;
    private DefaultTableModel tableModel;

    public PageMission(int idUtilisateur){

        // création de la fenêtre
        frame.setTitle("Voici Vos Missions ajoutées");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        // Charger les missions de l'utilisateur depuis la base de données (remplacez cela par votre propre logique)
        Connection connection = null;
        try {
            // Remplacez les paramètres de connexion par les vôtres
            String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037";
            String usuarioBD = "projet_gei_037";
            String contrasenaBD = "ook5ue9R";

            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Exécuter la requête SQL pour récupérer les missions de l'utilisateur
            String sql = "SELECT id, mission_description, date, States, Commentaire FROM Mission WHERE id_user = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUtilisateur);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Parcourir les résultats et ajouter les lignes au modèle de tableau
                    while (resultSet.next()) {
                        int id = tableModel.getRowCount() + 1;
                        String desc = resultSet.getString("mission_description");
                        int date =  resultSet.getInt("date");
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
    private void deletemission(){
         Connection connection = null;
            try {
                // Établir la connexion à la base de données
                connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037", "projet_gei_037", "ook5ue9R");

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
    
    private void ajouterMission(int idUser) {
        // Créer une boîte de dialogue pour saisir les informations de la mission
        JTextField descriptionField = new JTextField();
        JTextField dateField = new JTextField();
    
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(new JLabel("Description:"));
        myPanel.add(descriptionField);
        myPanel.add(new JLabel("Date:"));
        myPanel.add(dateField);
        
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez saisir les informations de la mission", JOptionPane.OK_CANCEL_OPTION);
        
        // Si l'utilisateur clique sur OK, ajouter la nouvelle mission
        if (result == JOptionPane.OK_OPTION) {
            String nouvelleDescription = descriptionField.getText();
            int nouvelleDate = Integer.parseInt(dateField.getText());
            String nouvelEtat ="Waiting";
            
            String nouveauCommentaire = "Pas de Commentaire";

            // Ajouter la nouvelle mission au modèle de tableau
            int nouvelId = tableModel.getRowCount() + 1; // Vous devrez peut-être ajuster la logique pour obtenir le nouvel ID
            tableModel.addRow(new Object[]{nouvelId, nouvelleDescription, nouvelleDate, nouvelEtat, nouveauCommentaire});

            // Insérer la nouvelle mission dans la base de données
            Connection connection = null;
            try {
                // Établir la connexion à la base de données
                connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037", "projet_gei_037", "ook5ue9R");

                // Exécuter la requête SQL pour insérer la nouvelle mission
                String sql = "INSERT INTO Mission (id_user, mission_description, date, States, Commentaire) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setInt(1, idUser);
                    preparedStatement.setString(2, nouvelleDescription);
                    preparedStatement.setInt(3, nouvelleDate);
                    preparedStatement.setString(4, nouvelEtat);
                    preparedStatement.setString(5, nouveauCommentaire);
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
        new PageMission(1);
    }
}
