package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Board;
import board.BoardRequestDto;
import board.controller.BoardDao;
import client.Client;

public class BoardAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bcateCode = request.getParameter("bcateCode");
		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute("log");
		System.out.println(client.getClientAddress());
		String bWriter = client.getClientContact();

		BoardRequestDto boardDto = new BoardRequestDto(bTitle, bContent, bWriter, bcateCode);
		BoardDao boardDao = BoardDao.getInstance();
		boardDao.createBoard(boardDto);
		int bCode = boardDao.getMaxBcode();
		response.sendRedirect("boardContent?bCode=" + bCode);
	}
}
