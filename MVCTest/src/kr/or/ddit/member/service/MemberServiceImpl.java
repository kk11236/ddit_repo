package kr.or.ddit.member.service;

import java.util.List;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemverService {

	private IMemberDao memDao;

	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();	
	};

	@Override
	public int registMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		
		//동현 계좌 100만 인출
		//다오에 update
		
		
		//형택 계좌 100만 입금
		//다오에 update
		
		
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {		
		boolean isExist = memDao.checkMember(memId);
		return isExist;
	}

	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> memList = memDao.selectAll();
		return memList;
	}

}
