package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageBenev extends JFrame{

    public PageBenev () {
        // création de la fenêtre
        setTitle("Page Benevole");
        setSize(400, 175);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JPanel panelprincip = new JPanel(new BorderLayout());  

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));
        
        // création du bouton
        JButton bouton = new JButton("Inscription");

        // ajout d'un écouteur d'événements au bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new inscri(2);
               
            }
        });
        // affichage de la fenêtre
        setVisible(true);

        // création du bouton
        JButton bouton1 = new JButton("Connexion");

        // ajout d'un écouteur d'événements au bouton
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new cnx(2);
            }
        });

        // affichage de la fenêtre
        setVisible(true);
        add(bouton).setBounds(0, 0, 400, 70);
        add(bouton1).setBounds(0,70,400,70);
        setLocationRelativeTo(null);

        
        // ajout du bouton à la fenêtre
        getContentPane().add(panelprincip);
        panelprincip.add(pnlButton,BorderLayout.CENTER);

    }
    public static void main(String[] args) {
        new PageBenev();
    }
}
