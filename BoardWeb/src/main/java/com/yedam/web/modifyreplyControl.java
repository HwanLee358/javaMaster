package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class modifyreplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		String reply = req.getParameter("reply");
		
		ReplyVO vo = new ReplyVO();
		vo.setReplyNo(Integer.parseInt(bno));
		vo.setReply(reply);
		
		ReplyService svo = new ReplyServiceImpl();
		if(svo.modifyReply(vo)) {
			// {"retCode": "OK"}
			resp.getWriter().print("{\"retCode\":\"OK\"}");
		}else {
			resp.getWriter().print("{\"retCode\":\"NG\"}");
		}
	}
}
