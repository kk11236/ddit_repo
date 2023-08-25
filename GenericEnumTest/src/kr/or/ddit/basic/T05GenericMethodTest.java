package kr.or.ddit.basic;

class Util2{
	
	// 제한 된 타입 파라미터 문법
	// Number 이거나 Number를 extends한 하위 타입만 가능하다.
	//extends 를 써서 타입에 제한을 건 것임.
	
	public static <T extends Number> int compare(T t1, T t2) {
		
		double v1 = t1.doubleValue();	//숫자면 됨.
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
	
}

public class T05GenericMethodTest {
	
	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.<Number>compare(3.14, 3);
		System.out.println(result2);
		
		//Util2.compare(3, "java"); //에러 발생
	}

}
