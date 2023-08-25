package hw.board.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hw.board.util.JDBCUtil;
import hw.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> selectAll() {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.createStatement();
			
			String sql = "select * from jdbc_board";
			
			rs = stmt.executeQuery(sql);		
			
			while(rs.next()) {
				int boardNum = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				Date regDt = rs.getTimestamp("board_date");
				String content = rs.getString("board_content");
				
				BoardVO bv = new BoardVO(title, writer, content);
				
				bv.setBoardNum(boardNum);
				bv.setRegDt(regDt);
				
				boardList.add(bv);
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public int insertPost(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
					+ "values(board_seq.nextval, ?, ?, sysdate, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());

			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int updatePost(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_writer = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getContent());
			pstmt.setString(3, bv.getWriter());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deletePost(String writer) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}



	@Override
	public boolean checkWriter(String writer) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " select count(*) as cnt from jdbc_board where board_writer = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("CNT");
				
			}
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return isExist;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO parambv) {
		
	List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from jdbc_board where 1 = 1 ";
		
			if(parambv.getTitle()!= null && !parambv.getTitle().equals("")) {
				sql += " and board_title like '%' || ? || '%' ";
			}
			if(parambv.getWriter() != null && !parambv.getWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			
			if(parambv.getTitle()!= null && !parambv.getTitle().equals("")){
				pstmt.setString(index++,parambv.getTitle());
			}
			if(parambv.getWriter() != null && !parambv.getWriter().equals("")) {
				pstmt.setString(index++,parambv.getWriter());
			}
	
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				Date boardDate = rs.getDate("board_date");
				String boardContent = rs.getString("board_content");
				
				BoardVO bv = new BoardVO(boardTitle, boardWriter);		
				
				bv.setBoardNum(boardNo);
				bv.setRegDt(boardDate);
				bv.setContent(boardContent);
				
				
				boardList.add(bv);
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	
	}

}
