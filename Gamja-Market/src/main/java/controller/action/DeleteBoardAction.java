package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.BoardDao;

public class DeleteBoardAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bCode = Integer.parseInt(request.getParameter("bCode"));
		
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.deleteBoard(bCode);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("admin") != null) {
			response.sendRedirect("admin?delete=Y");
		}
		
		else {
			response.sendRedirect("myBoardList?delFinish=Y");
		}
	}
}
