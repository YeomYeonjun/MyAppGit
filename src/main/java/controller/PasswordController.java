package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.KosmoDao;

public class PasswordController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글처리]-POST방식으로 넘어오는 파라미터인 원본 파일명 위한 처리
		req.setCharacterEncoding("UTF-8");
		//파라미터 받기]
		String no = req.getParameter("no");
		String mode = req.getParameter("mode");
		String orignalFileName = req.getParameter("orignalFileName");
		String password = req.getParameter("password");
		System.out.println("모드:"+mode);
		//모델호출 및 결과값 받기]	
		KosmoDao dao = new KosmoDao(getServletContext());
		boolean flag = dao.isCorrectPassword(no,password);
		dao.close();
		System.out.println(flag?"비번 맞아":"비번 틀려");
		//뷰 선택 후 포워딩]
		//[비밀번호가 틀린 경우는 이전 페이지로]
		if(!flag) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out= resp.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않아요');");
			out.println("history.back();");
			out.println("</script>");
			
			return;
		}
		//[비밀번호가 일치하는 경우]
		if("UPDATE".equals(mode)) {//수정폼으로 이동
			req.getRequestDispatcher("/DataRoom/Edit.kosmo").forward(req, resp);
		}
		else {//삭제를 누른 경우-삭제처리후 메시지 뿌려주는 페이지로 이동
			dao = new KosmoDao(getServletContext());
			int successOrFail=dao.delete(no);
			req.getRequestDispatcher("/Board/Message.jsp").forward(req, resp);
		}
	}/////////////doPost
}////////////////
