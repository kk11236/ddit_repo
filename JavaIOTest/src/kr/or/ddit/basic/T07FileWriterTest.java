package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
   public static void main(String[] args) {
      // 사용자가 입력한 내용을 그대로 파일에 저장하기

      // 콘솔(표준 입력장치)과 연결된 입력용 문자 스트림 생성
      // InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조스트림이다
      InputStreamReader isr = new InputStreamReader(System.in);
      // InputStream만보면? 바이트기반스트팀 Reader를보면? 문자기반스트림
      // System.in -> 기본적으로 InputStream, 바이트기반, 문자기반스트림으로 바꿔줘야 한글이 안깨짐

      // 자 보조스트림이 등장했습니다, 그럼 지금까지 쓴건 다 기본스트림입니다
      // 보조스트림이 뭔가? 보조스트림은 기본스트림을 보조해주는게 보조스트림이다
      // 기본 스트림이 이제 새로운 기능을 안에다가 더 구현하고 싶은거야
      // 근데 그걸 그냥 기본스트림을 뜯어고치지 않고 보조스트림을 하나 새로 만들었어

      FileWriter fw = null; // 파일 출력용 문자기반 스트림

      try {
    	  
    	  fw = new FileWriter("e:/D_Other/testChar.txt");
    	  
    	  int data = 0;
    	  
    	  System.out.println("아무거나 입력하세요...(입력후 Ctrl + z 눌러 주세요.)");
    	  
    	  //콘솔에서 입력할 때 입력끝 표시는 Ctrl + z 키를 누르면 된다.
    	  while((data = isr.read()) != -1) {
    		  fw.write(data);	//콘솔에서 입력받은 값을 파일에 저장하기 	  
    	  }
    	  System.out.println("작업 끝....");

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            fw.close();
            isr.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}