package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    public Accueil () {
        JPanel panelprincip = new JPanel(new BorderLayout());
     
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));
       
        setTitle("Connexion Utilisateur");
        setSize(250, 135);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));


        JButton btnsoumettre = new JButton("Benevole");
        // ajout d'un écouteur d'événements au bouton
        btnsoumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new PageBenev();
            }
        });

        // affichage de la fenêtre
        setVisible(true);


   
        JButton btnsoumettre1 = new JButton("Client");
        // ajout d'un écouteur d'événements au bouton
        btnsoumettre1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new PageClient();
            }
        });

        // affichage de la fenêtre
        setVisible(true);
        getContentPane().add(panelprincip);
        panelprincip.add(pnlButton,BorderLayout.NORTH);
        add(btnsoumettre).setBounds(0, 0, 250, 50);
        add(btnsoumettre1).setBounds(0,50,250,50);

        // ajout du bouton à la fenêtre

    }
    public static void main(String[] args) {
        new Accueil();
    }
}
