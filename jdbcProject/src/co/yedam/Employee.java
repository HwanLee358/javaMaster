package co.yedam;


public class Employee {
	private String name;
	private String phone;
	private String email;
	private int salary;
	private String hiredate;
	private int empNO;
	
	public int getEmpNO() {
		return empNO;
	}


	public void setEmpNO(int empNO) {
		this.empNO = empNO;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public String getHiredate() {
		return hiredate;
	}


	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}


	@Override
	public String toString() {		
		return String.format("%4d %4s %12s %5d", empNO, name, email, salary);
	}
	
	
}
