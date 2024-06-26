package co.ham;

import java.util.List;
import java.util.Scanner;

public class HamBurGerSalesUI {
	// 매출 현황 폼
	
	public void HamBurGurSale() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		HamBurGerSalesDAO dao = new HamBurGerSalesDAO();
		String search = "";
		while(run) {
			List<HamBurGerorderdetails> orderlist = dao.HamBurGerorderlist(search);
			System.out.println("		              매출 현황                                   ");
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("번호\t햄버거명\t\t수량\t가격\t구매자\t\t날짜");
			System.out.println("-----------------------------------------------------------------------------");
			if(orderlist.size() > 0) {
				for(HamBurGerorderdetails hbg : orderlist) {
					System.out.println(hbg.orderString());				
				}
			}else {
				System.out.println("주문기록이 없습니다");
			}
			System.out.println("-----------------------------------------------------------------------------");
			int[] result = dao.HBGordersum(search);
			System.out.printf("\t\t수량 : %d\t\t\t\t합계 : %d\n",result[0],result[1]);
			System.out.print("검색 > ");
			search = sc.nextLine();
			if(search.equals("0")) {
				run = false;
			}
		}
		
	}
}
