package boardCateogry;

public class BoardCategoryRequestDto {
	String bcateCode;
	String bcateName;

	public BoardCategoryRequestDto(String bcateCode, String bcateName) {
		this.bcateCode = bcateCode;
		this.bcateName = bcateName;
	}

	public String getBcateCode() {
		return bcateCode;
	}

	public void setBcateCode(String bcateCode) {
		this.bcateCode = bcateCode;
	}

	public String getBcateName() {
		return bcateName;
	}

	public void setBcateName(String bcateName) {
		this.bcateName = bcateName;
	}

}
