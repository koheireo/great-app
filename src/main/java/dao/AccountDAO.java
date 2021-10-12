package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	String url = "jdbc:postgresql://localhost:5432/sukkiriShop";
	String user = "postgres";
	String password = "postgres";

	public Account findOne(String id, String pass) {
		Account account = null;
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM \"ACCOUNT\" WHERE \"USER_ID\"=? AND \"PASS\"=?");
			pStmt.setString(1, id);
			pStmt.setString(2, pass);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("USER_ID");
				pass = rs.getString("PASS");
				account = new Account(id, pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("見つけたアカウントを返すよ");
		return account;
	}

}
