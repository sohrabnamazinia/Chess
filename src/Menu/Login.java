package Menu;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application
{
    private ImageView imageView;
    private Image LImg;

    @Override
    public void start(Stage stage)
    {
        initUI(stage);
    }

    private void initUI(Stage stage)
    {
        // Creating a pane.
        Pane root = new Pane();

        // Setting text fields, label and buttons.
        TextField txtFld1 = new TextField();
        TextField txtFld2 = new TextField();
        Label lbl1 = new Label("Enter your username:");
        Label lbl2 = new Label("Enter your password:");
        Button btnOk = new Button("Ok");
        Button btnBack = new Button("_Back");
        Button btnQuit = new Button("_Quit");

        // Setting labels font and color.
        lbl1.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        lbl1.setTextFill(Color.WHITE);
        lbl2.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        lbl2.setTextFill(Color.WHITE);

        btnOk.setOnAction((ActionEvent event) -> {
            if (!(txtFld1.getText().equals("")) && !(txtFld2.getText().equals("")))
            {
                //if (this user exists)
                try {
                    Main.main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else
            {
                Text text = new Text();
                text.setFont(Font.font("Serif", FontWeight.BOLD, 20));
                text.setFill(Color.RED);
                text.setText("Username/Password is not entered.");
                text.setX(50);
                text.setY(250);

                root.getChildren().addAll(text);
            }
        });

        btnBack.setOnAction((ActionEvent event) -> {
            Menu menu = new Menu();
            menu.start(stage);
        });
        btnQuit.setOnAction((ActionEvent event) -> {Platform.exit();});

        // setting positions.
        txtFld1.setLayoutX(250);
        txtFld1.setLayoutY(150);
        txtFld2.setLayoutX(250);
        txtFld2.setLayoutY(190);
        lbl1.setLayoutX(50);
        lbl1.setLayoutY(152);
        lbl2.setLayoutX(50);
        lbl2.setLayoutY(192);
        btnOk.setLayoutX(500);
        btnOk.setLayoutY(190);
        btnBack.setLayoutX(650);
        btnBack.setLayoutY(540);
        btnQuit.setLayoutX(710);
        btnQuit.setLayoutY(540);

        // Setting an image.
        loadImage();
        imageView = new ImageView(LImg);
        imageView.setFitHeight(620);
        imageView.setFitWidth(820);

        // Adding image, text field and buttons.
        root.getChildren().addAll(imageView, txtFld1, lbl1, txtFld2, lbl2, btnOk, btnBack, btnQuit);

        // Creating a scene for stage.
        Scene scene = new Scene(root, 800, 600);

        // Setting title and scene of the stage and disabling changing its size.
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void loadImage()
    {
        LImg = new Image("/resources/images/Login.png");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
