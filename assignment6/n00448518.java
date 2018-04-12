import java.util.*;
import java.io.*;
import java.lang.*;

public class n00448518{
	
	public static void main (String args[]) throws Exception{
		Scanner sc = new Scanner(new File(args[0]));
		ArrayList<String> fileA = new ArrayList<String>();
	
		while(sc.hasNext()){
			fileA.add(sc.next());
		}	

		int size = nextPrimeSieve(fileA.size());
		
    		System.out.println(size);
	
		HashTable A = new HashTable(size);
		HashTable B = new HashTable(size);
	
		for(String i: fileA){
			A.insertLin(new DataItem(i));
			B.insertQuad(new DataItem(i));
		}

		System.out.println("\n\n\n\t\t              |------------------------------------------|             ");
		System.out.println("\t\t              |      Hash Table with Linear Probing      |");
		A.displayHashTable();

		System.out.println("\n\n\n\t\t              |------------------------------------------|             ");
		System.out.println("\t\t              |     Hash Table with Quadratic Probing    |");
		B.displayHashTable();

		sc = new Scanner(new File(args[1]));
		ArrayList<String> fileB = new ArrayList<String>();

		while(sc.hasNext()){
			fileB.add(sc.next());
		}
		
		System.out.println("\n\n\n|------------------------------------------|");
		System.out.println("| Find Results with Linear Probing         |");
		findResults(A, fileB, true);

		System.out.println("\n\n\n|------------------------------------------|");
		System.out.println("| Find Results with Quadratic Probing      |");
		findResults(B, fileB, false);
		
		sc = new Scanner(new File(args[2]));
		ArrayList<String> fileC = new ArrayList<String>();

		while(sc.hasNext()){
			fileC.add(sc.next());
		}

		System.out.println("\n\n\n|------------------------------------------|");
		System.out.println("| Deletion Results with Linear Probing     |");
		deleteResults(A, fileC, true);
		
		System.out.println("\n\n\n|------------------------------------------|");
		System.out.println("| Deletion Results with Quadratic Probing  |");
		deleteResults(B, fileC, false);
	}
	
	public static void deleteResults(HashTable ht, ArrayList<String> file, boolean lin){		
		float countFail = 0; 
		float countSucc = 0;
		int sumFail = 0;
		int sumSucc = 0;
		DataItem temp;

		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");
		System.out.println("|                 String                   | Success | Failure |Probe Length (Success)|Probe Length (Failure)|");
		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");

		for(String i: file){
			if(lin)
				temp = ht.deleteLin(i);
			else
				temp = ht.deleteQuad(i);

			

			if(temp.getKey().equals("")){
				 
				System.out.println(String.format("| %-40s ", i) +     "|         |   Yes   |                      |          " + temp.getProbes() + "           |");
				countFail++;
				sumFail += temp.getProbes();
			}
			else{	
				System.out.println(String.format("| %-40s ", i) +     "|   Yes   |         |          " + temp.getProbes() + "           |                      |");
				countSucc++;
				sumSucc += temp.getProbes();
			}
		}
		String avgSucc = ((countSucc>0)?String.format("%4.2f",sumSucc/countSucc):"     ");
		String avgFail = ((countFail>0)?String.format("%5.2f",sumFail/countFail):"     ");

		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");
		System.out.println("|                                                           Avg Success: " + avgSucc + "     Avg Failure: " + avgFail + "         |");
		System.out.println("|------------------------------------------------------------------------------------------------------------|");
	}

	public static void findResults(HashTable ht, ArrayList<String> file, boolean lin){		
		float countFail = 0; 
		float countSucc = 0;
		int sumFail = 0;
		int sumSucc = 0;
		DataItem temp;

		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");
		System.out.println("|                 String                   | Success | Failure |Probe Length (Success)|Probe Length (Failure)|");
		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");

		for(String i: file){
			if(lin)
				temp = ht.findLin(i);
			else
				temp = ht.findQuad(i);

			if(temp.getKey().equals("")){ 
				System.out.println(String.format("| %-40s ", i) +     "|         |   Yes   |                      |          " + temp.getProbes() + "           |");
				countFail++;
				sumFail += temp.getProbes();
			}
			else{	
				System.out.println(String.format("| %-40s ", i) +     "|   Yes   |         |          " + temp.getProbes() + "           |                      |");
				countSucc++;
				sumSucc += temp.getProbes();
			}
		}
		String avgSucc = ((countSucc>0)?String.format("%4.2f",sumSucc/countSucc):"     ");
		String avgFail = ((countFail>0)?String.format("%5.2f",sumFail/countFail):"     ");

		System.out.println("|------------------------------------------|---------|---------|----------------------|----------------------|");
		System.out.println("|                                                           Avg Success: " + avgSucc + "     Avg Failure: " + avgFail + "         |");
		System.out.println("|------------------------------------------------------------------------------------------------------------|");
	}

