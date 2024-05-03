package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.dawidzjava.stracmiliona.serialization.DeserializeQuestion;

import java.io.IOException;
import java.util.List;

public class HelloController {
    public Label errorMessage;
    public TextField playerNameField;

    @FXML
    protected void onStartGameButtonClick(ActionEvent event) throws IOException {
        String questionsFolder="src/main/resources/questions/";
        boolean debugModeStatus =true;
        int questionsNumber=4;

        if (isNameValid()) {
            List<Question> questions =loadQuestions(debugModeStatus,questionsFolder);
            if (questions!=null && questions.size()>=questionsNumber){
                GameEngine gameEngine = new GameEngine(debugModeStatus, questions, questionsNumber, playerNameField.getText());
                ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
                SceneLoader.loadGameScene(this, gameEngine);
            }
            else errorMessage.setText("NIe udało się załadować gry :-(");

        }

    }

    private List<Question> loadQuestions(boolean debugMode,String questionsFolder ){
        DeserializeQuestion deserializeQuestion = new DeserializeQuestion(questionsFolder, debugMode);
        List<Question> questions = deserializeQuestion.getQuestions();
        if (questions != null) {
            if(debugMode){
                for (Question question : questions) {
                    System.out.println(question);
                }
            }
            return questions;

        } else {
            System.out.println("Folder jest pusty");
            return null;
        }
    }

    private boolean isNameValid(){
        String name=playerNameField.getText();
        if (name.isEmpty()) {
            errorMessage.setText("Wpisz swoje Imię");
            return false;
        }
        if (name.length()>12){
            errorMessage.setText("Twoje Imię jest za długie");
            return false;
        }
        return true;
    }


    public void onAddQuestionButtonClick(ActionEvent event) throws IOException {
        SceneLoader.loadAddQuestionScene(this);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }
}
