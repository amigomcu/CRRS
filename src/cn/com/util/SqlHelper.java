package cn.com.util;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SqlHelper {

	// �������ݿ����
	private static String url = "";
	private static String SQLUser = "";
	private static String SQLPassword = "";
	private static String driver = "";

	// �������ļ�����
	private static Properties pp = null;
	private static InputStream is = null;

	
	/**
	 * ��ȡ�����ļ����������ݿ�����
	 * */
	static {
		try {
			pp = new Properties();
			is = SqlHelper.class.getClassLoader().getResourceAsStream(
					"dbinfo.properties");
			pp.load(is);

			url = pp.getProperty("url");
			SQLUser = pp.getProperty("SQLUser");
			SQLPassword = pp.getProperty("SQLPassword");
			driver = pp.getProperty("driver");
 
			Class.forName(driver);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			is = null;
		}
	}

	
	/**
	 * ִ��SQL��ѯ���
	 * 
	 * @param String
	 *            sql, SQL��ѯ���
	 * @param String
	 *            [] parameters, sql����еĲ���
	 * 
	 * @return ArrayList<ResultSet> �����
	 * @see objects[i] ���ݿ��е�i+1������
	 * */
	public static ArrayList<Object[]> executeQuery(String sql,
			String[] parameters) {
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Object[]> alOfObejcts = new ArrayList<Object[]>();

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			prepareCommand(parameters, ps);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objects = new Object[numberOfColumns];

				for (int i = 1; i <= numberOfColumns; i++) {
					objects[i - 1] = rs.getObject(i);
				}

				alOfObejcts.add(objects);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error in executeQuery:"
					+ e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
		return alOfObejcts;
	}

	
	/**
	 * ����ִ�зǲ�ѯ��䣨eg:insert, update, delete��
	 * 
	 * @param String
	 *            sql, SQLִ�����
	 * @param String
	 *            [] parameters, SQLִ������еĲ���
	 * 
	 * @return int, SQL���ִ��Ӱ�������
	 * */
	public static int executeNonQuery(String sql, String[] parameters) {

		PreparedStatement ps = null;
		Connection ct = null;
		int counts = 0;

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			prepareCommand(parameters, ps);
			counts = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error in executeNonQuery:"
					+ e.getMessage());
		} finally {
			close(null, ps, ct);
		}
		return counts;
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection ct) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �������ݿ�
	 * 
	 * @retrun Connection ct
	 **/
	public static Connection getConnection() {
		Connection ct = null;
		try {
			ct = DriverManager.getConnection(url, SQLUser, SQLPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	public static String getUrl() {
		return url;
	}



	/**
	 * Ԥ�������˽�к���
	 * */
	private static void prepareCommand(String[] parameters, PreparedStatement ps)
			throws SQLException {
		if ((parameters != null) && (!parameters.equals(""))) {
			for (int i = 0; i < parameters.length; i++) {
				ps.setObject(i + 1, parameters[i]);
			}
		}
	}
}
