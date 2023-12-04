package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        loadNewScene();
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }

    protected void setWelcomeText(String text){
        this.welcomeText.setText(text);
    }

    public void loadNewScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();

        GameController gameController = loader.getController();
        gameController.setWelcomeText("POLONEZA CZAS ZACZĄĆ");

        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setScene(new Scene(root));
        stage.show();
    }


}
