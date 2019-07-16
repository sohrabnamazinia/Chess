package database;

public class Player
{
    String name;
    String pass;
    int score;

    public Player(String name, String pass, int score)
    {
        this.name = name;
        this.pass = pass;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setScore(int score) {
        this.score = score;
    }
}