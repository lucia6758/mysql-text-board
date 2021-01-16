package com.sbs.example.mysqlTextBoard.container;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.AppConfig;
import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.BuildController;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.dao.ArticleDao;
import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.dao.TagDao;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.BuildService;
import com.sbs.example.mysqlTextBoard.service.DisqusApiService;
import com.sbs.example.mysqlTextBoard.dao.Ga4DataDao;
import com.sbs.example.mysqlTextBoard.service.GoogleAnalyticsApiService;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.service.TagService;
import com.sbs.example.mysqlTextBoard.session.Session;

public class Container {

	public static Scanner scanner;
	public static Session session;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static ArticleService articleService;
	public static MemberController memberController;
	public static ArticleController articleController;
	public static BuildController buildController;
	public static BuildService buildService;
	public static DisqusApiService disqusApiService;
	public static AppConfig config;
	public static GoogleAnalyticsApiService googleAnalyticsApiService;
	public static Ga4DataDao ga4DataDao;
	public static TagService tagService;
	public static TagDao tagDao;

	static {
		config = new AppConfig();

		scanner = new Scanner(System.in);
		session = new Session();

		ga4DataDao = new Ga4DataDao();
		tagDao = new TagDao();
		articleDao = new ArticleDao();
		memberDao = new MemberDao();

		googleAnalyticsApiService = new GoogleAnalyticsApiService();
		disqusApiService = new DisqusApiService();
		tagService = new TagService();
		articleService = new ArticleService();
		memberService = new MemberService();
		buildService = new BuildService();

		memberController = new MemberController();
		articleController = new ArticleController();
		buildController = new BuildController();
	}

}
