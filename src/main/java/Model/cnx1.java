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

public class cnx1 extends JFrame {
    private JTextField Champuser;
    private JPasswordField userpasswrd;
    private JLabel msgErreur;
    private int i;

    public cnx1() {
        //Config Fenetre
        setTitle("Connexion Utilisateur");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        //Etiquettes
        JLabel labelUser = new JLabel("Nom d'utilisateur ");
        JLabel labelPassword = new JLabel("Mot de passe");


        Champuser = new JTextField();
        userpasswrd = new JPasswordField();

        //Bouton
        JButton btnsoumettre = new JButton("soumettre");

        // Message d'erreur
        msgErreur = new JLabel("");
        msgErreur.setForeground(Color.RED);

        // Écouteur d'événements pour le bouton Soumettre
        btnsoumettre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Name = Champuser.getText();
                char[] password = userpasswrd.getPassword();

                i=validerUtilisateur(Name, new String(password));
                if (i!=-1) {
                    //Connexion reussie
                    JOptionPane.showMessageDialog(null, "Authentification réussie. Redirection vers la page...");
                    new PageMission(i);//else
                    //{new PageMissionBenev(); }

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
        add(btnsoumettre);
        add(new JLabel("")); // Espace vide
        add(msgErreur);

        // Affichage de la fenêtre
        setVisible(true);
    }

    private int validerUtilisateur(String Name, String password) {
        // Configure la connexion de la BBDD
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037";
        String usuarioBD = "projet_gei_037";
        String contrasenaBD = "ook5ue9R";

        try (Connection connection = DriverManager.getConnection(url, usuarioBD, contrasenaBD)) {
            //Requête SQL pour vérifier les informations d'identification
            String sql = "SELECT id FROM User WHERE nom = ? AND mot_de_passe = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
        
            // S´il y a un résultat, les informations d'identification sont valides.
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                // Aucun utilisateur trouvé avec ces informations d'identification
                return -1; // ou une autre valeur qui indique une absence d'ID valide
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
    new cnx1();

    }
}
