package interests;

public class InterestsRequestDto {
	private int interestsCode, sbCode;
	private String clientContact;

	public InterestsRequestDto(int sbCode, String clientContact) {
		this.sbCode = sbCode;
		this.clientContact = clientContact;
	}
	
	public InterestsRequestDto(int interestsCode, int sbCode, String clientContact) {
		this.interestsCode = interestsCode;
		this.sbCode = sbCode;
		this.clientContact = clientContact;
	}

	public int getInterestsCode() {
		return interestsCode;
	}

	public void setInterestsCode(int interestsCode) {
		this.interestsCode = interestsCode;
	}

	public int getSbCode() {
		return sbCode;
	}

	public void setSbCode(int sbCode) {
		this.sbCode = sbCode;
	}

	public String getClientContact() {
		return clientContact;
	}

	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}
}
