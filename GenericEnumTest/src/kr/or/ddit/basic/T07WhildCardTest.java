package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T07WhildCardTest {

	// 장바구니 항목조회를 위한 메서드 (모든 타입의 장바구니허용)
	public static void displatCartItenInfo(Cart<?> cart) {
		System.out.println(" = 음식류 장바구니 항목 리스트  = ");

		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("------------------------------------");
	}

	// 장바구니 항목조회를 위한 메서드 (음료나 그 하위 타입의 장바구니허용)
	public static void displatCartItenInfo2(Cart<? extends Drink> cart) {
		System.out.println(" = 음식나 그 하위 타입의 장바구니 항목 리스트  = ");

		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("------------------------------------");
	}

	// 장바구니 항목조회를 위한 메서드 (고기나 그 상위 타입의 장바구니허용)
	public static void displatCartItenInfo3(Cart<? super Meat> cart) {
		System.out.println(" = 고기나 그 상위 타입의 장바구니 항목 리스트  = ");

		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("------------------------------------");
	}
	
	
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<>();
		
		foodCart.add(new Meat("돼지고기", 5000));
		foodCart.add(new Meat("소고기", 50000));
		foodCart.add(new Juice("오렌지쥬스", 2000));
		foodCart.add(new Coffee("마끼아또", 4000));
		
		Cart<Meat> meatCart = new Cart<>();
		meatCart.add(new Meat("돼지고기", 5000));
		meatCart.add(new Meat("소고기", 50000));
		
		Cart<Drink> drinkCart = new Cart<>();
		drinkCart.add(new Juice("오렌지쥬스", 2000));
		drinkCart.add(new Coffee("마끼아또", 4000));
		
		displatCartItenInfo(foodCart);
		displatCartItenInfo(meatCart);
		displatCartItenInfo(drinkCart);
		
		//displatCartItenInfo2(foodCart);
		//displatCartItenInfo2(meatCart);
		displatCartItenInfo2(drinkCart);
		
		displatCartItenInfo3(foodCart);
		displatCartItenInfo3(meatCart);
		//displatCartItenInfo3(drinkCart);

		
	}

}

class Food {
	private String name; // 음식이름
	private int price; // 음식가격

	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}

}

class Meat extends Food {

	public Meat(String name, int price) {
		super(name, price);
	}

}

class Drink extends Food {

	public Drink(String name, int price) {
		super(name, price);

	}

}

class Juice extends Drink {

	public Juice(String name, int price) {
		super(name, price);

	}

}

class Coffee extends Drink {

	public Coffee(String name, int price) {
		super(name, price);

	}

}

/**
 * 장바구니
 * 
 * @param <T>
 */

class Cart<T> {
	private List<T> list;

	public Cart() {
		list = new ArrayList<T>();
	}

	public void add(T item) {
		list.add(item);
	}

	public List<T> getList() {
		return list;
	}

}