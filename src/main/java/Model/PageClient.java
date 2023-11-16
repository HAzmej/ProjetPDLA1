package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageClient {
    private JPanel contentpane;
    public PageClient () {


        // création de la fenêtre
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLayout(new GridBagLayout());


        // création du bouton
        JButton bouton = new JButton("Inscription");
        bouton.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton.setBackground(new Color(64, 128, 128));
        bouton.setFont(new Font("Arial", Font.PLAIN, 25));

        //bouton.setBounds(220, 400, 1000, 500);


        // ajout d'un écouteur d'événements au bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inscri inscriptionFrame = new inscri();
                inscriptionFrame.setVisible(true);
            }
        });

        // ajout du bouton à la fenêtre
        frame.getContentPane().add(bouton);

        // affichage de la fenêtre
        frame.setVisible(true);


        // création de la fenêtre

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(100, 100);

        // création du bouton
        JButton bouton1 = new JButton("Connexion");
        bouton1.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton1.setBackground(new Color(64, 128, 128));
        bouton1.setFont(new Font("Arial", Font.PLAIN, 25));
        //bouton1.setBounds(442, 400, 155, 36);

        // ajout d'un écouteur d'événements au bouton
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new cnx();
            }
        });

        // ajout du bouton à la fenêtre
        frame.getContentPane().add(bouton1);

        // affichage de la fenêtre
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new PageClient();
    }
}


