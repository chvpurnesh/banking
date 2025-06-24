package Banking;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String username, password, accountnumber;

        // Login loop
        while (true) {
            System.out.println("Enter username:");
            username = sc.nextLine();

            System.out.println("Enter password:");
            password = sc.nextLine();

            System.out.println("Enter account number:");
            accountnumber = sc.nextLine();

            Banking bsession = new Banking();
            if (bsession.UserValidationCredantials(username, password, accountnumber)) {
                break;
            } else {
                System.out.println("Your credentials did not match. Please try again.\n");
            }
        }// login while loop block end

        Banking bsession = new Banking();

        // Main menu loop
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("A) Deposit");
            System.out.println("B) Check Balance");
            System.out.println("Q) Quit");

            String option = sc.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    int depositAmt = bsession.ValidateDepositAmount();
                    if (bsession.depositAmount(accountnumber, depositAmt)) {
                        System.out.println("Deposit successful! Amount deposited: " + depositAmt);
                    } else {
                        System.out.println("Deposit failed. Please try again.");
                    }
                    break;

                case "B":
                	CheckBalance c = new CheckBalance();
                    String balance = c.checkBal(accountnumber);
                    System.out.println("Your balance: " + balance);
                    break;

                case "Q":
                    System.out.println("Thank you for using our banking system.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please choose A, B, or Q.");
            }//switch case block end
        }
    }//main menu while loop block end

    // Validate login credentials using JDBC
    public boolean UserValidationCredantials(String username, String password, String accountnumber) 
    {
        try (Connection conn = DBUtil.getConnection())
        {
            String sql = "SELECT * FROM accounts WHERE username = ? AND password = ? AND account_number = ?";
            var ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, accountnumber);
            var rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }// uservalidatecrendantials method block end

    // Deposit logic using JDBC
    private boolean depositAmount(String accountnumber, int depositAmount)
    {
        try (Connection conn = DBUtil.getConnection()) 
        {
            String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            var ps = conn.prepareStatement(sql);
            ps.setInt(1, depositAmount);
            ps.setString(2, accountnumber);
            int updated = ps.executeUpdate();
            return updated > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }// depositAmount  method  block end

  
    // Validate deposit amount
    public int ValidateDepositAmount() {
        Scanner Sc = new Scanner(System.in);
        int depositAmount = 0;

        while (true) {
            System.out.println("How much do you want to deposit:");
            String deposit = Sc.nextLine();

            try {
                depositAmount = Integer.parseInt(deposit);
                if (depositAmount > 0) {
                    break;
                } else {
                    System.out.println("Enter a positive amount.");
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("Enter numeric values only.");
            }
        }// inner while loop block end 
        return depositAmount;
    }// validatedepositamount method block end 
    
}// mian banking class end

class DBUtil {
    public static Connection getConnection() throws SQLException 
    {
        String url = "jdbc:mysql://localhost:3306/banking"; 
        String user = "root"; 
        String pass = "venkatpurnesh"; 

        return DriverManager.getConnection(url, user, pass);
    }
}



