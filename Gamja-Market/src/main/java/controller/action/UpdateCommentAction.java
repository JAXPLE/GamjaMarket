package controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.CommentRequestDto;
import comment.controller.CommentDao;

public class UpdateCommentAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		
		int bCode 			 = Integer.parseInt(request.getParameter("bCode"));
		int comCode 		 = Integer.parseInt(request.getParameter("comCode"));
		String comContent 	 = request.getParameter("comContent");
		String comDate		 = request.getParameter("comDate");
		Timestamp time 		 = Timestamp.valueOf(comDate);
		
		CommentDao commentDao = CommentDao.getInstance();
		CommentRequestDto commentRequestDto = new CommentRequestDto(comCode, comContent, time);
		commentDao.updateComment(commentRequestDto);
		session.removeAttribute("updateCommentComCode");
	
		response.sendRedirect("boardContent?bCode=" + bCode);
	}
}
