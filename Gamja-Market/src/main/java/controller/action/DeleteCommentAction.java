package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.BoardDao;
import comment.controller.CommentDao;

public class DeleteCommentAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int comCode = Integer.parseInt(request.getParameter("comCode"));
		String bCode = request.getParameter("bCode");
		String board = request.getParameter("page");
		
		CommentDao commentDao = CommentDao.getInstance();
		commentDao.deleteComment(comCode);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("admin") != null) {
			response.sendRedirect("admin?delete=Y");
			return; // redirect 이후 메소드를 종료합니다.
		}
		
		if (board != null) {
			response.sendRedirect("boardContent?bCode=" + Integer.parseInt(bCode));
			return; // redirect 이후 메소드를 종료합니다.
		}
		
		if (board == null){
			response.sendRedirect("myCommentList?delFinish=Y");
			return; // redirect 이후 메소드를 종료합니다.
		}
	}

}
