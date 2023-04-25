package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import interests.InterestsRequestDto;
import interests.controller.InterestsDao;

public class AddInterestsAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbCode = Integer.parseInt(request.getParameter("code"));

		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute("log");
		String clientContact = client.getClientContact();

		InterestsRequestDto interestsDto = new InterestsRequestDto(sbCode, clientContact);
		InterestsDao interestsDao = InterestsDao.getInstance();
		interestsDao.createInterests(interestsDto);
		response.sendRedirect("viewSale?code=" + sbCode);
	}
}