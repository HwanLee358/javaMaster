package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 원본글, 댓글작성자, 댓글내용
		resp.setContentType("text/json;charset=utf-8");
		
		String bno = req.getParameter("bno");
		String reply = req.getParameter("reply");
		String replyer = req.getParameter("replyer");

		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		
		Map<String, Object> result = new HashMap<>();

		ReplyService svc = new ReplyServiceImpl();
		if (svc.addReply(rvo)) {
			// {"retCode": "OK"}
			result.put("retCode","OK");
			result.put("retVal", rvo);
			
//			resp.getWriter().print("{\"retCode\":\"OK\"}");
		} else {
			result.put("retCode","NG");
			result.put("retVal", null);
			
		}
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);
		resp.getWriter().print(json);
	}

}
