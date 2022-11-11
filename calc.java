import java.util.Scanner;
import java.lang.Math;

class IPFragmentationCalculator{
  public static void main(String[] args){
    Scanner myInput = new Scanner(System.in);
    int datasize;
    int header;
    int mtu;
    int i;
    
    System.out.print("Enter DATA SIZE: ");
    while (!myInput.hasNextInt()) 
    {
        myInput.nextLine();
        System.out.print("That's not a number! Try again: ");
    }
    datasize = myInput.nextInt();
    System.out.print("Enter MTU: ");
    while (!myInput.hasNextInt()) 
    {
        myInput.nextLine();
        System.out.print("That's not a number! Try again: ");
    }
    mtu = myInput.nextInt();

    System.out.print("Enter Header Size: ");
    Scanner sc = new Scanner(System.in);
    header = sc.nextInt();
    // datasize=datasize;
    int n = (int) Math.ceil((double)(datasize - header)/(mtu - header));    
    
    if (datasize >= mtu){
      System.out.println("\n" + n + " fragments were created:");
      System.out.println("----------------------------------------------");
      System.out.printf("%2s %20s %10s %10s", "#", "TOTAL LENGTH", "FLAG", "OFFSET");
      System.out.println();
      System.out.println("----------------------------------------------");
      int len=0;
      int count=0;
      for(i=1;i<=n-1;i++){
          
        int length = mtu-header;
        int k=0;
        while(k<length)
        {
          k=k+8;
        }
        // System.out.println("Value of K"+k);
        length=k-8;
        len=length;
        int flags =   1; 
        int offset = (length/8)*count;
        count++;
                
        
        System.out.format("%2d %20d %10d %10d", i, length, flags, offset);
        System.out.println();  
        System.out.println("----------------------------------------------");
      }
      // System.out.println("HIHIH"+len);
      int final_length = datasize - len*(n-1);
      int final_flags = 0;
      int final_offset = (len/8)*count;
      
      System.out.format("%2d %20d %10d %10d", i, final_length, final_flags, final_offset);
      System.out.println();
      System.out.println("----------------------------------------------");
      }else{        
        System.out.println("\nSince MTU > DATA SIZE, the packet moves on to the next encapsulation phase without fragmentation:");
        System.out.println("----------------------------------------------");
        System.out.printf("%2s %20s %10s %10s", "#", "TOTAL LENGTH", "FLAG", "OFFSET");
        System.out.println();
        System.out.println("----------------------------------------------");   
        System.out.format("%2d %20d %10d %10d", 1, datasize, 0, 0);
        System.out.println();  
        System.out.println("----------------------------------------------");  
      }
    myInput.close();
  }        
} 