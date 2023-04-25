package interests.controller;

import java.sql.*;
import java.util.*;

import interests.Interests;
import interests.InterestsRequestDto;
import saleboard.Saleboard;
import saleboard.SaleboardRequestDto;
import util.DBManager;

public class InterestsDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private InterestsDao() {
	}

	private static InterestsDao instance = new InterestsDao();

	public static InterestsDao getInstance() {
		return instance;
	}

	public void createInterests(InterestsRequestDto interestsDto) {
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "INSERT INTO `interests` (sb_code, client_contact) VALUES (?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, interestsDto.getSbCode());
				this.pstmt.setString(2, interestsDto.getClientContact());
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}

	public Interests getInterestsByCode(int interestsCode) {
		Interests interests = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM interests WHERE interests_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, interestsCode);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					int sbCode = this.rs.getInt(2);
					String clientContact = this.rs.getString(3);

					InterestsRequestDto interestsRequestDto = new InterestsRequestDto(interestsCode, sbCode, clientContact);
					interests = new Interests(interestsRequestDto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return interests;
	}

	public int getInterestsCode(int sbCode, String clientContact) {
		int interestsCode = -1;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM interests WHERE sb_code = ? AND client_contact = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, sbCode);
				this.pstmt.setString(2, clientContact);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					interestsCode = this.rs.getInt(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return interestsCode;
	}
	
	public ArrayList<Interests> getInterestsCodeByContact(String clientContact) {
		ArrayList<Interests> list = new ArrayList<Interests>();
		
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM interests WHERE client_contact = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, clientContact);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int interestsCode = this.rs.getInt(1);
					int sbCode = this.rs.getInt(2);
				
					InterestsRequestDto interestsRequestDto = new InterestsRequestDto(interestsCode,sbCode, clientContact);
					Interests interests = new Interests(interestsRequestDto);
					list.add(interests);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	public boolean getInterests(int sbCode, String clientContact) {
		boolean isInterested = false;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM interests WHERE sb_code = ? AND client_contact = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, sbCode);
				this.pstmt.setString(2, clientContact);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					isInterested = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return isInterested;
	}
	
	public ArrayList<Saleboard> getInterestsListByContact(String clientContact) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard s JOIN interests i ON (s.sb_code = i.sb_code) WHERE i.client_contact = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, clientContact);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int sbCode = this.rs.getInt(1);
					String cateCode = this.rs.getString(2);
					String sbImg = this.rs.getString(3);
					String sbTitle = this.rs.getString(4);
					String sbContent = this.rs.getString(5);
					String sbSellClient = this.rs.getString(6);
					String sbBuyClient = this.rs.getString(7);
					Timestamp sbPublishDate = this.rs.getTimestamp(8);
					Timestamp sbModifyDate = this.rs.getTimestamp(9);
					int sbModiCount = this.rs.getInt(10);
					int sbPrice = this.rs.getInt(11);

					SaleboardRequestDto saleboardRequestDto = new SaleboardRequestDto(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient,
							sbBuyClient, sbPublishDate, sbModifyDate, sbModiCount, sbPrice);
					Saleboard board = new Saleboard(saleboardRequestDto);
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

	public void deleteInterests(int interestsCode) {
		Interests interests = getInterestsByCode(interestsCode);
		this.conn = DBManager.getConnectionFromMySQL();
		if (interests != null && this.conn != null) {
			String sql = "DELETE FROM interests WHERE interests_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, interestsCode);
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}
}