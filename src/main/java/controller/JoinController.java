package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KosmoDao;
import service.MemberDto;

public class JoinController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Join/Join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		MemberDto dto = new MemberDto();
		KosmoDao dao = new KosmoDao(req.getServletContext());
		
		dto.setId(req.getParameter("id"));
		dto.setPassword(req.getParameter("password"));
		dto.setName(req.getParameter("name"));
		dto.setGender(req.getParameter("gender"));
		String[] inters= req.getParameterValues("inter");
		String item="";
		for(int i=0; i< inters.length;i++) {
			item+= inters[i]+" ";
		}
		dto.setInter(item);
		dto.setGrade(req.getParameter("grade"));
		dto.setSelf(req.getParameter("self"));
		int affected=dao.memberInsert(dto);
		if(affected==1) {
			req.getRequestDispatcher("/kosmo/Login.do").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/Join/Join.jsp").forward(req, resp);
		}
		
	}
}
