package co.ham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HamBurGerDAO {
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
	
	// 0. 메뉴 용량
	int MenuListsize() {
		getConn();
		int size = 0;
		String sql = "select count(ham_no) from hamburger";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				size = rs.getInt("count(ham_no)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return size;
	}
	// 1. 햄버거 전체적 메뉴를 보여줌.
	List <HamBurGer> hamburgerList(int page){
		getConn();
		List<HamBurGer> list = new ArrayList<>();
		String sql = "select ham_no, ham_name, ham_kcal, ham_hire_date, ham_price "
				   + "from( select rownum as  ham_no, ham_name, ham_kcal, ham_hire_date, ham_price "
			             + "from hamburger where rownum <=?) "
			       + "where ham_no >?";
		int max = page*5;
		int min = max-5;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, max);
			psmt.setInt(2, min);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date date = format.parse(rs.getString("ham_hire_date"));
				String date_str = format.format(date);
				
				HamBurGer hbg = new HamBurGer();
				hbg.setHam_No(rs.getInt("ham_no"));
				hbg.setHam_Name(rs.getString("ham_name"));
				hbg.setHam_Kcal(rs.getInt("ham_kcal"));
				hbg.setHam_Hire_Date(date_str);
				hbg.setHam_Price(rs.getInt("ham_price"));
				list.add(hbg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return list;
	}
	// 2. 햄버거 메뉴 추가.
	boolean inserthamburger(HamBurGer hgr) {
		getConn();
		String sql = "insert into hamburger(ham_no, ham_name, ham_kcal, ham_hire_date, ham_price, ham_stuff) "
				   + "values(hamburger_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, hgr.getHam_Name());
			psmt.setInt(2, hgr.getHam_Kcal());
			psmt.setString(3, hgr.getHam_Hire_Date());
			psmt.setInt(4, hgr.getHam_Price());
			psmt.setString(5, hgr.getHam_Stuff());
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return false;
	}
	// 3. 햄버거 메뉴 수정
	boolean updateHamBurGer(HamBurGer hgr) {
		getConn();
		String sql = "update hamburger "
				   + "set    ham_price = ? "
				   + "where  ham_name = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, hgr.getHam_Price());
			psmt.setString(2, hgr.getHam_Name());
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return false;
	}
	// 4. 햄버거 메뉴 삭제
	boolean deleteHamBurGer(String hgrName) {
		getConn();
		String sql = "delete hamburger "
				   + "where ham_name = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, hgrName);
			int r = psmt.executeUpdate();
			if(r > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// 5. 햄버거 정보 보기
	List <HamBurGer> showHamBurGer(String hgrName) {
		getConn();
		List<HamBurGer> showBurGer = new ArrayList<>();
		String sql = "select * from hamburger "
				   + "where ham_name = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, hgrName);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date date = format.parse(rs.getString("ham_hire_date"));
				String date_str = format.format(date);
				
				HamBurGer hbg = new HamBurGer();
				hbg.setHam_Name(rs.getString("ham_name"));
				hbg.setHam_Price(rs.getInt("ham_price"));
				hbg.setHam_Kcal(rs.getInt("ham_kcal"));
				hbg.setHam_Hire_Date(date_str);
				hbg.setHam_Stuff(rs.getString("ham_stuff"));
				showBurGer.add(hbg);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception e) {}
			if(conn != null) try {conn.close();} catch(Exception e) {}
			if( psmt != null) try { psmt.close();} catch(Exception e) {}
		}
		return showBurGer;
	}
}
