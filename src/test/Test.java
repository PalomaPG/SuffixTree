package test;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import suffixtree.SuffixTree;

public class Test {
	
	static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	private static String readFile(String filename)
	{
	  StringBuilder records = new StringBuilder();
	  try
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	      records.append(line);
	    }
	    reader.close();
	    return records.toString();
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	    return null;
	  }
	}
	
	static public StringBuilder clean(String initialText){
		StringBuilder cleanText = new StringBuilder();
		initialText = initialText.toLowerCase();
		for (int i=0; i<initialText.length(); i++){
			if (alphabet.contains("" + initialText.charAt(i))){
				cleanText.append(initialText.charAt(i));
			}
		}
		return cleanText;
	}
	
	static public void constructionTest() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		for (int i=15; i<21; i++){
			SuffixTree st = new SuffixTree();
			String text = readFile("input/t" + i + ".txt");
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("input/test2" + i + ".txt"), "utf-8"))) {
				TimeWatch watch = TimeWatch.start();
				st = (SuffixTree)st.ukkonen(text);
		        st.convertToReal();
		    
				String out = "Elapsed Time custom format: " + watch.toMinuteSeconds() + "\n" +
							"Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS) + "\n" +
							"Elapsed Time in nano seconds: " + watch.time() + "\n" +
							"Costs: " + st.getCosts();
				writer.write(out);
			}
			System.out.println("finished: " + i);
		}
	}
	
	static public void processFile(String in_filepath, String out_filepath, int length) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		String text = clean(readFile(in_filepath)).toString();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(out_filepath), "utf-8"))) {
			writer.write(text.substring(0, (int) Math.pow(2, length)));
		}
	}
	
	public static void main (String [] args) throws IOException{
		
		constructionTest();
		
	}
}