package kr.or.ddit.basic;

class NonGenericClass{
	
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
		
}

class MyGeneric<T>{		//선언하는 부분이기 때문에 <T>라고 쓴거.
	private T val;
	/**기분이 좋을때 쓰는 클래스
	 * @pram T
	 * @return val
	 **/
	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
		
}


public class T03GenericClassTest {
	/*
	 제너릭 클래스를 만드는 방법
	 
	 형식)
	 	class 클래스명<제너릭타입글자> {
	 	
	 		제너릭타입글자 변수명; //변수선언에 제너릭을 사용할 경우
	 		...
	 		
	 		제너릭타입글자 메서드명(){	//반환값이 있는 메서드에서 사용하는 경우
	 			...
	 			
	 			return 값;
	 		}
	 		
	 		...
	 			 	
	 	}
	 	
	 --제너릭타입글자--
	 T => Type
	 K => Key
	 V => Value
	 E => Element	//요소,원소 List에 담는 요소,원소 등등
	 */
	
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnVal1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 rtnVal1 => " + rtnVal1);
		
		Integer rtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수형 반환값 rtnVal2 => " + rtnVal2);
		System.out.println();
		
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnVal1 = mg1.getVal();
		rtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnVal1);
		System.out.println("제너릭 정수형 반환값 : " + rtnVal2);
	}

	
}
