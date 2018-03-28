/* Created by: Samuel Schwartz - n00448518
 * Last Updated 8:01 PM 01/20/2018
 *
 *
 *
 *
 *
 *
 *
 *
 */
 
 
class HighArray {
   private long[] a;
   private int nElems;
   private int switchCount = 0;
   
   public HighArray(int max){
      a= new long[max];
      nElems = 0;
   }   
   
   public boolean find(long searchKey){
      int j;
      for(j=0; j<nElems; j++)
         if(a[j] == searchKey)
            break;
      if (j == nElems)
         return false;
      else 
         return true;
   }
   
   public void insert(long value){
      a[nElems] = value;
      nElems++;
   }
   
   public boolean delete(long value){
      int j;
      for(j=0; j<nElems; j++)
         if(value == a[j])
            break;
      if(j == nElems)
         return false;
      else{
         for(int k=j; k<nElems; k++)
            a[k] = a[k+1];
            nElems--;
            return true;
      }
   }
   
   public void display(){
      if (nElems>0)
         for(int j=0; j<nElems; j++)
            System.out.print(a[j] + " ");
      else
         switchCount++;
         switch(switchCount){
            case 1: System.out.print("Array has not been filled!\n(Did you comment out the .insert() \nsection of main in the source code?)");
                    break;
            case 2: System.out.print("Still can't display...\nan empty array.");
                    switchCount = 0;
                    break;
         } 
      
      
      System.out.println("");
   }
   
   public long getMax(){
      long currMax = a[0];
      for(int j=1; j<nElems; j++)
         if(a[j] > currMax)
            currMax = a[j];
      if (nElems == 0)
         return -1;
      return currMax;
   }
   
   public long getMin(){
      long currMin = a[0];
      for(int j=1; j<nElems; j++)
         if(a[j] < currMin)
            currMin = a[j];
      if (nElems == 0)
         return -1;      
      return currMin;
   }
}

class n00448518 {
   public static void main(String[] args){
      int maxSize = 100;
      HighArray arr;
      arr = new HighArray(maxSize);
   
      arr.insert(77);
      arr.insert(99);
      arr.insert(44);
      arr.insert(55);
      arr.insert(22);
      arr.insert(88);
      arr.insert(11);
      arr.insert(00);
      arr.insert(66);
      arr.insert(33);
      
      arr.display();
      
      int searchKey = 35;
      if(arr.find(searchKey))
         System.out.println("Found " + searchKey);
      else 
         System.out.println("Can't find " + searchKey);
         
      arr.delete(00);
      arr.delete(55);
      arr.delete(99);
      
      arr.display();
      
      long y = arr.getMax();
      long z = arr.getMin();
      
      if(z == -1 && y == -1)
         System.out.println("An empty set has no maximum, \nnor a minimum...");
      else{ 
         System.out.println(y);
         System.out.println(z);
      }
   }
}   