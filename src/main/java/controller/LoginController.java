package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import service.BoardDto;
import service.KosmoDao;
import service.MemberDto;
 
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/Login/Login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		KosmoDao dao = new KosmoDao(getServletContext());
		String id= req.getParameter("id");
		String password= req.getParameter("password");
		boolean flag=dao.isLogin(id, password);
		dao.close();
		if(flag){	
			//1.로그인 처리]-세션영역에 속성(주로 식별자만) 저장
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			//req.setAttribute("id", id);
			req.setAttribute("password", password);
			//2.로그인 처리후 홈 페이지로 이동]
			resp.sendRedirect("http://localhost:8080/YeomYeonJunPro2/Home.jsp");
			//req.getRequestDispatcher("/Home.jsp").forward(req, resp);
		}
		else{//아이디 비번 불일치
			//리퀘스트 영역에 필요한 데이터 저장
			req.setAttribute("NOT-LOGIN","아이디 비번 불일치");
			//로그인 페이지로 포워드
			req.getRequestDispatcher("/Login/Login.jsp").forward(req, resp);
		}
	}
	
}
