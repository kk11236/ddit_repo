package kr.or.ddit.basic;

import java.io.*;

/*
 * 객체 입출력 보조 스트림(직렬화와 역직렬화)
 */

public class T16ObjectStreamTest {
   public static void main(String[] args) {
      // Member 인스턴스 생성
      Member mem1 = new Member("레이", 20, "서울");
      Member mem2 = new Member("리즈", 20, "제주");
      Member mem3 = new Member("원영", 20, "대전");
      Member mem4 = new Member("이서", 18, "경기");

      ObjectOutputStream oos = null;
      
      try {
         
         oos = new ObjectOutputStream(
        		 new BufferedOutputStream(
        		 new FileOutputStream("e:/D_Other/memObj.bin")));
         
         // 쓰기 작업
         oos.writeObject(mem1); // 직렬화
         oos.writeObject(mem2);
         oos.writeObject(mem3);
         oos.writeObject(mem4);
         // 파일에 객체를 저장하려면 Serializable이 필요
         
         // 직렬화란 ?
         // 안에 있는 데이터들을 쭉 늘어뜨리는 작업?
         // 레/이/2/0/서/울 이런식으로 쭉 늘어뜨려서 저장하는 그런 느낌임 파일에 한땀한땀 write해주는 느낌
         // 이렇게 객체를 직렬화 해줘야 정상적인 IO 작업이 일어난다
         // 꺼내올때 보조스트림이 알아서 그 직렬화된? 자료들을 읽어서 다시 객체로 만들어서 가져옴(역직렬화)
         
         System.out.println("객체 쓰기 작업 완료");

         
      } catch (IOException e) {
         e.printStackTrace();
         try {
            oos.close();
         } catch (IOException e2) {
            e2.printStackTrace();
         }
      }
      
      ///////////////////////////////////
      
      ObjectInputStream ois = null;
      
      try {
         
         ois = new ObjectInputStream(
        		 new BufferedInputStream(
        		 new FileInputStream("e:/D_Other/memObj.bin")));
         
         Object obj = null;
         
         //readObject() 역질렬화
         while((obj = ois.readObject()) != null) {
        	 
        	 // 읽어온 데이터를 원래의 객체형으로 변환후 사용한다.
        	 Member mem = (Member) obj;
        	 System.out.println("이름 : " + mem.getName());
        	 System.out.println("나이 : " + mem.getAge());
        	 System.out.println("주소 : " + mem.getAddr());
        	 System.out.println("------------------------");
         }
         
      } catch (IOException e) {
         //e.printStackTrace();
         System.out.println("출력 완료!!!");
      } catch (ClassNotFoundException e) {
		 e.printStackTrace();
	} finally {
         try {
            ois.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }
}

/*
 * 회원정보를 담기위한 VO
 */
class Member implements Serializable {
   // 자바는 Serializable 인터페이스를 구현한 객체만 직렬화 할 수 있도록 제한하고 있음
   // 해당 인터페이스에는 아무 메소드도 없지만 Serializable을 구현해야만 직렬화가 가능
	
	// transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	//				직렬화가 되지 않는 멤버변수는 기본값으로 저장됩다.
	//				(참조변수: null, 숫자형변수: 0)
	//				(*static 필드도 직렬화가 되지 않는다.)
	//				IO작업을 할 때 민감한 정보들은 객체안에 담지 않는게 좋다 (ex:비밀번호,주민번호 등)
	
   
   transient private String name;
   transient private int age;
   private String addr;
   
   public Member(String name, int age, String addr) {
      super();
      this.name = name;
      this.age = age;
      this.addr = addr;
   }
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getAge() {
      return age;
   }
   public void setAge(int age) {
      this.age = age;
   }
   public String getAddr() {
      return addr;
   }
   public void setAddr(String addr) {
      this.addr = addr;
   }
}