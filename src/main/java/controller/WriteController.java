package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import service.BoardDto;
import service.KosmoDao;

//1]HttpServlet상속-컨트롤러가 됨 즉 서블릿이 됨
public class WriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/Board/Write.jsp").forward(req, resp);
	}///doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글처리]
		req.setCharacterEncoding("UTF-8");
		int success;
		String title = req.getParameter("title");
		String password = req.getParameter("password");
		String content = req.getParameter("content");
		String id = (String) req.getSession().getAttribute("id");
		String name = (String) req.getSession().getAttribute("name");
		// 데이터베이스 CRUD작업과 관련된 모델 호출
		KosmoDao dao = new KosmoDao(req.getServletContext());
		BoardDto dto = new BoardDto();
		dto.setContent(content);
		dto.setTitle(title);
		dto.setId(id);
		dto.setName(name);
		success = dao.insert(dto);
		resp.setContentType("text/html; charset=UTF-8");
		// 자원반납
		dao.close();
		req.setAttribute("success", success);		
		req.setAttribute("WHERE", "INS");
		req.getRequestDispatcher("/Board/Message.jsp").forward(req, resp);
		//resp.sendRedirect("http://localhost:8080/YeomYeonJunPro2/kosmo/List.do");
	}/////////////doPost
}
