package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppTest {
	public static void main(String[] args) {
		// 1.ojdbc 라이브러리.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		// 2.Connection 객체 접속
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Connection conn = DriverManager.getConnection(url,"jsp","jsp");
			// 3.PreparedStatement 객체 쿼리 실행
			String sql1 = "select * from emp";
			String sql2 = "insert into emp(emp_no, emp_name, emp_phone, email, salary) ";
			sql2 += "values(emp_seq.nextval, ?, ?, ?, ?)";
			PreparedStatement psmt = conn.prepareStatement(sql2);
			psmt.setString(1, "홍길동");
			psmt.setString(2, "03-1111-2222");
			psmt.setString(3, "hgd@email");
			psmt.setInt(4, 3000);
			
			int r = psmt.executeUpdate(); // insert, update, delete 일 경우.
			System.out.println("등록된 건수: "+ r);
			
			psmt = conn.prepareStatement(sql1);
			ResultSet rs = psmt.executeQuery(); // psmt.executeUpdate()
			while(rs.next()) {
				// 4.결과 => 화면출력, 기능수행.
				System.out.println("사원번호: "+rs.getInt("emp_no"));
				System.out.println("사원이름: "+rs.getString("emp_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end of prog");
	}
}
