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
                String reply = "{author:" + author + ", "  + "sentence:" + text + ", " + "timestamp: " + timestamp;
                return reply;

        }

}
