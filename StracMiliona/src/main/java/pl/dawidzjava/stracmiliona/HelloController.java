package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        if (loadNewScene()) {
            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
        }
        welcomeText.setText("NIe udało się załadować gry :-(");
    }

    protected void setWelcomeText(String text){
        this.welcomeText.setText(text);
    }

    public boolean loadNewScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();

        DeserializeQuestion deserializeQuestion=new DeserializeQuestion("src/main/resources/questions/");
        List<Question> questions=deserializeQuestion.getQuestions();
        if (questions!=null){
            for (Question question : questions) {
                System.out.println(question);
            }
        }
        else {
            System.out.println("Folder jest pusty");
            return false;
        }

        Game game=new Game(questions, 4, "Tadek");
        if (4>questions.size())return false;

        GameController gameController = loader.getController();
        gameController.setGameProperties(game);

        Stage stage = new Stage();
        stage.setTitle("Strać Miliona !");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        return true;
    }


}
