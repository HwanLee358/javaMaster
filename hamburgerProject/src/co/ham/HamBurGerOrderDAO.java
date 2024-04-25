package co.ham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	// 2. 주문하기
	public void HBGorder(HamBurGerorderdetails hbg) {
		getConn();
		String url = "insert into orderdetails(ham_order_no, ham_name, ham_count, ham_price, order_name_id) "
				+ "values(orderdetails_seq.nextval," + "?,?,"
				+ "(select ham_price from hamburger where ham_name = ?)*?," + "?)";
		try {
			psmt = conn.prepareStatement(url);
			psmt.setString(1, hbg.getHam_name());
			psmt.setInt(2, hbg.getHam_count());
			psmt.setString(3, hbg.getHam_name());
			psmt.setInt(4, hbg.getHam_count());
			psmt.setString(5, hbg.getOrderer_id());
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
	}

	// 3. 주문확인
	List<HamBurGerorderdetails> HBGordercheck(String order_id) {
		getConn();
		List<HamBurGerorderdetails> list = new ArrayList<>();
		String url = "select ham_name, ham_count, ham_price from orderdetails where order_name_id = ? and order_state = 'waiting'";
		try {
			psmt = conn.prepareStatement(url);
			psmt.setString(1, order_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				HamBurGerorderdetails hbg = new HamBurGerorderdetails();
				hbg.setHam_name(rs.getString("ham_name"));
				hbg.setHam_count(rs.getInt("ham_count"));
				hbg.setHam_price(rs.getInt("ham_price"));
				list.add(hbg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return list;
	}

	// 3.1 주문 합계
	int HBGordersum(String order_id) {
		int result = 0;
		getConn();
		String url = "select sum(ham_price) from orderdetails where order_name_id = ? and order_state = 'waiting'";
		try {
			psmt = conn.prepareStatement(url);
			psmt.setString(1, order_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("sum(ham_price)");
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return result;
	}

	// 4. 주문취소
	public void HBGordercancel(HamBurGerorderdetails hbg) {
		getConn();
		String url1 = "select ham_count from orderdetails where ham_name = ?";
		String url2 = "update orderdetails set ham_count = ?, ham_price = (select ham_price from hamburger where ham_name = ?)*? where ham_name = ?";
		String url3 = "delete orderdetails where ham_name = ?";
		int check = 0;
		try {
			// 수량 업데이트
			psmt = conn.prepareStatement(url2);
			psmt.setInt(1, hbg.getHam_count());
			psmt.setString(2, hbg.getHam_name());
			psmt.setInt(3, hbg.getHam_count());
			psmt.setString(4, hbg.getHam_name());
			psmt.executeUpdate();

			// 수량 체크
			psmt = conn.prepareStatement(url1);
			psmt.setString(1, hbg.getHam_name());
			rs = psmt.executeQuery();
			while (rs.next()) {
				check = rs.getInt("ham_count");
			}

			if (check == 0) {
				psmt = conn.prepareStatement(url3);
				psmt.setString(1, hbg.getHam_name());
				psmt.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
	}
	// 5. 주문완료
	public void Ordercomplete(String order_id) {
		getConn();
		String url = "update orderdetails set order_state = 'complete' where order_name_id = ?";
		try {
			psmt = conn.prepareStatement(url);
			psmt.setString(1, order_id);
			psmt.executeUpdate();
			System.out.println("주문완료!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
	}

}
