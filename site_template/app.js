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
		plugins: [toastui.Editor.plugin.codeSyntaxHighlight, codepenPlugin] 
	});
}

ArticleDetail_body_init();

// codepen 플러그인 시작
function codepenPlugin() {
  toastui.Editor.codeBlockManager.setReplacer('codepen', url => {
    const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

    // Avoid sanitizing iframe tag
    setTimeout(renderCodepen.bind(null, wrapperId, url), 0);

    return `<div id="${wrapperId}"></div>`;
  });
}

function renderCodepen(wrapperId, url) {
  const el = document.querySelector(`#${wrapperId}`);
  
  var urlParams = new URLSearchParams(url.split('?')[1]);
  var height = urlParams.get('height');

  el.innerHTML = `<div class="toast-ui-codepen-plugin-wrap"><iframe height="${height}" scrolling="no" src="${url}" frameborder="no" loading="lazy" allowtransparency="true" allowfullscreen="true"></iframe></div>`;
}
// codepen 플러그인 끝

// 탑버튼 
$(function() { 
  $(window).scroll(function() { 
    if ($(this).scrollTop() > 150) {
      $('.top-botton').fadeIn(); } else { 
        $('.top-botton').fadeOut(); 
      } 
  }); 
  $(".top-botton").click(function() { 
    $('html, body').animate({ scrollTop : 0 }, 400); 
  return false; 
  }); 
});

// 탑버튼 끝
