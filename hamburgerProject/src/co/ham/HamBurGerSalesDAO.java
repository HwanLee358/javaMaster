package co.ham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HamBurGerSalesDAO {
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

	// 매출 리스트
	List<HamBurGerorderdetails> HamBurGerorderlist(String str) {
		getConn();
		List<HamBurGerorderdetails> orderlist = new ArrayList<>();
		String sql = "select ham_order_no, ham_name, ham_count, ham_price, order_name_id, order_date "
				+ "from orderdetails " + "where order_state = 'complete' ";
		if (str.equals("")) {
		} else {
			sql += "and (ham_name = ? or order_name_id = ?)";
		}
		try {
			psmt = conn.prepareStatement(sql);
			if (str.equals("")) {
			} else {
				psmt.setString(1, str);
				psmt.setString(2, str);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				HamBurGerorderdetails HBGorder = new HamBurGerorderdetails();
				HBGorder.setHam_order_no(rs.getInt("ham_order_no"));
				HBGorder.setHam_name(rs.getString("ham_name"));
				HBGorder.setHam_count(rs.getInt("ham_count"));
				HBGorder.setHam_price(rs.getInt("ham_price"));
				HBGorder.setOrderer_id(rs.getString("order_name_id"));
				HBGorder.setOrder_date(rs.getString("order_date"));
				orderlist.add(HBGorder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			if (psmt != null)
				try {
					psmt.close();
				} catch (Exception e) {
				}
		}
		return orderlist;
	}

	// 매출 합계
	int HBGordersum(String str) {
		getConn();
		int result = 0;
		String sql = "select sum(ham_price) " + "from orderdetails " + "where order_state = 'complete'";
		if (str.equals("")) {
		} else {
			sql += "and (ham_name = ? or order_name_id = ?)";
		}

		try {
			psmt = conn.prepareStatement(sql);
			if (str.equals("")) {
			} else {
				psmt.setString(1, str);
				psmt.setString(2, str);
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("sum(ham_price)");
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			if (psmt != null)
				try {
					psmt.close();
				} catch (Exception e) {
				}
		}
		return result;
	}

}
