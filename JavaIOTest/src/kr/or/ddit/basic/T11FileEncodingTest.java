package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class T11FileEncodingTest {
	public static void main(String[] args) throws IOException {
	/*
		OutputStreamWriter => 바이트기반의 출력용 객체를 문자기반 출력용 객체로 변환해주는 보조 스트림
			=> 이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
	*/
		// 키보드로 입력한 내용을 파일로 저장하는데...
		// out_utf8.txt 파일은 'utf-8' 인코딩 방식으로
		// out_ansi.txt 파일은 'ms949' 인코딩 방식으로 저장하도록 한다.
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// 파일 출력용
		FileOutputStream fos1 = new FileOutputStream("e:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("e:/D_Other/out_ansi.txt");
		
		// 기반 스트림은 fos1이고 UTF-8방식으로 저장하겠다
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");
		
		int data = 0;
		
		System.out.println("아무거나 입력한 후 Ctrl-z를 눌러 주세요...");
		
		while((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);
		}
		
		System.out.println("작업 완료...");
		
		// 명시적으로 보조스트림을 안닫아도 기반스트림이 닫히면 알아서 닫힘, 닫아도 상관없긴함
		osw1.close();
		osw2.close();
		isr.close();
		
		fos1.close();
		fos2.close();
	}

}
