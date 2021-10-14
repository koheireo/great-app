package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	String url = "jdbc:postgresql://ec2-52-87-123-108.compute-1.amazonaws.com:5432/dctris4sbf8pdc";
	String user = "rmcyzvztzozyoj";
	String password = "8db8839ec9559648cd2222dcc07db95bb796ec038904c7f1ef94fdf61daa28d9";

	public Account findOne(String id, String pass) {
		Account account = null;
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pStmt = conn.prepareStatement("select * from account where id=? and pass=?");
			pStmt.setString(1, id);
			pStmt.setString(2, pass);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
				pass = rs.getString("pass");
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
