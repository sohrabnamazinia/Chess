import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Menu extends Application
{
    private ImageView imageView;
    private Image MenuImg;

    @Override
    public void start(Stage stage)
    {
        initUI(stage);
    }

    private void initUI(Stage stage)
    {
        // Creating a Pane.
        Pane root = new Pane();

        // Creating buttons.
        Button btnCA = new Button("_Create account");
        Button btnL = new Button("_Login");
        Button btnQuit = new Button("_Quit");

        btnL.setPrefWidth(125);

        // Creating Tooltips for buttons and assigning them to the considered buttons.
        Tooltip btnCATip = new Tooltip("Create account and join the game.");
        Tooltip btnLTip = new Tooltip("Login and join the game.");
        Tooltip.install(btnCA, btnCATip);
        Tooltip.install(btnL, btnLTip);

        // Setting actions of buttons.
        btnCA.setOnAction((ActionEvent event) -> {
            CreateAccount createAccount = new CreateAccount();
            createAccount.start(stage);
        });
        btnL.setOnAction((ActionEvent event) -> {
            Login login = new Login();
            login.start(stage);
        });
        btnQuit.setOnAction((ActionEvent event) -> {Platform.exit();});

        // setting buttons positions.
        btnCA.setLayoutX(40);
        btnCA.setLayoutY(40);
        btnL.setLayoutX(40);
        btnL.setLayoutY(73);
        btnQuit.setLayoutX(700);
        btnQuit.setLayoutY(540);

        // Setting an image.
        loadImage();
        imageView = new ImageView(MenuImg);
        imageView.setFitHeight(620);
        imageView.setFitWidth(820);

        // Adding image and buttons.
        root.getChildren().addAll(imageView, btnCA, btnL, btnQuit);

        // Creating a scene for the stage.
        Scene scene = new Scene(root, 800, 600);

        // Setting title and scene of the stage and disabling changing its size.
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void loadImage()
    {
        MenuImg = new Image("/resources/images/Menu.jpg");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}