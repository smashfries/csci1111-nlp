import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map; //place with imports
import java.util.Collections; //place with imports

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

                HashMap<String, Integer> topwords = printTopWords(sentences);

                Map.Entry<String, Integer> maxEntry = null;
                for (Map.Entry<String, Integer> entry : topwords.entrySet())
                        if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                                maxEntry = entry;
                int maxValueLen = maxEntry.getValue().toString().length();
                ArrayList<String> results = new ArrayList<String>();
                for (Map.Entry set : topwords.entrySet()) {
                        String value = set.getValue().toString();
                        while (value.length() < maxValueLen)
                                value = " " + value;
                        results.add(value + " of " + set.getKey());
                }
                Collections.sort(results);
                Collections.reverse(results);
                for (int i = 0; i < results.size() && i < 100; i++)
                        System.out.println(results.get(i));

        }

        public static HashMap<String, Integer> printTopWords(ArrayList<Sentence> sentences) {

                HashMap<String, Integer> hashMap = new HashMap<>();
                // with lemmatization
                ArrayList<String> words = Sentence.lemmatize(sentences);

                ArrayList<String> bigrams = Sentence.ngramPhrases(words, 2);

                ArrayList<String> trigrams = Sentence.ngramPhrases(words, 3);

                words.addAll(bigrams);
                words.addAll(trigrams);

                for (int b = 0; b < words.size(); b++) {
                        if (hashMap.get(words.get(b)) != null) {
                                hashMap.put(words.get(b), hashMap.get(words.get(b)) + 1);

                        } else {
                                hashMap.put(words.get(b), 1);
                        }
                }

                // without lemmatization
                // for (int h = 0; h < sentences.size(); h++) {
                // Sentence sentence = sentences.get(h);

                // ArrayList<String> words = sentence.splitSentence();

                // for (int b = 0; b < words.size(); b++) {
                // if (hashMap.get
                // (words.get(b)) != null) {
                // hashMap.put(words.get(b), hashMap.get(words.get(b)) + 1);

                // } else {
                // hashMap.put(words.get(b), 1);
                // }
                // }

                // }
                return hashMap;

        }

}
