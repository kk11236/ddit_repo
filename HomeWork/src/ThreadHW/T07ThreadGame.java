package ThreadHW;

import javax.swing.JOptionPane;

//컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
//
//컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
//사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다.
//
//입력시간은 5초로 제한하고 카운트 다운을 진행한다.
//5초안에 입력이 없으면 게임을 진 것으로 처리한다.
//
//5초안에 입력이 완료되면 승패를 출력한다.
//
//결과예시)
//=== 결 과 ===
//컴퓨터 : 가위
//당 신 : 바위
//결 과 : 당신이 이겼습니다.

public class T07ThreadGame {
	public static boolean inputCheck = false;
	public static String man = "";
	
	public static void main(String[] args) {
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3);
		String com = data[index];
		
		Thread gt = new GameTimer();
		gt.start();
	}
	
}

class GameTimer extends Thread{
	
	@Override
	public void run() {
		for(int i = 5; i >=1; i--) {
			System.out.println(i);
		}
	}
	
	
}

