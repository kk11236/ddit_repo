package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class T03LambaTest {
	public static void main(String[] args) {
		
		List<String> strList = new ArrayList<String>();
		
		strList.add("김태영");
		strList.add("박지혜");
		strList.add("송예주");
	
		for(String str : strList) {
			System.out.println(str);
		}
		
		System.out.println("-----------------------------------------");
		
		//////////////////////////////////////////////////////
		
	    // forEach라는 메소드가 있어 forEach는 객체로 Consumer를 요구해,
	    // Consumer = 추상메소드가 1개인 인터페이스를 구현한 객체

		// 자바는 객체지향이지만 코드지향적?으로 코딩한것
		strList.forEach(name -> System.out.println(name));
		
		System.out.println("-----------------------------------------");
		
		//위에는 람다식, 아래는 풀구현, forEach는 consumer라는 메소드를 갖고있다.
		
		strList.forEach(new Consumer<String>() {
			@Override
			public void accept(String name) {
				System.out.println(name);				
			}
		});
		
		//로또번호 출력
		System.out.println();
		new Random().ints(1, 46).distinct().limit(6).sorted()
				.forEach(num -> System.out.print(num + " "));
		
		
	}

}
