package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.EmpDAO;
import com.yedam.vo.EmpVO;

@WebServlet("/empsave.json")
public class EmpJson extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 추가, 수정, 삭제
		String job = req.getParameter("job");
		EmpDAO edao = new EmpDAO();

		String eno = req.getParameter("empNo");
		String salary = req.getParameter("salary");
		String email = req.getParameter("email");
		EmpVO evo = new EmpVO();

		Map<String, Object> map = new HashMap<>();
		Gson gson = new GsonBuilder().create();
		
		if (job.equals("add")) {
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String hire = req.getParameter("hire");

			evo.setEmpName(name);
			evo.setEmpPhone(phone);
			evo.setSalary(Integer.parseInt(salary));
			evo.setHireDate(hire);
			evo.setEmail(email);

			if (edao.insertEmp(evo)) {
				map.put("retCode", "OK");
				map.put("retVal", evo);
			} else {
				map.put("retCode", "NG");
				map.put("retVal", null);
			}
			resp.getWriter().print(gson.toJson(map));
			
		} else if (job.equals("edit")) {
			// 수정기능
			evo.setEmpNO(Integer.parseInt(eno));
			evo.setSalary(Integer.parseInt(salary));
			evo.setEmail(email);
			
			if(edao.updateEmp(evo)) {
				evo = edao.selectEmp(evo.getEmpNO());
				map.put("retCode", "OK");
				map.put("retVal", evo);
			}else {
				map.put("retCode", "NG");
				map.put("retVal", null);
			}
			resp.getWriter().print(gson.toJson(map));
			
		} else if (job.equals("delete")) {
			// 삭제기능(사원번호 emp.html에서 파라미터 수신)

			if (edao.deleteEmp(Integer.parseInt(eno))) {
				resp.getWriter().print("{\"retCode\": \"OK\"}");
			} else {
				resp.getWriter().print("{\"retCode\": \"NG\"}");
			}
		}
	}
}
