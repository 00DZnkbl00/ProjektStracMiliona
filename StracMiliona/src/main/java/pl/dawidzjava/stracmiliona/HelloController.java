package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    private final boolean debugMode =true;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        if (loadNewScene()) {
            ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
        }
        welcomeText.setText("NIe udało się załadować gry :-(");
    }

    protected void setWelcomeText(String text) {
        this.welcomeText.setText(text);
    }

    public boolean loadNewScene() throws IOException {


        DeserializeQuestion deserializeQuestion = new DeserializeQuestion("src/main/resources/questions/", debugMode);
        List<Question> questions = deserializeQuestion.getQuestions();
        if (questions != null) {
            if(debugMode){
                for (Question question : questions) {
                    System.out.println(question);
                }
            }
        } else {
            System.out.println("Folder jest pusty");
            return false;
        }

        Game game = new Game(debugMode, questions, 4, "Tadek");
        if (4 > questions.size()) return false;

        SceneLoader.loadGameScene(this,game);

        return true;
    }


    public void onAddQuestionButtonClick(ActionEvent event) throws IOException {
        SceneLoader.loadAddQuestionScene(this);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }
}
