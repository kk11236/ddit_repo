package kr.or.ddit.basic;

public class T04ThreadTest {
	public static void main(String[] args) {
	/*
		1~20억까지의 합계를 구하는데 걸린 시간 체크해 보기
		전체 합계를 구하는 작업을 단독으로 했을 때(1개의 스레드를 이용할 때)와
		여러 스레드로 분할해서 작업할 때의 시간을 확인해 보기
	*/	
		//단독으로  처리할 때..
		
		SumThread sumTh = new SumThread(1, 2000000000);
		
		long startTime = System.currentTimeMillis();
		
		sumTh.start();
		
		try {
			
			sumTh.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독을 처리했을 때의 처리 시간 : " + (endTime - startTime) + "(ms)");
		
		//여러 스레드로 나누어 계산해 보기
		//단점 : 복잡하다.디버깅이 어렵다.
		SumThread[] sumThs = new SumThread[] {
			new SumThread(         1L,  500000000L),	
			new SumThread( 500000001L, 1000000000L),	
			new SumThread(1000000001L, 1500000000L),	
			new SumThread(1500000001L, 2000000000L)	
		};
		
		startTime = System.currentTimeMillis();
		for(Thread th : sumThs) {
			th.start();
		}
				
		for(Thread th : sumThs) {
			try {
				th.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("여러개의 스레드로 나누어 처리했을 때 걸린 시간 : " + (endTime - startTime) + "(ms)");
		
	}
		
}

class SumThread extends Thread{
	
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		
		System.out.println(min + "~" + max + "까지의 합계 : " + sum);
	}
	
}
