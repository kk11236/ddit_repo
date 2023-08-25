package kr.or.ddit.basic;

import java.io.*;

public class T17NonSerializableParentTest {
   /*
    * 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우 부모객체의 필드값 처리 방법
    * 
    * 1. 부모클래스가 Serializable 인터페이스를 구현하도록 한다 
    * 2. 자식클래스에 writeObject()와 readObject() 메서드를 이용하여 부모객체의 필드값을 직접 처리할 수 있도록 구현한다
    */

   /*
    * 부모의 요소들을 상속시킬 때 자식한테 시키고 싶은거가 있고 시키고 싶지 않은 것이 있을 수가 있어 그리고 부모는 직렬화를 시키고 싶지
    * 않은데 자식은 시키고 싶어 이럴 때가 있을 수가 있어
    */
   public static void main(String[] args) throws Exception{
      
      ObjectOutputStream oos = 
            new ObjectOutputStream(new FileOutputStream("e:/D_Other/nonSerializable.bin"));
      Child child = new Child();
      child.setParentName("부모");
      child.setChildName("자식");
      
      oos.writeObject(child); // 직렬화
      
      oos.close();
      
      // 위는 객체를 만든 후 직렬화를 시켜주는 코드들
      ////////////////////////////////////////
      // 아래는 그 직렬화 된 것들을 꺼내 다시 객체로 맹글어주기
      
      ObjectInputStream ois = 
            new ObjectInputStream(new FileInputStream("e:/D_Other/nonSerializable.bin"));
      Object obj = ois.readObject(); // 역직렬화
      Child c = (Child)obj;
      
      System.out.println("parentName : " + c.getParentName());
      System.out.println("childName : " + c.getChildName());
      // 분명 위에서 parentName에 "부모"를 줬는데 ? 출력이 안됨
      // 여기서 부모를 직렬화(Serializable)하면 부모 값이 출력되긴 한데 나는 지금 자식만 주고싶은거야~
      // 왜? 부모를 직렬화하면 자식들도 다 직렬화가 되니까 그래서 이걸 해결하기 위한 방법이 위에서
      // 2. 자식클래스에 writeObject()와 readObject() 메서드를 이용하여 부모객체의 필드값을 직접 처리할 수 있도록 구현한다
      // 이 방법인거야
      
      
      ois.close();
      
   }
}

class Parent {
   private String parentName;

   public String getParentName() {
      return parentName;
   }

   public void setParentName(String parentName) {
      this.parentName = parentName;
   }
}

class Child extends Parent implements Serializable {
   private String childName;
   
   public String getChildName() {
      return childName;
   }
   
   public void setChildName(String childName) {
      this.childName = childName;
   }
   
   /**
    * 직렬화 될 때 자동으로 호출됨
    * (접근제어자가 private이 아니면 자동호출 되지 않음)
    * @param out
    */
   private void writeObject(ObjectOutputStream out) throws IOException{
	   
	   // 문자열을 먼저 저장을 하고? 오브젝트저장하는 기능으로 넘어간다..
	   out.writeUTF(getParentName());
	   
	   out.defaultWriteObject();

   }
   /**
    * 역직렬화 될 때 자동으로 호출됨.
    * (접근제어자가 private이 아니면 자동호출되지 않음)
    * @param in
    * @throws IOException
    * @throws ClassNotFoundException
    */
   
   private void readObject(ObjectInputStream in)
		   throws IOException, ClassNotFoundException {
	   
	   setParentName(in.readUTF());
	   
	   in.defaultReadObject();
      
   }
}