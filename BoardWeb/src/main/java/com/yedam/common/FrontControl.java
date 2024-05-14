package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.web.AddBoardControl;
import com.yedam.web.AddFormControl;
import com.yedam.web.AddReplyControl;
import com.yedam.web.BoardInfoControl;
import com.yedam.web.LoginControl;
import com.yedam.web.LoginForm;
import com.yedam.web.LogoutControl;
import com.yedam.web.MainControl;
import com.yedam.web.MemberListControl;
import com.yedam.web.ProductListControl;
import com.yedam.web.RemoveControl;
import com.yedam.web.RemoveFormControl;
import com.yedam.web.RemoveReplyControl;
import com.yedam.web.ReplyListControl;
import com.yedam.web.TotalCountControl;
import com.yedam.web.modfiyBoardControl;
import com.yedam.web.modfiyFormControl;

public class FrontControl extends HttpServlet {
	Map<String, Control> map;

	// 생성자
	public FrontControl() {
		map = new HashMap<>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// url 패턴과 실행할 Control 구현클래스 정의
		map.put("/main.do", new MainControl());
		//추가
		map.put("/addForm.do", new AddFormControl());
		map.put("/addBoard.do", new AddBoardControl());
		//글정보
		map.put("/boardInfo.do", new BoardInfoControl());
		//수정
		map.put("/modBoardForm.do", new modfiyFormControl());
		map.put("/modBoard.do", new modfiyBoardControl());
		//삭제
		map.put("/deleteForm.do", new RemoveFormControl());
		map.put("/deleteBoard.do", new RemoveControl());
		//로그인
		map.put("/logForm.do", new LoginForm());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		
		//댓글관련
		map.put("/replyList.do", new ReplyListControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		map.put("/getTotalCnt.do",new TotalCountControl());
		
		// 관리자 권한.
		map.put("/memberList.do", new MemberListControl());
		
		// 상품 관련
		map.put("/productList.do", new ProductListControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8"); // EncodingFiter 에서 함.
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		System.out.println("uri: " + uri + ", context: " + context);
		String path = uri.substring(context.length());
		System.out.println("path: " + path);

		Control control = map.get(path);
		control.exec(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
