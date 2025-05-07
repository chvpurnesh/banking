package Banking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckBalance{
	

	public  String checkBal(String accountnumber,String file) throws NumberFormatException, IOException
	{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			 String line;
			  
			 while ((line = reader.readLine()) != null)
			 {
				
				String arr[] = line.split(",");
				if(arr[2].equals(accountnumber))
				{
					return arr[3];
				}
			 }
			 return " ";
			
	}
}





