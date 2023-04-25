package controller.action;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tool.Tool;
import client.ClientRequestDto;
import client.controller.ClientDao;

public class RegistAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String contact = request.getParameter("contact");
		String pw = null;
		try {
			pw = Tool.setEncryption(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = request.getParameter("nickname");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address")+" "+request.getParameter("detailAddress");		
		double clientY = Double.parseDouble(request.getParameter("y"));
		double clientX = Double.parseDouble(request.getParameter("x"));
		
		ClientRequestDto clientDto = new ClientRequestDto(contact, pw, name, postcode, address, clientY, clientX);
		ClientDao clientDao = ClientDao.getInstance();
		clientDao.createClient(clientDto);
		
		request.setAttribute("welcome", true);
		request.getRequestDispatcher("hiddenWork").forward(request, response);
	}
}
