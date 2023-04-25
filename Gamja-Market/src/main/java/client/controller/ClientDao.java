package client.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import client.Client;
import client.ClientRequestDto;
import util.DBManager;

public class ClientDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ClientDao() {
	}

	private static ClientDao instance = new ClientDao();

	public static ClientDao getInstance() {
		return instance;
	}

	public void createClient(ClientRequestDto clientDto) {
		Client client = new Client(clientDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "INSERT INTO client VALUES(?, ?, ?, ?, ?, ?, ?, DEFAULT, 0);";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, client.getClientContact());
				this.pstmt.setString(2, client.getClientPassword());
				this.pstmt.setString(3, client.getClientNickname());
				this.pstmt.setString(4, client.getClientPostcode());
				this.pstmt.setString(5, client.getClientAddress());
				this.pstmt.setDouble(6, client.getClientY());
				this.pstmt.setDouble(7, client.getClientX());
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public ArrayList<Client> getClientAll() {
		ArrayList<Client> list = new ArrayList<Client>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM client ORDER BY client_joindate";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String contact = this.rs.getString(1);
					String pw = this.rs.getString(2);
					String name = this.rs.getString(3);
					String postcode = this.rs.getString(4);
					String address = this.rs.getString(5);
					double clientY = this.rs.getDouble(6);
					double clientX = this.rs.getDouble(7);
					Timestamp joinDate = this.rs.getTimestamp(8);
					int admin = this.rs.getInt(9);

					ClientRequestDto clientRequestDto = new ClientRequestDto(contact, pw, name, postcode, address, clientY, clientX, joinDate, admin);
					Client client = new Client(clientRequestDto);
					list.add(client);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public ArrayList<Double> getXAll() {
		ArrayList<Double> list = new ArrayList<Double>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_X FROM client";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Double x = this.rs.getDouble(1);
					list.add(x);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public ArrayList<Double> getYAll() {
		ArrayList<Double> list = new ArrayList<Double>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_Y FROM client";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Double y = this.rs.getDouble(1);
					list.add(y);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<String> getNicknameAll() {
		ArrayList<String> list = new ArrayList<String>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_nickname FROM client";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String nickname = this.rs.getString(1);
					list.add(nickname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public ArrayList<String> getContactAll() {
		ArrayList<String> list = new ArrayList<String>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_contact FROM client";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String contact = this.rs.getString(1);
					list.add(contact);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public Client getClientByContact(String contact) {
		Client client = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM client WHERE client_contact=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, contact);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String pw = this.rs.getString(2);
					String name = this.rs.getString(3);
					String postcode = this.rs.getString(4);
					String address = this.rs.getString(5);
					double clientY = this.rs.getDouble(6);
					double clientX = this.rs.getDouble(7);
					Timestamp joindate = this.rs.getTimestamp(8);
					int clientAdmin = this.rs.getInt(9);

					ClientRequestDto clientRequestDto = new ClientRequestDto(contact, pw, name, postcode, address, clientY, clientX, joindate, clientAdmin);
					client = new Client(clientRequestDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return client;
	}

	public String getClientNicknameByContact(String contact) {
		String nickname = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_nickname FROM client WHERE client_contact=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, contact);
				this.rs = this.pstmt.executeQuery();
				this.rs.next();
				nickname = this.rs.getString(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return nickname;
	}
	
	public String getClientContactByNickname(String nickname) {
		String contact = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT client_contact FROM client WHERE client_nickname=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, nickname);
				this.rs = this.pstmt.executeQuery();
				this.rs.next();
				contact = this.rs.getString(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return contact;
	}

	public void updateClient(ClientRequestDto clientDto) {
		Client client = new Client(clientDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null && client != null) {
			String sql = "UPDATE client SET client_password=?, client_nickname=?, client_postcode=?, client_address=?, client_Y=?, client_X=? WHERE client_contact=?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, client.getClientPassword());
				this.pstmt.setString(2, client.getClientNickname());
				this.pstmt.setString(3, client.getClientPostcode());
				this.pstmt.setString(4, client.getClientAddress());
				this.pstmt.setDouble(5, client.getClientY());
				this.pstmt.setDouble(6, client.getClientX());
				this.pstmt.setString(7, client.getClientContact());
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public void removeClientByClientContact(String contact) {
		Client client = getClientByContact(contact);
		this.conn = DBManager.getConnectionFromMySQL();

		if (client != null && this.conn != null) {
			String sql = "DELETE FROM client WHERE client_contact = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, contact);
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
