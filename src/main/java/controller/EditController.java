package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDto;
import service.KosmoDao;

public class EditController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 파라미터 받기
		String boardno = req.getParameter("boardno");	
		// 모델 호출 및 결과 값 받기
		KosmoDao dao = new KosmoDao(getServletContext());
		BoardDto record=dao.selectOne(boardno);	
		// 리퀘스트 영역에 저장
		req.setAttribute("record", record); 
		req.setAttribute("boardno", boardno);
		req.setAttribute("nowPage", req.getParameter("nowPage"));
		
		// 자원 반납
		dao.close();
		// 포워드
		req.getRequestDispatcher("/Board/Edit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int success;
		req.setCharacterEncoding("UTF-8");
		// 파라미터 받기
		String no = req.getParameter("boardno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = (String)req.getSession().getAttribute("id");
		// CRUD
		KosmoDao dao = new KosmoDao(getServletContext());
		BoardDto dto = new BoardDto();
		dto.setBoardno(no);
		dto.setTitle(title);
		dto.setContent(content);
		// 수정용
		dao.update(dto);
		// 자원반납	
		success=dao.update(dto);
		dao.close();
		//DB업데이트 성공하고 파일 교체시에만 파일 삭제
		req.setAttribute("SUCCFAIL", success);		
		req.setAttribute("WHERE", "EDT");
		req.getRequestDispatcher("/Board/Message.jsp").forward(req, resp);
		//req.getRequestDispatcher("/kosmo/View.do?nowPage=").forward(req, resp);
	}
}	
