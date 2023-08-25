package hw.board.dao;

import java.util.List;

import hw.board.vo.BoardVO;

public interface IBoardDao {
		
	public int insertPost(BoardVO bv);
	
	public int updatePost(BoardVO bv);
	
	public int deletePost(String writer);
	
	public List<BoardVO> searchPost(BoardVO bv);
	
	public boolean checkWriter(String writer);
	
	public List<BoardVO> selectAll();
	

}
