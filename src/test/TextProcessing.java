package test;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import suffixtree.SuffixTree;

public class TextProcessing {
	
	static String a = "abcdefghijklmnopqrstuvwxyz";
	
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
			if (a.contains("" + initialText.charAt(i))){
				cleanText.append(initialText.charAt(i));
			}
		}
		return cleanText;
	}
	
	public static void main (String [] args) throws IOException{
		

		
//		String text = clean(readFile("/Users/paolapintosilva/Documents/workspace/T2/src/tests/t15.txt")).toString();
//		System.out.println(text.length());
//		
//		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//	              new FileOutputStream("/Users/paolapintosilva/Documents/workspace/T2/src/tests/pequeño.txt"), "utf-8"))) {
//			writer.write(text);
//			
//			
//	}
//		
//		System.out.println(readFile("/Users/paolapintosilva/Documents/workspace/T2/src/tests/tt15.txt").length());

//		String text = "theprotheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookofjectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookoftheprojectgutenbergebookof";
//		System.out.println(text.length());
		
		SuffixTree st = new SuffixTree();
		String text = readFile("/Users/natto/Downloads/tt15.txt");
//		System.out.println(text.length());
//        TimeWatch watch = TimeWatch.start();
        st = (SuffixTree)st.ukkonen(text);
        st.convertToReal();
//        System.out.println("Elapsed Time custom format: " + watch.toMinuteSeconds());
//        System.out.println("Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS));
//        System.out.println("Elapsed Time in nano seconds: " + watch.time());

		    

		
	}
}