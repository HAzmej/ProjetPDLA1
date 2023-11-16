package Model;

import users.Client;

import javax.swing.*;

public class Accueil {
    private JPanel contentPane;

    public class Benevo {

        public static void main(String[] args) {
            // Création de la fenêtre
            JFrame frame = new JFrame("Benevolat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Création du bouton
            JButton bouton = new JButton("Benevolat");

            // Ajout d'un écouteur d'événements au bouton
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appel de la fonction lorsque le bouton est cliqué
                    new PageBenev();
                }
            });

            // Ajout du bouton à la fenêtre
            frame.getContentPane().add(bouton);

            // Affichage de la fenêtre
            frame.setVisible(true);
        }
    }
    public class Client1 {

        public static void main(String[] args) {
            // Création de la fenêtre
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            // Création du bouton
            JButton bouton = new JButton("Client");

            // Ajout d'un écouteur d'événements au bouton
            bouton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appel de la fonction lorsque le bouton est cliqué
                    new PageClient();
                }
            });

            // Ajout du bouton à la fenêtre
            frame.getContentPane().add(bouton);

            // Affichage de la fenêtre
            frame.setVisible(true);
        }
    }

}
