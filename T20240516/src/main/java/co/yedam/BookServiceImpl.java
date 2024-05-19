package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
	public Connection conn;
	public PreparedStatement psmt;
	public ResultSet rs;

	public void conn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void disCon() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<BookVO> bookList() {
		List<BookVO> list = new ArrayList<>();
		conn();
		try {
			psmt = conn.prepareStatement("select * from tbl_book");
			rs = psmt.executeQuery();
			while(rs.next()) {
				BookVO bk = new BookVO();
				bk.setBookCode(rs.getString("book_code"));
				bk.setBookName(rs.getString("book_name"));
				bk.setBookAuthor(rs.getString("book_author"));
				bk.setBookPress(rs.getString("book_press"));
				bk.setBookPrice(rs.getInt("book_price"));
				list.add(bk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disCon();
		}
		return list;
	}

}
