package category.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import category.Category;
import category.CategoryRequestDto;
import util.DBManager;

public class CategoryDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CategoryDao() {
	}

	private static CategoryDao instance = new CategoryDao();

	public static CategoryDao getInstance() {
		return instance;
	}

	public void createCategory(CategoryRequestDto categoryDto) {
		Category category = new Category(categoryDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "INSERT INTO category VALUES(?, ?);";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, category.getCateCode());
				this.pstmt.setString(2, category.getCateName());
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public ArrayList<Category> getCategoryAll() {
		ArrayList<Category> list = new ArrayList<Category>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM category";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String cateCode = this.rs.getString(1);
					String cateName = this.rs.getString(2);

					Category category = new Category(cateCode, cateName);
					list.add(category);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public String getCategoryNameByCode(String cateCode) {
		String cateName = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT cate_name FROM category WHERE cate_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, cateCode);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					cateName = this.rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return cateName;
	}

	public void deleteCategory(String cateCode) {

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "DELETE FROM category WHERE cate_code=?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, cateCode);
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
