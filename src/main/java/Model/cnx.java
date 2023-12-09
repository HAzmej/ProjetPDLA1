package Model;


import javax.swing.*;

import Network.Network;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cnx extends JFrame {
    private JTextField Champuser;
    private JPasswordField userpasswrd;
    private JLabel msgErreur;
    private int i;

    public cnx(int n) {
        //Config Fenetre
        setTitle("Connexion ");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        
        //Etiquettes
        JLabel labelUser = new JLabel("Mail ");
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
                String mail = Champuser.getText();
                char[] password = userpasswrd.getPassword();
                
                if (n==1){
                i=validerUtilisateur(mail, new String(password));
                if (i!=-1) {
                    //Connexion reussie
                    JOptionPane.showMessageDialog(null, "Authentification réussie. Redirection vers la page...");
                    try {
                        new PageMission(i);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                } else {
                    // Afficher un message d'erreur
                    msgErreur.setText("Authentification échouée. Vérifiez vos données.");
                }
            } else if (n==2) {if (validerBenevole(mail, new String(password))) {
                //Connexion reussie
                JOptionPane.showMessageDialog(null, "Authentification réussie. Redirection vers la page...");
                new PageMissionBenev();
                

            } else {
                // Afficher un message d'erreur
                msgErreur.setText("Authentification échouée. Vérifiez vos données.");
            }
            } else if (n==0) {if (validerValideur(mail, new String(password))) {
                //Connexion reussie
                JOptionPane.showMessageDialog(null, "Authentification réussie. Redirection vers la page...");
                new PageMissionValid();
                

            } else {
                // Afficher un message d'erreur
                msgErreur.setText("Authentification échouée. Vérifiez vos données.");
            }
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
        
        Connection connection = null;
        try  {
            connection =Network.Connect();
            //Requête SQL pour vérifier les informations d'identification
            String sql = "SELECT id FROM User WHERE mail = ? AND mot_de_passe = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
        
            // S´il y a un résultat, les informations d'identification sont valides.
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    private boolean validerBenevole(String Name, String password) {
     
        Connection connection =null;
        try  {
            connection =Network.Connect();
            //Requête SQL pour vérifier les informations d'identification
            String sql = "SELECT * FROM Benevole WHERE mail = ? AND mot_de_passe = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // S´il y a un résultat, les informations d'identification sont valides.
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean validerValideur(String Name, String password) {
       
        Connection connection=null;
        try  {
            connection =Network.Connect();
            //Requête SQL pour vérifier les informations d'identification
            String sql = "SELECT * FROM Valideur WHERE mail = ? AND mot_de_passe = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // S´il y a un résultat, les informations d'identification sont valides.
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
    new cnx(1);

    }
}
