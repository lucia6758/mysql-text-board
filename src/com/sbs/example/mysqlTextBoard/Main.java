package com.sbs.example.mysqlTextBoard;

import com.sbs.example.mysqlTextBoard.Util.Util;

public class Main {
	public static void main(String[] args) {
		//new App().run();
		
		testApi();
	}

	private static void testApi() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		
		String rs = Util.callApi(url, "api_key=BaIQq3yDal19LyyTlIPtRYUi3LpDivsZAwaJC5HUCtZ8VTuvL63eFEec7yx8k9y8","forum=klvs-blog", "thread:ident=article_5.html");
		System.out.println(rs);
		
	}
}
