package saleboard.controller;

import java.sql.*;
import java.util.*;

import client.Client;
import comment.Comment;
import saleboard.Saleboard;
import saleboard.SaleboardRequestDto;
import util.DBManager;

public class SaleboardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private SaleboardDao() {}

	private static SaleboardDao instance = new SaleboardDao();

	public static SaleboardDao getInstance() {
		return instance;
	}

	public void createSaleboard(SaleboardRequestDto saleboardDto) {
		this.conn = DBManager.getConnectionFromMySQL();

		if(this.conn!=null) {
			String sql = "INSERT INTO saleboard (cate_code, sb_img, sb_title, sb_content, sb_sellclient, sb_price) VALUES (?, ?, ?, ?, ?, ?)";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, saleboardDto.getCateCode());
				this.pstmt.setString(2, saleboardDto.getSbImg());
				this.pstmt.setString(3, saleboardDto.getSbTitle());
				this.pstmt.setString(4, saleboardDto.getSbContent());
				this.pstmt.setString(5, saleboardDto.getSbSellClient());
				this.pstmt.setInt(6, saleboardDto.getSbPrice());
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}

	public Saleboard getSaleboardByCode(int sbCode) {
		Saleboard board = null;
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard WHERE sb_code = ? ";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, sbCode);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
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

					SaleboardRequestDto saleboardRequestDto = new SaleboardRequestDto(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient, sbBuyClient,
							sbPublishDate, sbModifyDate, sbModiCount, sbPrice);
					board = new Saleboard(saleboardRequestDto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return board;
	}
	
	
	public ArrayList<Saleboard> getAllLisBySaleboard(){
		ArrayList<Saleboard> list = new ArrayList<>();
		
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn == null) {
		    return list;
		}

		String sql = "SELECT * FROM saleboard ORDER BY sb_code DESC";
		try {
		    this.pstmt = this.conn.prepareStatement(sql);
		    this.rs = this.pstmt.executeQuery();
		    while (this.rs.next()) {
		        int sbCode = this.rs.getInt("sb_code");
		        String cateCode = this.rs.getString("cate_code");
		        String sbImg = this.rs.getString("sb_img");
		        String sbTitle = this.rs.getString("sb_title");
		        String sbContent = this.rs.getString("sb_content");
		        String sbSellClient = this.rs.getString("sb_sellclient");
		        String sbBuyClient = this.rs.getString("sb_buyclient");
		        Timestamp sbPublishDate = this.rs.getTimestamp("sb_publish_date");
		        Timestamp sbModifyDate = this.rs.getTimestamp("sb_modify_date");
		        int sbModiCount = this.rs.getInt("sb_modi_count");
		        int sbPrice = this.rs.getInt("sb_price");

		        SaleboardRequestDto saleboardRequestDto = new SaleboardRequestDto(
		                sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient, 
		                sbBuyClient, sbPublishDate, sbModifyDate, sbModiCount, sbPrice
		        );
		        Saleboard saleboard = new Saleboard(saleboardRequestDto);
		        list.add(saleboard);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public ArrayList<Saleboard> getPopularSaleboardList() {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM popularSale LIMIT 0, 4";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
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
	
	public ArrayList<Saleboard> getSaleboardListAll() {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard ORDER BY sb_code DESC";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
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
	
	public ArrayList<Saleboard> getSaleboardList(Client client, String search, String value) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT s.* FROM saleboard s JOIN `client` c ON (s.sb_sellclient = c.client_contact)";
			if(client!=null && client.getClientAdmin()!=1) {
				sql += " WHERE c.client_Y>? AND c.client_Y<? AND c.client_X>? AND c.client_X<?";
			}
			sql += String.format(" AND %s LIKE ? ORDER BY s.sb_code DESC", search);

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				if(client!=null && client.getClientAdmin()!=1) {
					double coverageY = 0.017056;
					double coverageX = 0.017400;
					this.pstmt.setDouble(1, client.getClientY()-coverageY);
					this.pstmt.setDouble(2, client.getClientY()+coverageY);
					this.pstmt.setDouble(3, client.getClientX()-coverageX);
					this.pstmt.setDouble(4, client.getClientX()+coverageX);
				} else {
					this.pstmt.setString(1, "%" + value + "%");
				}
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

	public ArrayList<Saleboard> getPopularSaleboardList(Client client) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM popularSale p JOIN `client` c ON (p.sb_sellclient = c.client_contact)";
			if(client!=null && client.getClientAdmin()!=1) {
				sql += " WHERE c.client_Y>? AND c.client_Y<? AND c.client_X>? AND c.client_X<?";
			}
			sql += " ORDER BY p.count DESC LIMIT 0,4";

			try {
				System.out.println("Y: " + client.getClientY());
				System.out.println("X: " + client.getClientX());
				
				this.pstmt = this.conn.prepareStatement(sql);
				if(client!=null && client.getClientAdmin()!=1) {
					double coverageY = 0.017056;
					double coverageX = 0.017400;
					this.pstmt.setDouble(1, client.getClientY()-coverageY);
					this.pstmt.setDouble(2, client.getClientY()+coverageY);
					this.pstmt.setDouble(3, client.getClientX()-coverageX);
					this.pstmt.setDouble(4, client.getClientX()+coverageX);
				}
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
	
	public ArrayList<Saleboard> getSaleboardList(Client client) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT s.* FROM saleboard s JOIN `client` c ON (s.sb_sellclient = c.client_contact)";
			if(client!=null && client.getClientAdmin()!=1) {
				sql += " WHERE c.client_Y>? AND c.client_Y<? AND c.client_X>? AND c.client_X<?";
			}
			sql += " ORDER BY s.sb_code DESC";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				if(client!=null && client.getClientAdmin()!=1) {
					double coverageY = 0.017056;
					double coverageX = 0.017400;
					this.pstmt.setDouble(1, client.getClientY()-coverageY);
					this.pstmt.setDouble(2, client.getClientY()+coverageY);
					this.pstmt.setDouble(3, client.getClientX()-coverageX);
					this.pstmt.setDouble(4, client.getClientX()+coverageX);
				}
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

					Saleboard board = new Saleboard(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient,
							sbBuyClient, sbPublishDate, sbModifyDate, sbModiCount, sbPrice);

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

	public ArrayList<Saleboard> getSaleboardList(String clientContact) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard WHERE sb_sellclient = ? ORDER BY sb_code DESC";

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

	public ArrayList<Saleboard> getSaleboardListByCateCode(Client client, String cateCode, String search, String value) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT s.* FROM saleboard s JOIN `client` c ON (s.sb_sellclient = c.client_contact)";
			if(client!=null && client.getClientAdmin()!=1) {
				sql += " WHERE c.client_Y>? AND c.client_Y<? AND c.client_X>? AND c.client_X<?";
			}
			sql += String.format(" AND cate_code = ? AND %s LIKE ? ORDER BY s.sb_code DESC", search);

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				if(client!=null && client.getClientAdmin()!=1) {
					double coverageY = 0.017056;
					double coverageX = 0.017400;
					this.pstmt.setDouble(1, client.getClientY()-coverageY);
					this.pstmt.setDouble(2, client.getClientY()+coverageY);
					this.pstmt.setDouble(3, client.getClientX()-coverageX);
					this.pstmt.setDouble(4, client.getClientX()+coverageX);
					this.pstmt.setString(5, cateCode);
					this.pstmt.setString(6, "%" + value + "%");			
				} else {
					this.pstmt.setString(1, cateCode);
					this.pstmt.setString(2, "%" + value + "%");					
				}
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int sbCode = this.rs.getInt(1);
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

	public ArrayList<Saleboard> getSaleboardListByCateCode(Client client, String cateCode) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
//			String sql = "SELECT * FROM saleboard WHERE cate_code = ? ORDER BY sb_code DESC";
			String sql = "SELECT s.* FROM saleboard s JOIN `client` c ON (s.sb_sellclient = c.client_contact)";
			if(client!=null && client.getClientAdmin()!=1) {
				sql += " WHERE c.client_Y>? AND c.client_Y<? AND c.client_X>? AND c.client_X<?";
			}
			sql += " AND cate_code = ? ORDER BY s.sb_code DESC";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				if(client!=null && client.getClientAdmin()!=1) {
					double coverageY = 0.017056;
					double coverageX = 0.017400;
					this.pstmt.setDouble(1, client.getClientY()-coverageY);
					this.pstmt.setDouble(2, client.getClientY()+coverageY);
					this.pstmt.setDouble(3, client.getClientX()-coverageX);
					this.pstmt.setDouble(4, client.getClientX()+coverageX);
					this.pstmt.setString(5, cateCode);					
				} else {
					this.pstmt.setString(1, cateCode);					
				}
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int sbCode = this.rs.getInt(1);
					String sbImg = this.rs.getString(3);
					String sbTitle = this.rs.getString(4);
					String sbContent = this.rs.getString(5);
					String sbSellClient = this.rs.getString(6);
					String sbBuyClient = this.rs.getString(7);
					Timestamp sbPublishDate = this.rs.getTimestamp(8);
					Timestamp sbModifyDate = this.rs.getTimestamp(9);
					int sbModiCount = this.rs.getInt(10);
					int sbPrice = this.rs.getInt(11);

					Saleboard board = new Saleboard(sbCode, cateCode, sbImg, sbTitle, sbContent, sbSellClient,
							sbBuyClient, sbPublishDate, sbModifyDate, sbModiCount, sbPrice);

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

	public ArrayList<Saleboard> getSaleboardListForBuyer(String clientContact) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard WHERE sb_buyclient = ? ORDER BY sb_code DESC";

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

	public ArrayList<Saleboard> getSaleboardList(int boardCategoryCode) {
		ArrayList<Saleboard> list = new ArrayList<Saleboard>();
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "SELECT * FROM saleboard WHERE bcate_code = ? ORDER BY sb_code DESC";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, boardCategoryCode);
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
	
	public int getMaxOfSaleboardCode() {
		int sbCode = -1;
		this.conn = DBManager.getConnectionFromMySQL();
		if(this.conn!=null) {
			String sql = "SELECT MAX(sb_code) FROM saleboard";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next())
					sbCode = this.rs.getInt(1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		System.out.println("sbCode: " + sbCode);
		
		return sbCode;
	}
	
	public int countOfInterestsBySbCode(int sbCode) {
		int count = 0;
		this.conn = DBManager.getConnectionFromMySQL();
		if(this.conn!=null) {
			String sql = "SELECT COUNT(*) FROM interests WHERE sb_code = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, sbCode);
				this.rs = this.pstmt.executeQuery();
				if(this.rs.next()) {
					count = this.rs.getInt(1); 
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return count;
	}
	
	public void commitSale(String sbBuyClient, String sbCode) {
		this.conn = DBManager.getConnectionFromMySQL();
		if(this.conn!=null) {
			String sql = "UPDATE saleboard SET sb_buyclient = ? WHERE sb_code = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, sbBuyClient);
				this.pstmt.setString(2, sbCode);
				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}

	public void updateSaleBoard(SaleboardRequestDto saleboardDto) {
		this.conn = DBManager.getConnectionFromMySQL();
		if (this.conn != null) {
			String sql = "UPDATE saleboard SET cate_code = ?, sb_img = ?, sb_title = ?, sb_content = ?, sb_modify_date = current_timestamp(), "
					+ "sb_modi_count = sb_modi_count + 1, sb_price = ? WHERE sb_code = ? AND sb_modi_count < 3";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, saleboardDto.getCateCode());
				this.pstmt.setString(2, saleboardDto.getSbImg());
				this.pstmt.setString(3, saleboardDto.getSbTitle());
				this.pstmt.setString(4, saleboardDto.getSbContent());
				this.pstmt.setInt(5, saleboardDto.getSbPrice());
				this.pstmt.setInt(6, saleboardDto.getSbCode());
				
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}

	public void deleteSaleBoard(int sbCode) {
		Saleboard board = getSaleboardByCode(sbCode);
		this.conn = DBManager.getConnectionFromMySQL();
		if (board != null && this.conn != null) {
			String sql = "DELETE FROM saleboard WHERE sb_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, sbCode);
				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}
}