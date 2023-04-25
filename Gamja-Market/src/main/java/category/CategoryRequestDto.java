package category;

public class CategoryRequestDto {
	String cateCode;
	String cateName;

	public CategoryRequestDto(String cateCode, String cateName) {
		this.cateCode = cateCode;
		this.cateName = cateName;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
}
