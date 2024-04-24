package co.ham;

import java.util.Scanner;

public class HamBurGerlogin {
	String name;
	// 로그인 폼
	public void HamBurGerloginform() {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		HamBurGerloginDAO dao = new HamBurGerloginDAO();
		while (run) {
			System.out.println("		              햄버거 가게                                        ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1. 로그인 			2. 회원가입 		3. 뒤로가기");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("선택 > ");
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.print("아이디 > ");
				name = sc.nextLine();
				System.out.print("비밀번호 > ");
				String password = sc.nextLine();
				Members login = new Members();

				login.setMember_id(name);
				login.setMember_password(password);

				if (dao.login(login)) {
					System.out.println("로그인 성공");
					run = false;
				} else {
					System.out.println("로그인 실패");
				}
				break;
			case 2:
				System.out.print("아이디 > ");
				name = sc.nextLine();
				System.out.print("비밀번호 > ");
				password = sc.nextLine();
				System.out.print("닉네임 > ");
				String kname = sc.nextLine();

				Members sighup = new Members();
				sighup.setMember_id(name);
				sighup.setMember_password(password);
				sighup.setMember_name(kname);

				System.out.println(dao.signup(sighup));
				break;
			case 3:
				break;
			}

		}
	}
}
