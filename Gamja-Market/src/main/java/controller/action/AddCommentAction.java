package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tool.Tool;
import comment.CommentRequestDto;
import comment.controller.CommentDao;

public class AddCommentAction implements Action {
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String bCode = 			request.getParameter("bCode");
		String comContent = 	request.getParameter("comContent");
		String clientContact =  request.getParameter("clientContact");
		
		int intBCode = Integer.parseInt(bCode);
		CommentRequestDto comment = new CommentRequestDto(intBCode,comContent,clientContact);
		CommentDao commentDao = CommentDao.getInstance();
		commentDao.createComment(comment);
		response.sendRedirect("boardContent?bCode=" + bCode);
	}
}