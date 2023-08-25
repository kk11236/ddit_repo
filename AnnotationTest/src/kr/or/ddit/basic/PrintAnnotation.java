package kr.or.ddit.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*	
 	Annotaion에 대하여...
 	
 	주석 같은 이놈이.. 다른 필요로하는 사용자?에게 정보를 제공해 준다.
 	
 	프로그램 소스코드 안에서 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨것.
 	(JDK1.5부터 지원함.) 주석처럼 프로그램에 영향을 미치지 않으면서, 이를 필요로 하는
 	프로그램에게는 유용한 정보를 제공해 줄 수 있다.
 	
 	종류 : 1. 표준 애너테이션
 		 2. 메타 애너테이션 (애너테이션을 위한 애너테이션, 즉 애너테이션을 정의할 때 사용하는 애너테이션)
 	
 	애너테이션 타입 정의하기
 	
 	@interface 애너테이션이름 {
 		요소타입 타입요소이름();	//반환값이 있는 매게변수는 없는 추상메서드의 형태
 			...
  	}
  	
  	애너테이션 요소의 규칙
  	1. 요소타입은 기본형, String, enum, annotaion, class만 허용된다.
  	2. ()안에 매개변수를 선언 할 수 없다.
  	3. 예외를 선언 할 수 없다.
  	4. 요소타입에 제너릭타입글자를 사용할 수 없다.
  	 	
 */

@Target({ElementType.METHOD, ElementType.FIELD})	//소스코드를 붙이는 위치를 지정해줄 수 있음.
@Retention(RetentionPolicy.RUNTIME) // 기본값 : CLASS 유지기간을 우리가 지정해 줄 수 있음  종류 : 소스,클래스,런타임
public @interface PrintAnnotation {
	
	int id = 100; // 상수 선언가능. static final int = 100;

	String value() default "-"; // 기본값을 '-'로 설정

	int count() default 20;
	
}
