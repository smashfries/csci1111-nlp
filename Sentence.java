import java.util.ArrayList;
import java.util.Arrays;

// Blueprint for Sentence object
public class Sentence {
        private String text;
        private String author;
        private String timestamp;

        // Constructor(Method) for Sentence passes in Text, Authort and timestamp
        public Sentence(String text, String author, String timestamp) {
                this.text = text;
                this.author = author;
                this.timestamp = timestamp;

        }

        // gets text
        public String getText() {
                return text;
        }

        // sets text in private with the argument(Uses new string), does not return text
        public void setText(String text) {
                this.text = text;
        }

        public String getAuthor() {
                return author;

        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
        }

        public String toString() {
                String reply = "{author:" + author + ", " + "sentence:\"" + text + "\", " + "timestamp:\"" + timestamp
                                + "\"}";
                return reply;

        }

        public static Sentence convertLine(String line) {

                String[] convertLine = line.split("\",");
                for (int i = 0; i < convertLine.length; i++) {
                        convertLine[i] = convertLine[i].replaceAll("\\.|,|\"", "");
                }
                // datetimest stores the timestamp
                String[] datetimest = convertLine[2].split(" ");
                // comptime formats the timestamp as Month/Day/year
                String comptime = datetimest[1] + " " + datetimest[2] + " " + datetimest[5];

                Sentence sentence = new Sentence(convertLine[5], convertLine[4], comptime);

                System.out.println(line);
                System.out.println(convertLine.length);
                System.out.println(Arrays.toString(convertLine));

                return sentence;

        }

        public static ArrayList<String> lemmatize(ArrayList<Sentence> sentences) {
                StanfordLemmatizer lemmatizer = new StanfordLemmatizer();
                ArrayList<String> result = new ArrayList<String>();

                for (int i = 0; i < sentences.size(); i++) {
                      result.addAll(lemmatizer.lemmatize(sentences.get(i).getText()));
                }

                ArrayList<String> withoutStopWords = removeStopWords(result);

                return withoutStopWords;

                
  }

        public ArrayList<String> splitSentence() {
                String[] pieces = text.split(" ");


                ArrayList<String> arrlist = removeStopWords(new ArrayList<>(Arrays.asList(pieces)));

                return arrlist;
        }

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

                                arrlist.add(words.get(i));
                        }
                }

                return arrlist;
        }

}
