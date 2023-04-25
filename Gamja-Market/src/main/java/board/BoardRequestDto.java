package board;

import java.sql.Timestamp;

public class BoardRequestDto {
	private int bCode;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private Timestamp bPublishDate;
	private Timestamp bModifyDate;
	private String bcateCode;

	public BoardRequestDto(int bCode, String bTitle, String bContent, String bWriter, Timestamp bPublishDate,
			Timestamp bModifyDate, String bcateCode) {
		this.bCode = bCode;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bPublishDate = bPublishDate;
		this.bModifyDate = bModifyDate;
		this.bcateCode = bcateCode;
	}
	
	public BoardRequestDto(
			int bCode, String bTitle, String bContent,
			String bWriter, Timestamp bPublishDate, Timestamp bModifyDate
			) {
		this.bCode = bCode;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bPublishDate = bPublishDate;
		this.bModifyDate = bModifyDate;
	}

	public BoardRequestDto(String bTitle, String bContent, String bWriter, String bcateCode) {
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bcateCode = bcateCode;
	}

	public BoardRequestDto(int bCode, String bTitle, String bContent, String bcateCode) {
		this.bCode = bCode;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bcateCode = bcateCode;
	}
	
	public int getbCode() {
		return bCode;
	}

	public void setbCode(int bCode) {
		this.bCode = bCode;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public Timestamp getbPublishDate() {
		return bPublishDate;
	}

	public void setbPublishDate(Timestamp bPublishDate) {
		this.bPublishDate = bPublishDate;
	}

	public Timestamp getbModifyDate() {
		return bModifyDate;
	}

	public void setbModifyDate(Timestamp bModifyDate) {
		this.bModifyDate = bModifyDate;
	}

	public String getBcateCode() {
		return bcateCode;
	}

	public void setBcateCode(String bcateCode) {
		this.bcateCode = bcateCode;
	}

}
