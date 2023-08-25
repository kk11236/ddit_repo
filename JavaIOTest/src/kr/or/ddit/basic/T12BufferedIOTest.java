package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 * (바이트기반의 Buffered스트림 사용 예제)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		
		//파일에 데이터를 쓸때 FileOutputStream
		//Output = 바이트기반 스트림
		//바이트기반스크림 = 1byte단위로 읽고,쓰고하는 것.
		//문자기반스트림 = Filewritet,Reader
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;//버터기능을 제공하는 outputstream 보조스트림임.
				
		try{
			fos = new FileOutputStream("E:/D_Other/bufferTest.txt");
			
			//버퍼 크기를 지정하지 않으면 기본적으로 8192byte(8KB)로 설정된다.
			bos = new BufferedOutputStream(fos, 5);
			
			for(char ch='1'; ch <= '9'; ch++) {
				bos.write(ch);
			}
			
			System.out.println("작업 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();	//보조스트림만 닫아도 된다.
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		
	}

}
