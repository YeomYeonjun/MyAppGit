package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardDto;
import service.KosmoDao;
import service.MemberDto;

public class MemberDataEditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		KosmoDao dao =new KosmoDao(getServletContext());
		MemberDto record=dao.member(id);
		req.setAttribute("record", record); 
		req.getRequestDispatcher("/Join/MemberDataEdit.jsp").forward(req, resp);
		dao.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println(id);
		resp.setContentType("text/html; charset=UTF-8");
		MemberDto dto = new MemberDto();
		KosmoDao dao = new KosmoDao(req.getServletContext());
		dto.setId(id);
		dto.setPassword(req.getParameter("password"));
		System.out.println(req.getParameter("password"));
		dto.setName(req.getParameter("name"));
		System.out.println(req.getParameter("name"));
		dto.setGender(req.getParameter("gender"));
		System.out.println(req.getParameter("gender"));
		String[] inters= req.getParameterValues("inter");
		String item="";
		for(int i=0; i< inters.length;i++) {
			item+= inters[i]+" ";
			System.out.println(item);
		}
		dto.setInter(item);
		dto.setGrade(req.getParameter("grade"));
		System.out.println(req.getParameter("grade"));
		dto.setSelf(req.getParameter("self"));
		System.out.println(req.getParameter("self"));
		dao.memberUpdate(dto);
		req.getRequestDispatcher("/Join/MemberDataEdit.jsp").forward(req, resp);
	}
}
