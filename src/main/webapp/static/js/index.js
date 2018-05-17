// 轮播图
var x = 0;
var poster1 = setInterval(function() {
	if(x < 3300) {
		x += 1100;
		var s = "-" + x + "px";
		$("#banner").animate({left: s});
	} else {
		x = 0;
		$("#banner").animate({left: "0"});
	}

}, 2000);

$(function(){
	$("#next").click(function(){
		clearInterval(poster1);
		if(x < 3300) {
			x += 1100;
			var s = "-" + x + "px";
			$("#banner").animate({left: s});
		} else {
			x = 0;
			$("#banner").animate({left: "0"});
		}
		poster1 = setInterval(function() {
			if(x < 3300) {
				x += 1100;
				var s = "-" + x + "px";
				$("#banner").animate({left: s});
			} else {
				x = 0;
				$("#banner").animate({left: "0"});
			}
		}, 2000);
	});
	$("#pre").click(function(){
		clearInterval(poster1);
		if(x >= 1100) {
			x -= 1100;
			var s = "-" + x + "px";
			$("#banner").animate({left: s});
		} else {
			x = 3300;
			$("#banner").animate({left: "-3300px"});
		}
		poster1 = setInterval(function() {
			if(x < 3300) {
				x += 1100;
				var s = "-" + x + "px";
				$("#banner").animate({left: s});
			} else {
				x = 0;
				$("#banner").animate({left: "0"});
			}
		}, 2000);
	});
});
