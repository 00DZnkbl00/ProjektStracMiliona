package pl.dawidzjava.stracmiliona;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class SceneLoader {

    public static void loadHelloScene(Object object) throws IOException {
        FXMLLoader loader = new FXMLLoader(object.getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();


        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void loadAddQuestionScene(Object object) throws IOException {
        FXMLLoader loader = new FXMLLoader(object.getClass().getResource("add-question.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void loadGameScene(Object object, GameEngine gameEngine) throws IOException {
        FXMLLoader loader = new FXMLLoader(object.getClass().getResource("game-view.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        gameController.setGameProperties(gameEngine);

        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
