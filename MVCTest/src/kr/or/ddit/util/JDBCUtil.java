package kr.or.ddit.util;

import java.sql.*;

public class JDBCUtil {
   // static 블럭 이용, 블럭 안에 드라이버 로딩 체크를 넣어두면
   // JDBCUtil이라는 클래스를 호출할 때 static블럭이 있다면 static블럭에 있는 코드들이 한번만 딱 실행돼 처음 로딩될 때
   // 그니까 이제 싱글톤패턴의 생성자느낌인가
   static {
      try {
         // 드라이버 로딩 확인
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("드라이버 로딩 완료");
      } catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * 커넥션 객체 가져오기
    * @return Connection 객체, 예외발생시 null 리턴
    */
   public static Connection getConnection() {
      try {
         return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc12", "java");
      } catch (SQLException e) {
         e.printStackTrace();
         return null; // 만약 예외가 터지면 null 리턴
      } 

   }
   
   /**
    * 자원반납 해주는 메소드
    * @param conn
    * @param stmt
    * @param pstmt
    * @param rs
    */
   public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
      if(rs != null) try {rs.close();}catch(SQLException e) {}
      if(stmt != null) try {stmt.close();}catch(SQLException e) {}
      if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
      if(conn != null) try {conn.close();}catch(SQLException e) {}
   }
}