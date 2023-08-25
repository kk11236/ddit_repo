package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {
/*
 	와일드 카드에 대하여...
 	
 	알 수 없는 타입을 표현하는 하나의 타입<?>
 	
 	사용하는 시점까지 정확히 어떤 타입인지 알 수 없기 때문에 와일드 카드를 쓴다.
 	
 	와일드 카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의
 	인수(argument)로서, 변수선언, 객체생성 및 메서드 정의할 때 사용된다.
 	
 	
 	<? extends T> => 와일드 카드와 상한 제한. T와 그 자손들만 가능.
 	<? super T>	  => 와일드카드의 하한 제한. T와 그 조상들만 가능.
 	<?>			  => 허용가능한 모든타입 가능.
*/

	public static void main(String[] args) {
		
		List<?> list = new ArrayList<Integer>();

		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 다이아몬드 문법
		FruitBox<Apple> appleBox = new FruitBox<>();

		fruitBox.add(new Apple());
		fruitBox.add(new Grape());

		System.out.println(fruitBox.getFruitList());

		appleBox.add(new Apple());
		appleBox.add(new Apple());
		// appleBox.add(new Grape());

		System.out.println(appleBox.getFruitList());

		Juicer.makeJuice(fruitBox);

		Juicer.makeJuice(appleBox);

	}
}

class Juicer {

	//static void makeJuice(FruitBox<Fruit> box) {
	//static <T extends Fruit> void makeJuice(FruitBox<T> box) {
	//static void makeJuice(FruitBox<? extends Fruit> box) {
	static void makeJuice(FruitBox<?> box) {
		String fruitListStr = ""; // 과일 목록

		int cnt = 0;

		for (Fruit f : box.getFruitList()) {

			if (cnt == 0) {
				fruitListStr += f;
			} else {
				fruitListStr += ", " + f;
			}

			cnt++;
		}

		System.out.println(fruitListStr + " => 쥬스 완성!!!");

	}
}

class Fruit {

	private String name;

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}

}

class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {

	public Grape() {
		super("포도");
	}
}

//클래스를 선언하는 시점에 타입제한을 걸어버림.
class FruitBox<T extends Fruit> {

	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	// 과일 담기
	public void add(T fruit) {
		fruitList.add(fruit);
	}

	public List<T> getFruitList() {
		return fruitList;
	}

}
