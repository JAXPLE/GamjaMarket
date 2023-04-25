package Tool;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

import boardCateogry.controller.BoardCategoryDao;
import category.controller.CategoryDao;
import client.controller.ClientDao;

public class Tool {

	public static String getCateName(String cateCodee) {
		if (cateCodee != null) {
			String cateCode = cateCodee;
			CategoryDao categoryDao = CategoryDao.getInstance();
			String cateName = categoryDao.getCategoryNameByCode(cateCode);
			return cateName;
		}
		return "";
	}

	public static String getbCateName(String bcateCodee) {
		if (bcateCodee != null) {
			String bcateCode = bcateCodee;
			BoardCategoryDao boardCategoryDao = BoardCategoryDao.getInstance();
			String bcateName = boardCategoryDao.getBoardCategoryNameByCode(bcateCode);
			return bcateName;
		}
		return "";
	}

	public static String getClientNickname(String clientContactt) {
		if (clientContactt != null) {
			String clientContact = clientContactt;
			ClientDao clientDao = ClientDao.getInstance();
			String clientNickname = clientDao.getClientNicknameByContact(clientContact);
			return clientNickname;
		}
		return "";
	}

	public static String getClientContactByNickname(String nicknamee) {
		if (nicknamee != null) {
			String nickname = nicknamee;
			ClientDao clientDao = ClientDao.getInstance();
			String clientNickname = clientDao.getClientContactByNickname(nickname);
			return clientNickname;
		}
		return "";
	}

	public static String getTime(Timestamp datee) {
		if (datee != null) {
			return datee.toString().substring(0, 16);
		}
		return "";
	}

	public static String setEncryption(String inputPassWord) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		StringBuilder hexString = new StringBuilder();

		byte[] hash = messageDigest.digest(inputPassWord.getBytes(StandardCharsets.UTF_8));

		for (byte b : hash)
			hexString.append(String.format("%02X", b));

		return hexString.toString();
	}

	public static ArrayList<Double> addListDouble(ArrayList<Double> list, double value) {
		list.add(value);
		return list;
	}

	public static ArrayList<String> addListString(ArrayList<String> list, String value) {
		list.add(value);
		return list;
	}
	
	public static String valueOf(int number) {
		return String.valueOf(number);
	}
	
	public static int parseInt(String number) {
		return Integer.parseInt(number);
	}
	
	
	public static String getSize(int size) {
		if (size < 10) {
			String returnSize = "0" + String.valueOf(size);
			return returnSize;
		}
		return String.valueOf(size);
	}
}
