package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImplWithJDBC implements IMemberDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IMemberDao memDao;
	
	private MemberDaoImplWithJDBC() {
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImplWithJDBC();
		}
		
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;

		try {

			conn = JDBCUtil3.getConnection();

			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr, reg_dt)"
					+ "values(?, ?, ?, ?, sysdate)";

			pstmt = conn.prepareStatement(sql);
			// ?에 값을 넣어 주는것.
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {

			conn = JDBCUtil3.getConnection();

			String sql = "update mymember set mem_name = ?, " + "mem_addr = ?, mem_tel = ? where mem_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemAddr());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "delete from mymember where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean isExist = false;

		try {
			conn = JDBCUtil3.getConnection();

			String sql = "select count(*)as cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			int cnt = 0;

			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				isExist = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			conn = JDBCUtil3.getConnection();

			stmt = conn.createStatement();

			String sql = "select * from mymember ";

			rs = stmt.executeQuery(sql); // 결과값이 있기때문에 executeQuery 그 결과를 rs에 담아낸다.
			

			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				
				mv.setRegDt(regDt);
				
				memList.add(mv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO paramMv) {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember where 1=1 ";
			
			if(paramMv.getMemId() != null && !paramMv.getMemId().equals("")) {
				sql += " and mem_id = ? ";
			}
			if(paramMv.getMemName() != null && !paramMv.getMemName().equals("")) {
				sql += " and mem_name = ? ";
			}
			if(paramMv.getMemTel() != null && !paramMv.getMemTel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			if(paramMv.getMemAddr() != null && !paramMv.getMemAddr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(paramMv.getMemId() != null && !paramMv.getMemId().equals("")) {
				pstmt.setString(index++, paramMv.getMemId());
			}
			if(paramMv.getMemName() != null && !paramMv.getMemName().equals("")) {
				pstmt.setString(index++, paramMv.getMemName());
			}
			if(paramMv.getMemTel() != null && !paramMv.getMemTel().equals("")) {
				pstmt.setString(index++, paramMv.getMemTel());
			}
			if(paramMv.getMemAddr() != null && !paramMv.getMemAddr().equals("")) {
				pstmt.setString(index++, paramMv.getMemAddr());
			}
			rs = pstmt.executeQuery();
			
			
			
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				
				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				
				mv.setRegDt(regDt);
				
				memList.add(mv);
			}
			
			
		}catch(SQLException e) {
			
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

}