	public static int nextPrime(int num){
		while(true){
			boolean isPrime = true;
			num++;
			int sqt = (int)Math.sqrt(num);

			for(int i = 2; i<sqt; i++){
				if (num % i == 0){
					isPrime = false;
					break;
				}
			}

			if(isPrime)
				return num;
		}
	}

	public static int nextPrimeSieve(int n){
		int primes[] = new int[4*n];
	
		for( int i = 0; i < 4*n; i++)
			primes[i] = 1;
		for(int i = 2; i*i < 4*n; i++){
			if(primes[i] == 1){
				if (i > 2*n) return i;
				for(int j = 2; i*j < 4*n; j++)
					primes[i*j] = 0;
			}
		}
		return nextPrime(n*2);
	}
}

class DataItem{
	private String iData;
	private int probes;

	public DataItem (String str){
		iData = str;
		probes = 0;
	} 

	public DataItem (String str, int pro){
		iData = str;
		probes = pro;
	}

	public String getKey(){
		return iData;
	}

	public int getProbes(){
		return probes;
	}

	public void setProbes(int i){
		probes = i;
	}
}

class HashTable{
	private DataItem hashArray[];
	private int arraySize;
	private DataItem nonitem;

	public HashTable(int size){
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonitem = new DataItem ("");
	}

	public int hashFunc(String key){
		int hashVal = 0;
		//for(int j=key.length()-1; j!=0; j--){
		for(int j=0; j<key.length(); j++){
			int letter = key.charAt(j);
//			System.out.println(letter);
			hashVal = (hashVal * 26 + letter) % arraySize;
		}
		return hashVal;
	}
	
	public void insertLin(DataItem item){
		String key = item.getKey();
		int hashVal = hashFunc(key);
		int i = 0;		
		//System.out.println(hashVal);	
		while(hashArray[hashVal] != null && !hashArray[hashVal].getKey().equals("")){
			i++;
			hashVal++;
			hashVal %= arraySize;
		}
		hashArray[hashVal] = item;
		hashArray[hashVal].setProbes(i+1);
	}

	public void insertQuad(DataItem item){
		String key = item.getKey();
		int hashVal = hashFunc(key);
		int i = 0, tempVal = hashVal;
		while(hashArray[tempVal] != null && !hashArray[tempVal].getKey().equals("")){
			i++;
			tempVal = hashVal + i*i;
			tempVal %= arraySize;
		}
		
		hashVal = tempVal;
		hashArray[hashVal] = item;
		hashArray[hashVal].setProbes(i+1);
	}

	public DataItem deleteLin(String key){
		int hashVal = hashFunc(key);
		int i = 0;

		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getKey().equals(key)){
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonitem;
				//temp.setProbes(i-1);
				return temp;
			}
			i++;
			hashVal++;
			hashVal %= arraySize;
		}

		return new DataItem("", i+1);
	}

	public DataItem deleteQuad(String key){
		int hashVal = hashFunc(key);
		//System.out.println(hashVal);
		int i = 0, orig = hashVal;;

		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getKey().equals(key)){
				DataItem temp = hashArray[hashVal];
				hashArray[hashVal] = nonitem;
				//temp.setProbes(i-1);
				//System.out.println(hashVal);				
				return temp;
			}
			i++;
			hashVal = orig + i*i;
			hashVal %= arraySize;
		}
		//System.out.println(hashVal);
		return new DataItem("", i+1);
	}

	public DataItem findLin(String key){
		int hashVal = hashFunc(key);
		int i = 0;

		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getKey().equals(key))
				return hashArray[hashVal];
			i++;
			hashVal++;
			hashVal %= arraySize;
		}
		return new DataItem("", i+1);
	}

	public DataItem findQuad(String key){
		int hashVal = hashFunc(key);
		int i = 0, orig = hashVal;

		while(hashArray[hashVal] != null){
			if(hashArray[hashVal].getKey().equals(key))
				return hashArray[hashVal];
			i++;
			hashVal = orig + i*i;
			hashVal %= arraySize;
		}
		return new DataItem("", i+1);
	}

	public void displayHashTable(){
		int sum = 0;
		float count = 0;

		System.out.println("\t\t      |-------|------------------------------------------|------------|");
		System.out.println("\t\t      | Index |                  String                  |Probe Length|");
		System.out.println("\t\t      |-------|------------------------------------------|------------|");

		for(int j = 0; j < arraySize; j++){
			if (hashArray[j] != null && !hashArray[j].getKey().equals("")){
				System.out.println("\t\t      | " + String.format("%5d",j) + " | " + String.format("%-40s", hashArray[j].getKey()) + " |      " +  hashArray[j].getProbes() + "     |");
				sum += hashArray[j].getProbes();
				count++;
			}
		}

		System.out.println("\t\t      |-------|------------------------------------------|------------|");
		System.out.println("\t\t      |                                        Average Probe: " + String.format("%5.2f",sum/count) + "   |");
		System.out.println("\t\t      |---------------------------------------------------------------|");
			
	}

	public int getSize(){
		return arraySize;
	}
}
