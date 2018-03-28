import java.io.*;
import java.util.*;

public class n00448518{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		
		while(true){
			
			System.out.println("Enter 3 integers (or enter stop to stop): ");

			String[] str = sc.nextLine().split("[ \t]+");

			if (str[0].equals("stop"))
				break;
			else{
				LinkList theList = new LinkList();
				ListIterator iter1 = theList.getIterator();

				long size = Long.parseLong(str[0]);
				int start = Integer.parseInt(str[1]);
				int jump = Integer.parseInt(str[2]);
				
				//System.out.println(size+" "+start+" "+jump);	
						
				for(int i=1; i<=size; i++){
					iter1.insertAfter(i);
					if(i==size)
						iter1.current.next = theList.first;

				}	
				
				iter1.current = theList.first;
				
				//iter1.current.displayLink();
				
				for(int i=1; i<start; i++){
					iter1.nextLink();
					//iter1.current.displayLink();
				}

				while(!(iter1.current.next.value == iter1.current.value)){
					for(int i=0; i<jump; i++){
						iter1.nextLink();
					}
					//iter1.current.displayLink();
					iter1.deleteCurrent();
				}	

				iter1.current.displayLink();						
			}

		}
	}
}

class Link{

	public long value;
	public Link next;

	public Link(long value){
		this.value = value;
	}

	public void displayLink(){
		System.out.println(value);
	}
}

class LinkList{

	public Link first;

	public LinkList(){
		first = null;
	}

	public Link getFirst(){
		return first;
	}
	
	public void setFirst(Link f){
		first = f;
	}

	public boolean isEmpty(){
		return first==null;
	}
	
	public ListIterator getIterator(){
		return new ListIterator(this);
	}
	
	public void displayList(){
		Link current = first;
		while(current != null){
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

class ListIterator{
	
	public Link current;
	public Link previous;
	public LinkList ourList;

	public ListIterator(LinkList list){
		ourList = list;
		reset();
	}

	public void reset(){
		current = ourList.getFirst();
		previous = null;
	}
	
	public boolean atEnd(){
		return (current.next==null);
	}

	public void nextLink(){
		previous = current;
		current = current.next;
	}

	public Link getCurrent(){
		return current;
	}

	public void insertAfter(long value){
		Link newLink = new Link(value);

		if(ourList.isEmpty()){
			ourList.setFirst(newLink);
			current = newLink;
		}
		else {
			newLink.next = current.next;
			current.next = newLink;
			nextLink();
		}
	}

	public void insertBefore(long value){
		Link newLink = new Link(value);
		
		if(previous==null){
			newLink.next = ourList.getFirst();
			ourList.setFirst(newLink);
			reset();
		}
		else{
			newLink.next = previous.next;
			previous.next = newLink;
			current = newLink;
		}
	}

	public long deleteCurrent(){
		long value = current.value;
		
		if(previous==null){
			ourList.setFirst(current.next);
			reset();
		}

		else{
			previous.next = current.next;
			if( atEnd())
				reset();
			else
				current = current.next;
		}
		
		return value;
	}
}


