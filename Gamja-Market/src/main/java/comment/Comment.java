package comment;

import java.sql.Timestamp;

public class Comment { 
	private int comCode, bCode;
	private String comContent, clientContact;
	private Timestamp comDate;
	
	public Comment(int comCode,int bCode, String comContent,String clientContact,Timestamp comDate) {
		this.comCode = comCode;
		this.clientContact = clientContact;
		this.bCode = bCode;
		this.comContent = comContent;
		this.comDate = comDate;
	}
	
	public Comment(CommentRequestDto commentRequestDto) {
		this.comCode = commentRequestDto.getComCode();
		this.clientContact = commentRequestDto.getClientContact();
		this.bCode = commentRequestDto.getbCode();
		this.comContent = commentRequestDto.getComContent();
		this.comDate = commentRequestDto.getComDate();
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

	@Override
	public String toString() {
		return "Comment [comCode=" + comCode + ", bCode=" + bCode + ", comContent=" + comContent + ", clientContact="
				+ clientContact + ", comDate=" + comDate + "]";
	}

	
}