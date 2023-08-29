package kr.or.ddit.comm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.parsing.ParsingException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDaoImpl implements IAtchFileDao{
	
	private static IAtchFileDao atchFileDao;
	
	public AtchFileDaoImpl() {
		
	}
	
	public static IAtchFileDao getInstance() {
		if(atchFileDao == null) {
			atchFileDao = new AtchFileDaoImpl();
		}
		
		return atchFileDao;
	}

	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			
			cnt = session.insert("atchFile.insertAtchFile", atchFileVO);
			
			session.commit();
			
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	

	@Override
	public int insertAtchDetail(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			
			cnt = session.insert("atchFile.insertAtchFileDetail", atchFileVO);
			
			session.commit();
			
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			
			session.close();
		}
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		List<AtchFileVO> atchFileList = new ArrayList<AtchFileVO>();
		
		try {
			
			atchFileList = session.selectList("atchFile.getAtchFileList", atchFileVO);
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			
		}finally {
			session.close();
		}
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		AtchFileVO fileVO = new AtchFileVO();
		
		try {
			
			fileVO = session.selectOne("atchFile.getAtchFileDetail", atchFileVO);
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			
		}finally {
			
			session.close();
		}
		
		return fileVO;
	}

	}


