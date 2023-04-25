package boardCateogry;

public class BoardCategory {
	String bcateCode;
	String bcateName;

	public BoardCategory(String bcateCode, String bcateName) {
		this.bcateCode = bcateCode;
		this.bcateName = bcateName;
	}

	public BoardCategory(BoardCategoryRequestDto boardCategoryDto) {
		this.bcateCode = boardCategoryDto.getBcateCode();
		this.bcateName = boardCategoryDto.getBcateName();
	}

	public String getBcateCode() {
		return bcateCode;
	}

	public String getBcateName() {
		return bcateName;
	}

	@Override
	public String toString() {
		return "BoardCategory [bcateCode=" + bcateCode + ", bcateName=" + bcateName + "]";
	}

	
}
