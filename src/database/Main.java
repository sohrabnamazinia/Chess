package database;

public class Main {
    public static void main(String[] args) throws Exception{
            String userName = "Ali";
            String pass = "1234";
            Player player = new Player(userName, pass);
            PlayerDB playerDB  = new PlayerDB();
            playerDB.addPlayer(player);


//        Player player = new database.Player("farbod","farbod12345", 22);
//        PlayerDB playerDB = new database.PlayerDB();
//        playerDB.addPlayer(player);
        // playerDB.deletePlayer("farbod_sh");
        // database.Player player = new database.Player("Sohrab Namazi nia", "12345678", 20);
        //  database.PlayerDB playerDB = new database.PlayerDB();
        //  playerDB.addPlayer(player);


//        personDB.changePass(person,"123456789");
//        System.out.println(personDB.getPerson("kiarash23"));
//        personDB.getPersons();
        //  playerDB.deletePlayer("kiarash23");
    }
}

