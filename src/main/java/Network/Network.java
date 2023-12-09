package Network;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Network {
    public Network(){};

    static public Connection Connect () throws SQLException{
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_037";
        String usuarioBD = "projet_gei_037";
        String contrasenaBD = "ook5ue9R";

        // Établir la connexion à la base de données
        return DriverManager.getConnection(url, usuarioBD, contrasenaBD);
    }

    static public void tableauShowMissionBenev(DefaultTableModel tableModel,Connection connection) throws SQLException{
        String sql = "SELECT id, mission_description, date, States, Commentaire FROM Mission WHERE States IN (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "IN PROGRESS" );
                preparedStatement.setString(2, "ACCEPTED" );
                preparedStatement.setString(3, "FINISHED" );
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    
                    // Parcourir les résultats et ajouter les lignes au modèle de tableau
                    while (resultSet.next()) {
                        int id = tableModel.getRowCount() + 1;
                        String desc = resultSet.getString("mission_description");
                        Date date =  resultSet.getDate("date");
                        String state = resultSet.getString("States");
                        String c = resultSet.getString("Commentaire");
                        tableModel.addRow(new Object[]{id, desc, date, state, c});

                    }
                }
            }

    }
    static public void tableauShowMissionID(DefaultTableModel tableModel,Connection connection, int idUtilisateur) throws SQLException{
        String sql = "SELECT id, mission_description, States, Commentaire,date FROM Mission WHERE id_user = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idUtilisateur);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Parcourir les résultats et ajouter les lignes au modèle de tableau
                while (resultSet.next()) {
                    int id = tableModel.getRowCount() + 1;
                    String desc = resultSet.getString("mission_description");
                    Date date =  resultSet.getDate("date");
                    String state = resultSet.getString("States");
                    String c = resultSet.getString("Commentaire");
                    tableModel.addRow(new Object[]{id, desc, date, state, c});
            }
        }
    
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    // Fermer la connexion à la base de données
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}

    
}
}
