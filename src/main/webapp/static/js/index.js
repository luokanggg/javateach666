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



var y = 0;
var poster2 = setInterval(function(){
	if(y < 5) {
		document.getElementsByClassName("showimg1")[0].className="showimg2";
		document.getElementsByClassName("shownum1")[0].className="shownum2";
		y++;
		document.getElementsByClassName("showimg2")[y].className="showimg1";
		document.getElementsByClassName("shownum2")[y].className="shownum1";
	} else {
		document.getElementsByClassName("showimg1")[0].className="showimg2";
		document.getElementsByClassName("shownum1")[0].className="shownum2";
		y=0;
		document.getElementsByClassName("showimg2")[y].className="showimg1";
		document.getElementsByClassName("shownum2")[y].className="shownum1";
	}
},2500);
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
	$("#p2 li").mouseover(function(){
		clearInterval(poster2);
		$(".showimg1").eq(0).attr("class","showimg2");
		$(".shownum1").eq(0).attr("class","shownum2");
		var index = $(this).index();
		$(this).children("span").attr("class","shownum1");
		$("#col_g1").find("img").eq(index).attr("class","showimg1");
		y = index;
	});
	$("#p2 li").mouseout(function(){
        poster2 = setInterval(function(){
			if(y < 5) {
				document.getElementsByClassName("showimg1")[0].className="showimg2";
				document.getElementsByClassName("shownum1")[0].className="shownum2";
				y++;
				document.getElementsByClassName("showimg2")[y].className="showimg1";
				document.getElementsByClassName("shownum2")[y].className="shownum1";
			} else {
				document.getElementsByClassName("showimg1")[0].className="showimg2";
				document.getElementsByClassName("shownum1")[0].className="shownum2";
				y=0;
				document.getElementsByClassName("showimg2")[y].className="showimg1";
				document.getElementsByClassName("shownum2")[y].className="shownum1";
			}
		},2500);
	});
	$("#col_g1 img").mouseover(function(){
		clearInterval(poster2);
	});
	$("#col_g1 img").mouseout(function(){
		 poster2 = setInterval(function(){
			if(y < 5) {
				document.getElementsByClassName("showimg1")[0].className="showimg2";
				document.getElementsByClassName("shownum1")[0].className="shownum2";
				y++;
				document.getElementsByClassName("showimg2")[y].className="showimg1";
				document.getElementsByClassName("shownum2")[y].className="shownum1";
			} else {
				document.getElementsByClassName("showimg1")[0].className="showimg2";
				document.getElementsByClassName("shownum1")[0].className="shownum2";
				y=0;
				document.getElementsByClassName("showimg2")[y].className="showimg1";
				document.getElementsByClassName("shownum2")[y].className="shownum1";
			}
		},2500);
	});
});
