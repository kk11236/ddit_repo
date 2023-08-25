package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

//프로그램을 만들면 파일채로 줄 때가 있으면 그 걸 이제 가져와서 쓰는용도?
//자바에서 내가 설정한 경로에 폴더,파일을 만드는 방법


public class T01FileTest {
	
	public static void main(String[] args) throws IOException {
		// File객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로명)
		// => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
		File file = new File("e:/D_Other/test.txt");
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일여부 : " + file.isFile());
		System.out.println("디렉토리(폴더)여부 : " + file.isDirectory());
		
		System.out.println("-----------------------------------");
		
		File file2 = new File("e:/D_Other");
		System.out.print(file2.getName() + "은 " );
		if(file2.isFile()) {
			System.out.println("파일");
		}else if(file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		
		System.out.println("-------------------------------------");
		
		// 2. new File(File parent, String child)
		// => 'parent' 디렉토리 안에 있는 'child' 파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량(크기) : " 
				+ file3.length());
		
		// 3. new File(String parent, String child)
		// .하나는 현재 경로
		//상대경로 : 변할 수 있는 경로
		//절대경로 : 절대 변하지 않는 경로 
		
		File file4 = new File(".\\D_Other\\test\\..", "test.txt");
		//운영체제가 window일 때는 D:, E: 등으로 시작함
		//window가 아니면 / 로 시작함 /<--- 절대경로
		//상대경로는 .으로 시작함.
		System.out.println("절대경로 : " + file4.getAbsolutePath());		
		System.out.println("상대경로 : " + file4.getPath());
		
		//javaIO에서 현재프로젝트 경로가 현재라고 생각하고 있다.
		//절대경로와 같지만 ..같은 것을 제거해버린 것
		System.out.println("표준경로 : " + file4.getCanonicalPath());
		System.out.println("-------------------------------------------");
		
		//절대경로보다 상대경로를 쓰면 좋은점 프로젝트 폴더가 있고 그 안에서 
		//image폴더를 만들고 그 안에 이미지를 쓴다고 치면 이미지를 어느폴더에 옮겨놔도 쓸수 있다.	
		//경로가 변하지 않는 경우는 절대경로를 쓰는 게 맞는 거고 경로가 변할 가능성이 있으면 상대경로를 쓰는 게 더 낫다.
		//file4 = new File("E:\\highjava\\workspace\\JavaIOTest\\src\\img\\hello.jpg");
		//file4 = new File(".\\src\\img\\hello.jpg");
		
		/*
		 	디렉토리(폴더) 만들기
		 	
		 	1.mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다. 
		 		중간의 경로가 모두 미리 만들어져 있어야 한다.
		 	
		 	2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다.
		 	
		 	=> 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환함.
		*/
		
		File file5 = new File("e:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		}else {
			System.out.println(file5.getName() + " 만들기 실패!!!");
		}
		
		File file6 = new File("e:/D_Other/test/java/src");
		if(file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공!");
		}else {
			System.out.println(file6.getName() + " 만들기 실패!!!");
		}
		
	}

}
