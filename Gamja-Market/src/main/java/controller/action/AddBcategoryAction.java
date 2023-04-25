package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardCateogry.BoardCategoryRequestDto;
import boardCateogry.controller.BoardCategoryDao;
import category.controller.CategoryDao;

public class AddBcategoryAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bcateCode = request.getParameter("bcateCode");
		String bcateName = request.getParameter("bcateName");
		
//		System.out.println(bcateCode);
//		System.out.println(bcateName);
		
		BoardCategoryRequestDto boardCategoryDto = new BoardCategoryRequestDto(bcateCode, bcateName);
		BoardCategoryDao boardCategoryDao = BoardCategoryDao.getInstance();
		
		boardCategoryDao.createBoardCategory(boardCategoryDto);
		response.sendRedirect("admin?create=Y");
	}
}