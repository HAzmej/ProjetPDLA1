package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageClient extends JFrame{

    public PageClient () {
        // création de la fenêtre
        setTitle("Page Utilisateur");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JPanel panelprincip = new JPanel(new BorderLayout());
        //Bouton
        JButton btnsoumettre = new JButton("Inscription");
        // ajout d'un écouteur d'événements au bouton
        btnsoumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                inscri1 inscriptionFrame = new inscri1();
                inscriptionFrame.setVisible(true);
            }
        });

        // ajout du bouton à la fenêtre
        getContentPane().add(btnsoumettre);

        // affichage de la fenêtre
        setVisible(true);


        // création du bouton
        
        JButton btnsoumettre1 = new JButton("Connexion");
        /*btnsoumettre1.setBackground(new Color(183, 57, 86, 213));
        btnsoumettre1.setFont(new Font("Arial", Font.PLAIN, 25));*/

        // ajout d'un écouteur d'événements au bouton
        btnsoumettre1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new cnx1();
            }
        });

        // affichage de la fenêtre
        setVisible(true);
        
        add(btnsoumettre).setBounds(0, 0, 250, 50);
        add(btnsoumettre1).setBounds(0,50,250,50);
        // ajout du bouton à la fenêtre
        getContentPane().add(panelprincip);
        
    }
    public static void main(String[] args) {
        new PageClient();
    }
}


