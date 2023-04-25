package board;

import java.sql.Timestamp;

public class Board {
	private int bCode;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private Timestamp bPublishDate;
	private Timestamp bModifyDate;
	private String bcateCode;

	public Board(int bCode, String bTitle, String bContent, String bWriter, Timestamp bPublishDate,
			Timestamp bModifyDate, String bcateCode) {
		this.bCode = bCode;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bWriter = bWriter;
		this.bPublishDate = bPublishDate;
		this.bModifyDate = bModifyDate;
		this.bcateCode = bcateCode;
	}

	public Board(BoardRequestDto boardDto) {
		this.bCode 		 = boardDto.getbCode();
		this.bTitle 	 = boardDto.getbTitle();
		this.bContent	 = boardDto.getbContent();
		this.bWriter 	 = boardDto.getbWriter();
		this.bPublishDate= boardDto.getbPublishDate();
		this.bModifyDate = boardDto.getbModifyDate();
		this.bcateCode 	 = boardDto.getBcateCode();
	}

	public int getbCode() {
		return bCode;
	}

	public String getbTitle() {
		return bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public String getbWriter() {
		return bWriter;
	}

	public Timestamp getbPublishDate() {
		return bPublishDate;
	}

	public Timestamp getbModifyDate() {
		return bModifyDate;
	}

	public String getBcateCode() {
		return bcateCode;
	}

	
}
