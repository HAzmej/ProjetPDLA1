package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageMission {

    public PageMission(){

        // création de la fenêtre
        JFrame frame= new JFrame("PageMission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new GridBagLayout());




    }

    public static void main(String[] args) {
        new PageMission();
    }
}
