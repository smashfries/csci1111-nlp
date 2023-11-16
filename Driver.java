import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

        public static void main(String[] args) {
                // ArrayList of sentences from the csv file.
                ArrayList<Sentence> sentences = new ArrayList<>();
                try {
                        // File goes to the csv file path.
                        File myObj = new File("data/testdata.manual.2009.06.14.csv");
                        Scanner myReader = new Scanner(myObj);

                        // this is a while loop that repeats until each line has been printed
                        while (myReader.hasNextLine()) {

                                // string called data is the next line of the csv file.
                                String data = myReader.nextLine();

                                // converts line to a sentence object
                                Sentence sentence = Sentence.convertLine(data);

                                // adds sentence to the arraylist
                                sentences.add(sentence);

                                // prints out the line
                                System.out.println(data);
                                
                        }
                        myReader.close();

                        // if there is an exception this catch block will print the error
                } catch (FileNotFoundException error) {
                        System.out.println("An error occurred.");
                        error.printStackTrace();
                }
        }

}
