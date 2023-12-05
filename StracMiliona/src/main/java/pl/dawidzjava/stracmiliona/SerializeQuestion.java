package pl.dawidzjava.stracmiliona;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeQuestion {
    private static int x = 0;

    public static void main(String[] args) {
        Question question = new Question("Test?", "NO", "NO", "YES", "NO", 0, 3);
        addQuestion(question);
        addQuestion(new Question("Test!", "YES", "NO", "NO", "NO", 0, 1));
        addQuestion(new Question("Test!?", "NO", "YES", "NO", "NO", 0, 2));
        addQuestion(new Question("Test?!", "NO", "NO", "NO", "YES", 0, 4));
    }

    //USED: https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk
    public static void addQuestion(Question question) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/questions/question" + x++ + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(question);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved question" + (x - 1) + ".ser");
        } catch (IOException i) {
            System.out.println(i.getMessage());
        }
    }
}
