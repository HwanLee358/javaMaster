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
		while (run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1. 햄버거 메뉴 2. 주문하기 3.주문확인 4. 주문취소 5.계산하기 6.뒤로가기 ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택할 번호 > ");
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				boolean rum2 = true;
				int page = 1;
				while (rum2) {
					int pageSize = hbgdao.MenuListsize();
					List<HamBurGer> lists = hbgdao.hamburgerList(page);
					int Currentpage = page;
					System.out.println("		              햄버거 가게                                        ");
					System.out.println("-----------------------------------------------------------------------");
					System.out.println("	햄버거 이름 	칼로리		출시 날짜		가격");
					System.out.println("-----------------------------------------------------------------------");
					for (HamBurGer hbg : lists) {
						System.out.println(hbg.toString());
					}
					System.out.println("-----------------------------------------------------------------------");
					if (pageSize % 5 != 0) {
						page = pageSize / 5 + 1;
					}
					for (int i = 1; i <= page; i++) {
						if (Currentpage == i) {
							System.out.printf("%d ", i);
						} else {
							System.out.printf("[%d] ", i);
						}
					}
					System.out.println();
					System.out.print("페이지 > ");
					page = Integer.parseInt(sc.nextLine());
					if (page == 0) {
						rum2 = false;
					}
				}
				break;
			case 2:
				System.out.print("선택할 버거 > ");
				String name = sc.nextLine();

				HamBurGerorderdetails order = new HamBurGerorderdetails();
				order.setHam_name(name);

				if (hbgorderdao.HBGmenucheck(order).equals(name)) {
					System.out.print("수량 > ");
					int count = Integer.parseInt(sc.nextLine());

					order.setHam_count(count);
					order.setOrderer_id(order_id);

					hbgorderdao.HBGorder(order);
				} else {
					System.out.println("메뉴에 없습니다");
				}

				break;
			case 3:
				System.out.println("		              햄버거 가게                                        ");
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("	햄버거 이름			수량			가격");
				System.out.println("-----------------------------------------------------------------------");
				List<HamBurGerorderdetails> orderlist = hbgorderdao.HBGordercheck(order_id);
				if (orderlist.size() > 0) {
					for (HamBurGerorderdetails hbg : orderlist) {
						System.out.println(hbg.toString());
					}
				} else {
					System.out.println("				주문하세요.");
				}
				System.out.println("-----------------------------------------------------------------------");

				System.out.println("\t\t\t\t\t\t\t합계 : " + hbgorderdao.HBGordersum(order_id));
				break;
			case 4:
				System.out.print("취소(수정)할 버거 > ");
				name = sc.nextLine();

				System.out.print("수량 > ");
				int count = Integer.parseInt(sc.nextLine());

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
				orderlist = hbgorderdao.HBGordercheck(order_id);
				if (orderlist.size() > 0) {
					for (HamBurGerorderdetails hbg : orderlist) {
						System.out.println(hbg.toString());
					}
					System.out.println("-----------------------------------------------------------------------");
					System.out.println("\t\t\t\t\t\t\t합계 : " + hbgorderdao.HBGordersum(order_id));
					System.out.print("주문완료(1) > ");
				}else {
					System.out.println("주문이 없습니다");
					System.out.println("-----------------------------------------------------------------------");
					System.out.print("나가기 > ");
				}

				int check = Integer.parseInt(sc.nextLine());
				if (check == 1) {
					hbgorderdao.Ordercomplete(order_id);
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
