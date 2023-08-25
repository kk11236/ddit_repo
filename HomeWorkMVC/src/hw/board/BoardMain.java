package hw.board;

import java.util.List;
import java.util.Scanner;

import hw.board.service.BoardServiceImpl;
import hw.board.service.IBoardService;
import hw.board.vo.BoardVO;

public class BoardMain {

	private Scanner sc;

	private IBoardService boardService;

	public BoardMain() {
		sc = new Scanner(System.in);
		boardService = new BoardServiceImpl();
	}

	public static void main(String[] args) {

		BoardMain boardMain = new BoardMain();
		boardMain.start();

	}

	private void start() {

		int choice = 0;

		do {
			displaymenu();
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				selectAll();
				break;
			case 2:
				newPost();
				break;
			case 3:
				modifyPost();
				break;
			case 4:
				deletePost();
				break;
			case 5:
				searchPost();
				break;
			case 6:
				end();
				break;
			default:
				System.out.println("잘못누르셨습니다.");
				break;

			}

		} while (choice != 6);

	}

	private void end() {
		System.out.println("프로그램이 종료됩니다.");
		System.exit(0);

	}

	private void searchPost() {
		String writer = "";
		
		
		boolean isExist = false;
				
		do{
			
			System.out.println("작성했던 이름을 입력해주세요.");
			System.out.print("이름 >> ");
			writer = sc.next();
			
			isExist = boardService.checkWriter(writer);
			
			if(!isExist) {
				System.out.println(writer + "은 존재하지 않는 작성자 입니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		
		BoardVO bv = new BoardVO(writer);
			
		List<BoardVO> boardList = boardService.searchPost(bv);
		
		System.out.println("================================================");
		System.out.println("\t번호\t제목 \t작성자 \t내용\t작성일자");
		System.out.println("================================================");
		
		if(boardList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			for(BoardVO bv2 : boardList) {
				System.out.println(bv2.getBoardNum() + "\t" + bv2.getTitle()
				+ "\t\t" + bv2.getWriter() + "\t" + bv2.getContent() + "\t" + bv2.getRegDt());
			}
		}
		
		

	}

	private void deletePost() {
		boolean isExist = false;
		
		String writer = "";
		
		do {
			System.out.println("");
			System.out.println("작성했던 이름을 입력해주세요.");
			System.out.println("이름 >> ");
			
			writer = sc.next();
			
			isExist = boardService.checkWriter(writer);
			
			if(!isExist) {
				System.out.println("존재하지 않는 작성자 입니다.");
				System.out.println("다시 입력해주세요.");
				return;
			}
		}while(!isExist);
		
		int cnt = boardService.removePost(writer);
		
		if(cnt > 0) {
			System.out.println(writer + "의 게시글을 삭제하였습니다.");
		}else {
			System.out.println(writer + "의 게시글 삭제 실패!!!");
		}

	}

	private void modifyPost() {
		
		boolean isExist = false;
		
		String writer = "";
		
		do {

			System.out.println("");
			System.out.println("작성했던 이름을 입력해주세요.");
			System.out.print("이름 >> ");
			writer = sc.next();
			
			isExist = boardService.checkWriter(writer);
			
			if(!isExist) {
				System.out.println(writer + "은 존재하지 않는 작성자 입니다.");
				return;
			}

		} while (!isExist);
		
		System.out.print("제목 >> ");
		String title = sc.next();
		

		sc.nextLine();
		
		System.out.print("내용 >> ");
		String content = sc.nextLine();
		
		BoardVO bv = new BoardVO(title, writer, content);
		
		int cnt = boardService.modifyPost(bv);
		
		if(cnt > 0) {
			System.out.println(writer + "게시글을 수정했습니다.");
		}else {
			System.out.println(writer + "게시글 수정실패!!!");
		}
				

	}

	private void newPost() {
		
		System.out.println("==========게시판 새글 작성==========");
		System.out.print("제목 : ");
		String title = sc.next();
		System.out.print("작성자 이름 : ");
		String writer = sc.next();

		sc.nextLine(); // 입력버퍼 제거

		System.out.print("내용 : ");
		String content = sc.nextLine();
		
		BoardVO bv = new BoardVO(title, writer, content);
		
		int cnt = boardService.registPost(bv);
		
		if(cnt > 0) {
			System.out.println("게시글이 등록 되었습니다.");
			System.out.println("");
		}else {
			System.out.println("게시글이 등록되지 않았습니다ㅜㅜ..");
			System.out.println("");
		}

	}

	private void selectAll() {

		System.out.println("================================================");
		System.out.println("\t번호\t제목 \t작성자 \t내용\t작성일자");
		System.out.println("================================================");
		
		List<BoardVO> boardList = boardService.selectAll();
		
		if(boardList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			for(BoardVO bv : boardList) {
				System.out.println(bv.getBoardNum() + "\t" + bv.getTitle()
				+ "\t\t" + bv.getWriter() + "\t" + bv.getContent() + "\t" + bv.getRegDt());
			}
		}
		
		System.out.println("================================================");
		System.out.println("");
		

	}

	private void displaymenu() {
		System.out.println("");
		System.out.println("==========게시판==========");
		System.out.println("1.전체 목록 출력");
		System.out.println("2.새글작성");
		System.out.println("3.수정");
		System.out.println("4.삭제");
		System.out.println("5.검색");
		System.out.println("6.종료");
		System.out.print("메뉴 선택 >> ");

	}

}
