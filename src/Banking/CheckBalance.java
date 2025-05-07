package Banking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckBalance 
{
	

	public static  String checkBal(int sum,int bal,String file) throws NumberFormatException, IOException {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			 String line;
			  
			 while ((line = reader.readLine()) != null)
			 {
				
				 bal = Integer.parseInt(line);
				 sum = sum + bal; 
			 }
			 if(sum == 0)
			 {
				 System.out.println("There is no deposit for this account");
			 }
			 System.out.println("your balance is "+sum);
			return String.valueOf(sum);
	}
}



