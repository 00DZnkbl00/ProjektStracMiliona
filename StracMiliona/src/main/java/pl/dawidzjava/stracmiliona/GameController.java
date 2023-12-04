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

    private int moneyToDivide=1_000_000;

    @FXML
    protected void onTestButtonAction(ActionEvent event) throws IOException {
        loadNewScene();
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void onPlusButtonClick(ActionEvent event){
        changeOptionValue(event,25_000);
    }

    @FXML
    public void onMinusButtonClick(ActionEvent event){
        changeOptionValue(event,-25_000);
    }

    private void changeOptionValue(ActionEvent event,int changeValue){
        Button button=(Button) event.getSource();
        ObservableList<Node> children = button.getParent().getChildrenUnmodifiable();
        Label label = (Label) children.get(1);
        int value=Integer.parseInt(label.getText());
        if (moneyToDivide-changeValue<0||moneyToDivide-changeValue>1_000_000) return;
        if (value+changeValue<0)return;
        if (!isOneScreenZero(label))return;
        value+=changeValue;
        moneyToDivide-=changeValue;
        moneyToDivideLabel.setText(String.valueOf(moneyToDivide));
        label.setText(String.valueOf(value));

    }

    private boolean isOneScreenZero(Label label){
        if (label!=screen1&&Integer.parseInt(screen1.getText())==0)return true;
        if (label!=screen2&&Integer.parseInt(screen2.getText())==0)return true;
        if (label!=screen3&&Integer.parseInt(screen3.getText())==0)return true;
        if (label!=screen4&&Integer.parseInt(screen4.getText())==0)return true;
        return false;
    }


    private void loadNewScene() throws IOException {
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
        this.gameShowHost.setText(text);
    }
}
