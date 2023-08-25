package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
/**
 * 실제 DB와 연결해서 SQL문을 수행한 후 결과를 작성하여 서비스에 전달하는 DAO의 Interface
 * @author PC-13
 *
 */

public interface IMemberDao {
	
	//public int insertMember(String memId, String memName, String memTel, String memAddr);
	
	//위랑 똑같은 건데 vo자체를 매게변수로 받아버리면 변경사항이 있을 시 더 편리? 하다.
	/**
	 * MemberVO에 담겨진 데이터를 DB에 insert하기 위한 메서드
	 * @param mv DB에 등록할 데이터가 담겨진 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환됨.
	 */
	public int insertMember(MemberVO mv);
	
	
	/**
	 * MemberVO에 담겨진 데이터를 DB에 update하기 위한 메서드
	 * @param mv DB에 등록할 데이터가 담겨진 MemberVO객체
	 * @return DB작업이 성공하면 1이 반환되고, 실패하면 0이 반환됨.
	 */
		
	public int updateMember(MemberVO mv);
	
	
	/**
	 * 해당 ID에 해당하는 회원정보를 삭제하기 위한 메서드
	 * @param memId 삭제할 회원ID
	 * @return	삭제 성공하면 1, 실패하면 0 반환됨.
	 */
	public int deleteMember(String memId);		//key값인 memId만 받아도 된다.
	
	/**
	 * 해당 ID에 해당하는 회원 정보가 존재하는지 확인하기 위한 메서드
	 * @param memId 체크할 회원ID
	 * @return 해당 회원이 존재하면 true, 존재하지 않으면 false 리턴함.
	 */
	public boolean checkMember(String memId);
	
	
	/**
	 *  전체 회원정보를 가져오기 위한 메서드
	 * @return 전체 회원정보를 담은 리스트
	 */
	public List<MemberVO> selectAll();
	
	/**
	 * 회원정보를 검색하기 위한 메서드
	 * @param mv 검색할 회원정보를 담은 MemberVO객체
	 * @return 검색된 회원정보를 담은 List객체
	 */
	public List<MemberVO> searchMember(MemberVO mv);
}
