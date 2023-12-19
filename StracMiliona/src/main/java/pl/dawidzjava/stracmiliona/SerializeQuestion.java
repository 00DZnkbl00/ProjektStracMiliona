package pl.dawidzjava.stracmiliona;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SerializeQuestion {
    private static int x = 0;

    public static void main(String[] args) {

        addQuestion(new Question("Który kraj leży na Półwyspie Apenińskim?"
                , "Francja", "Włochy", "Hiszpania"
                , "Grecja", 0, 2));

        addQuestion(new Question("Jakie morze oddziela Grecję od Turcji?"
                , "Morze Adriatyckie", "Morze Czarne", "Morze Egejskie"
                , "Morze Jońskie", 0, 3));

        addQuestion(new Question("W którym kraju znajduje się Machu Picchu?"
                , "Brazylia", "Peru", "Meksyk"
                , "Kolumbia", 0, 2));

        addQuestion(new Question("Jaka to góra, najwyższy szczyt Afryki?"
                , "Mont Blanc", "Matterhorn", "Kilimandżaro"
                , "Góra Elbrus", 0, 3));

        addQuestion(new Question("Które państwo jest największe pod względem powierzchni na świecie?"
                , "Rosja", "Stany Zjednoczone", "Kanada"
                , "Chiny", 0, 1));

        addQuestion(new Question("Na którym kontynencie leży Australia?"
                , "Azja", "Europa", "Ameryka Południowa"
                , "Australia", 0, 4));

        addQuestion(new Question("Które miasto jest stolicą Japonii?"
                , "Pekin", "Tokio", "Seul"
                , "Bangkok", 0, 2));

        addQuestion(new Question("Jaka rzeka przepływa przez Paryż?"
                , "Tamiza", "Sekwana", "Dunaj"
                , "Ren", 0, 2));

        addQuestion(new Question("W którym kraju znajduje się Wielki Kanion Kolorado?"
                , "Kanada", "Meksyk", "Stany Zjednoczone"
                , "Australia", 0, 3));

        addQuestion(new Question("Gdzie leży Złota Brama w San Francisco?"
                , "Nowy Jork", "Los Angeles", "San Francisco"
                , "Miami", 0, 3));

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
