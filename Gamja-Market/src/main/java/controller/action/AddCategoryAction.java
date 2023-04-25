package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.CategoryRequestDto;
import category.controller.CategoryDao;

public class AddCategoryAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateCode = request.getParameter("cateCode");
		String cateName = request.getParameter("cateName");
		CategoryRequestDto categoryDto = new CategoryRequestDto(cateCode, cateName);
		CategoryDao categoryDao = CategoryDao.getInstance();
		categoryDao.createCategory(categoryDto);
		response.sendRedirect("admin?create=Y");
	}
}