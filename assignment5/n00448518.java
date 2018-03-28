import java.util.*;
import java.io.*;



public class n00448518
{
	public static String codes[] = new String[7];

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(new File(args[0]));
		Tree forest[] = new Tree[7];
		forest[0] = new Tree(new Node('A'));
		forest[1] = new Tree(new Node('B'));
		forest[2] = new Tree(new Node('C'));
		forest[3] = new Tree(new Node('D'));
		forest[4] = new Tree(new Node('E'));
		forest[5] = new Tree(new Node('F'));
		forest[6] = new Tree(new Node('G'));

		sc.useDelimiter("");
		while(sc.hasNext())
		{
			char curChar = sc.next().charAt(0);
			
			switch(curChar)
			{

				case 'A': forest[0].root.value++;
					  break;
				case 'B': forest[1].root.value++;
					  break;
				case 'C': forest[2].root.value++;
					  break;
				case 'D': forest[3].root.value++;
					  break;
				case 'E': forest[4].root.value++;
					  break;
				case 'F': forest[5].root.value++;
					  break;
				case 'G': forest[6].root.value++;
					  break;
			}
		}

		NodeQue theNQ = new NodeQue();
		
		for(int i=0; i<7; i++)
			theNQ.insert(forest[i].root);
		
		Tree huffTree = new Tree(makeHuffTree(theNQ));
		getCodes(huffTree.root, "");	
		String bitStr = "";
		bitStr = makeBitStr(huffTree, args[0]); 

		Scanner input = new Scanner(System.in);
		char choice;

		do	
		{
			System.out.print("\nPlease enter one of the following characters (a,b,c,d,q):");
			System.out.print("\na: display tree\nb: make codes\nc: encode the file");
			System.out.print("\nd: decode\nq: quit program");
			System.out.print("\n\nEnter your choice: ");			

			choice = input.next().charAt(0);
			System.out.println();		
			switch(choice)
			{
	
				case 'a': huffTree.displayTree();
					  break;
				case 'b': for(int i = 0; i <7; i++)
						System.out.println((char)('A'+i) + ": " + codes[i]);
					  break;
				case 'c': printEncoded(bitStr);	
					  break;
				case 'd': decode(huffTree, bitStr);
					  break;	
			}
		}while(choice != 'q');
	} 
	
	public static Node makeHuffTree(NodeQue theNQ)
	{
		for(int i = 0; i<6; i++)
		{
			Node templeft = theNQ.remove();
			Node tempright = theNQ.remove();
			Node newnode = new Node('-');

			newnode.value = templeft.value + tempright.value;
			newnode.left = templeft;
			newnode.right = tempright;

			theNQ.insert(newnode);
		}
		return theNQ.remove();
	}

	public static void getCodes(Node localRoot, String str)
	{
		if(localRoot.key != '-')
			codes[localRoot.key-'A'] = str;
		if(localRoot.key == '-')
		{
			getCodes(localRoot.left, str + "0");
			getCodes(localRoot.right, str + "1");
		}
	}

	public static void printEncoded(String bitStr)
	{
		for(int i=1; i<bitStr.length()+1; i++)
		{
			System.out.print(bitStr.charAt(i-1));
			if(i%8 == 0)
				System.out.print(" ");
			if(i%24 == 0)
				System.out.println();
		}
		System.out.println();
	}

	public static String makeBitStr(Tree huffTree, String file) throws Exception
	{
		String bitStr = "";
		Scanner sc = new Scanner(new File(file));
		sc.useDelimiter("");
		while(sc.hasNext())
		{
			char curChar = sc.next().charAt(0);
			
			switch(curChar)
			{

				case 'A': bitStr += codes[0];
					  break;
				case 'B': bitStr += codes[1];
					  break;
				case 'C': bitStr += codes[2];
					  break;
				case 'D': bitStr += codes[3];
					  break;
				case 'E': bitStr += codes[4];
					  break;
				case 'F': bitStr += codes[5];
					  break;
				case 'G': bitStr += codes[6];
					  break;
			}
			
		}

		return bitStr;
	}

	public static void decode(Tree huffTree, String bitStr)
	{
		Node current = huffTree.root;
		
		for(int i=0; i<bitStr.length(); i++)
		{	
			if(bitStr.charAt(i) == '0')
				current = current.left;
			else
				current = current.right;
			if(current.key != '-')
			{
				System.out.print(current.key);
				current = huffTree.root;
			}
		}
		System.out.println();
	}		
}

class Tree
{
	Node root;
	
	public Tree(Node rt)
	{
		root = rt;
	}

	public void displayTree()
	{
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................................");
		while(isRowEmpty == false)
		{
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;

			for(int j = 0; j<nBlanks; j++)
				System.out.print(' ');
			
			while(globalStack.empty()==false)
			{	
				Node temp = (Node)globalStack.pop();
				if(temp!=null)
				{	
					if(temp.key == '-') System.out.print(temp.value);
					else System.out.print(temp.key);
					localStack.push(temp.left);
					localStack.push(temp.right);

					if(temp.left != null || temp.right != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j = 0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			nBlanks /= 2;
			while(localStack.empty()==false)
				globalStack.push(localStack.pop());
		}
		System.out.println("......................................................................");
	}
}

class Node
{
	public char key;
	public int value;
	public Node left;	
	public Node right;

	public Node(char ch)
	{
		key = ch;
		value = 0;
	}
}

class NodeQue
{
	private Node[] queArray;
	private int nItems;

	public NodeQue()
	{
		queArray = new Node[7];
		nItems = 0;
	}
	
	public void insert(Node item)
	{
		int j;

		if(nItems==0)
			queArray[nItems++]=item;
		else
		{
			for(j=nItems-1; j>=0; j--)
			{
				if(item.value > queArray[j].value)
					queArray[j+1] = queArray[j];
				else
					break;
			}
			queArray[j+1] = item;
			nItems++;
		}
	}

	public Node remove()
	{
		return queArray[--nItems];
	}
}
