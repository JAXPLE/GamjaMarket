package interests;

public class Interests {
	private int interestsCode, sbCode;
	private String clientContact;
	
	public Interests(InterestsRequestDto interestsDto) {
		this.interestsCode = interestsDto.getInterestsCode();
		this.sbCode = interestsDto.getSbCode();
		this.clientContact = interestsDto.getClientContact();
	}
	
	public Interests(int interestsCode, int sbCode, String clientContact) {
		this.interestsCode = interestsCode;
		this.sbCode = sbCode;
		this.clientContact = clientContact;
	}
	
	public int getInterestsCode() {
		return interestsCode;
	}
	
	public int getSbCode() {
		return sbCode;
	}
	
	public String getClientContact() {
		return clientContact;
	}
}
