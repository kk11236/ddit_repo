package kr.or.ddit.basic;

public class T20WaitNotifyTest {
	
   public static void main(String[] args) {
	   
      DataBox db = new DataBox();
      
      Thread producerTh = new ProducerThread(db);
      Thread consumerTh = new ConsumerThread(db);
      
      producerTh.start();
      consumerTh.start();
      
   }
}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
   private String data;

   // data가 null이 아닐 때(DataBox에 데이터가 있을 때) data값을 반환하는 메서드
   public synchronized String getData() {
      System.out.println(Thread.currentThread().getName() + " : getData() 메서드 진입");
      
      if(this.data == null) {
         try {
            System.out.println(Thread.currentThread().getName() + " : getData()메서드 안에서 wait()호출");
            wait(); // 일시정지
         } catch (InterruptedException e){
            e.printStackTrace();
         }
      }
      
      String returnData = this.data;
      System.out.println("읽어온 데이터 : " + returnData);
      this.data = null; // 데이터 제거
      
      System.out.println(Thread.currentThread().getName() + " : getData() 메서드 안에서 notify() 호출");
      notify();
      
      System.out.println(Thread.currentThread().getName() + " getData() 메서드 끝");
      
      return returnData;
   }
   
   // data가 null일 경우에만 데이터를 세팅하는 메서드
   public synchronized void setData(String data) {
      System.out.println(Thread.currentThread().getName() + " : setData() 메서드 진입");
      
      if(this.data != null) {
         try {
            System.out.println(Thread.currentThread().getName() + " : setData() 메서드 안에서 wait()호출");
            wait();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      
      this.data = data;
      System.out.println("세팅한 데이터 : " + this.data);
      System.out.println(Thread.currentThread().getName() + " : setData() 메서드 안에서 notify() 호출");
      /* 데이터가 wait으로  대기실에 있으면 세팅하는놈이 notify()로 꺠워야 다시 세팅가능, 이게 이 예제의 목적*/
      /* 쓰레드가 효울적인 작업을 위해 꺼내는 입장에선 데이터가 없으면 대기실에서 기다리고 세팅한는놈이 세팅했으니까 가져가도돼~라고 notify로 알리는거임*/
      notify();
      
      System.out.println(Thread.currentThread().getName() + " : setData() 메서드 끝");
   }
}

// 데이터를 세팅만 하는 스레드
class ProducerThread extends Thread{
   
   private DataBox dataBox;
   
   public ProducerThread(DataBox dataBox) {
      super("ProducerThread");
      this.dataBox = dataBox;
   }
   
   @Override
   public void run() {
      for(int i = 1; i<=5; i++) {
         String data = "Data-" + i;
         System.out.println(this.getName() + "가 dataBox.setData(" + data + ") 호출하려고 함.");
         dataBox.setData(data);
      }
   }
}

// 데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
   private DataBox dataBox;
   
   public ConsumerThread(DataBox dataBox) {
      this.dataBox = dataBox;
   }
   
   @Override
   public void run() {
      for(int i = 1; i<=5; i++) {
         String data = dataBox.getData();
         System.out.println(this.getName() + "가 dataBox.getData() 호출 후 결과받음 : " + data);
      }
   }
}