package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageClient {

    public PageClient () {

        // création de la fenêtre
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new GridBagLayout());

        JPanel panelprincip = new JPanel(new BorderLayout());

        //ajout Image
        ImageIcon image = new ImageIcon("/home/mejri/Bureau/ProjetPDLA/ProjetPDLA1/src/main/java/Model/IMG1.png");
        Image orgimage = image.getImage();
        Image sizeimg= orgimage.getScaledInstance(1000,-1,Image.SCALE_SMOOTH);
        ImageIcon SclIcon = new ImageIcon(sizeimg);

        //ajout JLabel
        JLabel lblimage = new JLabel();
        lblimage.setIcon(SclIcon);
        panelprincip.add(lblimage,BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));

        // création du bouton
        JButton bouton = new JButton("Inscription");
        bouton.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton.setBackground(new Color(183, 57, 86));
        bouton.setFont(new Font("Arial", Font.PLAIN, 25));

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


        // création du bouton
        JButton bouton1 = new JButton("Connexion");
        bouton1.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton1.setBackground(new Color(183, 57, 86, 213));
        bouton1.setFont(new Font("Arial", Font.PLAIN, 25));
        //bouton1.setBounds(442, 400, 155, 36);

        // ajout d'un écouteur d'événements au bouton
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new cnx();
            }
        });


        // affichage de la fenêtre
        frame.setVisible(true);
        pnlButton.add(bouton);
        pnlButton.add(bouton1);

        // ajout du bouton à la fenêtre
        frame.getContentPane().add(panelprincip);
        panelprincip.add(pnlButton,BorderLayout.NORTH);
    }
    public static void main(String[] args) {
        new PageClient();
    }
}


