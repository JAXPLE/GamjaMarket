package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saleboard.SaleboardRequestDto;
import saleboard.controller.SaleboardDao;

public class SaleboardModifyAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbCode = Integer.parseInt(request.getParameter("sbCode"));
		String cateCode = request.getParameter("cateCode");
		String sbImg = request.getParameter("sbImg");
		String sbTitle = request.getParameter("sbTitle");
		String sbContent = request.getParameter("sbContent");
		String sbSellClient = request.getParameter("sbSellClient");
		int sbPrice = Integer.parseInt(request.getParameter("sbPrice"));
		SaleboardRequestDto saleboardDto = new SaleboardRequestDto(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient, sbPrice);
		
		SaleboardDao saleboardDao = SaleboardDao.getInstance();
		saleboardDao.updateSaleBoard(saleboardDto);
		
		response.sendRedirect("viewSale?code=" + sbCode);
		
	}
}
