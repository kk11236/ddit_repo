package JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hotel_JDBC {

   public static void main(String[] args) {

      Hotel_JDBC hotel = new Hotel_JDBC();
      hotel.hotelStart();

   }

   private Connection conn;
   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;

   Scanner sc = new Scanner(System.in);

   int choice;

   public void hotelStart() {

      System.out.println("**************************************");
      System.out.println("호텔 문을 열었습니다.");
      System.out.println("**************************************");
      System.out.println(" ");

      do {

         displayMenu();

         switch (choice) {

         case 1:
            checkIn();
            break;
         case 2:
            checkOut();
            break;
         case 3:
            Room();
            break;
         case 4:
            end();
            break;
         default:
            System.out.println("잘못 누르셨습니다.");
            break;

         }
      } while (choice != 4);

   }


   private void displayMenu() {

      System.out.println("**************************************");
      System.out.println("어떤 업무를 하시겠습니까?");
      System.out.println("**************************************");
      System.out.println(" ");
      System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
      System.out.print("메뉴 선택 >> ");
      choice = sc.nextInt();

   }

   private void checkIn() {

      boolean isExist = false;

      System.out.println("어느방에 체크인 하시겠습니까?");
      System.out.print("방번호 입력 : ");

      String roomnum = sc.next();

      isExist = checkRoomNum(roomnum);

      if (isExist) {
         System.out.println(roomnum + "은 이미 손님이 있습니다.");
         System.out.println(" ");
         return;
      }

      System.out.println("누구를 체크인 하시겠습니까?");
      System.out.print("이름 입력 : ");
      String name = sc.next();

      try {

         conn = JDBCUtil.getConnection();

         String sql = "insert into hotel (roomnum, name) values ( ?, ?)";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, roomnum);
         pstmt.setString(2, name);

         int cnt = pstmt.executeUpdate();

         if (cnt > 0) {
            System.out.println("체크인 되었습니다.");
            System.out.println(" ");
         } else {
            System.out.println("체크인 실패했습니다.");
            System.out.println(" ");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }

   private void checkOut() {

      boolean isExist = false;

      System.out.println("어느방을 체크아웃 하시겠습니까?");
      System.out.print("방번호 입력 : ");
      String roomnum = sc.next();

      isExist = checkRoomNum(roomnum);

      if (!isExist) {

         System.out.println(roomnum + "은 빈방입니다.");
         return;
      }

      try {

         conn = JDBCUtil.getConnection();

         String sql = "delete from hotel where roomnum = ?";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, roomnum);

         int cnt = pstmt.executeUpdate();

         if (cnt > 0) {
            System.out.println("체크아웃 되었습니다.");
         } else {
            System.out.println("체크아웃 실패!!!");
         }

      } catch (SQLException e) {
         e.printStackTrace();
         
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }

   private void Room() {

      try {
         conn = JDBCUtil.getConnection();

         String sql = "select * from hotel";

         stmt = conn.createStatement();

         rs = stmt.executeQuery(sql);

         int count = 0;
         
         System.out.println("------------------------------");
         System.out.println("\t 방번호 \t 이름");
         System.out.println("------------------------------");

         while (rs.next()) {
            count++;
            String roomnum = rs.getString("roomnum");
            String name = rs.getString("name");
            
            System.out.println(count +"\t"+ roomnum +"\t"+ name);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }
   

   private void end() {
      

      System.out.println("**************************************");
      System.out.println("호텔영업을 종료합니다.");
      System.out.println("**************************************");
      System.exit(0);
      
   }

   private boolean checkRoomNum(String roomnum) {

      boolean isExist = false;
      try {
         conn = JDBCUtil.getConnection();
         String sql = "select count(*) as cnt from hotel where roomnum = ? ";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, roomnum);

         rs = pstmt.executeQuery();

         int cnt = 0;

         while (rs.next()) {
            cnt = rs.getInt("cnt");
         }

         if (cnt > 0) {
            isExist = true;
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }
      return isExist;

   }

}