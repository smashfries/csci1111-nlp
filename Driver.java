import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.Collections;

public class Driver {

        public static void main(String[] args) {
                // ArrayList of sentences that will be loaded from the csv file.
                ArrayList<Sentence> sentences = new ArrayList<>();

                // Filtered list of sentences using keep method
                ArrayList<Sentence> focusSentences = new ArrayList<>();

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
                                // System.out.println(data);

                        }
                        myReader.close();

                        // if there is an exception this catch block will print the error
                } catch (FileNotFoundException error) {
                        System.out.println("An error occurred.");
                        error.printStackTrace();
                }

                // hash map that keeps track of number of occurences of words
                HashMap<String, Integer> topwords = printTopWords(sentences);

                // hash map to store sentiment of topwords
                HashMap<String, Float> sentiments = null;

                // Prints out hashmap
                // Map.Entry<String, Integer> maxEntry = null;
                // for (Map.Entry<String, Integer> entry : topwords.entrySet())
                // if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                // maxEntry = entry;
                // int maxValueLen = maxEntry.getValue().toString().length();
                // ArrayList<String> results = new ArrayList<String>();
                // for (Map.Entry set : topwords.entrySet()) {
                // String value = set.getValue().toString();
                // while (value.length() < maxValueLen)
                // value = " " + value;
                // results.add(value + " of " + set.getKey());
                // }
                // Collections.sort(results);
                // Collections.reverse(results);
                // for (int i = 0; i < results.size() && i < 100; i++)
                // System.out.println(results.get(i));

                // prints sentiment score for all sentences
                // for(int i = 0; i< sentences.size(); i++){

                // Sentence mySentence = sentences.get(i);
                // int sentimentscore = mySentence.getSentiment();

                // System.out.println(mySentence + " --- Sentiment score: " + sentimentscore);
                // }

                // uses keep method to filter sentences with a date range
                // for (int i = 0; i < sentences.size(); i++) {
                // boolean keep = sentences.get(i).keep("Jun 09 2009-Jun 10 2009");
                // if (keep) {
                // focusSentences.add(sentences.get(i));
                // System.out.println(sentences.get(i).toString());
                // }
                // }

                // used for data analysis to determine average sentiment of top words
                String phrase = "star trek";
                String[] phraseWords = phrase.split(" ");

                int counter = 0;
                int sum = 0;
                for (int i = 0; i < sentences.size(); i++) {
                        Sentence sentence = sentences.get(i);
                        String tweet = sentence.getText();
                        System.out.println(tweet);

                        ArrayList<String> words = sentence.splitSentence();

                        int counter2 = 0;
                        for (int j = 0; j < words.size(); j++) {
                                if (words.get(j).equals(phraseWords[counter2])) {
                                        counter2++;
                                } else {
                                        counter2 = 0;
                                }

                                if (counter2 == phraseWords.length) {
                                        counter++;
                                        int sentiment = sentence.getSentiment();
                                        sum += sentiment;
                                        break;
                                }
                        }
                }

                System.out.println(sum);
                System.out.println(counter);
                float average = (float) sum / counter;
                System.out.println("Average sentiment score for " + phrase + ": " + average);
        }

        // adds words to the hashmap
        public static HashMap<String, Integer> printTopWords(ArrayList<Sentence> sentences) {
                HashMap<String, Integer> hashMap = new HashMap<>();

                // with lemmatization
                ArrayList<String> words = Sentence.lemmatize(sentences);

                // bigrams
                ArrayList<String> bigrams = Sentence.ngramPhrases(words, 2);

                // trigrams
                ArrayList<String> trigrams = Sentence.ngramPhrases(words, 3);

                words.addAll(bigrams);
                words.addAll(trigrams);

                // checks if the word is in the hashmap
                // if it is, increment value
                // else add it and set the value to 1
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
