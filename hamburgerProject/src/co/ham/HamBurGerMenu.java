package co.ham;

import java.util.List;
import java.util.Scanner;

public class HamBurGerMenu {
	//filed
	
	
	//method
	public void BurGerMenu() {
		boolean run2 = true;
		Scanner sc = new Scanner(System.in);
		HamBurGerDAO dao = new HamBurGerDAO();
		while(run2) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1 메뉴 목록 / 2 메뉴 추가 / 3 메뉴 수정 / 4 메뉴 삭제 / 5.메뉴 정보 / 6 뒤로 가기");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num2 = Integer.parseInt(sc.nextLine());
			switch(num2) {
			case 1:
				List<HamBurGer> lists = dao.hamburgerList();
				System.out.println("		              햄버거 가게                                        ");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("	햄버거 이름 	칼로리		출시 날짜		가격");
				System.out.println("-----------------------------------------------------------------------");
				for(HamBurGer hbg : lists) {
					System.out.println(hbg.toString());
				}
				break;
			case 2:
				System.out.print("햄버거 이름 > ");
				String name = sc.nextLine();
				System.out.print("칼로리 > ");
				int kcal = Integer.parseInt(sc.nextLine());
				System.out.print("출시날짜 > ");
				String hire_date = sc.nextLine();
				System.out.print("가격 > ");
				int price = Integer.parseInt(sc.nextLine());
				System.out.print("재료 > ");
				String studff = sc.nextLine();
				HamBurGer bgr = new HamBurGer();
				
				bgr.setHam_Name(name);
				bgr.setHam_Kcal(kcal);
				bgr.setHam_Hire_Date(hire_date);
				bgr.setHam_Price(price);
				bgr.setHam_Stuff(studff);
				
				if(dao.inserthamburger(bgr)) {
					System.out.println("햄버거 추가 성공!");
				}else{
					System.out.println("햄버거 추가 실패...");
				};
				break;
			case 3:
				System.out.print("메뉴 수정할 햄버거 > ");
				name = sc.nextLine();
				System.out.print("가격 > ");
				price = Integer.parseInt(sc.nextLine());
				
				bgr = new HamBurGer();
				bgr.setHam_Name(name);
				bgr.setHam_Price(price);
				
				if(dao.updateHamBurGer(bgr)) {
					System.out.println("햄버거 메뉴 수정 성공!");
				}else{
					System.out.println("햄버거 메뉴 수정 실패...");
				};
				break;
			case 4:
				System.out.print("메뉴 삭제할 햄버거 > ");
				name = sc.nextLine();
				if(dao.deleteHamBurGer(name)) {
					System.out.println("햄버거 메뉴 삭제 성공!");
				}else {
					System.out.println("햄버거 메뉴 삭제 실패...");
				}
				break;
			case 5:
				System.out.print("선택할 햄버거 > ");
				name = sc.nextLine();
				List<HamBurGer> selectHbg = dao.showHamBurGer(name);
				for(HamBurGer hbg : selectHbg) {
					System.out.printf("햄버거 : %s\t\t 가격 : %d\n",hbg.getHam_Name(),hbg.getHam_Price());
					System.out.printf("칼로리 : %d(kcal)\t 출시일짜 : %s\n",hbg.getHam_Kcal(),hbg.getHam_Hire_Date());
					System.out.printf("재료 : %s\n",hbg.getHam_Stuff());
				}
				break;
			case 6:
				run2 = false;
				break;
			}
		}
	}
}
