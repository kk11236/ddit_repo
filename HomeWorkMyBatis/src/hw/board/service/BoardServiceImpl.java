package hw.board.service;

import java.util.List;

import hw.board.dao.BoardDaoImpl;
import hw.board.dao.IBoardDao;
import hw.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	
	private static IBoardService boardService;
	
	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int registPost(BoardVO bv) {
		
		int cnt = boardDao.insertPost(bv);
		
		return cnt;
	}

	@Override
	public int modifyPost(BoardVO bv) {
		
		int cnt = boardDao.updatePost(bv);
		
		return cnt;
	}

	@Override
	public int removePost(String writer) {

		int cnt = boardDao.deletePost(writer);
		
		return cnt;
	}

	@Override
	public boolean checkWriter(String writer) {
		
		boolean isExist = boardDao.checkWriter(writer);
		
		return isExist;
	}
	

	

	@Override
	public List<BoardVO> selectAll() {

		List<BoardVO> boardList = boardDao.selectAll();
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		
		
		List<BoardVO> boardList = boardDao.searchPost(bv);
		
		return boardList;
	}


}
