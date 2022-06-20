package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import service.KosmoDao;
import service.MemberDto;

public class LogoutController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getSession().invalidate();
			resp.sendRedirect("http://localhost:8080/YeomYeonJunPro2/Home.jsp");
			//req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}
	
}
