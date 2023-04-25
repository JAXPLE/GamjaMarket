package controller;

import controller.action.*;

public class ActionFactory {

	private ActionFactory() {
	}

	private static ActionFactory instance = new ActionFactory();

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("board")) {
			action = new BoardAction();
		}

		else if (command.equals("updateBoard")) {
			action = new UpdateBoardAction();
		}
		
		else if (command.equals("updateComment")) {
			action = new UpdateCommentAction();
		}

		else if (command.equals("join")) {
			action = new RegistAction();
		}

		else if (command.equals("login")) {
			action = new LoginAction();
		}
		
		else if (command.equals("hello")) {
			action = new HelloAction();
		}
		
		else if (command.equals("leave")) {
			action = new ClientLeaveAction();
		}

		else if (command.equals("modifyClient")) {
			action = new ModifyClientAction();
		}

		else if (command.equals("deleteBoard")) {
			action = new DeleteBoardAction();
		}
		
		else if (command.equals("deleteComment")) {
			action = new DeleteCommentAction();
		}

		else if (command.equals("sale")) {
			action = new SaleboardAction();
		}

		else if (command.equals("commitSale")) {
			action = new CommitSaleAction();
		}

		else if (command.equals("modifySell")) {
			action = new SaleboardModifyAction();
		}

		else if (command.equals("deleteSell")) {
			action = new SaleboardDeleteAction();
		}
		
		else if (command.equals("addCate")) {
			action = new AddCategoryAction();
		}
		
		else if (command.equals("addBcate")) {
			action = new AddBcategoryAction();
		}
		
		else if (command.equals("deleteCate")) {
			action = new DeleteCategoryAction();
		}
		
		else if (command.equals("deleteBcate")) {
			action = new DeleteBcategoryAction();
		}
		
		else if (command.equals("addComment")) {
			action = new AddCommentAction();
		}
		
		else if (command.equals("addInterests")) {
			action = new AddInterestsAction();
		}
		
		else if (command.equals("removeInterests")) {
			action = new RemoveInterestsAction();
		}
		
		return action;
	}
}