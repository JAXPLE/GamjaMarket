package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import interests.Interests;
import interests.controller.InterestsDao;

public class RemoveInterestsAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbCode = Integer.parseInt(request.getParameter("code"));
		HttpSession session = request.getSession();
		Client log = (Client)session.getAttribute("log");
		String clientContact = log.getClientContact();
		
		InterestsDao interestsDao = InterestsDao.getInstance();
		int interestsCode = interestsDao.getInterestsCode(sbCode, clientContact);
		
		interestsDao.deleteInterests(interestsCode);
		response.sendRedirect("viewSale?code=" + sbCode);
	}
}