package node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import suffixtree.SuffixTree;
import test.Test;

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
		
		String path = "input/t15.txt";
		
		//st = (SuffixTree)st.ukkonen("xyzxyaxyz$");    
		//st = (SuffixTree)st.ukkonen("abcabxabcd");
		//st = (SuffixTree)st.ukkonen(text2); 
		
		read(path);
		String text = "the";
		//String greater = TextProcessing.clean("pizza chili").toString();
		
		st = (SuffixTree)st.ukkonen("theanimalwastheretherefore"); 		
		st.convertToReal();		
		
		
		//System.out.println(great.length());
	    String costs = st.getCosts(); 
	    
	    st.resetCounter();
		System.err.println(st.search(text, new LinkedList<Integer>(), st.getRoot(), st.getText()));
	    //System.out.println(costs);
		//st.print();
		
		//System.out.println(st.search("abcd", new LinkedList<Integer>(),st.getRoot(), great));			
		
		
	}

}
