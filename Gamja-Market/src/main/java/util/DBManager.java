package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection getConnectionFromMySQL() {
		Connection conn = null;

		try {
			Context init = new InitialContext();
			DataSource source = (DataSource) init.lookup("java:comp/env/Gamja_Market");
			conn = source.getConnection();

			System.out.println("DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연동 실패");
		}

		return conn;
	}

	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
