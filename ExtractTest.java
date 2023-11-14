public class ExtractTest {
	public static void main(String[] args) {

		// Test case for commas
		String line = "\"5\",\"6\",\"Tue Oct 21 09:27:40 UTC 2010\",\"iphone5\",\"tomin\",\"@stark I " 
		 +
				"look for free emulators. I enjoy playing old games, but some remakes are downright trash.\"";
		Sentence expected = new Sentence("@stark I " 
		 +
				"look for free emulators I enjoy playing old games but some remakes are downright trash",
				"tomin", "Oct 21 2010");
		Sentence result = Sentence.convertLine(line);
		System.out.println("test1: " + expected.getText().equals(result.getText()));
		System.out.println("test6: " + expected.getTimestamp().equals(result.getTimestamp()));

	}

}
