package saleboard;

import java.sql.Timestamp;

public class SaleboardRequestDto {
	private int sbCode, sbModiCount, sbPrice;
	private String cateCode, sbImg, sbTitle, sbContent, sbSellClient, sbBuyClient;
	private Timestamp sbPublishDate, sbModifyDate;
	
	public SaleboardRequestDto(int sbCode, String cateCode, String sbImg, String sbTitle, String sbContent, String sbSellClient, int sbPrice) {
		this.sbCode = sbCode;
		this.cateCode = cateCode;
		this.sbImg = sbImg;
		this.sbTitle = sbTitle;
		this.sbContent = sbContent;
		this.sbSellClient = sbSellClient;
		this.sbPrice = sbPrice;
	}
	
	public SaleboardRequestDto(int sbCode, String cateCode, String sbImg, String sbTitle, String sbContent, String sbSellClient, 
			String sbBuyClient, Timestamp sbPublishDate, Timestamp sbModifyDate, int sbModiCount, int sbPrice) {
		this.sbCode = sbCode;
		this.sbModiCount = sbModiCount;
		this.cateCode = cateCode;
		this.sbImg = sbImg;
		this.sbTitle = sbTitle;
		this.sbContent = sbContent;
		this.sbSellClient = sbSellClient;
		this.sbBuyClient = sbBuyClient;
		this.sbPublishDate = sbPublishDate;
		this.sbModifyDate = sbModifyDate;
		this.sbPrice = sbPrice;
	}

	public int getSbCode() {
		return sbCode;
	}

	public void setSbCode(int sbCode) {
		this.sbCode = sbCode;
	}

	public int getSbModiCount() {
		return sbModiCount;
	}

	public void setSbModiCount(int sbModiCount) {
		this.sbModiCount = sbModiCount;
	}

	public int getSbPrice() {
		return sbPrice;
	}

	public void setSbPrice(int sbPrice) {
		this.sbPrice = sbPrice;
	}	

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getSbImg() {
		return sbImg;
	}

	public void setSbImg(String sbImg) {
		this.sbImg = sbImg;
	}

	public String getSbTitle() {
		return sbTitle;
	}

	public void setSbTitle(String sbTitle) {
		this.sbTitle = sbTitle;
	}

	public String getSbContent() {
		return sbContent;
	}

	public void setSbContent(String sbContent) {
		this.sbContent = sbContent;
	}

	public String getSbSellClient() {
		return sbSellClient;
	}

	public void setSbSellClient(String sbSellClient) {
		this.sbSellClient = sbSellClient;
	}

	public String getSbBuyClient() {
		return sbBuyClient;
	}

	public void setSbBuyClient(String sbBuyClient) {
		this.sbBuyClient = sbBuyClient;
	}

	public Timestamp getSbPublishDate() {
		return sbPublishDate;
	}

	public void setSbPublishDate(Timestamp sbPublishDate) {
		this.sbPublishDate = sbPublishDate;
	}

	public Timestamp getSbModifyDate() {
		return sbModifyDate;
	}

	public void setSbModifyDate(Timestamp sbModifyDate) {
		this.sbModifyDate = sbModifyDate;
	}
}
