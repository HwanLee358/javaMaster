package co.ham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HamBurGerloginDAO {
	// filed
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// Constructor

	// method
	private void getConn() {
		String url = "jdbc:oracle:thin:@192.168.0.8:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}// end connect

	// 1.로그인
	boolean login(Members login) {
		getConn();
		String sql = "select * from members where member_id = ? and member_pw = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, login.getMember_id());
			psmt.setString(2, login.getMember_password());
			rs = psmt.executeQuery();
			while (rs.next()) {
				if(rs.getString("member_id").equals(login.getMember_id()) && rs.getString("member_pw").equals(login.getMember_password())) {
					return true;					
				}else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 2.회원가입
	String signup(Members sign) {
		getConn();
		String sql1 = "select * from members where member_id = ?";
		String sql2 = "insert into members values(?,?,?,members_seq.nextval)";
		String check = "";
		String str = "";
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, sign.getMember_id());
			rs = psmt.executeQuery();
			while (rs.next()) {
				check = rs.getString("member_id");
			}
			if (check.equals(sign.getMember_id())) {
				str = "이미 존재하는 아이디 입니다";
				return str;
			} else {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, sign.getMember_id());
				psmt.setString(2, sign.getMember_password());
				psmt.setString(3, sign.getMember_name());
				psmt.executeUpdate();
				str = "회원가입을 성공 하셨습니다";
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
