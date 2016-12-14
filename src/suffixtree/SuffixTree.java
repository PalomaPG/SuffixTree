package suffixtree;

import java.util.HashMap;
import java.util.LinkedList;

import node.Arc;
import node.InnerNode;
import node.Leaf;
import node.Node;
import node.NotLeafNode;
import node.Root;
import node.SuffixLink;

public class SuffixTree extends AbsSuffixTree {
	
	
	
	public SuffixTree(){
		root = new Root(this);	
	}	
	
	
	public int extensionByRules(int i, int j, NotLeafNode ini, String s) {		
		// C�lculo de BETA
		String beta;		
		if (j >= i) beta = "";
		else if (s.equals(text)) beta = s.substring(j, i);	
		else beta = s;	
		counter_by_phase[fase]++;
		//System.out.println("beta : " + beta);		
		
		if (beta == "") {
			last = ini;
			counter_by_phase[fase]++;
			
			if (!ini.getChildren().containsKey(text.charAt(i))) {
				//System.out.println("Se crea un nuevo arco1");
				Arc nuevo = new Arc(ini, new Leaf(j, this), i, -1);				
				
				ini.addChild(nuevo);
			}
			else {
				//System.out.println("No se hace nada");
				return 1;
			}
			return 0;
		}
		
		Object [] found = ini.searchFinalArc(i, beta, null);	
		Arc edge = (Arc)found[0];	
		counter_by_phase[fase]++;
		
		int last_pos = (int)found[1];	
		counter_by_phase[fase]++;
		
		//Regla 1. Beta termina en una hoja
		if (last_pos == -2) {	
			//System.out.println("beta termina en una hoja");						
		}
		
	    //// Estamos en la ra�z � Regla 2.1 Beta termina en un nodo interno
		else if (edge == null || last_pos == -1) {			
			NotLeafNode node;			
			if (edge == null) { //  || edge.getChild() instanceof Leaf) {
				//System.out.println("Estamos en la ra�z");					
				node = ini; 
				last = ini;		
				counter_by_phase[fase] += 2;
				
			}
			else {
				//System.out.println("beta termina en un nodo interno");
				node = (NotLeafNode)edge.getChild();
				last = edge.getParent();	//////////////////////////////////////////////////////////
			}
			HashMap<Character, Arc> chldrn = node.getChildren();			
			if (chldrn.containsKey(text.charAt(i))) {
				// Regla 3. Si existe un camino que comienza con s[i]. No se hace nada
				//System.out.println("No se hace nada");	
				//jL -= 1;
				//System.out.println("REGLA3");
				return 1;
			}
			// Si no existe un camino que comienza con s[i], se crea un nuevo arco
			else {
				//System.out.println("Se crea nuevo arco2");
				Arc nuevo = new Arc(node, new Leaf(j, this), i, -1);
				node.addChild(nuevo);					
			}					
		}
		
		// Regla 2.2 Beta se termina a mitad de un arco
		else {				
			//System.out.println("beta termina en mitad de un arco");				
			if (text.charAt(edge.getLabel()[0] + last_pos) == text.charAt(i)) {
				counter_by_phase[fase]++;
				last = edge.getParent();	
				return 1;
			}
			else {
				//System.out.println("Se crea nodo interno");
				NotLeafNode parent = edge.getParent();	
				
				// Se crea un nodo interno.
				InnerNode new_node = new InnerNode(this);
				new_node.setName(++num);
				
				// Se elimina el arco actual
				parent.removeChild(edge);					
				
				// Se crea nuevo arco con el key del arco actual, pero s�lo hasta donde lleg� beta
				Arc beta_edge = new Arc(parent, new_node, edge.getLabel()[0], edge.getLabel()[0] + last_pos - 1);							
				parent.addChild(beta_edge);				
				
				// Se crea nuevo arco con el key del arco actual con la parte que no se recorri�
				Arc rest = new Arc(new_node, edge.getChild(), edge.getLabel()[0] + last_pos, edge.getLabel()[1]);
				new_node.addChild(rest);		
				
				// Se crea otro arco, hijo del nuevo nodo interno, con la letra que se agrega en esta fase
				Arc new_edge = new Arc(new_node, new Leaf(j, this), i, -1);
				new_node.addChild(new_edge);
				
				// El nuevo nodo parte con un suffix link apuntando a la ra�z
				new_node.setSuffixLink(new SuffixLink(root));
				
				last = new_node;
				counter_by_phase[fase]++;
				
				// Si se sigui� la regla 2.2 en la extensi�n anterior
				if (w != null) {
					//System.out.println("Se crea SuffixLink entre " + w.getName() + " y " + new_node.getName());					
					w.setSuffixLink(new SuffixLink(new_node));					
				}				
				w = new_node;				
				count_w = 0;	
				counter_by_phase[fase] += 2;
				
			}				
		}		
		//jL = j;
		return 0;		
	}
	
public LinkedList<Integer> search(String s, LinkedList<Integer> positions, Node root, String text){
		
		HashMap<Character, Arc> children_ = root.getChildren();
		//System.out.println(children_);
		Arc arc = children_.get(s.charAt(0));
		//System.out.println(arc);
		this.incrSearchCost();
		
		if (arc!= null){
			
			String arc_s = arc.getKeyByIndex(text);
			Node next = arc.getChild();
			
			if (s.equals(arc_s)){
				
				if(next.getPosition()>-1){
					positions.add(next.getPosition());
					this.incrSearchCost();
					return positions;
				}
				else {	
					next.getLeavesValues(positions);
					return positions;
				}
			}
			
			else{
				/*The string s is larger than the arc stored string*/
				if(s.length()>arc.getKey().length()){
					
					if(s.indexOf(arc.getKey())==0){
						String new_s = s.substring(arc.getKeyByIndex(text).length(), s.length());
						return search(new_s, positions, next,text);
					}
					
					else return positions;
				}
				else{
					/*The string s is shorter than the arc stored string*/
					if(arc.getKey().toString().indexOf(s)==0){
						next.getLeavesValues(positions);
						this.incrSearchCost();
						return positions;
					}
					/*The string s is differs in at least one character*/
					else return positions;
				}		
			}
		}
				
		return positions;
	}
	
