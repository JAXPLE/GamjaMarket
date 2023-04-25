package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import saleboard.controller.SaleboardDao;

public class SaleboardDeleteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbCode = Integer.parseInt(request.getParameter("code"));
		
		SaleboardDao saleboardDao = SaleboardDao.getInstance();
		saleboardDao.deleteSaleBoard(sbCode);
		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("admin") != null) {
			response.sendRedirect("adminSellList");
		}
		
		else {
			response.sendRedirect("saleboard");
		}
		
	}

}
