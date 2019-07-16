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
    public static Label label1 = new Label();
    public static Label label2 = new Label();

    @Override
    public void start(Stage stage) throws Exception {
        initUI(stage);
    }

    public void initUI(Stage stage) throws Exception {
        Pane root = new Pane();

        label1.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        label1.setTextFill(Color.BLACK);
        label2.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        label2.setTextFill(Color.BLACK);

        label1.setLayoutX(25);
        label1.setLayoutY(25);
        label2.setLayoutX(325);
        label2.setLayoutY(25);

        new PlayerDB().getRanking();

        root.getChildren().addAll(label1, label2);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Scoreboard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}