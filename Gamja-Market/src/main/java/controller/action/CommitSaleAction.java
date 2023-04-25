package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tool.Tool;
import saleboard.controller.SaleboardDao;

public class CommitSaleAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sbBuyClient = Tool.getClientContactByNickname(request.getParameter("sbBuyClient"));
		String sbCode = request.getParameter("sbCode");
		SaleboardDao saleboardDao = SaleboardDao.getInstance();
		saleboardDao.commitSale(sbBuyClient, sbCode);
		
		response.sendRedirect("clientPageList?sellDone=true");
	}

}
