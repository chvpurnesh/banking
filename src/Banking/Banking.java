package Banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Banking {
	public static void main(String args[]) {
		String accountfile = "config\\account.txt";
		int bal = 0;
		int sum = 0; 
		Scanner Sc = new Scanner(System.in);

		System.out.println("enter username:");
		String Username = Sc.nextLine();
 
		System.out.println("Enter Password:");
		String password = Sc.nextLine();
		

		System.out.println("enter account number:");
		String accountnumber = Sc.nextLine();

		Banking bsession = new Banking();

		while (true) {
			if (bsession.UserValidationCredantials(accountfile, Username, password, accountnumber) == true)
			{
				break;
			}// validateCredentials if block end
			else {
                 
				System.out.println("your credentials did not match please re enter your details");
				
				Sc = new Scanner(System.in);

				System.out.println("enter username:");
				Username = Sc.nextLine();

				System.out.println("Enter Password:");
				password = Sc.nextLine();

				System.out.println("enter account number:");
				accountnumber = Sc.nextLine();
			}// validate credentials else block end 

		}// validate credentials while loop end

		while (true) {
			System.out.println(
					"Welcome,please let us know what you want to perform: A)For Deposit B)For Balance checking");

			String option = Sc.nextLine();

			switch (option) {
			case "A":
				bsession.ValidateDepositAmount();
				/* if (bsession.depositAmount(accountfile, accountnumber, String.valueOf(depositAmount)))
				  {
                      System.out.println("Deposit successful! Amount deposited: " + depositAmount);
                  } else 
                  {
                      System.out.println("Deposit failed. Please try again.");
                  }
                  break;*/
				

			case "B": 
				
				try {

					CheckBalance c = new CheckBalance();
					String balance = c.checkBal(accountnumber, accountfile);
					System.out.println("your balance: "+balance);
					break;
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}//switch block end

				sum = 0;

			}// while loop end (for deposit and balance checking)
		
	}// main method block end

	private boolean depositAmount(String accountfile, String accountnumber ,String depositAmount ) 
	{
	
		try(BufferedReader reader = new BufferedReader(new FileReader(accountfile)))
		{
			StringBuffer fileContent = new StringBuffer();
			
			
			String line;
			boolean accountFound = false;
			
			while((line = reader.readLine()) != null)
			{
				String arr[] = line.split(",");
				
				if(arr.length >=4 && arr[2].equals(accountnumber))
				{
					try
					{
					int currentBalance = Integer.parseInt(arr[3]);
					int newBalance = currentBalance + Integer.parseInt(depositAmount);
					
					arr[3] = Integer.toString(newBalance);
					accountFound = true;
					}
					catch(NumberFormatException e)
					{
						System.out.println("invalid deposit amount. Please enter a numeric value.");
						return false;
					}
				}
				fileContent.append(String.join(",", arr)).append("\n");
			}
			if(!accountFound)
			{
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountfile)))
				{
					writer.write(fileContent.toString());
				}
				return true;
			}
				else 
				{
					System.out.println("account not found.");
				}
		}
			catch(IOException | NumberFormatException e)
			{
				System.out.println(" error");
			}
			
		     return false;
		
	}//

	public boolean UserValidationCredantials(String accountfile, String Username, String password,String accountnumber) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(accountfile));
			String line;

			while ((line = reader.readLine()) != null)
			{
				String arr[] = line.split(",");

				if (arr[0].equals(Username) && arr[1].equals(password) && arr[2].equals(accountnumber))
				{
					return true;
				}

			}
		} catch (FileNotFoundException e) {
			System.out.println(" Account file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading the account file.");
			e.printStackTrace();
		}
		return false;
	}
	public boolean ValidateDepositAmount()
	{
		int depositAmount = 0;
		boolean isValidAmount = false;
		
		while(!isValidAmount)
		{
			Scanner Sc = new Scanner(System.in);
		System.out.println("How much do you want to deposit:");
		String deposit = Sc.nextLine();

		try {
			if(deposit.length()>6)
			{
			depositAmount = Integer.parseInt(deposit);
			if(depositAmount <= 0)
			{
				System.out.println("please enter a positive number.");
			}// depositAmount if block end
			else {
				
				isValidAmount = true;
			}// validatedepositAmount else block end
			}// validatedeposit.length if block end
		}
			catch(NumberFormatException e)
		{
			System.out.println("enter only numaric values");
		} // depositamount try-catch block end
		}// depositamount while loop end 
		return true;
	}// validate depositAmount end 
}//main class end




