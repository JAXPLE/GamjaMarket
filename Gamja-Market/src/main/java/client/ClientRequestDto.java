package client;

import java.sql.Timestamp;

public class ClientRequestDto {

	int clientAdmin;
	double clientY, clientX;
	String clientContact, clientPassword, clientNickname, clientPostcode, clientAddress; 
	Timestamp clientJoindate;
	

	public ClientRequestDto(String clientContact, String clientPassword, String clientNickname, String clientPostcode, String clientAddress, double clientY, double clientX, Timestamp clientJoindate, int clientAdmin) {
		this.clientContact = clientContact;
		this.clientPassword = clientPassword;
		this.clientNickname = clientNickname;
		this.clientPostcode = clientPostcode;
		this.clientAddress = clientAddress;
		this.clientY = clientY;
		this.clientX = clientX;
		this.clientJoindate = clientJoindate;
		this.clientAdmin = clientAdmin;
	}
	
	public ClientRequestDto(String clientContact, String clientPassword, String clientNickname, String clientPostcode, String clientAddress, double clientY, double clientX) {
		this.clientContact = clientContact;
		this.clientPassword = clientPassword;
		this.clientNickname = clientNickname;
		this.clientPostcode = clientPostcode;
		this.clientAddress = clientAddress;
		this.clientY = clientY;
		this.clientX = clientX;
	}
	
	public ClientRequestDto(String clientContact, String clientPassword, String clientNickname, String clientPostcode, String clientAddress, Timestamp clientJoindate) {
		this.clientContact = clientContact;
		this.clientPassword = clientPassword;
		this.clientNickname = clientNickname;
		this.clientPostcode = clientPostcode;
		this.clientAddress = clientAddress;
		this.clientJoindate = clientJoindate;
	}
	
	public ClientRequestDto(String clientContact, String clientPassword, String clientNickname, String clientPostcode, String clientAddress) {
		this.clientContact = clientContact;
		this.clientPassword = clientPassword;
		this.clientNickname = clientNickname;
		this.clientPostcode = clientPostcode;
		this.clientAddress = clientAddress;
	}
	
	public int getClientAdmin() {
		return clientAdmin;
	}


	public void setClientAdmin(int clientAdmin) {
		this.clientAdmin = clientAdmin;
	}


	public String getClientPostcode() {
		return clientPostcode;
	}


	public void setClientPostcode(String clientPostcode) {
		this.clientPostcode = clientPostcode;
	}


	public double getClientY() {
		return clientY;
	}


	public void setClientY(double clientY) {
		this.clientY = clientY;
	}


	public double getClientX() {
		return clientX;
	}


	public void setClientX(double clientX) {
		this.clientX = clientX;
	}


	public String getClientContact() {
		return clientContact;
	}


	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}


	public String getClientPassword() {
		return clientPassword;
	}


	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}


	public String getClientNickname() {
		return clientNickname;
	}


	public void setClientNickname(String clientNickname) {
		this.clientNickname = clientNickname;
	}


	public String getClientAddress() {
		return clientAddress;
	}


	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}


	public Timestamp getClientJoindate() {
		return clientJoindate;
	}


	public void setClientJoindate(Timestamp clientJoindate) {
		this.clientJoindate = clientJoindate;
	}
	
	
	
}
