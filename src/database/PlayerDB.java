package database;

import entry.Scoreboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDB {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PlayerDB() throws Exception
    {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=proj", "postgres", "god.sn7.cr7");
    }

    public void getRanking() throws Exception{
        preparedStatement = connection.prepareStatement("select username, score from proj.player order by score DESC;");
        ResultSet resultSet = preparedStatement.executeQuery();
        String sb = "";
        int i = 1;
        while (resultSet.next())
        {
            sb += i + " ) " + resultSet.getString("username") + " : " + resultSet.getString("score") + "\n";
            i++;
        }
        Scoreboard.label.setText(sb);
    }

    public void getPlayer() throws Exception{
        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            System.out.println(resultSet.getString("username"));
        }
    }

    public void addPlayer(Player player) throws Exception
    {
        preparedStatement = connection.prepareStatement("insert into player values (?,?,?)");
        preparedStatement.setString(1, player.getName());
        preparedStatement.setString(2, player.getPass());
        preparedStatement.setInt(3, player.getScore());
        preparedStatement.executeUpdate();
    }



    public String getPlayer(String username) throws Exception{
        preparedStatement = connection.prepareStatement("select * from player where username = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("username");
    }

    public String getPlayer(String username, String pass) throws Exception{
        preparedStatement = connection.prepareStatement("select * from player where username = ? and pass = ?");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("username");
    }

    public void changePass(Player player, String newPass) throws Exception{
        preparedStatement = connection.prepareStatement("update player set pass = ? where username = ?");
        preparedStatement.setString(1, newPass);
        preparedStatement.setString(2,player.getName());
        preparedStatement.executeUpdate();
    }

    public void deletePlayer(String username) throws Exception{
        preparedStatement = connection.prepareStatement("delete from player where username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }

    public void close() throws Exception
    {
        preparedStatement.close();
        connection.close();
    }
}