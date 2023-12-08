import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import java.util.Date;
import org.ejml.simple.SimpleMatrix;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

// Blueprint for Sentence object
public class Sentence {
        private String text;
        private String author;
        private String timestamp;

        // Constructor(Method) for Sentence passes in Text, Author and timestamp
        public Sentence(String text, String author, String timestamp) {
                this.text = text;
                this.author = author;
                this.timestamp = timestamp;

        }

        // returns sentiment score for the object's tweet
        public int getSentiment() {
                Properties props = new Properties();
                props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
                StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
                Annotation annotation = pipeline.process(text);
                CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                return RNNCoreAnnotations.getPredictedClass(tree);
        }

        // gets text
        public String getText() {
                return text;
        }

        // sets text
        public void setText(String text) {
                this.text = text;
        }

        // gets author
        public String getAuthor() {
                return author;

        }

        // sets author
        public void setAuthor(String author) {
                this.author = author;
        }

        // gets timestamp
        public String getTimestamp() {
                return timestamp;
        }

        // sets timestamp
        public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
        }

        // returns a string representation of the object
        public String toString() {
                String reply = "{author:" + author + ", " + "sentence:\"" + text + "\", " + "timestamp:\"" + timestamp
                                + "\"}";
                return reply;

        }

        // takes a line from the csv as argument, and processes it into a sentence
        // object
        public static Sentence convertLine(String line) {

                String[] splitLine = line.split("\",");
                for (int i = 0; i < splitLine.length; i++) {
                        splitLine[i] = splitLine[i].replaceAll("\\.|,|\"", "");
                }
                // string array of the parts of a timestamp
                String[] timestampArr = splitLine[2].split(" ");

                // formattedTime stores the formated timestamp as Month/Day/year
                String formattedTime = timestampArr[1] + " " + timestampArr[2] + " " + timestampArr[5];

                Sentence sentence = new Sentence(splitLine[5], splitLine[4], formattedTime);

                return sentence;
        }

        // takes sentences as argument and returns an arrayList of lemmas
        public static ArrayList<String> lemmatize(ArrayList<Sentence> sentences) {
                StanfordLemmatizer lemmatizer = new StanfordLemmatizer();
                ArrayList<String> result = new ArrayList<String>();

                for (int i = 0; i < sentences.size(); i++) {
                        result.addAll(lemmatizer.lemmatize(sentences.get(i).getText()));
                }

                ArrayList<String> withoutStopWords = removeStopWords(result);

                return withoutStopWords;

        }

        // splits the tweet into words based on spaces
        public ArrayList<String> splitSentence() {
                String[] pieces = text.split(" ");

                ArrayList<String> arrlist = removeStopWords(new ArrayList<>(Arrays.asList(pieces)));

                return arrlist;
        }

        // removes stopwords from an arrayList of words
        private static ArrayList<String> removeStopWords(ArrayList<String> words) {
                String[] stopwords = { "-lrb-", "-rrb-", "'s", ":", "''", "!", "?", "", "-", "a", "about", "above",
                        "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren't", "as",
                        "at", "be", "because", "been", "before", "being", "below", "between", "both", "but",
                        "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't",
                        "doing", "don't", "down", "during", "each", "few", "for", "from", "further", "had",
                        "hadn't", "has", "hasn't", "have", "haven't", "having", "he", "he'd", "he'll", "he's",
                        "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's",
                        "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is", "isn't", "it", "it's",
                        "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no", "nor",
                        "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our",
                        "ours ourselves", "out", "over", "own", "same", "shan't", "she", "she'd", "she'll",
                        "she's", "should", "shouldn't", "so", "some", "such", "than", "that", "that's", "the",
                        "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they",
                        "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too",
                        "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're",
                        "we've", "were", "weren't", "what", "what's", "when", "when's", "where", "where's",
                        "which", "while", "who", "who's", "whom", "why", "why's", "with", "won't", "would",
                        "wouldn't", "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself",
                        "yourselves" }; // from https://www.ranks.nl/stopwords

                ArrayList<String> arrlist = new ArrayList<>();

                for (int i = 0; i < words.size(); i++) {
                        boolean isStopWord = false;
                        for (int j = 0; j < stopwords.length; j++) {
                                if (words.get(i).toLowerCase().equals(stopwords[j])) {
                                        isStopWord = true;
                                        break;
                                }
                        }
                        if (!isStopWord) {

                                arrlist.add(words.get(i).toLowerCase());
                        }
                }

                return arrlist;
        }

        // returns an arrayList of ngrams based on argument n and arrayList of words
        public static ArrayList<String> ngramPhrases(ArrayList<String> words, int ngram) {
                ArrayList<String> phrases = new ArrayList<String>();
                for (int i = 0; i < words.size() - (ngram - 1); i++) {
                        String ngrams = "";
                        for (int j = 0; j < ngram; j++) {
                                ngrams += (words.get(i + j));

                                if (j != ngram - 1) {
                                        ngrams += " ";
                                }

                        }
                        phrases.add(ngrams);

                }

                return phrases;
        }

        // given a string time range, determiens if the sentence is within it or not
        public boolean keep(String temporalRange) {
                String[] dates = temporalRange.split("-");
                try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
                        Date startDate = dateFormat.parse(dates[0]);
                        Date endDate = dateFormat.parse(dates[1]);
                        Date sentenceDate = dateFormat.parse(timestamp);

                        return sentenceDate.getTime() >= startDate.getTime()
                                        && sentenceDate.getTime() <= endDate.getTime();
                } catch (Exception error) {
                        System.out.println(error.toString());
                        return false;
                }
        }

}
