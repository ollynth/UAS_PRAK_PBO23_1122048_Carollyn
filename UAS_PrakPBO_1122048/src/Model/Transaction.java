package src.Model;

public class Transaction {
    private int id;
    private int userID;
    private int gameID;
    
    public Transaction(int id, int userID, int gameID) {
        this.id = id;
        this.userID = userID;
        this.gameID = gameID;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

}