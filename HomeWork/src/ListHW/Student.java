package ListHW;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Student {

	public static void main(String[] args) {

		List<Member> list = new ArrayList<Member>();

		list.add(new Member("11", "김태일", 80, 90, 100));
		list.add(new Member("55", "김태오", 90, 95, 88));
		list.add(new Member("33", "김태삼", 97, 79, 70));
		list.add(new Member("22", "김태이", 80, 100, 90));
		list.add(new Member("44", "김태사", 89, 86, 90));

		Collections.sort(list);

		System.out.println("학번의 오름차순 정렬");

		for (Member mem : list) {
			list.get(list.indexOf(mem)).setRank(list.indexOf(mem) + 1);
			System.out.println(mem);			
		}
		
		System.out.println();

		Collections.sort(list, new totalScore());
		System.out.println("총점의 내림차순 정렬");

		for (Member mem : list) {
			
			list.get(list.indexOf(mem)).setRank(list.indexOf(mem) + 1);
			
			System.out.println(mem);
		}

	}

}

class Member implements Comparable<Member> {

	private String id; // 학번
	private String name; // 이름
	private int kor;
	private int eng;
	private int math;
	private int totalScore;
	private int rank;


	public Member(String id, String name, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.totalScore = kor + eng + math;
	}

	public String getId() {
		return id;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int gettotalScore() {

		return totalScore;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", totalScore=" + totalScore + " rank=" + rank + "]";
	}

	@Override
	public int compareTo(Member mem) {

		return this.id.compareTo(mem.getId());
	}

}

//외부정렬자
class totalScore implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		
		if(mem1.gettotalScore() == mem2.gettotalScore()) {
			
			 return mem1.getId().compareTo(mem2.getId()) * -1;
			
		}
			 return new Integer(mem1.gettotalScore()).compareTo(mem2.gettotalScore()) * -1;
		}
	}

