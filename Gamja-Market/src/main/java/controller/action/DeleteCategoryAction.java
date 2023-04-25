package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.controller.CategoryDao;

public class DeleteCategoryAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateCode = request.getParameter("cateCode");
		CategoryDao categoryDao = CategoryDao.getInstance();
		categoryDao.deleteCategory(cateCode);
		response.sendRedirect("admin?delete=Y");
	}
}