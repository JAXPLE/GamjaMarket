package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import client.Client;
import client.controller.ClientDao;

public class HelloAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		HttpSession session = request.getSession();
		Client log = (Client) session.getAttribute("log");
		ClientDao clientDao = ClientDao.getInstance();

		ArrayList<Client> list = clientDao.getClientAll();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<String> clients = new ArrayList<String>();
		ArrayList<String> contacts = new ArrayList<String>();

		for (Client client : list) {
			y.add(client.getClientY());
			x.add(client.getClientX());
			clients.add(client.getClientNickname());
			contacts.add(client.getClientContact());
		}

		JSONObject addressInfo = new JSONObject(); // { }
		
		if (log != null) {
			double clientX = log.getClientX();
			double clientY = log.getClientY();
			
			addressInfo.put("clientX", clientX);	// {"clientX", clientX}
			addressInfo.put("clientY", clientY);	// {"clientX", clientX, "clientY", clientY}
		}
		
		addressInfo.put("y", y);
		addressInfo.put("x", x);
		addressInfo.put("clients", clients);
		addressInfo.put("contacts", contacts);
		
		response.getWriter().print(addressInfo.toString());
		response.getWriter().flush();
	}
}



