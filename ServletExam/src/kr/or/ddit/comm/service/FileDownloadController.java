package kr.or.ddit.comm.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
		resp.setContentType("application/octet-stream");
//		resp.setContentType("image/jpeg");
		
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + atchFileVO.getOrignlFileNm() +"\"");
		
		
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
