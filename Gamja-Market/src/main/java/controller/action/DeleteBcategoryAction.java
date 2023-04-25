package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardCateogry.controller.BoardCategoryDao;
import category.controller.CategoryDao;

public class DeleteBcategoryAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bcateCode = request.getParameter("bcateCode");
		BoardCategoryDao boardCategoryDao = BoardCategoryDao.getInstance();
		
		boardCategoryDao.deleteBoardCategory(bcateCode);
		response.sendRedirect("admin?delete=Y");
	}
}