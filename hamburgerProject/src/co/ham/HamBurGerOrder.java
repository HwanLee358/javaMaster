package co.ham;

import java.util.List;
import java.util.Scanner;

public class HamBurGerOrder {
	// 주문 폼
	public void HamBurGerOrderform(String order_id) {
		Scanner sc = new Scanner(System.in);	
		boolean run = true;
		HamBurGerDAO hbgdao = new HamBurGerDAO();
		HamBurGerlogin logindao = new HamBurGerlogin();
		HamBurGerOrderDAO hbgorderdao = new HamBurGerOrderDAO();
		while(run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1. 햄버거 메뉴 2. 주문하기 3.주문확인 4. 주문취소 5.계산하기 6.뒤로가기 ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num = Integer.parseInt(sc.nextLine()); 
			switch(num) {
			case 1:
				List<HamBurGer> list = hbgdao.hamburgerList();
				System.out.println("		              햄버거 가게                                        ");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("	햄버거 이름 	칼로리		출시 날짜		가격");
				System.out.println("-----------------------------------------------------------------------");
				for(HamBurGer hbg : list) {
					System.out.println(hbg.toString());
				}
				break;
			case 2:
				System.out.print("선택할 버거 > ");
				String name = sc.nextLine();
				System.out.print("수량 > ");
				int count = Integer.parseInt(sc.nextLine());
				
				HamBurGerorderdetails order = new HamBurGerorderdetails();
				order.setHam_name(name);
				order.setHam_count(count);
				order.setOrderer_id(order_id);
				
				hbgorderdao.HBGorder(order);
				break;
			case 3:
				System.out.println("		              햄버거 가게                                        ");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("	햄버거 이름			수량			가격");
				System.out.println("-----------------------------------------------------------------------");
				List<HamBurGerorderdetails> orderlist = hbgorderdao.HBGordercheck();
				for(HamBurGerorderdetails hbg : orderlist) {
					System.out.println(hbg.toString());
				}
				break;
			case 4:
				System.out.print("취소(수정)할 버거 > ");
				name = sc.nextLine();
				System.out.print("수량 > ");
				count = Integer.parseInt(sc.nextLine());
				
				order = new HamBurGerorderdetails();
				order.setHam_name(name);
				order.setHam_count(count);
				
				hbgorderdao.HBGordercancel(order);
				break;
			case 5:
				System.out.println("		              햄버거 가게                                        ");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("	햄버거 이름			수량			가격");
				System.out.println("-----------------------------------------------------------------------");
				orderlist = hbgorderdao.HBGordercheck();
				for(HamBurGerorderdetails hbg : orderlist) {
					System.out.println(hbg.toString());
				}
				System.out.println("-----------------------------------------------------------------------");
				
				System.out.println("\t\t\t\t\t\t\t합계 : "+hbgorderdao.HBGcalculator());
				
				System.out.print("주문완료(1) > ");
				int check = Integer.parseInt(sc.nextLine());
				if(check == 1) {
					run = false;
				}
				break;
			case 6:
				run = false;
				break;
			}
		}
	}
}
