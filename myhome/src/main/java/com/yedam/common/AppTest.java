package com.yedam.common;

import java.util.List;

import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVO;

public class AppTest {
	public static void main(String[] args) {
		//목록
		EmpDAO edao = new EmpDAO();
		List<EmpVO> list = edao.selectList();
		for(EmpVO e : list) {
			System.out.println(e.toString());
		}
		
		//추가
		EmpVO evo = new EmpVO();
		evo.setEmpName("산길동");
		evo.setEmpPhone("01-3254-3212");
		evo.setEmail("suiu123@email");
		evo.setSalary(6000);
		if(edao.insertEmp(evo)) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");			
		}
		
		//수정
		evo = new EmpVO();
		evo.setSalary(5000);
		evo.setEmail("suqiw@email");
		evo.setEmpNO(5);
		if(edao.updateEmp(evo)) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		
		//삭제
		if(edao.deleteEmp(6)) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
	}
}
