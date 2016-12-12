package node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import suffixtree.SuffixTree;
import test.TextProcessing;

public class Main {
	
	//static String great; 
	
	static StringBuffer great;
	
	public static void read(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        great = new StringBuffer();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            great = great.append(cadena);
        }
        b.close();        
    }


	public static void main(String[] args) throws FileNotFoundException, IOException {	
		
		//String word = "theprojectgutenbergebookof";
		/*String text = word.concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word)
				     .concat(word).concat(word).concat(word).concat(word);		
		String text2 = text.concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).
				       concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).
				       concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text).concat(text);
				       */
		SuffixTree st = new SuffixTree();	
		
		String path = "C:/Users/natto/Downloads/t24.txt";
		
		//st = (SuffixTree)st.ukkonen("xyzxyaxyz$");    
		//st = (SuffixTree)st.ukkonen("abcabxabcd");
		//st = (SuffixTree)st.ukkonen(text2); 
		
		read(path);
		
		//String greater = TextProcessing.clean("pizza chili").toString();
		
		st = (SuffixTree)st.ukkonen(great.toString()); 		
		st.convertToReal();		
		
		
		System.out.println(great.length());
	    String costs = st.getCosts(); 
	    
	    st.resetCounter();
		
	    System.out.println(costs);
		st.print();
		
		//System.out.println(st.search("abcd", new LinkedList<Integer>(),st.getRoot(), great));			
		
		
	}

}
