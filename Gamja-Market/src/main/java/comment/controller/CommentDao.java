package comment.controller;

import java.sql.*;
import java.util.ArrayList;

import board.Board;
import comment.*;
import util.DBManager;

public class CommentDao {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	private CommentDao() {}
	private static CommentDao instance = new CommentDao();
	public static CommentDao getInstance() {return instance;}

	//create
	public void createComment(CommentRequestDto commentRequestDto) {
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection == null) {
			return;
		}
		
		String sql = "INSERT INTO comment (b_code, com_content, client_contact) VALUES (?,?,?);";
		try {
			this.preparedStatement = this.connection.prepareStatement(sql);
			this.preparedStatement.setInt(1, commentRequestDto.getbCode());
			this.preparedStatement.setString(2, commentRequestDto.getComContent());
			this.preparedStatement.setString(3, commentRequestDto.getClientContact());
			this.preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
	}
	
	//read
	public Comment getCommentByComCode(int comCode) {
		Comment comment = null;
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection != null) {
			String sql = "SELECT * FROM comment WHERE com_code=?";

			try {
				this.preparedStatement = this.connection.prepareStatement(sql);
				this.preparedStatement.setInt(1, comCode);
				this.resultSet = this.preparedStatement.executeQuery();
				
				while(this.resultSet.next()) {
					int bCode = this.resultSet.getInt("b_code");
					String comContent = this.resultSet.getString("com_content");
					String clientContact = this.resultSet.getString("client_contact");
					Timestamp comDate = this.resultSet.getTimestamp("com_date");
					
					CommentRequestDto commentRequestDto = new CommentRequestDto(comCode, bCode, comContent, clientContact, comDate);
					comment = new Comment(commentRequestDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(connection, preparedStatement, resultSet);
			}
		}
//		System.out.println(comment);
		return comment;
	}
	
	public ArrayList<Comment> getCommentByAll(){
		ArrayList<Comment> list = new ArrayList<>();
		
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection == null) {
			return list;
		}
		
		String sql = "SELECT * FROM comment";
		try {
			this.preparedStatement = this.connection.prepareStatement(sql);
			this.resultSet = this.preparedStatement.executeQuery();
			while(this.resultSet.next()) {
				int comCode = this.resultSet.getInt("com_code");
				int bCode = this.resultSet.getInt("b_code");
				String comContent = this.resultSet.getString("com_content");
				String clientContact = this.resultSet.getString("client_contact");
				Timestamp comDate = this.resultSet.getTimestamp("com_date");
				
				CommentRequestDto commentRequestDto = new CommentRequestDto(comCode, bCode, comContent, clientContact, comDate);
				Comment comment = new Comment(commentRequestDto);
				list.add(comment);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	public ArrayList<Comment> getCommentByClientContact(String clientContact){
		ArrayList<Comment> list = new ArrayList<>();
		
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection == null) {
			return list;
		}
		
		String sql = "SELECT * FROM comment WHERE client_contact=?";
		try {
			this.preparedStatement = this.connection.prepareStatement(sql);
			this.preparedStatement.setString(1, clientContact);
			this.resultSet = this.preparedStatement.executeQuery();
			while(this.resultSet.next()) {
				int comCode = this.resultSet.getInt("com_code");
				int bCode = this.resultSet.getInt("b_code");
				String comContent = this.resultSet.getString("com_content");
				Timestamp comDate = this.resultSet.getTimestamp("com_date");

				CommentRequestDto commentRequestDto = new CommentRequestDto(comCode, bCode, comContent, clientContact, comDate);
				Comment comment = new Comment(commentRequestDto);
				list.add(comment);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			DBManager.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	
	public ArrayList<Comment> getComment(int boardCode){
		ArrayList<Comment> list = new ArrayList<>();
		System.out.println(boardCode);
		
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection != null) {
			String sql = "SELECT * FROM comment WHERE b_code = ?";
			try {
				this.preparedStatement = this.connection.prepareStatement(sql);
				this.preparedStatement.setInt(1, boardCode);
				this.resultSet = this.preparedStatement.executeQuery();
				
				while(this.resultSet.next()) {
					int comCode = this.resultSet.getInt(1);
					int bCode = this.resultSet.getInt(2);
					String comContent = this.resultSet.getString(3);
					String clientContact = this.resultSet.getString(4);
					Timestamp comDate = this.resultSet.getTimestamp(5);
					
					CommentRequestDto commentRequestDto = new CommentRequestDto(comCode,bCode,comContent,clientContact,comDate);
					Comment comment = new Comment(commentRequestDto);
					list.add(comment);
				}
			} catch (SQLException e){
				e.printStackTrace();
				
			} finally {
				DBManager.close(this.connection, this.preparedStatement, this.resultSet);
			}
		}
		return list;
	}
	
	//update
	public void updateComment(CommentRequestDto commentRequestDto) {
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection != null && commentRequestDto != null) {
			String sql = "UPDATE comment SET com_content = ?, com_date = ? WHERE com_code = ?";
	
			try {
				this.preparedStatement = connection.prepareStatement(sql);
				this.preparedStatement.setString(1, commentRequestDto.getComContent());
				this.preparedStatement.setTimestamp(2, commentRequestDto.getComDate());
				this.preparedStatement.setInt(3, commentRequestDto.getComCode());
				
				this.preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(connection, preparedStatement);
			}
		}
	}
	
	//delete
	public void delete(CommentRequestDto commentRequestDto) {
		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection != null) {
			String sql = "DELETE FROM comment WHERE b_code = ? AND com_code = ?";
	
			try {
				this.preparedStatement = connection.prepareStatement(sql);
				this.preparedStatement.setInt(1, commentRequestDto.getbCode());
				this.preparedStatement.setInt(2, commentRequestDto.getComCode());
				this.preparedStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(connection, preparedStatement);
			}
		}
	}
	
	public void deleteComment(int comCode) {

		this.connection = DBManager.getConnectionFromMySQL();
		if (this.connection != null) {
			String sql = "DELETE FROM comment WHERE com_code=?";

			try {
				this.preparedStatement = connection.prepareStatement(sql);
				this.preparedStatement.setInt(1, comCode);
				this.preparedStatement.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(connection, preparedStatement);
			}
		}
	}
}