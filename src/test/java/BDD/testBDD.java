package BDD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import Model.*;


public class testBDD {
     @Test
    public void testinscrireUtilisateur() {
        inscri connection = new inscri(1);

        // Test d'inscription réussie
        boolean result = connection.inscrireUtilisateur("test1","test2","test@mail.com", "password123");
        assertTrue(!result);

        // Test d'inscription échouée
        result = connection.inscrireUtilisateur("test1","test2","test@mail.com", "wrongpassword");
        assertFalse(result);
    }
     @Test
    public void testinscrireBenev() {
        inscri connection = new inscri(2);

        // Test d'authentification réussie
        boolean result = connection.inscrireBenevole("test1benev","test2benev","benevole@mail.com", "password456");
        assertTrue(!result);

        // Test d'authentification échouée
        result = connection.inscrireBenevole("test1benev","test2benev","benevole@mail.com", "wrongpassword");
        assertFalse(result);
    }
      @Test
    public void testinscrireValid() {
        inscri connection = new inscri(0);

        // Test d'authentification réussie
        boolean result = connection.inscrireValideur("test1V","test2V","valideur@mail.com", "password789");
        assertTrue(!result);

        // Test d'authentification échouée
        result = connection.inscrireValideur("test1V","test2V","valideur@mail.com", "wrongpassword");
        assertFalse(result);
    }
    
    
     @Test
    public void testValiderUtilisateur() {
        cnx connection = new cnx(1);

        // Test d'authentification réussie
        int result = connection.validerUtilisateur("test@mail.com", "password123");
        assertTrue(result>=1);

        // Test d'authentification échouée
        result = connection.validerUtilisateur("test@mail.com", "wrongpassword");
        assertEquals(-1, result);
    }

    @Test
    public void testValiderBenevole() {
        cnx connection = new cnx(2);

        // Test d'authentification réussie
        boolean result = connection.validerBenevole("benevole@mail.com", "password456");
        assertTrue(result);

        // Test d'authentification échouée
        result = connection.validerBenevole("benevole@mail.com", "wrongpassword");
        assertFalse(result);
    }

    @Test
    public void testValiderValideur() {
        cnx connection = new cnx(0);

        // Test d'authentification réussie
        boolean result = connection.validerValideur("valideur@mail.com", "password789");
        assertTrue(result);

        // Test d'authentification échouée
        result = connection.validerValideur("valideur@mail.com", "wrongpassword");
        assertFalse(result);
    }



    /*@Test
    public void testAjouterMission() throws SQLException {
        // Créez une instance de PageMission avec un ID utilisateur fictif
        PageMission pageMission = new PageMission(1);

        // Test d'ajout de mission
        pageMission.ajouterMission(1);

        // Assurez-vous que la mission a été ajoutée à la table
        assertTrue(pageMission.ajouterMission(1));
    }*/
}
