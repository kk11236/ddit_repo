package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

// byteArray를 IO작업해준거임
public class T04ByteArrayIOTest2 {

   public static void main(String[] args) throws IOException {

      byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
      byte[] outSrc = null;
      
      // 1바이트씩 작업하니 퍼포먼스가 좋지않아서 버퍼로 합니다
      byte[] temp = new byte[4]; // 자료를 읽을 때 사용할 배열
      
      
      ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      
      //int data = 0; // 읽어온 데이터를 저장할 변수
      int readBytes = 0; // 읽어온 바이트 수?
      
      // 최대 4바이트씩 한번에 읽을 수 있음
      // 내가 읽은 바이트 만큼을 리턴함 ex)남은 데이터가3개인데 4까지 읽으면 남잖어? 그럼 읽은만큼만 리턴
      // readBytes = bais.read(temp) 이 부분에서 저장이 됨
      while((readBytes = bais.read(temp)) != -1) {
         System.out.println("readBytes : " + readBytes);
         System.out.println("temp : " + Arrays.toString(temp));
         baos.write(temp, 0, readBytes); 
      }
      // 근데 이러면 쓰렉이 데이터 까지 전부 write시켜서 복사가 제대로 안됨, 이럴때 필요한게 write메소드의 오버로딩된걸 쓰면 됨
      // write의 매개변수에 어디부터읽을찌, 어디까지읽을지를 추가
      // 배열의 첫번째부터 readBytes까지 읽으라고 하면 됨
      // readBytes는 bais.read(temp)로 지금 방금 내가 읽은 바이트 개수만큼 리턴받았으니까
      
      outSrc = baos.toByteArray();
      
      System.out.println("원본 inSrc       => " + Arrays.toString(inSrc));
      System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
   }
}