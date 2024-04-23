package co.ham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HamBurGerOrderDAO {
	// filed
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// Constructor
	
	// method
	private void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}// end connect
	
	//1.로그인
	int login(Members login) {
		getConn();
		String sql = "select * from members where member_id = ? and member_pw = ?";
		int check = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login.getMember_id());
			psmt.setString(2, login.getMember_password());
			rs = psmt.executeQuery();
			while(rs.next()) {
				 check = rs.getString("member_id").length();				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	//2.회원가입
	String signup(Members sign) {
		getConn();
		String sql1 = "select * from members where member_id = ?";
		String sql2 = "insert into members values(?,?,?,members_seq.nextval)";
		int check = 0;
		String str = "";
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, sign.getMember_id());
			rs = psmt.executeQuery();
			while(rs.next()) {
				 check = rs.getString("member_id").length();				
			}
			if(check == 0) {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, sign.getMember_id());
				psmt.setString(2, sign.getMember_password());
				psmt.setString(3, sign.getMember_name());
				psmt.executeUpdate();
				str = "회원가입을 성공 하셨습니다";
				return str;
			}else {
				str = "이미 존재하는 아이디 입니다";
				return str;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = "회원가입을 실패 하셨습니다";
		return str;	
	}
}
