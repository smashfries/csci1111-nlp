import java.util.Arrays;

public class Sentence {
        private String text;
        private String author;
        private String timestamp;

        public Sentence(String text, String author, String timestamp) {
                this.text = text;
                this.author = author;
                this.timestamp = timestamp;

        }

        public String getText() {
                return text;
        }

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
                String reply = "{author:" + author + ", "  + "sentence:\"" + text + "\", " + "timestamp:\"" + timestamp + "\"}";
                return reply;

        }

        public static Sentence convertLine(String line) {

                String[] convertLine = line.split("\","); 
                for (int i = 0; i < convertLine.length; i++) {
                        convertLine[i] = convertLine[i].replaceAll("\\.|,|\"", "");
                }
                String[] datetimest = convertLine[2].split(" ");
                String comptime = datetimest[1] + " " + datetimest[2] + " " + datetimest[5];
                                        
                Sentence sentence = new Sentence(convertLine[5],convertLine[4],comptime);
                
                System.out.println(line);
                System.out.println(convertLine.length);
                System.out.println(Arrays.toString(convertLine));

                


                return sentence;
                
        }


}
