package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.bind.DataBindingException;

public class T08ServletFilter implements Filter{

/*
	서블릿 필터에 대하여...
	
	1. 사용목적
	- 클라이언트 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
	- 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
	
	2. 사용 예
	- 인증필터
	- 데이터 압축필터
	- 인코딩 필터
	- 로깅 및 감사처리 필터
	- 이미지 변환 필터 등.
	
*/
	@Override
	public void destroy() {
		//서블릿이 제거 되기 전에 해야할 작업이 있으면 여기다 해주면 됨.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//실제 filter관련된 작업을 여기다 구현하면 됨.
		System.out.println("T08ServletFilter : doFilter() 시작...");
		
		//클라이언트의 IP주소 가져오기
		String ipAppr = req.getRemoteAddr();
		
		System.out.println("IP주소 : " + ipAppr + "\n포트번호 : " 
		                   + req.getRemotePort() + "\n현재 시간 :" + new Date());
		
		//필터체인을 실행한다. (요청 및 응답객체 전달) (요청 -> 필터 -> 응답 , 요청 <- 필터 <- 응답)
		chain.doFilter(req, resp);
		
		System.out.println("T08ServletFilter : doFilter() 끝...");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//처음에 한번만 딱 해야되는 작업을 여기다 해주면 됨.
		System.out.println("T08ServletFilter => init() 호출됨.");
		
		//초기화 파라미터정보 가져오기
		String initparam = filterConfig.getInitParameter("init-param");
		
		System.out.println("init-param : " + initparam);
		
	}
	
	
	

}
