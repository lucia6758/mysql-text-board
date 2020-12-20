function ArticleDetail_body_init() {
	if ( toastui === undefined ) {
		return;
	}

	var body = document.querySelector('.detail_body');
	var initialValue = body.innerHTML.trim();

	var viewer = new toastui.Editor.factory({
		el : body,
		initialValue : initialValue,
		viewer : true,
		plugins: [toastui.Editor.plugin.codeSyntaxHighlight] 
	});
}

ArticleDetail_body_init();
