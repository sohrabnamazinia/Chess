package entry;

import database.PlayerDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Scoreboard extends Application
{
    // Creating static labels to use on database.
    public static Label label1 = new Label();
    public static Label label2 = new Label();

    @Override
    public void start(Stage stage) throws Exception {initUI(stage);}

    public void initUI(Stage stage) throws Exception
    {
        // Creating a pane.
        Pane root = new Pane();

        // Setting labels' font and color.
        label1.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        label1.setTextFill(Color.BLACK);
        label2.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        label2.setTextFill(Color.BLACK);

        // Setting Labels' positions.
        label1.setLayoutX(25);
        label1.setLayoutY(25);
        label2.setLayoutX(325);
        label2.setLayoutY(25);

        // updating ranks.
        new PlayerDB().getRanking();

        // Adding labels to pane.
        root.getChildren().addAll(label1, label2);

        // Creating a scene.
        Scene scene = new Scene(root, 400, 600);

        // Setting stage title and scene.
        stage.setTitle("Scoreboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){launch(args);}
}