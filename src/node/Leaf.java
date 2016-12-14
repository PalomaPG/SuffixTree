package node;

import java.util.HashMap;
import java.util.LinkedList;

import suffixtree.AbsSuffixTree;
import suffixtree.SuffixTree;

public class Leaf extends Node {

	protected int position;
	
	public Leaf(){
		position = -1;
		st.counter_by_phase[st.fase]++;
		
	}
	
		
	public Leaf(int position, AbsSuffixTree stree){
		this.position = position;
		st = stree;
		st.counter_by_phase[st.fase] += 2;
		
	}
	
	public int getPosition(){
		st.counter_by_phase[st.fase]++;
		return this.position;
	}
	
	public void setPosition(int position){
		st.counter_by_phase[st.fase]++;
		this.position=position;
	}

	@Override
	public NotLeafNode getInitialNode() {	
		st.counter_by_phase[st.fase]++;
		return null;
	}

	@Override
	public Object[] searchFinalArc(int fase, String beta, Arc last) {		
		Object [] res = new Object[2];
		res[0] = last;
		res[1] = -2;	
		st.counter_by_phase[st.fase] += 3;
		
		if (last != null) st.setGamma(st.getText().substring(last.getLabel()[0], fase));		
		return res;					
	}

	@Override
	public void print() {	
		System.out.print("(" + position + ")");
	}
	
	public void labelPrint() {
		print();
	}
	
	@Override
	public void getLeavesValues(LinkedList<Integer> positions) {
		// TODO Auto-generated method stub
		positions.add(position);
		st.incrSearchCost();
	}
	
	public HashMap<Character, Arc> getChildren() {
		st.counter_by_phase[st.fase]++;
		return null;
	}
	
}
