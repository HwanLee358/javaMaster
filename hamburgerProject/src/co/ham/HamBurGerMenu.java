package co.ham;

import java.util.List;
import java.util.Scanner;

public class HamBurGerMenu {
	//filed
	
	//method
	public void BurGerMenu() {
		boolean run = true;
		Scanner sc = new Scanner(System.in);
		HamBurGerDAO dao = new HamBurGerDAO();
		HamBurGerOrderDAO orderdao = new HamBurGerOrderDAO();
		
		String ham_name = "";
		int kcal = 0;
		int price = 0;
		String ham_date = "";
		String ham_stuff = "";
		
		HamBurGer bgr;
		while(run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1 메뉴 목록 / 2 메뉴 추가 / 3 메뉴 수정 / 4 메뉴 삭제 / 5.메뉴 정보 / 6 뒤로 가기");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num = Integer.parseInt(sc.nextLine());
			switch(num) {
			case 1:
				boolean rum2 = true;
				int page = 1;
				while(rum2) {
					int pageSize = dao.MenuListsize();
					List<HamBurGer> lists = dao.hamburgerList(page);
					int Currentpage = page;
					System.out.println("		              햄버거 가게                                        ");
					System.out.println("-----------------------------------------------------------------------");
					System.out.println("	햄버거 이름 	칼로리		출시 날짜		가격");
					System.out.println("-----------------------------------------------------------------------");
					for(HamBurGer hbg : lists) {
						System.out.println(hbg.toString());
					}
					System.out.println("-----------------------------------------------------------------------");
					
					if(pageSize%5 != 0) {
						page = pageSize/5 + 1;
					}
					for(int i=1; i<=page; i++) {
						if(Currentpage == i) {
							System.out.printf("%d ", i);														
						}else {
							System.out.printf("[%d] ", i);							
						}
					}
					System.out.println();
					System.out.print("페이지 > ");
					page = Integer.parseInt(sc.nextLine());
					if(page == 0) {
						rum2 = false;
					}
				}
				break;
			case 2:
				System.out.print("햄버거 이름 > ");
				String name = sc.nextLine();
				HamBurGerorderdetails order = new HamBurGerorderdetails();
				order.setHam_name(name);

				if (orderdao.HBGmenucheck(order).equals(name)) {
					System.out.println("이미 존재하는 버거입니다");
				}else {
					System.out.print("칼로리 > ");
					kcal = Integer.parseInt(sc.nextLine());
					System.out.print("출시날짜 > ");
					String hire_date = sc.nextLine();
					System.out.print("가격 > ");
					price = Integer.parseInt(sc.nextLine());
					System.out.print("재료 > ");
					String studff = sc.nextLine();
					bgr = new HamBurGer();
					
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
				}
				break;
			case 3:
				System.out.print("메뉴 수정할 햄버거 > ");
				name = sc.nextLine();
				order = new HamBurGerorderdetails();
				order.setHam_name(name);
				if (orderdao.HBGmenucheck(order).equals(name)) {
					System.out.println("1.햄버거명 2.가격 3.칼로리 4.날짜 5.재료");
					System.out.print("선택 > ");
					int menu_up = Integer.parseInt(sc.nextLine());
					bgr = new HamBurGer();
					switch(menu_up) {
					case 1:
						System.out.print("햄버거명 > ");
						ham_name = sc.nextLine();
						bgr.setHam_Name_modify(ham_name);
						bgr.setHam_type(1);
						break;
					case 2:
						System.out.print("가격 > ");
						price = Integer.parseInt(sc.nextLine());
						bgr.setHam_Price(price);
						bgr.setHam_type(2);
						break;
					case 3:
						System.out.print("칼로리 >");
						kcal = Integer.parseInt(sc.nextLine());
						bgr.setHam_Kcal(kcal);
						bgr.setHam_type(3);
						break;
					case 4:
						System.out.print("날짜 > ");
						ham_date = sc.nextLine();
						bgr.setHam_Hire_Date(ham_date);
						bgr.setHam_type(4);
						break;
					case 5:
						System.out.print("재료 > ");
						ham_stuff = sc.nextLine();
						bgr.setHam_Stuff(ham_stuff);
						bgr.setHam_type(5);
						break;
					}
					bgr.setHam_Name(name);
					
					if(dao.updateHamBurGer(bgr)) {
						System.out.println("햄버거 메뉴 수정 성공!");
					}else{
						System.out.println("햄버거 메뉴 수정 실패...");
					};
				}else {					
					System.out.println("메뉴에 없는 버거입니다");
				}
				break;
			case 4:
				System.out.print("메뉴 삭제할 햄버거 > ");
				name = sc.nextLine();
				order = new HamBurGerorderdetails();
				order.setHam_name(name);
				if(dao.deleteHamBurGer(name)) {
					System.out.println("햄버거 메뉴 삭제 성공!");
				}else {
					System.out.println("메뉴에 없는 버거 입니다");
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
				run = false;
				break;
			}
		}
	}
}
