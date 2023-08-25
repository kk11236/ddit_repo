package ListHW;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hotel1 {

	public static void main(String[] args) {
		new Hotel1().hotelStart();

	}

	Map<String, HotelVO> hotelMap = new HashMap<String, HotelVO>();

	Scanner sc = new Scanner(System.in);

	private void hotelStart() {
		System.out.println("================================");
		System.out.println("호텔문을 열었습니다.");
		System.out.println("================================");

		int cus = 0;

		while (true) {

			displayMenu();

			switch (cus) {
			case 1:
				//checkIn();
				break;
			case 2:
				//checkOut();
				break;
			case 3:
				//room();
				break;
			case 4:
				//end();
				break;
			default:
				System.out.println("잘못누르셨습니다.");

			}
		}

	}

	private void displayMenu() {
		
		
	}

}
