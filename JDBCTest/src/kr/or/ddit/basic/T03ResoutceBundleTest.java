package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03ResoutceBundleTest {
/*
	ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와 key값과 value값을 분리한 정보를 갖는 객체
					  => 읽어올 파일은 'key값 =value값' 형태로 되어 있어야 한다.
					  
	소스 폴더는 컴파일 대상이 되어서 자동적으로 컴파일? 해준다.
*/
	
	public static void main(String[] args) {
		//ResourceBundle객체 생성하기
		// => 파일명을 지정할때 '파일명'만 지정하고 확장자는 지정하지 않는다.
		//	  (확장자는 항상 .properties이기 때문에...)
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.JAPANESE);
		
		// 모든 값 출력하기
		
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key값을 이용하여 value값을 읽어오는 방법
			// => bundle객체 변수.getString(key값)
			String value = bundle.getString(key);
		
			System.out.println(key + " => " + value);
		}
		
		System.out.println("출력 끝...");
	}

}