	public static void main (String [] args){
		
		SuffixTree st = new SuffixTree();
		Root root=st.getRoot();
		Arc arc1, arc2, arc3, arc4, arc5, arc6, arc7, arc8, arc9, arc10, arc11;
		
		root = new Root();
		/*
		arc1= new Arc()
		arc2= new Arc(root, new InnerNode(st), "A");
		arc3= new Arc(root, new Leaf(6), "CA$");
		arc4= new Arc(root, new InnerNode(st), "GA");
		arc5= new Arc(root, new Leaf(2), "TAGACA$");
		
		System.out.println(arc5);
		root.addChild('$', arc1);
		root.addChild('A', arc2);
		root.addChild('C', arc3);
		root.addChild('G', arc4);
		root.addChild('T', arc5);
		System.out.println(root.getChildren());

		arc6 = new Arc((InnerNode)arc2.getChild(), new Leaf(7), "$");
		arc7 = new Arc((InnerNode)arc2.getChild(), new Leaf(5), "CA$");
		arc8 = new Arc((InnerNode)arc2.getChild(), new Leaf(3), "GACA$");
		arc9 = new Arc((InnerNode)arc2.getChild(), new Leaf(1), "TAGACA$");
		
		
		
		((NotLeafNode)arc2.getChild()).addChild('$', arc6);
		((NotLeafNode)arc2.getChild()).addChild('C', arc7);
		((NotLeafNode)arc2.getChild()).addChild('G', arc8);
		((NotLeafNode)arc2.getChild()).addChild('T', arc9);
		System.out.println(((InnerNode)arc2.getChild()).getChildren());
		
		arc10 = new Arc((NotLeafNode)arc4.getChild(), new Leaf(4), "CA$");
		arc11 = new Arc((NotLeafNode)arc4.getChild(), new Leaf(0), "TAGACA$");
		
		
		((NotLeafNode)arc4.getChild()).addChild('C', arc10);
		((NotLeafNode)arc4.getChild()).addChild('T', arc11);
		System.out.println(((InnerNode)arc4.getChild()).getChildren());
		st.setRoot(root);
		
		System.out.println(st.search("A", new LinkedList<Integer>(), root));
		System.out.println(st.search("GA", new LinkedList<Integer>(), root));
		System.out.println(st.search("$", new LinkedList<Integer>(), root));
		System.out.println(st.getRoot().getChildren());
		System.out.println(root.getChildren());*/
	}


	
	
	
}
