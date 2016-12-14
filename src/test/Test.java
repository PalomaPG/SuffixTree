package test;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
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
		for (int i=15; i<16; i++){
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
	
	
	static public void searchTest() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		TimeWatch watch;
		String rdm_str;
		int l;
		int num_str;
		LinkedList<Integer> l_lst;
		for (int i=15; i<19; i++){
			SuffixTree st = new SuffixTree();
			
			String text = readFile("input/t" + i + ".txt");
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		              new FileOutputStream("input/search-test" + i + ".txt"), "utf-8"))) {
				
				st = (SuffixTree)st.ukkonen(text);
		        st.convertToReal();
		        l = (int) Math.pow(2, i);
		        num_str = l/10;
		        while(num_str>0){
		        	rdm_str = randomStr(l, text);
		        	watch = TimeWatch.start();
		        	l_lst = st.search(rdm_str, new LinkedList<Integer>(), st.getRoot(), text);
		        
		        	String out = "Elapsed Time custom format: " + watch.toMinuteSeconds() + "\n" +
							"Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS) + "\n" +
							"Elapsed Time in nano seconds: " + watch.time() + "\n" +
							"String length: "+ rdm_str.length()+"\n"+
							"Costs: " + st.getSearchCost()+"\n"+
							"Results :"+l_lst.toString()+"\n" ;
		        	writer.write(out);
		        	st.setSearchCost(0);
		        	num_str--;
		        }
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
	
	public static  String randomStr(int max, String text){
		
		int i, j;
		i=j=0;
		while(i==j || j<0 || i<0){
			i = ThreadLocalRandom.current().nextInt(0, max + 1);
		 	j = ThreadLocalRandom.current().nextInt(0, max + 1);
		}

		return i>j? text.substring(j,i) : text.substring(i,j);
	}
	
	
	public static void main (String [] args) throws IOException{
		
		searchTest();
		
	}
}