package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDto;
import service.KosmoDao;


public class ViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardno = req.getParameter("boardno");
		String nowPage=req.getParameter("nowPage");
		//모델 호출 및 결과값 받기
		KosmoDao dao = new KosmoDao(req.getServletContext());
		BoardDto record = dao.selectOne(boardno); 
		// 줄바꿈 처리
		record.setContent(record.getContent().replace("\r\n","<br/>"));
		// 자원반납
		dao.close();
		// 필요한 값 리퀘스트 영역에 저장
		req.setAttribute("record", record);
		req.getRequestDispatcher("/Board/View.jsp").forward(req, resp);
	}////////////service
}
