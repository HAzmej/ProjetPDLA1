package Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    public Accueil () {

        // création de la fenêtre
        JFrame frame = new JFrame("Acceuil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridBagLayout());
        JPanel panelprincip = new JPanel(new BorderLayout());

        //ajout Image
        ImageIcon image = new ImageIcon("/home/mejri/Bureau/ProjetPDLA/ProjetPDLA1/src/main/java/Model/IMG.png");
        Image orgimage = image.getImage();
        Image sizeimg= orgimage.getScaledInstance(1000,-1,Image.SCALE_SMOOTH);
        ImageIcon SclIcon = new ImageIcon(sizeimg);

        //ajout JLabel
        JLabel lblimage = new JLabel();
        lblimage.setIcon(SclIcon);
        panelprincip.add(lblimage,BorderLayout.CENTER);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));

        // création du bouton
        JButton bouton = new JButton("Benevolat");
        bouton.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton.setBackground(new Color(23, 182, 134));
        bouton.setFont(new Font("Arial", Font.PLAIN, 25));

        //bouton.setBounds(220, 400, 1000, 500);


        // ajout d'un écouteur d'événements au bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new PageBenev();
            }
        });

        // ajout du bouton à la fenêtre
        //frame.getContentPane().add(bouton);

        // affichage de la fenêtre
        frame.setVisible(true);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(100, 100);

        // création du bouton
        JButton bouton1 = new JButton("Client");
        bouton1.setPreferredSize(new Dimension(200, 50)); // Définir la taille préférée du bouton
        bouton1.setBackground(new Color(57, 179, 183));
        bouton1.setFont(new Font("Arial", Font.PLAIN, 25));
        //bouton1.setBounds(442, 400, 155, 36);

        // ajout d'un écouteur d'événements au bouton
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new PageClient();
            }
        });

        // ajout du bouton à la fenêtre
        //frame.getContentPane().add(bouton1);

        // affichage de la fenêtre
        frame.setVisible(true);
        pnlButton.add(bouton);
        pnlButton.add(bouton1);
        //frame.getContentPane().add(pnlButton);
        frame.getContentPane().add(panelprincip);
        panelprincip.add(pnlButton,BorderLayout.NORTH);
    }
    public static void main(String[] args) {
        new Accueil();
    }
}
