import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Part3Tester{

	public static void main(String[] args){

		// Test case for Twitter dataset
		Sentence sent = new Sentence("@stellargirl I " +
			"loooooooovvvvvveee my Kindle2 Not that the DX is cool but the 2 is fantastic in its own right", 
			"tpryan", "May 11 2009");
		ArrayList<String> result = sent.splitSentence();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("@stellargirl");
		expected.add("loooooooovvvvvveee");
		expected.add("kindle2");
		expected.add("dx");
		expected.add("cool");
		expected.add("2");
		expected.add("fantastic");
		expected.add("right");
		System.out.println("test1: " + expected.equals(result));

		// Test case for EBook dataset
		// sent = new Sentence("Example sentence from a book", "Ada Lovelace", "Chapter 1");
		// sent.addToEnd(" continues, and then ends, here.");
		// ArrayList<String> resultE = sent.splitSentence();
		// ArrayList<String> expectedE = new ArrayList<String>();
		// expectedE.add("example");
		// expectedE.add("sentence");
		// expectedE.add("book");
		// expectedE.add("continues");
		// expectedE.add("ends");
		// System.out.println("test1: " + expectedE.equals(resultE));

		// ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		// Sentence sent1 = new Sentence("@stellargirl I " +
		// 	"loooooooovvvvvveee my Kindle2 Not that the DX is cool but the 2 is fantastic in its own right", 
		// 	"tpryan", "May 11 2009");
		// Sentence sent2 = new Sentence("@stellargirl I " +
		// 	"loooooooovvvvvveee my Kindle Not that the DX is cool but the 2 is fantastic in its own right", 
		// 	"tpryan", "May 11 2009");
		// Sentence sent3 = new Sentence("@kinga Machine learning is cool", 
		// 	"tpryan", "May 11 2009");
		// sentences.add(sent1);
		// sentences.add(sent2);
		// sentences.add(sent3);
		// expected = new ArrayList<String>();
		// expected.add("3 of cool");
		// expected.add("2 of right");
		// expected.add("2 of loooooooovvvvvveee");
		// expected.add("2 of fantastic");
		// expected.add("2 of dx");
		// expected.add("2 of @stellargirl");
		// expected.add("2 of 2");
		// expected.add("1 of machine");
		// expected.add("1 of learning");
		// expected.add("1 of kindle2");
		// expected.add("1 of kindle");
		// expected.add("1 of @kinga");
		// HashMap<String,Integer> resultDict = Driver.printTopWords(sentences);
		// ArrayList<String> resultArr = new ArrayList<String>();
		// for (Map.Entry<String, Integer> entry : resultDict.entrySet())
		// 	resultArr.add(entry.getValue() + " of " + entry.getKey());
		// Collections.sort(expected); 
		// Collections.sort(resultArr);   
		// System.out.println("test2: " + expected.equals(resultArr));
	}
}