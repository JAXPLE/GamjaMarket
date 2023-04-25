package comment;

import java.sql.Timestamp;

public class CommentRequestDto { 
	private int comCode, bCode;
	private String comContent, clientContact;
	private Timestamp comDate;
	
	public CommentRequestDto(
			int comCode,int bCode, String comContent,String clientContact,Timestamp comDate
			) {
		this.comCode = comCode;
		this.clientContact = clientContact;
		this.bCode = bCode;
		this.comContent = comContent;
		this.comDate = comDate;
	}
	
	public CommentRequestDto(int bCode,String comContent,String clientContact) {
		this.clientContact = clientContact;
		this.bCode = bCode;
		this.comContent = comContent;
	}
	
	public CommentRequestDto(int comCode, String comContent, Timestamp comDate) {
		this.comCode = comCode;
		this.comContent = comContent;
		this.comDate = comDate;
	}
	
	public int getComCode() {
		return comCode;
	}

	public int getbCode() {
		return bCode;
	}

	public String getComContent() {
		return comContent;
	}

	public String getClientContact() {
		return clientContact;
	}

	public Timestamp getComDate() {
		return comDate;
	}

	public void setComCode(int comCode) {
		this.comCode = comCode;
	}

	public void setbCode(int bCode) {
		this.bCode = bCode;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}

	public void setComDate(Timestamp comDate) {
		this.comDate = comDate;
	}

	
	
}