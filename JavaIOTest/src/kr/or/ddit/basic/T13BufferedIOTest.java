package kr.or.ddit.basic;

import java.io.*;

public class T13BufferedIOTest {
   public static void main(String[] args) {
      
      FileReader fr = null;
      // 보조스트림, 기반스트림 필요
      BufferedReader br = null;
      
      try {
         
//         fr = new FileReader("src/kr/or/ddit/basic/T12BufferedIOTest.java");
//         fr = new FileReader("src/_0802/T12BufferedIOTest.java");
//         br = new BufferedReader(fr);
//         
//         int data = 0;
//         
//         while((data = br.read()) != -1) {
//            System.out.print((char)data);
         
//         BufferedReader를 이용해 읽기
         fr = new FileReader("src/kr/or/ddit/basic/T12BufferedIOTest.java");
//         fr = new FileReader("src/_0802/T12BufferedIOTest.java");
         br = new BufferedReader(fr);
         
         String tmpStr = "";
         
         // readLine()은 한줄한줄 읽고 그 한줄을 String 타입으로 return하다가 다음 값이 없으면 null을 리턴한다
         while((tmpStr = br.readLine()) != null) {
            System.out.println(tmpStr);
         }
         
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            fr.close();
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }
}