package kr.or.ddit.member.service;

import java.util.List;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dao.MemberDaoImplWithJDBC;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao memDao;
	
	private static IMemberService memService;

	
	private MemberServiceImpl() {
		
		//memDao = MemberDaoImplWithJDBC.getInstance();			<< JDBC기반
		memDao = MemberDaoImpl.getInstance();				//	<< Mybatis기반
	};
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}		
		return memService;
	}

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

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMember(mv);
		
		return memList;
	}

}
