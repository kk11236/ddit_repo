package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MyBatisUtil;
import oracle.net.ns.SessionAtts;

public class MemberDaoImpl implements IMemberDao {

	private static IMemberDao memDao;

	private MemberDaoImpl() {

	}

	public static IMemberDao getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImpl();
		}

		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		SqlSession session = MyBatisUtil.getInstance();

		int cnt = 0;

		try {
			cnt = session.insert("member.insertMember", mv);
			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {

		SqlSession session = MyBatisUtil.getInstance();

		int cnt = 0;
		try {

			cnt = session.update("member.updateMember", mv);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;

		try {
			cnt = session.delete("member.deleteMember", memId);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean isExist = false;

		SqlSession session = MyBatisUtil.getInstance(true);

		try {
			int cnt = session.selectOne("member.checkMember", memId);

			if (cnt > 0) {
				isExist = true;
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MyBatisUtil.getInstance();

		try {
			memList = session.selectList("member.selectAll");			

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO paramMv) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MyBatisUtil.getInstance(true);

		try {
			memList = session.selectList("member.searchMember", paramMv);			

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		
		SqlSession session = MyBatisUtil.getInstance(false);
		
		MemberVO mv = null;
		try {
			
			mv = session.selectOne("member.getMember", memId);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return mv;

	}

}
