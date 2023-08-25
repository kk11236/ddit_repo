package kr.or.ddit.basic;

public class T01ArgsTest {
	
	/*
	 * 가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다.
	 * 	- 특정한 타입의 데이터가 몇개가 올 지 모를 때 사용한다.
	 *  - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	 *  - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	 */    
		
	//인수 = 실제 파라미터(매게변수)로 들어 온 값
	//배열을 이용한 메서드
	public int sumArr(int[]data) {		
		
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			
			sum += data[i];
		}
		
		return sum;
	} 
	
	//가변형 인수를 이용한 메서드
	//가변형 인수 문법 (타입... 매게변수)
	//... = 사용자가 원하는 갯수만큼 넣을 수 있다. 데이터의 갯수가 정해지지 않았을 때 사용한다.
	
	public int sumArgs(int...data) {
		
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			
			sum += data[i];
		}
		
		return sum;
	} 
	
	/*
	 * 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야한다.
	 */	
	public String sumArg2(String name, int...data) {
		
		int sum = 0;
		
		for(int i = 0; i < data.length; i++) {
			
			sum += data[i];
		}
		
		return name + "씨 점수 : "  + sum;
	}
		
	public static void main(String[] args) {
		
		T01ArgsTest arg = new T01ArgsTest();		
		
		int[] nums = {100,200,300};
		System.out.println(arg.sumArr(nums));
		System.out.println(arg.sumArr(new int[] {1,2,3,4,5}));
		System.out.println();
		
		System.out.println(arg.sumArgs(100,200,300));
		System.out.println(arg.sumArgs(2,3,4,5));
		System.out.println();
		
		System.out.println(arg.sumArg2("홍길동", 1,2,3,4,5,6,7,8));
				
		
	}

	

}
