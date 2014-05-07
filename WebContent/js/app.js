window.onload = startApp;

function handleEvent() {
	$('#tag_btn').click(function() {
		var text = $('#text_content').val();
		$.ajax('/POSTagger/tagger?s=' + text).done(function(json) {
			console.log(json);
			k = json;
			var s = json[0];
			var t = s.tag.split(" ");
			s = s.s.split(" ");
			var c = "";
			for (var i = 0; i < t.length; i++) {
				c += "<span class='tag " + t[i] + "'>" + s[i] + "/" + t[i] + "</span>";
			}
			$('#draw_div').html(c);
		});
	});
}
function startApp() {
	console.log('load')
	handleEvent();

	
}
