package Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class inscri extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public inscri() {

        setTitle("Creation du compte utilisateur");
        setBounds(100, 100, 720, 421);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        button.setBounds(442, 400, 155, 36);
        contentPane.add(button);

        // Ajout d'un écouteur d'événements au bouton SignUp
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Insérer le code pour l'inscription ici
                // Par exemple, ouvrir une nouvelle fenêtre ou effectuer une action d'inscription

                JOptionPane.showMessageDialog(null, "Inscription réussie!"); // Exemple de message
            }
        });

        textField = new JTextField();
        textField.setBounds(392, 100, 267, 28);
        contentPane.add(textField);
        textField.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(395, 167, 264, 1);
        contentPane.add(separator);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(392, 77, 78, 13);
        contentPane.add(lblNewLabel);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(392, 141, 267, 28);
        contentPane.add(textField_1);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(395, 216, 264, 1);
        contentPane.add(separator_1);

        JLabel lblNewLabel1 = new JLabel("Surname");
        lblNewLabel.setBounds(392, 50, 78, 13);
        contentPane.add(lblNewLabel1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(392, 190, 267, 28);
        contentPane.add(textField_2);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(395, 216, 264, 1);
        contentPane.add(separator_2);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(392, 177, 78, 13);
        contentPane.add(lblEmail);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(392, 244, 267, 28);
        contentPane.add(textField_2);

        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setBounds(395, 270, 264, 1);
        contentPane.add(separator_1_1);

        JLabel lblMotDePasse = new JLabel("Mot de passe");
        lblMotDePasse.setBounds(392, 231, 78, 13);
        contentPane.add(lblMotDePasse);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(392, 244, 267, 28);
        contentPane.add(textField_2);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(395, 270, 264, 1);
        contentPane.add(separator_1_2);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    inscri frame = new inscri();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


