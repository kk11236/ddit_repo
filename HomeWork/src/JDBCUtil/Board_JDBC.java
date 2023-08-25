package JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Board_JDBC {

   public Connection conn;
   public Statement stmt;
   public PreparedStatement pstmt;
   public ResultSet rs;

   Scanner sc = new Scanner(System.in);

   int choice = 0;

   public static void main(String[] args) {
	   Board_JDBC Board = new Board_JDBC();
	   Board.start();
   }

   public void start() {

      do {
         displaymenu();

         switch (choice) {

         case 1:
            selectAll();
            break;
         case 2:
            newPost();
            break;
         case 3:
            modifyPost();
            break;
         case 4:
            deletePost();
            break;
         case 5:
            searchPost();
            break;
         case 6:
            // end();
            break;
         default:
            System.out.println("잘못누르셨습니다.");
            break;

         }

      } while (choice != 6);

   }

   private void searchPost() {
      
      String search = "";
      System.out.println("검색할 내용을 입력하세요.");
      System.out.print("입력 >> ");
      search = sc.next();
      
      
      try {
         conn = JDBCUtil.getConnection();
         
         stmt = conn.createStatement();
         
         String sql = "select * from jdbc_board where 1=1";
         
         if(search != null && search.equals("")) {
            sql += "and board_content like '%' || ? || '%' ";
         }
         
         pstmt = conn.prepareStatement(sql);
         
         int index = 1;
         
         if(search != null && search.equals("")) {
            pstmt.setString(index, search);
         }
         
         rs = pstmt.executeQuery();
         
         int count = 0;
         
         while (rs.next()) {
            count++;
            String title = rs.getString("board_title");
            String name = rs.getString("board_writer");
            String content = rs.getString("board_content");

            System.out.println(count + "\t" + title + "\t" + name + "\t" + content);
         }
         
      }catch(SQLException e){
         e.printStackTrace();
      }finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }
      
   }

   private void modifyPost() {
      
      boolean isExist = false;

      String name = "";
      do {

         System.out.println("");
         System.out.println("작성했던 이름을 입력해주세요.");
         System.out.print("이름 >> ");
         name = sc.next();
         
         isExist = checkWriter(name);
         
         if(!isExist) {
            System.out.println(name + "은 존재하지 않는 작성자 입니다.");
            return;
         }

      } while (!isExist);
      
      System.out.print("제목 >> ");
      String title = sc.next();
      
      //System.out.println("작성자 >> "); 
      //name = sc.next();
      
      sc.nextLine();
      
      System.out.print("내용 >> ");
      String content = sc.nextLine();
      
      
      try {
         
         conn = JDBCUtil.getConnection();
         
         String sql = "update jdbc_board set board_title = ?, board_content = ? where board_writer = ?";
         
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, title);
         pstmt.setString(2, content);
         pstmt.setString(3, name);
         
         int cnt = pstmt.executeUpdate();
         
         if(cnt > 0) {
            System.out.println("게시글을 수정하였습니다.");
         }else {
            System.out.println("게시글 수정 실패!!!");
         }
         
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }
      
   }

   private void deletePost() {
      

      boolean isExist = false;

      String name = "";
      do {

         System.out.println("");
         System.out.println("작성했던 이름을 입력해주세요.");
         System.out.print("이름 >> ");
         name = sc.next();
         
         isExist = checkWriter(name);
         
         if(!isExist) {
            System.out.println("존재하지 않는 작성자 입니다.");
            return;
         }

      } while (!isExist);

      try {
         
         conn = JDBCUtil.getConnection();

         String sql = "delete from jdbc_board where board_writer = ?";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, name);

         int cnt = pstmt.executeUpdate();

         if (cnt > 0) {
            System.out.println(name + "게시글이 삭제되었습니다.");
         } else {
            System.out.println(name + "게시글 삭제 실패!!!");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }

   private void selectAll() {
      try {

         conn = JDBCUtil.getConnection();

         String sql = "select * from jdbc_board";

         stmt = conn.createStatement();

         rs = stmt.executeQuery(sql);

         System.out.println("========================================");
         System.out.println("\t제목 \t작성자 \t 내용");
         System.out.println("========================================");

         int count = 0;

         while (rs.next()) {
            count++;
            String title = rs.getString("board_title");
            String name = rs.getString("board_writer");
            String content = rs.getString("board_content");

            System.out.println(count + "\t" + title + "\t" + name + "\t" + content);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }

   private void newPost() {

      System.out.println("==========게시판 새글 작성==========");
      System.out.print("제목 : ");
      String title = sc.next();
      System.out.print("작성자 이름 : ");
      String name = sc.next();

      sc.nextLine(); // 입력버퍼 제거

      System.out.print("내용 : ");
      String content = sc.nextLine();

      try {
         conn = JDBCUtil.getConnection();

         String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
               + "values(board_seq.nextval, ?, ?, sysdate, ?)";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, title);
         pstmt.setString(2, name);
         pstmt.setString(3, content);

         int cnt = pstmt.executeUpdate();

         if (cnt > 0) {
            System.out.println("게시글이 등록되었습니다.");
            System.out.println("");
         } else {
            System.out.println("게시글이 등록 되지 않았습니다.");
            System.out.println("");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(conn, stmt, pstmt, rs);
      }

   }

   private boolean checkWriter(String name) {

      boolean isExist = false;

      try {
         conn = JDBCUtil.getConnection();

         String sql = "select count(*)as cnt from jdbc_board where board_writer = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, name);

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

   public void displaymenu() {
      System.out.println("");
      System.out.println("==========게시판==========");
      System.out.println("1.전체 목록 출력");
      System.out.println("2.새글작성");
      System.out.println("3.수정");
      System.out.println("4.삭제");
      System.out.println("5.검색");
      System.out.println("6.종료");
      System.out.print("메뉴 선택 >> ");
      choice = sc.nextInt();
   }
}