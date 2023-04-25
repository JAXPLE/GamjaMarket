package controller.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Tool.Tool;
import client.Client;
import client.controller.ClientDao;

public class LoginAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String contact = request.getParameter("contact");
		String pw = null;
		try {
			pw = Tool.setEncryption(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ClientDao clientDao = ClientDao.getInstance();
		Client client = clientDao.getClientByContact(contact);

		if (client != null && pw.equals(client.getClientPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("log", client);

			if (client.getClientAdmin() == 1) {
				session.setAttribute("admin", "Y");
			}
			session.setAttribute("isPageLoaded", true);
			request.setAttribute("login", true);
			request.getRequestDispatcher("hiddenWork").forward(request, response);		}
		else {
			request.setAttribute("loginFail", true);
			request.getRequestDispatcher("hiddenWork").forward(request, response);		}
	}
}
