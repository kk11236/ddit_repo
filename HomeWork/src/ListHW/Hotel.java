package ListHW;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {

	public static void main(String[] args) {

		new Hotel().hotelStart();

	}

	Scanner sc = new Scanner(System.in);
	int cus = 0;

	private Map<String, HotelVO> hotelMap = new HashMap<String, HotelVO>();
	

	public void hotelStart() {

		System.out.println("**************************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************************");

		while (true) {
			
			displayMenu();
			
			switch (cus) {

			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				room();
				break;
			case 4:
				end();
				break;
			default:
				System.out.println("잘못 누르셨습니다.");
				break;

			}
		}

	}


	public void displayMenu() {

		System.out.println("**************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("**************************");

		System.out.print("번호를 입력하세요 : ");
		cus = sc.nextInt();

	}


	private void checkIn() {

		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호를 입력 해주세요 : ");
		String roomNum = sc.next();

		if (hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름을 입력해주세요 : ");
		String name = sc.next();

		HotelVO hv = new HotelVO(roomNum, name);

		hotelMap.put(roomNum, hv);

		System.out.println(roomNum + "호 체크인 완료");
	}
	

	private void checkOut() {

		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호를 입력 해주세요 : ");
		String roomNum = sc.next();

		if (hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum + "호는 체크인한 사람이 없습니다.");
		} else {
			System.out.println(roomNum + "는 체크아웃 되었습니다.");
		}

	}
	

	private void room() {

		System.out.println("==================================");
		System.out.println("\t방번호\t이름");
		System.out.println("==================================");

		Set<String> keySet = hotelMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("체크인된 방이 없습니다");
		} else {
			int num = 0;

			for (String room : keySet) {
				num++;
				HotelVO hv = hotelMap.get(room);

				System.out.println("" + num + "\t" + hv.getRoomNum() + "\t" + hv.getName());
			}
		}

	}

	private void end() {
		System.out.println("호텔문을 닫았습니다.");
		System.exit(0);
		
	}
}

class HotelVO {
	private String roomNum;
	private String name;

	public HotelVO(String roomNum, String name) {
		this.roomNum = roomNum;
		this.name = name;

	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HotelVO [방번호 =" + roomNum + ", 이름 =" + name + "]";
	}

}
