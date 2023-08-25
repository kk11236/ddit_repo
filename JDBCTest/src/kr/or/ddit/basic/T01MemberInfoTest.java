package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//rs가 하는 역활은 pstmt로 던진 쿼리를 가져와서 rs에 넣어준다.
	
	private Scanner scan = new Scanner(System.in); 
	
	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
	
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	*/
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					selectAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	/**
	 * 전체 회원자료를 출력하기 위한 메서드
	 */
	private void selectAll() {
		
		try {
			conn = JDBCUtil3.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from mymember ";
			
			rs = stmt.executeQuery(sql);	//결과값이 있기때문에 executeQuery 그 결과를 rs에 담아낸다.
			
			System.out.println("---------------------------------------------------------");
			System.out.println("ID\t\t생성일\t\t이 름\t전화번호\t주 소");
			System.out.println("---------------------------------------------------------");
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				System.out.println(memId + "\t" + regDt + "\t" + memName + "\t" 
				                        + memTel + "\t" + memAddr );				
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("출력 작업 끝...");
			
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력해 주세요.");
		System.out.println("회원 ID >>");
		
		String memId = scan.next();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원정보 삭제 하였습니다.");
			}else {
				System.out.println(memId + "회원정보 삭제 실패!!!");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원 정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		
		boolean isExist = false;
		//회원 아이디를 do while 문 안에서 선언하면 그 안에서 밖에 못쓰니 밖에서 선언
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			isExist = checkMember(memId);
			
			if(!isExist) {
				System.out.println("회원Id가 " + memId + "인 회원은 이미 존재하지 않습니다.\n 다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		/////////////////////////////////////////////////////
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.println("회원 주소>>");
		String memAddr = scan.nextLine();

		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = 
					"update mymember set mem_name = ?, "
					+ "mem_addr = ?, mem_tel = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memAddr);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원의 정보를 수정했습니다.");
			}else {
				System.out.println(memId + "회원의 정보를 수정실패!!!");
			}
			
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally{
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원정보를 등록하기 위한 메서드
	*/
	private void insertMember() {
		
		//id가 존재여부 필터링
		boolean isExist = false;
		//회원 아이디를 do while 문 안에서 선언하면 그 안에서 밖에 못쓰니 밖에서 선언
		String memId = "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력해 주세요.");
			System.out.println("회원 ID >>");
			
			memId = scan.next();
			
			isExist = checkMember(memId);
			
			if(isExist) {
				System.out.println("회원Id가 " + memId + "인 회원은 이미 존재합니다\n 다시 입력해주세요.");
			}
			
		}while(isExist);
		
		/////////////////////////////////////////////////////
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.println("회원 주소>>");
		String memAddr = scan.nextLine();

		
		///////////////////////////////////////////////
		
		try {
			//오라클 드라이버 로딩 확인
//			Class.forName("oracle.jdbc.driver.OracleDriver");   <--유틸로 만듬
			
			//connection 객체 얻어 온것.
			//유틸로 만듬.
//	         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc12", "java");
			conn = JDBCUtil3.getConnection();
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr, reg_dt)"
					+ "values(?, ?, ?, ?, sysdate)";
			
			SQL_LOGGER.debug("SQL : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			//?에 값을 넣어 주는것.
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			PARAM_LOGGER.warn("Parameter : " + "memId = " + memId 
					+ " , memName = " + memName + " , memTel = " + memTel 
					+ " , memAddr = " + memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			RESULT_LOGGER.debug("cnt : " + cnt);

			if(cnt > 0) {
				System.out.println("회원 등록 성공!!!");
			}else {
				System.out.println("회원 등록 실패ㅜㅜ..");
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
			
	}
	/**
	 * 회원아이디가 존재하는지 체크하기 위한 메서드
	 * @param memId	회원아이디
	 */
	private boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select count(*)as cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;
		
	}

	public static void main(String[] args) {
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}