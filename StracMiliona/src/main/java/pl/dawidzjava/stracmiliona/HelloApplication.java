package pl.dawidzjava.stracmiliona;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DeserializeQuestion deserializeQuestion=new DeserializeQuestion("src/main/resources/questions/");
        List<Question> questions=deserializeQuestion.getQuestions();
        if (questions!=null){
            for (Question question : questions) {
                System.out.println(question);
            }
        }
        else System.out.println("Folder jest pusty");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load() );
        stage.setTitle("Strać Miliona !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}