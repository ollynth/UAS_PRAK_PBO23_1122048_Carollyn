package src.Controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.Model.Games;
import src.Model.Transaction;
import src.Model.User;

public class Controller {
    static DatabaseController conn = new DatabaseController();

    // 1. Cari User
    public int getUser (String email, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
        int exists = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                exists = rs.getInt("id_user");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return exists;
    }

    // 2. Tampilkan list game
    public List<Games> getUserGame() {
        conn.connect();
        List<Games> games = new ArrayList<>();
        String query = "SELECT * FROM games";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id_games");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                int price = rs.getInt("price");
                Games game = new Games(id, name, genre, price);
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    // 3. add data game yang dibeli
    public boolean buyGame(int userID, int gameID) {
        conn.connect();
        String query = "INSERT INTO transactions (userID, gameID) VALUES (?, ?)";
        PreparedStatement stmt;
    
        try {
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, userID);
            stmt.setInt(2, gameID);
            int rowsAffected = stmt.executeUpdate();
    
            if (rowsAffected > 0) {
                return true;
            } else {
                System.out.println("Failed to insert into Transactions table.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
    

    public List<Transaction> getUserTransactions(int inputUserID) {
        conn.connect();
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT t.id, t.userID, u.name as username, t.gameID, g.name as Game_Name, g.price " +
                           "FROM Transactions t " +
                           "JOIN users u ON t.userId = u.id_user " +
                           "JOIN games g ON t.gameID = g.id_games " +
                           "WHERE t.user_id = ?";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idUser = rs.getInt("id_user");
                int idGame = rs.getInt("id_games");
                int price = rs.getInt("price");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;                   
    }
}