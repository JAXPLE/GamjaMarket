package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Tool.Tool;
import client.Client;
import client.ClientRequestDto;
import client.controller.ClientDao;

public class ModifyClientAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		ClientDao clientDao = ClientDao.getInstance();

		String clientContact = request.getParameter("contact");
		String clientPassword = null;
		try {
			clientPassword = Tool.setEncryption(request.getParameter("password"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String clientNickname = request.getParameter("nickname");
		String clientPostcode = request.getParameter("postcode");
		String clientAddress = request.getParameter("address");
		double clientY = Double.parseDouble(request.getParameter("y"));
		double clientX = Double.parseDouble(request.getParameter("x"));

		ClientRequestDto modifyClientInfo = new ClientRequestDto(clientContact, clientPassword, clientNickname,	clientPostcode, clientAddress, clientY, clientX);
		clientDao.updateClient(modifyClientInfo);
		Client log = clientDao.getClientByContact(clientContact);
		HttpSession session = request.getSession();
		session.setAttribute("log", log);
		
		request.setAttribute("modify", true);
		request.getRequestDispatcher("hiddenWork").forward(request, response);	}
}
