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

public class EmpDAO {
	//filed
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	//Const
	
	//method
	// DB 접속 후 Connection.
	private void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,"jsp","jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	} // end of getConn().
	
	// 사원목록 기능.
	List<Employee> empList() {
		getConn();
		List<Employee> list = new ArrayList<>();
		String sql = "select * from emp order by emp_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			// 조회 결과.
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmpNO(rs.getInt("emp_no"));
				emp.setName(rs.getString("emp_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhone(rs.getString("emp_phone"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHiredate(rs.getString("hire_date"));
				list.add(emp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	// 사원등록 기능.
	boolean insertEmp(Employee emp) {
		getConn();
		String sql="insert into emp(emp_no, emp_name, emp_phone, email, salary, hire_date)"
				+ "values(emp_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getName());
			psmt.setString(2, emp.getPhone());
			psmt.setString(3, emp.getEmail());
			psmt.setInt(4, emp.getSalary());
			psmt.setString(5, emp.getHiredate());
			
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
	
	// 수정기능.
	boolean updateEmp(Employee emp) {
		getConn();
		String sql = "update emp";
		sql += "      set salary = ?";
		try {
			if(emp.getEmail().length() == 0) {
				sql += "      where emp_no = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, emp.getSalary());
				psmt.setInt(2, emp.getEmpNO());
			}else {
				sql += "      ,email = ?";
				sql += "      where emp_no = ?";			
				psmt.setInt(1, emp.getSalary());
				psmt.setString(2, emp.getEmail());
				psmt.setInt(3, emp.getEmpNO());
			}
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// 삭제기능.
	boolean deleteEmp(int eno) {
		getConn();
		String sql = "delete from emp where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eno);
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
		String sql = "select * from emp "
				   + "where emp_no =? "
				   + "order by emp_no";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eno);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date date = format.parse(rs.getString("hire_date"));
				String date_str = format.format(date);
				
				str = "사원번호 : "+ rs.getInt("emp_no");
				str+= "\t\t이름 : " + rs.getString("emp_name");
				str+= "\n입사일 : "+ date_str;
				str+= "\t\t월급 : "+ rs.getInt("salary");
				str+= "\n이메일 : "+ rs.getString("email");
				str+= "\t전화번호 : "+ rs.getString("emp_phone");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
}
