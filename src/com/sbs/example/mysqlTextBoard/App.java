package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.Controller;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.mysqlutil.MysqlUtil;

public class App {
	private MemberController memberController;
	private ArticleController articleController;

	public App() {
		memberController = Container.memberController;
		articleController = Container.articleController;
	}

	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "textBoard");
			MysqlUtil.setDevMode(false);

			boolean needToExit = false;

			Controller controller = getControllerBycmd(cmd);
			if (controller != null) {
				controller.doCommand(cmd);
			} else if (cmd.equals("system exit")) {
				System.out.println("== 시스템 종료 ==");
				needToExit = true;
			}

			if (needToExit) {
				break;
			}
		}
	}

	private Controller getControllerBycmd(String cmd) {
		if (cmd.startsWith("article ")) {
			return articleController;
		} else if (cmd.startsWith("member ")) {
			return memberController;
		}
		return null;
	}
}
