package cn.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

	private static ConnectionDB db = null;

	private String dw = "";

	private String url = "";

	private String user = "";

	private String pw = "";

	public ConnectionDB() {
		init();

	}

	public static ConnectionDB getConnectionDB() {
		if (db == null) {
			db = new ConnectionDB();
		}
		return db;
	}

	// 只执行一次
	public void init() {
		config();
		try {
			Class.forName(dw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public void closeDB(Connection con, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 记得封装到配置文件
	 */
	private void config() {
		dw = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "lyh";
		pw = "lyh";
	}

//	public static void main(String[] args) {
//		try {
//			ConnectionDB.getConnectionDB().getCon().createStatement();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
