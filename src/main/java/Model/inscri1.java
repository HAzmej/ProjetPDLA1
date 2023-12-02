package Model;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class inscri1 extends JFrame {
    private JPanel contentPane;
    private JTextField nom;
    private JTextField prenom;
    private JTextField mail;
    private JTextField mdp;

    public inscri1() {

        setTitle("Creation du compte utilisateur");
        setBounds(100, 100, 720, 421);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(217, 10, 1, 1);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(64, 128, 128));
        panel_1.setBounds(0, 0, 334, 384);
        contentPane.add(panel_1);

        Button button = new Button("SignUp");
        button.setFont(new Font("Arial Black", Font.PLAIN, 12));
        button.setForeground(new Color(255, 255, 255));
        button.setBackground(new Color(206, 32, 55));
        button.setBounds(442, 300, 155, 36);
        contentPane.add(button);


        nom = new JTextField();
        nom.setBounds(392, 90, 267, 28);
        contentPane.add(nom);
        nom.setColumns(10);

        JSeparator separator_n = new JSeparator();
        separator_n.setBounds(395, 167, 264, 1);
        contentPane.add(separator_n);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(392, 73, 78, 13);
        contentPane.add(lblname);

        prenom = new JTextField();
        prenom.setColumns(10);
        prenom.setBounds(392, 141, 267, 28);
        contentPane.add(prenom);

        JSeparator separator_s = new JSeparator();
        separator_s.setBounds(395, 216, 264, 1);
        contentPane.add(separator_s);

        JLabel lblsurname = new JLabel("Surname");
        lblsurname.setBounds(392, 125, 78, 13);
        contentPane.add(lblsurname);

        mail = new JTextField();
        mail.setColumns(10);
        mail.setBounds(392, 190, 267, 28);
        contentPane.add(mail);

        JSeparator separator_email = new JSeparator();
        separator_email.setBounds(395, 216, 264, 1);
        contentPane.add(separator_email);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(392, 177, 78, 13);
        contentPane.add(lblEmail);

        mdp = new JTextField();
        mdp.setColumns(10);
        mdp.setBounds(392, 244, 267, 28);
        contentPane.add(mdp);

        JSeparator separator_mdp = new JSeparator();
        separator_mdp.setBounds(395, 270, 264, 1);
        contentPane.add(separator_mdp);


        JLabel lblmdp = new JLabel("Mot de passe");
        lblmdp.setBounds(392, 231, 100, 13);
        contentPane.add(lblmdp);

        // Ajout d'un écouteur d'événements au bouton SignUp
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = nom.getText();
                String Surname= prenom.getText();
                String Mail = mail.getText();
                String password = mdp.getText();

                if (inscrireUtilisateur1(Name, Surname,Mail,password)) {
                    // L'inscription a réussi
                    JOptionPane.showMessageDialog(null, "Inscription réussie. Vous pouvez maintenant vous connecter.");
                    cnx1 cnxFrame = new cnx1();
                    cnxFrame.setVisible(true);
                } else {
                    // L'inscription a échoué
                    JOptionPane.showMessageDialog(null, "L'inscription a échoué. Veuillez réessayer.");
                }
            }
        });
        add(lblname);
        add(nom);
        add(lblsurname);
        add(prenom);
        add(lblEmail);
        add(mail);
        add(lblmdp);
        add(mdp);
        add(new JLabel(""));
        add(button);
        setVisible(true);
    }

    private boolean inscrireUtilisateur1(String Name, String Surname, String Mail, String motdepasse) {
        // Configure la connexion de la BBDD
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037";
        String usuarioBD = "projet_gei_037";
        String contrasenaBD = "ook5ue9R";

        try (Connection connection = DriverManager.getConnection(url, usuarioBD, contrasenaBD)) {
            // Requête SQL pour insérer un nouvel utilisateur dans la table "usuarios"
            String sql = "INSERT INTO User (nom,prenom,mail, mot_de_passe) Values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, Surname);
            preparedStatement.setString(3, Mail);
            preparedStatement.setString(4, motdepasse);

            // Exécute la requête d'insertion
            int rowsAffected = preparedStatement.executeUpdate();

            // Si au moins une ligne a été affectée, l'inscription est réussie
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inscri1 frame = new inscri1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


