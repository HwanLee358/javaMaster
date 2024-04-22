package co.yedam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimEmpManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		SwimEmpDAO dao = new SwimEmpDAO(); 
		while(run) {
			System.out.println("1.회원목록 2.회원등록 3.정보수정 4.정보삭제 5.종료");
			System.out.print("선택> ");
			int Menu = Integer.parseInt(sc.nextLine());
			switch(Menu) {
			case 1:
				List<SwimEmployee> emps = dao.swimEmpList();
				System.out.println("회원번호 회원명 회원연락처    회원생일  성별");
				System.out.println("-----------------------------------");
				for(SwimEmployee emp : emps) {
					System.out.println(emp.toString());
				}
				break;
			case 2:
				System.out.print("회원명>> ");
				String name = sc.nextLine();
				System.out.print("연락처>> ");
				String phone = sc.nextLine();
				System.out.print("생일>> ");
				String birth = sc.nextLine();
				System.out.print("성별>> ");		
				String gender = sc.nextLine();
				SwimEmployee emp = new SwimEmployee();
				
				emp.setEmpName(name);
				emp.setEmpPhone(phone);
				emp.setHireDate(birth);
				emp.setGender(gender);
				
				if(dao.insertswimEmp(emp)) {
					System.out.println("등록 완료.");
				}else{
					System.out.println("등록 실패");
				};
				break;
			case 3:
				System.out.print("수정할 회원번호>> ");
				int empNo = Integer.parseInt(sc.nextLine());
				System.out.print("연락처>> ");
				phone = sc.nextLine();
				
				emp = new SwimEmployee();
				emp.setEmpPhone(phone);
				emp.setEmpNo(empNo);
				if(dao.updateswimEmp(emp)){
					System.out.println("수정 완료.");
				}else {
					System.out.println("수정 실패.");					
				}
				break;
			case 4:
				System.out.print("삭제할 회원번호>> ");
				empNo = Integer.parseInt(sc.nextLine());
				if(dao.deleteswimEmp(empNo)) {
					System.out.println("삭제 완료.");
				}else {
					System.out.println("삭제 실패");
				}
				break;
			case 5:
				sc.close();
				run = false;
				break;
			}
		}
	}
}
