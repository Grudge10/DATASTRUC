package midterms.OtherActivity.models;

public class Player {
    private String username;
    private String password;
    private int highestScore;

    public Player() {
    }

    public Player(String username, String password, int highestScore) {
        this.username = username;
        this.password = password;
        this.highestScore = highestScore;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }
}
