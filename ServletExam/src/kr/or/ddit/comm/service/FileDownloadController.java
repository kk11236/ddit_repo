package kr.or.ddit.comm.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.vo.AtchFileVO;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String atchFileId = req.getParameter("fileId");
		String fileSn = req.getParameter("fileSn");
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO fileVO = new AtchFileVO();
		
		fileVO.setAtchFileId(Long.parseLong(atchFileId));
		fileVO.setFileSn(Integer.parseInt(fileSn));
		
		AtchFileVO atchFileVO = fileService.getAtchFileDetail(fileVO);
		
		
		
		/*
			Content-Dispostion 헤더에 대하여...
			
			Content-Disposition: inline (default)
			Content-Disposition: attachment						//파일 다운로드
			Content-Disposition: attachment; filename="a.jpg"   //파일 다운로드
			
		*/
		
		
		//application/octet-stream(바이너리 데이터란 의미) 약속 된 하나의 이름 임. 
//		resp.setContentType("image/jpeg");
		resp.setContentType("application/octet-stream");
		
		
		// URL에는 공백문자를 포함할 수 없다. URLEncoding을 하면 공백은(+)로 표시되기 때문에
		// +문자를 공백문자인 %20으로 바꿔준다.
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(atchFileVO.getOrignlFileNm(), "UTF-8").replaceAll("\\+", "%20") + "\"");
		
		
		FileInputStream fis = new FileInputStream(atchFileVO.getFileStreCours());
		OutputStream out = resp.getOutputStream();
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int data =0;
		
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
				
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
