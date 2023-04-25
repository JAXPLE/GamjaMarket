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

public class ClientLeaveAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ClientDao clientDao = ClientDao.getInstance();
		Client client = (Client) session.getAttribute("log");
		String contact = client.getClientContact();
		System.out.println(client.getClientPassword());
		String pw = null;
		try {
			pw = Tool.setEncryption(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(pw.equals(client.getClientPassword())) {
			clientDao.removeClientByClientContact(contact);			
			request.getSession().removeAttribute("log");
			request.setAttribute("leave", true);
			request.getRequestDispatcher("hiddenWork").forward(request, response);		}
		else {
			response.sendRedirect("leave?failleave=true");
		}
		
		if(session.getAttribute("admin") != null) {
			response.sendRedirect("admin?delete=Y");
		}
	}
}
