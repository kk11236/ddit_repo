package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 읽기 예제
 */
public class T05FileStreamTest {
	public static void main(String[] args) {
		
		//FileInputStream는 byte기반 스트림
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("e:/D_Other/test2.txt");
			
			int data = 0;
						
			while((data = fis.read()) != -1) {
				//읽어온 데이터 콘솔 출력하기
				System.out.print((char) data);
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {	//파일 처리는 마무리는 깔끔하게 하기 위해서 finally를 써준다. 
					//왜? close를 안해 줬을 때 나중에 문제가 생길수도 있음 DB랑 같은 개념
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
