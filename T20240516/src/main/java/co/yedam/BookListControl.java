package co.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/bookList.do")
public class BookListControl extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookService svo = new BookServiceImpl();
		List<BookVO> list = svo.bookList();
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list);
		
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().println(json);
	}
}
