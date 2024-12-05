package practise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Banking {
	public static void main(String args[]) {
		String file = "C:\\Users\\purnesh\\Desktop/account.txt";
		int bal=0;
		int sum = 0;
		Scanner Sc = new Scanner(System.in);
		System.out.println("enter username:");
		String Username = Sc.nextLine();
		System.out.println("Enter Password:");
		String password = Sc.nextLine();
		System.out.println("enter account number:");
		String num = Sc.nextLine();

		while (true) {
			System.out.println(
					"Welcome,please let us know what you want to perform: A)For Deposit B)For Balance checking");
			String option = Sc.nextLine();

			switch (option) {
			case "A":
				System.out.println("How much do you want to deposit:");
				String deposit = Sc.nextLine();

				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					StringBuilder modifiedData = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						modifiedData.append(line).append("\n");
					}
					modifiedData.append(deposit);

					try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
						writer.write(modifiedData.toString());
					}

					System.out.println("succesfully deposited");

				} catch (IOException e) {
					System.out.println("enter only numaric values");

					e.printStackTrace();
				}
				break;

			case "B": {
				try {
					CheckBalance.checkBal(sum, bal, file);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

				sum = 0;

			}
		}
	}
}
