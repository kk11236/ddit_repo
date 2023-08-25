package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿을 이용한 파라미터 데이터 처리 예제
 * @author PC-13
 *
 */
public class T03ServletParameterTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//get방식이면 도메인에서 불러오고 post방식이면 from에서 불러온다.
		String username = req.getParameter("username");	//formdata.html name 속성에 적어놓은 name
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hoddy = req.getParameter("hobby");
		
		String rlgn = req.getParameter("rlgn");
		String[] foods = req.getParameterValues("foods");
		
		/////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("<p>username : " + username + "</p>");
		out.println("<p>password : " + password + "</p>");
		out.println("<p>gender : " + gender + "</p>");
		out.println("<p>hoddy : " + hoddy + "</p>");
		out.println("<p>rlgn : " + rlgn + "</p>");
		out.println("</body></html>");
		
		if(foods != null) {
			for(String food : foods) {
				out.println("<p>foods : " + food + "</p>");
			}
		}
		
		
		Enumeration<String> params = req.getParameterNames();
		
		while (params.hasMoreElements()) {
			String string = (String) params.nextElement();
			out.println("<p>파라미터 이름 : " + string + "</p>");
			
		}
		
		
	}
	
	

}
