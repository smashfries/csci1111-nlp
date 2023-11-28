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

        public ArrayList<String> splitSentence() {
                String[] pieces = text.split(" ");
                ArrayList<String> arrlist = new ArrayList<>();

                for (int i = 0; i < pieces.length; i++) {
                        arrlist.add(pieces[i]);
                }
                return arrlist;
        }

}
