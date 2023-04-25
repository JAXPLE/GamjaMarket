package boardCateogry.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import boardCateogry.BoardCategory;
import boardCateogry.BoardCategoryRequestDto;
import util.DBManager;


public class BoardCategoryDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardCategoryDao() {}
	
	private static BoardCategoryDao instance = new BoardCategoryDao();
	
	public static BoardCategoryDao getInstance() {
		return instance;
	}
	
	public void createBoardCategory(BoardCategoryRequestDto boardCategoryDto) {
		BoardCategory boardCategory = new BoardCategory(boardCategoryDto);

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "INSERT INTO board_category VALUES(?, ?);";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, boardCategory.getBcateCode());
				this.pstmt.setString(2, boardCategory.getBcateName());
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public ArrayList<BoardCategory> getBoardCategoryAll() {
		ArrayList<BoardCategory> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board_category";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String bcateCode = this.rs.getString(1);
					String bcateName = this.rs.getString(2);

					BoardCategory boardCategory = new BoardCategory(bcateCode, bcateName);
					list.add(boardCategory);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public String getBoardCategoryNameByCode(String bcateCode) {
		String bcateName = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT b_cate_name FROM board_category WHERE b_cate_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, bcateCode);
				this.rs = this.pstmt.executeQuery();
				this.rs.next();
				bcateName = this.rs.getString(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return bcateName;
	}

	public void deleteBoardCategory(String bcateCode) {

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "DELETE FROM board_category WHERE b_cate_code=?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, bcateCode);
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
