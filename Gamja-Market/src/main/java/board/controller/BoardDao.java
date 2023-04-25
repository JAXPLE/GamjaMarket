package board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import board.Board;
import board.BoardRequestDto;
import util.DBManager;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDao() {}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	public void createBoard(BoardRequestDto boardDto) {
		Board board = new Board(boardDto);

		this.conn = DBManager.getConnectionFromMySQL();

		if (this.conn != null) {
			String sql = "INSERT INTO board (b_title, b_content, b_writer, bcate_code)  VALUES(?, ?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, board.getbTitle());
				this.pstmt.setString(2, board.getbContent());
				this.pstmt.setString(3, board.getbWriter());
				this.pstmt.setString(4, board.getBcateCode());
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	public int getBoardSize() {
		int size = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		if(this.conn!=null) {
			String sql = "SELECT COUNT(*) FROM board";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					size = this.rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return size;
	}

	public ArrayList<Board> getBoardAll(int pages) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board ORDER BY b_code DESC LIMIT ?, ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				int start = (pages-1) * 10;
				int amount = 10;
				this.pstmt.setInt(1, start);
				this.pstmt.setInt(2, amount);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Board> getBoardListMain() {
		ArrayList<Board> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board ORDER BY b_code DESC LIMIT 0, 4";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public ArrayList<Board> getBoardAll() {
		ArrayList<Board> list = new ArrayList<>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Board> getBoardList(String search, String value) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = String.format("SELECT * FROM board WHERE %s LIKE ?", search);

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, "%" + value + "%");
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		return list;
	}
	
	public ArrayList<Board> getBoardListByCateCode(String cateCode, String search, String value) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = String.format("SELECT * FROM board WHERE bcate_code = ? AND %s LIKE ?", search);

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, cateCode);
				this.pstmt.setString(2, "%" + value + "%");
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	public Board getBoardByBcode(int bCode) {
		Board board = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE b_code=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, bCode);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					board = new Board(boardRequestDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return board;
	}

	public int getMaxBcode() {
		int bCode = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT MAX(b_code) FROM board";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					bCode = this.rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return bCode;
	}

	public ArrayList<Board> getBoardByBWriter(String bWriter) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE b_writer=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, bWriter);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);
					String bcateCode = this.rs.getString(7);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
					Board board = new Board(boardRequestDto);
					list.add(board);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Board> getBoardListByCateCode(String bcateCode) {
		ArrayList<Board> list = new ArrayList<Board>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM board WHERE bcate_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, bcateCode);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int bCode = this.rs.getInt(1);
					String bTitle = this.rs.getString(2);
					String bContent = this.rs.getString(3);
					String bWriter = this.rs.getString(4);
					Timestamp bPublishDate = this.rs.getTimestamp(5);
					Timestamp bModifyDate = this.rs.getTimestamp(6);

					BoardRequestDto boardRequestDto = new BoardRequestDto(bCode, bTitle, bContent, bWriter, bPublishDate, bModifyDate, bcateCode);
				    Board board = new Board(boardRequestDto);
				    list.add(board);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	public void updateBoard(BoardRequestDto boardDto) {

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null && boardDto != null) {
			String sql = "UPDATE board SET b_title=?, b_content=?, b_modify_date=DEFAULT, bcate_code=? WHERE b_code=?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, boardDto.getbTitle());
				this.pstmt.setString(2, boardDto.getbContent());
				this.pstmt.setString(3, boardDto.getBcateCode());
				this.pstmt.setInt(4, boardDto.getbCode());
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public void deleteBoard(int bCode) {

		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "DELETE FROM board WHERE b_code=?";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, bCode);
				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
