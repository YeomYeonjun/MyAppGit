package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KosmoDao;

public class DeleteController extends HttpServlet{
	@Override	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		// 파라미터 받기
		String no = req.getParameter("boardno");
		// CRUD 로직 호출
		int success;
		KosmoDao dao = new KosmoDao(req.getServletContext());
		success = dao.delete(no);
		// 자원반납
		dao.close();
		
		req.setAttribute("SUCCFAIL", success);		
		req.setAttribute("WHERE", "DEL");
		req.getRequestDispatcher("/Board/Message.jsp").forward(req, resp);

	}
}
