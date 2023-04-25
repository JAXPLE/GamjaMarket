package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saleboard.SaleboardRequestDto;
import saleboard.controller.SaleboardDao;

public class SaleboardAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SaleboardDao saleboardDao = SaleboardDao.getInstance();
		int sbCode = Integer.parseInt(request.getParameter("sbCode"));
		
		try {
			String cateCode = request.getParameter("cateCode");
			String sbImg = request.getParameter("sbImg");
			String sbTitle = request.getParameter("sbTitle");
			String sbContent = request.getParameter("sbContent");
			String sbSellClient = request.getParameter("sbSellClient");
			int sbPrice = Integer.parseInt(request.getParameter("sbPrice"));
			
			SaleboardRequestDto saleboardDto = new SaleboardRequestDto(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient, sbPrice);
			saleboardDao.createSaleboard(saleboardDto);
			
			if(sbCode==-1)
				sbCode = saleboardDao.getMaxOfSaleboardCode() + 1; 
			response.sendRedirect("viewSale?code=" + (sbCode+1));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("saleboard");
		}
	}
}
