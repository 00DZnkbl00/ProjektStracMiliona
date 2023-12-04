package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    private Label testLabel;

    @FXML
    protected void onTestButtonAction(ActionEvent event) throws IOException {
        loadNewScene();
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }


    public void loadNewScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        HelloController helloController = loader.getController();
        helloController.setWelcomeText("UDAŁO SIĘ");

        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setScene(new Scene(root));
        stage.show();
    }

    protected void setWelcomeText(String text){
        this.testLabel.setText(text);
    }
}
