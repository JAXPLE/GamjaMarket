package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import board.BoardRequestDto;
import board.controller.BoardDao;

public class UpdateBoardAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int bCode = Integer.parseInt((String) (session.getAttribute("updateBoardbCode")));
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bCateCode = request.getParameter("bCateCode");

		BoardDao boardDao = BoardDao.getInstance();
		BoardRequestDto boardDto = new BoardRequestDto(bCode, bTitle, bContent, bCateCode);
		boardDao.updateBoard(boardDto);
		session.removeAttribute("updateBoardbCode");

		response.sendRedirect("board");
	}
}
