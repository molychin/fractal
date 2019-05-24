package fractal.sunbowen.molychin;

import java.util.LinkedList;

public class LLStack {
	private LinkedList<Object> list=new LinkedList<Object>();
	
	public LLStack(){
	}
	
	public void clear(){
		list.clear();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public Object topElement(){
		return list.getLast();
	}
	
	public Object pop(){
		return list.removeLast();
	}
	
	public void push(Object element){
		list.add(element);
	}
	
	public String toString(){
		return list.toString();
	}
	
	
}
