package pl.dawidzjava.stracmiliona;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.dawidzjava.stracmiliona.serialization.SerializeQuestion;

import java.io.IOException;

public class QuestionAddController {



    @FXML
    public TextField questionFiled;
    @FXML
    public TextField answerAFiled;
    @FXML
    public TextField answerBFiled;
    @FXML
    public TextField answerCFiled;
    @FXML
    public TextField answerDFiled;
    @FXML
    public Label titleFiled;
    @FXML
    public RadioButton answerDButton;
    @FXML
    public RadioButton answerCButton;
    @FXML
    public RadioButton answerBButton;
    @FXML
    public RadioButton answerAButton;

    private int anwser=0;

    @FXML
    public void onBackButton(ActionEvent event) throws IOException {
        SceneLoader.loadHelloScene(this);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void onRadioButton(ActionEvent event) {
        RadioButton button=(RadioButton) event.getSource();
        if (button==answerAButton){
            anwser=1;
            answerAButton.setSelected(true);
        }
        else {
            answerAButton.setSelected(false);
        }

        if (button==answerBButton){
            anwser=2;
            answerBButton.setSelected(true);
        }
        else {
            answerBButton.setSelected(false);
        }

        if (button==answerCButton){
            anwser=3;
            answerCButton.setSelected(true);
        }
        else {
            answerCButton.setSelected(false);
        }

        if (button==answerDButton){
            anwser=4;
            answerDButton.setSelected(true);
        }
        else {
            answerDButton.setSelected(false);
        }
        System.out.println(anwser);
    }

    @FXML
    public void onAddButton(ActionEvent event) {
        if(!adQuestion()){
            titleFiled.setText("Nie można dodać pytania");
        }
        else {
            titleFiled.setText("Dodano pytanie");
            questionFiled.setText("");
            answerAFiled.setText("");
            answerBFiled.setText("");
            answerCFiled.setText("");
            answerDFiled.setText("");
        }
    }

    private boolean adQuestion(){
        String questionName=questionFiled.getText().trim();
        if (questionName.isEmpty())return false;

        String answerA=answerAFiled.getText().trim();
        if (answerA.isEmpty())return false;

        String answerB=answerBFiled.getText().trim();
        if (answerB.isEmpty())return false;

        String answerC=answerCFiled.getText().trim();
        if (answerC.isEmpty())return false;

        String answerD=answerDFiled.getText().trim();
        if (answerD.isEmpty())return false;

        if (anwser==0)return false;


        Question question=new Question(questionName,answerA,answerB,answerC,answerD,0,1);

        return SerializeQuestion.addQuestion(question);
    }

}
