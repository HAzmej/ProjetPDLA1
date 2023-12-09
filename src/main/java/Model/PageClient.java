package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageClient extends JFrame{

    public PageClient () {
        // création de la fenêtre
        setTitle("Page Utilisateur");
        setSize(400, 175);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JPanel panelprincip = new JPanel(new BorderLayout());
        //Bouton
        JButton btnsoumettre = new JButton("Inscription");
        // ajout d'un écouteur d'événements au bouton
        btnsoumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new inscri(1);
                
            }
        });

        // ajout du bouton à la fenêtre
        getContentPane().add(btnsoumettre);

        // affichage de la fenêtre
        setVisible(true);

        // création du bouton
        JButton btnsoumettre1 = new JButton("Connexion");
    
        // ajout d'un écouteur d'événements au bouton
        btnsoumettre1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new cnx(1);
                
            }
        });

        // affichage de la fenêtre
        setVisible(true);
        
        add(btnsoumettre).setBounds(0, 0, 400, 70);
        add(btnsoumettre1).setBounds(0,70,400,70);
        setLocationRelativeTo(null);
        // ajout du bouton à la fenêtre
        getContentPane().add(panelprincip);
        
    }
    public static void main(String[] args) {
        new PageClient();
    }
}


