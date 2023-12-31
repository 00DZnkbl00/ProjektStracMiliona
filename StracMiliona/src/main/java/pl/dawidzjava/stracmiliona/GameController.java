package pl.dawidzjava.stracmiliona;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    public Label moneyToDivideLabel;
    @FXML
    public Label gameShowHost;
    @FXML
    public Label screen1;
    @FXML
    public Label screen2;
    @FXML
    public Label screen3;
    @FXML
    public Label screen4;
    @FXML
    public Label anws1;
    @FXML
    public Label anws2;
    @FXML
    public Label anws3;
    @FXML
    public Label anws4;
    @FXML
    public Button applyButton;
    @FXML
    public Button nextButton;
    @FXML
    public Label questionScreen;

    private Game game;

    @FXML
    protected void onTestButtonAction(ActionEvent event) throws IOException {
        SceneLoader.loadHelloScene(this);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void onPlusButtonClick(ActionEvent event) {
        changeOptionValue(event, true);
    }

    @FXML
    public void onMinusButtonClick(ActionEvent event) {
        changeOptionValue(event, false);
    }

    @FXML
    public void onNextButtonClick() {
        game.loadNewQuestion();
        String[] questions = game.getActiveQuestionList();
        gameShowHost.setText( "Oto twoje pytanie.\nPodziel swoje pieniądze na opowiedzi, używając przycisków + i -. Możesz rozłożyć kwotę na maksymalnie 3 odpowiedzi. Po podziale naciśnij przycisk zatwierdź.");
        questionScreen.setText(game.getActiveQuestionString());
        anws1.setText(questions[0]);
        anws2.setText(questions[1]);
        anws3.setText(questions[2]);
        anws4.setText(questions[3]);
        nextButton.setDisable(true);
    }

    @FXML
    public void onApplyButtonClick() {
        if (game.isActiveQuestionNull()) return;
        int result = game.checkAnswer();

        if (result == 1) {
            nextButton.setDisable(true);
            questionScreen.setText("GRATULACJĘ");
            gameShowHost.setText("Gratulację " + game.getPlayerName() + "\n" +
                    "Wygrałeś: " + game.getMoneyToDivide() + "\n" +
                    "Kliknij \"Wyjdź\" aby powrócić do menu");
        } else if (result == -1) {
            nextButton.setDisable(true);
            questionScreen.setText("Dziękujemy za grę");
            gameShowHost.setText("Wielka szkoda " + game.getPlayerName() + "\n" +
                    "Może następnym razem ci się uda \n" +
                    "Kliknij \"Wyjdź\" aby powrócić do menu");
        } else {
            nextButton.setDisable(false);
            questionScreen.setText("Kliknij \"Dalej\" aby otrzymać kolejne pytanie");
            gameShowHost.setText("Zostało ci : " + game.getMoneyToDivide() + "\n" +
                    "Kliknij \"Dalej\" aby otrzymać kolejne pytanie");
        }

        moneyToDivideLabel.setText(String.valueOf(game.getMoneyToDivide()));
        applyButton.setDisable(true);


        game.resetScreens();
        screen1.setText(giveLcdText(0));
        screen2.setText(giveLcdText(0));
        screen3.setText(giveLcdText(0));
        screen4.setText(giveLcdText(0));
    }

    private void changeOptionValue(ActionEvent event, boolean isPlusButton) {
        Button button = (Button) event.getSource();
        ObservableList<Node> children = button.getParent().getChildrenUnmodifiable();
        Label label = (Label) children.get(1);
        int value;
        if ("screen1".equals(label.getId()))
            value = game.changeDoorValue(1, isPlusButton);
        else if ("screen2".equals(label.getId()))
            value = game.changeDoorValue(2, isPlusButton);
        else if ("screen3".equals(label.getId()))
            value = game.changeDoorValue(3, isPlusButton);
        else if ("screen4".equals(label.getId()))
            value = game.changeDoorValue(4, isPlusButton);
        else value = -1;

        if (value == -1) return;

        moneyToDivideLabel.setText(giveLcdText(game.getMoneyToDivide()));
        label.setText(giveLcdText(value));

        if (game.getMoneyToDivide() == 0 && !game.isActiveQuestionNull())
            applyButton.setDisable(false);
        else applyButton.setDisable(true);

    }

    private String giveLcdText(int number) {
        StringBuilder temp = new StringBuilder(String.valueOf(number));
        while (temp.length() < 7) {
            temp.insert(0, "0");
        }
        return temp.toString();
    }

    protected void setGameProperties(Game game) {
        gameShowHost.setText(game.getPlayerName() + " witaj w naszej grze!!!!\n" +
                "Kliknij \"Dalej\" aby otrzymać pierwsze pytanie");
        this.game = game;
    }
}
