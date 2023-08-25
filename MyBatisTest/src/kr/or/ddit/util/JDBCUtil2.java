package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법 방법1) Properties객체 이용하기
 */
public class JDBCUtil2 {

	static Properties prop; // 객체변수 선언

	static {

		prop = new Properties();

		try {

			// Properties객체로 파일내용 읽기
			prop.load(new FileInputStream("./res/db.properties"));

			// 드라이버 로딩 확인
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 완료");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 커넥션 객체 가져오기
	 * @return Connection 객체, 예외발생시 null 리턴
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // 만약 예외가 터지면 null 리턴
		}

	}

	/**
	 * 자원반납 해주는 메소드
	 * 
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
	}
}