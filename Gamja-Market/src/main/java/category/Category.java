package category;

public class Category {
	String cateCode;
	String cateName;

	public Category(String cateCode, String cateName) {
		this.cateCode = cateCode;
		this.cateName = cateName;
	}

	public Category(CategoryRequestDto categoryDto) {
		this.cateCode = categoryDto.getCateCode();
		this.cateName = categoryDto.getCateName();
	}

	public String getCateCode() {
		return cateCode;
	}

	public String getCateName() {
		return cateName;
	}
	
}
