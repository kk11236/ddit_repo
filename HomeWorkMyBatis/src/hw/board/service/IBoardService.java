package hw.board.service;

import java.util.List;

import hw.board.vo.BoardVO;

public interface IBoardService {
	
	public int registPost(BoardVO bv);
	
	public int modifyPost(BoardVO bv);
	
	public int removePost(String writer);
	
	public List<BoardVO> searchPost(BoardVO bv);	
	
	public List<BoardVO> selectAll();
	
	public boolean checkWriter(String writer);
	

}
