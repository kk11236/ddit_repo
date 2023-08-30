package kr.or.ddit.comm.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService{
	
	private static IAtchFileService fileService;
	
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	

	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) throws Exception {
		
		String uploadPath = "e:/D_Other/upload_files";
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFirstFile = true;	//첫번째 파일 여부
		
		//첫번째 파일 인 경우에는  ATCH_FILE 이랑 ATCH_FILE_DETAIL 둘 다 들어가야 하므로 아래 코드 처럼 설계한 것.

			for(Part part : parts) {
				
				// 파일명 추출
				String fileName = part.getSubmittedFileName(); 
				
				if(fileName != null && !fileName.equals("")) {	//파일인경우...
					
					if(isFirstFile) {
						isFirstFile = false;
						
						// ATCH_FILE 에 저장
						atchFileVO = new AtchFileVO();
						fileDao.insertAtchFile(atchFileVO);
						
					}
					
					String orignFileName = fileName; // 파일명
					long fileSize = part.getSize();	 // 파일크기
					String saveFileName = "";		 // 저장파일명
					String saveFilePath = "";		 // 저장파일 경로
					
					saveFileName = UUID.randomUUID().toString().replace("-", "");
					saveFilePath = uploadPath + File.separator + saveFileName;
					
					// 확장자명 추출
					// a.jpg 에서 jpg를 추출한것.
					String fileExtension = 
							orignFileName.lastIndexOf(".") < 0 ? "" :
							orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
					
					// 업로드 파일(원본파일) 저장하기
					part.write(saveFilePath);
					
					atchFileVO.setFileStreCours(saveFilePath);
					atchFileVO.setStreFileNm(saveFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setFileExtsn(fileExtension);
					//atchFileVO.setFileCn("");
					atchFileVO.setOrignlFileNm(orignFileName);
					
					// ATCH_FILE_DETAIL 에 저장
					fileDao.insertAtchDetail(atchFileVO);
					
					part.delete();	// 임시 업로드 파일 삭제하기
					
				}
			}
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		return fileDao.getAtchFileList(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		return fileDao.getAtchFileDetail(atchFileVO);
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
}
