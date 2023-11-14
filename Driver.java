import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
        public static void main(String[] args) {
                ArrayList<Sentence> sentences = new ArrayList<>();
                try {
                        File myObj = new File("data/testdata.manual.2009.06.14.csv");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                                String data = myReader.nextLine();
                                Sentence sentence = Sentence.convertLine(data);
                                sentences.add(sentence);
                                System.out.println(data);
                        }
                        myReader.close();
                } catch (FileNotFoundException error) {
                        System.out.println("An error occurred.");
                        error.printStackTrace();
                }
        }

}
