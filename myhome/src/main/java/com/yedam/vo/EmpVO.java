package com.yedam.vo;


import lombok.Data;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;

// lombok: 이클립스에 설치, 라이브러리 필요
// @Getter
// @Setter
// @ToString

// 세개 한꺼번에
@Data
public class EmpVO {
	private int empNO; // 오라클(emp_no) : empNO
	private String empName;
	private String empPhone;
	private String email;
	private String hireDate;
	private int salary;
	private String dept;
}
