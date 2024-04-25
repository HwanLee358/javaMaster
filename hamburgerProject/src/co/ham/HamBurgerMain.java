package co.ham;

import java.util.Scanner;

public class HamBurgerMain {
	private static String name_id;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		HamBurGerMenu menu = new HamBurGerMenu();
		HamBurGerOrder order = new HamBurGerOrder();
		HamBurGerlogin login = new HamBurGerlogin();
		HamBurGerSalesUI sales = new HamBurGerSalesUI();  
		while(run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1.메뉴 관리     /     2.가게 관리     /    3. 매출현황     /     4.종료하기");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num = Integer.parseInt(sc.nextLine());
			
			switch(num){
			case 1:
				menu.BurGerMenu();
				break;
			case 2:
				login.HamBurGerloginform();
				name_id = login.name;
				order.HamBurGerOrderform(login.name);
				break;
			case 3:
				sales.HamBurGurSale();
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
