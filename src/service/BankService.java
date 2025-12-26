package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import exception.InsufficientBalanceException;

public class BankService {

    // Deposit money
	public void deposit(int accountNo, double amount) throws Exception {

	    if (amount <= 0) {
	        throw new IllegalArgumentException("❌ Amount must be greater than 0");
	    }

	    if (!accountExists(accountNo)) {
	        throw new IllegalArgumentException("❌ Account does not exist");
	    }

	    Connection con = DBConnection.getConnection();

	    PreparedStatement ps = con.prepareStatement(
	            "UPDATE accounts SET balance = balance + ? WHERE account_no = ?");
	    ps.setDouble(1, amount);
	    ps.setInt(2, accountNo);

	    ps.executeUpdate();
	    con.close();
	}

    // Withdraw money
	public void withdraw(int accountNo, double amount)
	        throws Exception {

	    if (amount <= 0) {
	        throw new IllegalArgumentException("❌ Amount must be greater than 0");
	    }

	    if (!accountExists(accountNo)) {
	        throw new IllegalArgumentException("❌ Account does not exist");
	    }

	    Connection con = DBConnection.getConnection();

	    PreparedStatement check = con.prepareStatement(
	            "SELECT balance FROM accounts WHERE account_no = ?");
	    check.setInt(1, accountNo);

	    ResultSet rs = check.executeQuery();

	    if (rs.next()) {
	        double balance = rs.getDouble("balance");
	        if (balance < amount) {
	            con.close();
	            throw new exception.InsufficientBalanceException(
	                    "❌ Insufficient balance");
	        }
	    }

	    PreparedStatement ps = con.prepareStatement(
	            "UPDATE accounts SET balance = balance - ? WHERE account_no = ?");
	    ps.setDouble(1, amount);
	    ps.setInt(2, accountNo);

	    ps.executeUpdate();
	    con.close();
	}


    // Transfer money
	public void transfer(int fromAcc, int toAcc, double amount)
	        throws Exception {

	    if (fromAcc == toAcc) {
	        throw new IllegalArgumentException(
	                "❌ Cannot transfer to the same account");
	    }

	    if (!accountExists(fromAcc) || !accountExists(toAcc)) {
	        throw new IllegalArgumentException(
	                "❌ One or both accounts do not exist");
	    }

	    withdraw(fromAcc, amount);
	    deposit(toAcc, amount);
	}

    
    private boolean accountExists(int accountNo) throws Exception {

        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT account_no FROM accounts WHERE account_no = ?");
        ps.setInt(1, accountNo);

        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();

        con.close();
        return exists;
    }

}
