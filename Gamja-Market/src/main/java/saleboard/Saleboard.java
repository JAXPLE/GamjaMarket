package saleboard;

import java.sql.*;

public class Saleboard {
	private int sbCode, sbModiCount, sbPrice;
	private String cateCode, sbImg, sbTitle, sbContent, sbSellClient, sbBuyClient;
	private Timestamp sbPublishDate, sbModifyDate;

	public Saleboard(SaleboardRequestDto saleboardDto) {
		this.sbCode = saleboardDto.getSbCode();
		this.cateCode = saleboardDto.getCateCode();
		this.sbImg = saleboardDto.getSbImg();
		this.sbTitle = saleboardDto.getSbTitle();
		this.sbContent = saleboardDto.getSbContent();
		this.sbSellClient = saleboardDto.getSbSellClient();
		this.sbBuyClient = saleboardDto.getSbBuyClient();
		this.sbPublishDate = saleboardDto.getSbPublishDate();
		this.sbModifyDate = saleboardDto.getSbModifyDate();
		this.sbModiCount = saleboardDto.getSbModiCount();
		this.sbPrice = saleboardDto.getSbPrice();
	}
	
	public Saleboard(int sbCode, String cateCode, String sbImg, String sbTitle, String sbContent, String sbSellClient, 
			String sbBuyClient, Timestamp sbPublishDate, Timestamp sbModifyDate, int sbModiCount, int sbPrice) {
		this.sbCode = sbCode;
		this.cateCode = cateCode;
		this.sbImg = sbImg;
		this.sbTitle = sbTitle;
		this.sbContent = sbContent;
		this.sbSellClient = sbSellClient;
		this.sbBuyClient = sbBuyClient;
		this.sbPublishDate = sbPublishDate;
		this.sbModifyDate = sbModifyDate;
		this.sbModiCount = sbModiCount;
		this.sbPrice = sbPrice;
	}

	public int getSbCode() {
		return sbCode;
	}

	public int getSbModiCount() {
		return sbModiCount;
	}

	public int getSbPrice() {
		return sbPrice;
	}

	public String getCateCode() {
		return cateCode;
	}

	public String getSbImg() {
		return sbImg;
	}

	public String getSbTitle() {
		return sbTitle;
	}

	public String getSbContent() {
		return sbContent;
	}

	public String getSbSellClient() {
		return sbSellClient;
	}

	public String getSbBuyClient() {
		return sbBuyClient;
	}

	public Timestamp getSbPublishDate() {
		return sbPublishDate;
	}

	public Timestamp getSbModifyDate() {
		return sbModifyDate;
	}
}
