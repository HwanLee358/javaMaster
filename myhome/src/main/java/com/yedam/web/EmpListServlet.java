package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.EmpDAO;

// 웹프로그램 -> url 실행 -> 서블릿(java)
// init(최초실행) -> service(실행) -> destory(종료시)
// 제어의 역전

@WebServlet("/empList.action")
public class EmpListServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// web browser에 출력.
		// 출력하는 컨텐트 타입.
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		EmpDAO edao = new EmpDAO();
		List<Map<String, Object>> list = edao.empList();
		for(Map<String, Object> map : list) {
			System.out.println("------------");
			System.out.println("사원번호: "+ map.get("사원번호") + ", 사원명: "+ map.get("사원명"));
			out.print("<p>사원번호: "+map.get("사원번호")+"</p>");
			out.print("<p>사원명: "+map.get("사원명")+"</p>");
		}
		System.out.println("end of list");
	}
}
