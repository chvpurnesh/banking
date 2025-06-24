package Banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalance {

    public String checkBal(String accountnumber)
    {
        try (Connection conn = DBUtil.getConnection())
        {
            String sql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountnumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return String.valueOf(rs.getInt("balance"));
            } 
            else {
                return "Account not found";
            }
        }// try block end 
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error retrieving balance";
        }// catch block end
        
    }// checkbal method block end
}// check balance class block end





