package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	
	public static void main(String[] args) {
		
		// myBatis를 이용하여 DB자료를 처리하는 작업 순서
		
		// 1. myBatis의 환경설정파일을 읽어와 실행시킨다.
		
		SqlSessionFactory sessionFactory = null;
		
		try {   
			
			
			
			// 1-1. xml문 읽어오기
			// 설정파일의 인코딩 설정(한글처리를 위해서...)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");	
			//^이게 mybatis-config.xml 파일 안에 있는 정보들을 읽어와서 설정정보로 세션을 설정하지 위해서 불러옴.
			
			// 1-2위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		//-------------------------------------------------여기까지 세션팩토리
		
		// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		
		// 2-1. insert작업 연습
		System.out.println("insert 작업 시작...");
		
		// 1) 저장할 테이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-333-2222");
		mv.setMemAddr("경남 진해시");
		
		// 2) SqlSession객체변수를 이용하여 해당 쿼리문을 실행한다.
		//	형식 ) SqlSession객체.insert("namespace값.id값", 파라미터객체)
		//		반환값 : 성공한 레코드 수
		
		SqlSession sqlSession = sessionFactory.openSession(false);	//autoCommit여부 (true/false)	//세션을 열었음.
		
		try {
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			if(cnt > 0) {
				System.out.println("insert 작업 성공");
				sqlSession.commit();	// 커밋
			}else {
				System.out.println("insert 작업 실패!!!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback();
			
		}finally {
			sqlSession.close();//세션닫기
			System.out.println("-----------------------------------------------");
		}
		
		//2-2. update 작업 연습
		System.out.println("update작업 시작...");
		
		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d002");
		mv2.setMemName("김태탱");
		mv2.setMemTel("010-1111-1111");
		mv2.setMemAddr("대구시");
		
		sqlSession = sessionFactory.openSession();
		
		try {
			//update() 메서드의 반환값도 성공한 레코드 수 이다.
			int cnt = sqlSession.update("memberTest.updateMember", mv2);
			
			if(cnt > 0) {
				System.out.println("update 작업 성공");
				sqlSession.commit();	// 커밋
			}else {
				System.out.println("update 작업 실패!!!!");
			}
			
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
		System.out.println("-----------------------------------------------");
		
		//2-3. delete 연습
		/*System.out.println("delete 작업 시작.....");
		
		sqlSession = sessionFactory.openSession();
		
		try {
			
			int cnt = sqlSession.delete("memberTest.deleteMember", "uuuu");
			
			if(cnt > 0) {
				System.out.println("delete 작업 성공...");
				sqlSession.commit();
			}else {
				System.out.println("delete 작업 실패!!!");
			}
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			sqlSession.rollback();
		}finally {
			sqlSession.close();
		}
		
		System.out.println("-----------------------------------------------");
		////////////////////////////////////////////////////////////
		*/
		
		//2-4. select연습
		// 1) 응답의 결과가 여러개인 경우...
		System.out.println("select 연습(결과가 여러개일 경우)시작...");
		
		sqlSession = sessionFactory.openSession(true);
		
		try {
			List<MemberVO> memList =  sqlSession.selectList("memberTest.selectAll");
			
			for(MemberVO mv3 : memList ) {
				System.out.println("회원ID : " + mv3.getMemId());
				System.out.println("회원이름 : " + mv3.getMemName());
				System.out.println("회원전화 : " + mv3.getMemTel());
				System.out.println("회원주소 : " + mv3.getMemAddr());
				System.out.println("--------------------------------------");
			}
			
			System.out.println("목록 출력 끝...");
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		
		
		
		// 2) 응답의 결과가 1개일 경우...
		
		System.out.println("select 연습 (결과가 1개일 경우)시작...");
		
		sqlSession = sessionFactory.openSession(true);
		
		try {
			
			MemberVO mv4 = sqlSession.selectOne("memberTest.getMember", "d002");
			System.out.println("회원ID : " + mv4.getMemId());
			System.out.println("회원이름 : " + mv4.getMemName());
			System.out.println("회원전화 : " + mv4.getMemTel());
			System.out.println("회원주소 : " + mv4.getMemAddr());
			System.out.println("--------------------------------------");
			
		}catch(PersistenceException e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		//응답 결과가 1개가 확실할 경우에는 selectOne() 메서드 사용
		
		
		
		
		
	}

}
