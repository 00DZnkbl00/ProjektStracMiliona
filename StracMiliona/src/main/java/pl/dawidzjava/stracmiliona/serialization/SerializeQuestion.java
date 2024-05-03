package pl.dawidzjava.stracmiliona.serialization;

import pl.dawidzjava.stracmiliona.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SerializeQuestion {


    private static boolean isNameEmpty(String folderName,String filename) {
        File file = new File(folderName);
        if (Objects.requireNonNull(file.list()).length==0)return false;
        List<String> list = Arrays.stream(Objects.requireNonNull(file.list())).toList();
        return list.contains(filename);
    }

    //USED: https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk
    public static boolean addQuestion(Question question) {
        int x = 0;
        String path="src/main/resources/questions/",filename=null;
        while (filename==null){
            filename="question"+(x)+".ser";
            if (isNameEmpty(path,filename)) {
                filename = null;
                x++;
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/questions/question" + x + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(question);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved question" + (x) + ".ser");
        } catch (IOException i) {
            System.out.println(i.getMessage());
            return false;
        }
        return true;
    }
}
