package co.ham;

import java.util.Scanner;

public class HamManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		//1. 메뉴 관리
		HamBurGerMenu menudao = new HamBurGerMenu();
		//2. 가게 관리
		
		while(run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1.메뉴 관리     /     2.가게 관리     /    3. 매출관리     /     4.종료하기");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num = Integer.parseInt(sc.nextLine());
			
			switch(num){
			case 1:
				menudao.BurGerMenu();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				sc.close();
				run = false;
				System.out.println("종료 되었습니다");
				break;
			}
		}
	}
}
