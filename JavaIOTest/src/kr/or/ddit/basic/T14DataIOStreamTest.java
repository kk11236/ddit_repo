package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입(문자열 포함) 입출력 보조 스트림
 */
public class T14DataIOStreamTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			
			fos = new FileOutputStream("e:/D_Other/test.dat");
			dos = new DataOutputStream(fos);
			
			dos.writeUTF("홍길동");	//문자열 데이터 출력
			dos.writeInt(17); 		//정수형으로 데이터 출력
			dos.writeFloat(3.14f); 	//실수형(Float)으로 출력
			dos.writeDouble(3.14);  //실수형(Double)으로 출력
			dos.writeBoolean(true); //논리형으로 출력

			System.out.println("데이터 출력완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		////////////////////////////////////////////////////////
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream("e:/D_Other/test.dat");
			dis = new DataInputStream(fis);
			
			//입력한 순서대로 읽지 않으면 오류가 남 왜? 바이트 수를 끌어다 써서 이상해짐 뭔소린지 모르곗음 진챠,,
			//데이터 사이즈에 맞게? 바이트 수를 지원 해줌? 뭔소리임. .ㅎ..ㅁㅎ.ㄴㅁㅇㅎ...ㅁㄴㅇㄹ....
			System.out.println("문자열 자료 : " + dis.readUTF());
			System.out.println("정수형 자료 : " + dis.readInt());
			System.out.println("실수형(Float) 자료 : " + dis.readFloat());
			System.out.println("실수형(Double) 자료 : " + dis.readDouble());
			System.out.println("논리형 자료 : " + dis.readBoolean());
			
			System.out.println("읽기 작업 완료...");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				dis.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
	}

}
