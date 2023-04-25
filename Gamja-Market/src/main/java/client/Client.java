package client;

import java.sql.Timestamp;

public class Client {

	int clientAdmin;
	double clientY, clientX;
	String clientContact, clientPassword, clientNickname, clientPostcode, clientAddress;
	Timestamp clientJoindate;

	public Client(int clientAdmin, double clientY, double clientX, String clientContact, String clientPassword,
			String clientNickname, String clientPostcode, String clientAddress, Timestamp clientJoindate) {
		this.clientAdmin = clientAdmin;
		this.clientY = clientY;
		this.clientX = clientX;
		this.clientContact = clientContact;
		this.clientPassword = clientPassword;
		this.clientNickname = clientNickname;
		this.clientPostcode = clientPostcode;
		this.clientAddress = clientAddress;
		this.clientJoindate = clientJoindate;
	}

	public Client(ClientRequestDto clientRequestDto) {
		this.clientAdmin = clientRequestDto.clientAdmin;
		this.clientY = clientRequestDto.clientY;
		this.clientX = clientRequestDto.clientX;
		this.clientContact = clientRequestDto.clientContact;
		this.clientPassword = clientRequestDto.clientPassword;
		this.clientNickname = clientRequestDto.clientNickname;
		this.clientPostcode = clientRequestDto.clientPostcode;
		this.clientAddress = clientRequestDto.clientAddress;
		this.clientJoindate = clientRequestDto.clientJoindate;

	}
	
	public int getClientAdmin() {
		return clientAdmin;
	}

	public String getClientPostcode() {
		return clientPostcode;
	}

	public double getClientY() {
		return clientY;
	}

	public double getClientX() {
		return clientX;
	}

	public String getClientContact() {
		return clientContact;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public String getClientNickname() {
		return clientNickname;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public Timestamp getClientJoindate() {
		return clientJoindate;
	}

}
