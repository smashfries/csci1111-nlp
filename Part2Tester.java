public class Part2Tester{

	public static void main(String[] args){

		// Test case for Twitter dataset
		String line = "\"4\",\"3\",\"Mon May 11 03:17:40 UTC 2009\",\"kindle2\",\"tpryan\",\"@stellargirl I " +
			"loooooooovvvvvveee my Kindle2. Not that the DX is cool, but the 2 is fantastic in its own right.\"";
		Sentence expected = new Sentence("@stellargirl I " +
			"loooooooovvvvvveee my Kindle2 Not that the DX is cool but the 2 is fantastic in its own right", 
			"tpryan", "May 11 2009");
		Sentence result = Sentence.convertLine(line);
		System.out.println("test1: " + expected.getText().equals(result.getText()));
		System.out.println("test2: " + expected.getAuthor().equals(result.getAuthor()));
		System.out.println("test3: " + expected.getTimestamp().equals(result.getTimestamp()));

		// Test case for EBook dataset
		Sentence sent = new Sentence("Example sentence from a book", "Ada Lovelace", "Chapter 1");
		sent.addToEnd(" continues, and then ends, here.");
		System.out.println("test1: " + sent.getText().equals("Example sentence from a book continues and then ends here"));
	}
}
