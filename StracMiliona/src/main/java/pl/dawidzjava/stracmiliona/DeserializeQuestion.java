package pl.dawidzjava.stracmiliona;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class DeserializeQuestion {

    private String folderName;
    private boolean debugMode;

    public DeserializeQuestion(String folderName, boolean debugMode) {
        this.folderName = folderName;
        this.debugMode = debugMode;
    }

    public List<Question> getQuestions() {
        String[] files = getFileList(folderName);
        List<Question> questions = new ArrayList<>();
        for (String fileName : files) {
            Question temp = deserializeQuestion(fileName);
            if (temp != null) questions.add(temp);
        }
        if (questions.isEmpty()) {
            return null;
        } else return questions;
    }

    private String[] getFileList(String folderName) {
        File file = new File(folderName);
        return file.list();
    }

    //USED: https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk
    private Question deserializeQuestion(String fileName) {
        Question question = null;
        try {
            FileInputStream fileIn = new FileInputStream(folderName + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            question = (Question) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            if(debugMode){
                System.out.println("Nie wczytano pliku: " + fileName + "\npowód: " + i.getMessage());
            }
            return null;
        }
        return question;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
