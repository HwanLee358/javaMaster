package co.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SwimEmpDAO {
	//filed
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	//Constructor
	
	//method
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
	
	List <SwimEmployee> swimEmpList(){
		getConn();
		List<SwimEmployee> list = new ArrayList<>();
		String sql = "select * from swimemp order by emp_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SimpleDateFormat format = new SimpleDateFormat("MM-dd");
				Date date = format.parse(rs.getString("hire_date"));
				String date_str = format.format(date);
				
				SwimEmployee emp = new SwimEmployee();
				emp.setEmpNo(rs.getInt("emp_no"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpPhone(rs.getString("emp_phone"));
				emp.setHireDate(date_str);
				emp.setGender(rs.getString("gender"));
				list.add(emp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	boolean insertswimEmp(SwimEmployee emp) {
		getConn();
		String sql = "insert into swimemp(emp_no,emp_name,emp_phone,hire_date,gender)";
		sql +=       "values(swim_emp_seq.nextval, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getEmpName());
			psmt.setString(2, emp.getEmpPhone());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getGender());
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
	boolean updateswimEmp(SwimEmployee emp) {
		getConn();
		String sql = "update swimemp "; 
		sql+= "set emp_phone = ? "; 
		sql+= "where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getEmpPhone());
			psmt.setInt(2, emp.getEmpNo());
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

	boolean deleteswimEmp(int empNo) {
		getConn();
		String sql = "delete from swimemp where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empNo);
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String showEnp(int eno) {
		getConn();
		String str = "";
		String sql = "select * from swimemp "
				   + "where emp_no =? "
				   + "order by emp_no";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eno);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SimpleDateFormat format = new SimpleDateFormat("MM-dd");
				Date date = format.parse(rs.getString("hire_date"));
				String date_str = format.format(date);
				
				str = "회원번호 : "+ rs.getInt("emp_no");
				str+= "\t\t회원명 : " + rs.getString("emp_name");
				str+= "\n회원생일 : "+ date_str;
				str+= "\t\t성별 : "+ rs.getString("gender");
				str+= "\n회원연락처 : "+ rs.getString("emp_phone");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
