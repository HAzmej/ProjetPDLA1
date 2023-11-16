package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cnx extends JFrame {
    private JTextField Champuser;
    private JPasswordField userpasswrd;
    private JTextField ChampID;
    private JLabel msgErreur;

    public cnx() {
        //Config Fenetre
        setTitle("Connexion Utilisateur");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        //Etiquettes
        JLabel labelUser = new JLabel("Nom d'utilisateur ");
        JLabel labelPassword = new JLabel("Mot de passe");
        JLabel labelID = new JLabel("identifiant");


        Champuser = new JTextField();
        userpasswrd = new JPasswordField();
        ChampID = new JTextField();

        //Bouton
        JButton btnsoumettre = new JButton("soumettre");

        // Message d'erreur
        msgErreur = new JLabel("");
        msgErreur.setForeground(Color.RED);

        // Écouteur d'événements pour le bouton Soumettre
        btnsoumettre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = Champuser.getText();
                char[] password = userpasswrd.getPassword();
                String id = ChampID.getText();

                if (validerUtilisateur(user, new String(password), id)) {
                    //
                    JOptionPane.showMessageDialog(null, "Authentification réussie. Redirection vers la page...");
                } else {
                    // Afficher un message d'erreur
                    msgErreur.setText("Authentification échouée. Vérifiez vos données.");
                }
            }
        });
        // Ajout des composants à la fenêtre
        add(labelUser);
        add(Champuser);
        add(labelPassword);
        add(userpasswrd);
        add(labelID);
        add(ChampID);
        add(btnsoumettre);
        add(new JLabel("")); // Espace vide
        add(msgErreur);

        // Affichage de la fenêtre
        setVisible(true);
    }

    private boolean validerUtilisateur(String user, String password, String id) {
        // Configure la connexion de la BBDD
        String url = "jdbc:mysql://localhost:3306/projet_gei_37";
        String usuarioBD = "projet_gei_37";
        String contrasenaBD = "ook5ue9R";

        try (Connection connection = DriverManager.getConnection(url, usuarioBD, contrasenaBD)) {
            //Requête SQL pour vérifier les informations d'identification
            String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ? AND identificador = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            // S´il y a un résultat, les informations d'identification sont valides.
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
