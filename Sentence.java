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

        public String getAuthor() {
                return author;

        }

        public String getTimestamp() {
                return timestamp;
        }

}
