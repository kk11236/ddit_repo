package hw.board.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import hw.board.util.MyBatisUtil;
import hw.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> selectAll() {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			boardList = session.selectList("board.selectAll");
			
			
		}catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return boardList;
	}

	@Override
	public int insertPost(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = session.insert("board.insertPost", bv);
			
			if(cnt > 0) {
				session.commit();
			}			
		}catch(PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int updatePost(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.update("board.updatePost", bv);
			
			if(cnt > 0) {
				session.commit();
			}
			
		}catch(PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int deletePost(String writer) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = session.delete("board.deletePost", writer);
			
			if(cnt > 0) {
				session.commit();
			}

			
		}catch(PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			
			session.close();		
		}
		
		return cnt;
	}



	@Override
	public boolean checkWriter(String writer) {
		
		boolean isExist = false;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			int cnt = session.selectOne("board.checkWriter", writer);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO parambv) {
		
	List<BoardVO> boardList = new ArrayList<BoardVO>();
	
	SqlSession session = MyBatisUtil.getInstance(true);
		
		
		try {
			
			boardList = session.selectList("board.searchBoard", parambv);
										
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return boardList;
	
	}

}
