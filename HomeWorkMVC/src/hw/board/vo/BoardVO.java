package hw.board.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {
	
	private int boardNum;
	private String title;
	private String writer;
	private String content;
	private Date regDt;
	
	public BoardVO() {};
	
	public BoardVO(String writer) {
		this.writer = writer;
	};
	
	public BoardVO(String title, String writer) {
		this.title = title;
		this.writer = writer;
	}
	
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
	public String getRegDtDisplay() {
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
		
		return sdf.format(this.regDt);
		
	}

	@Override
	public String toString() {
		return "BorderVO [boardNum=" + boardNum + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regDt=" + regDt + "]";
	}
	
	


}
