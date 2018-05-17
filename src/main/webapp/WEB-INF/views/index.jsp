<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>官网主页</title>
		<%@include file="/common/easyui.jspf"%>
		<link rel="stylesheet" href="${basePath}/static/css/index.css" />
	</head>
	<body>
		<!--顶部加中间-->
		<div class="wrapper">
			<!--wrapper顶部-->
			<div class="wrapper_header">
				<!--logo盒子-->
				<div class="header_logo">
					<a href="#">
						<img src="${basePath}/static/img/login/logo_school.png" alt="重庆工商大学" />
					</a>
				</div>
				<!--顶部右侧选项-->
				<div class="header_r">
					<span class="loginbtn">
						<a href="login">网站登录入口</a>
					</span>
				</div>
			</div>
			<!--wrapper菜单-->
			<div class="wrapper_nav">
				<div class="nav_style">
					<ul>
						<li>
							<a id="index" href="javascript:;">首页</a>
						</li>
						<li>
							<a id="web_introduce" href="javascript:;">网站介绍</a>
						</li>
						<li>
							<a id="course_introduce" href="javascript:;">课程简介</a>
						</li>
						<li>
							<a id="teacher_introduce" href="#">师资力量</a>
						</li>
						<li>
							<a id="learning_materials" href="#">学习资源</a>
						</li>
						<!-- <li>
							<a id="ourinfo" href="javascript:;">关于我们</a>
						</li>  -->
					</ul>
				</div>
			</div>
			<!--轮播图-->
			<div class="wrapper_poster">
				<div class="banner_box">
					<div id="focus">
						<img src="${basePath}/static/img/icon/sprite.png" class="pre" id="pre"/>
						<img src="${basePath}/static/img/icon/sprite1.png" class="next" id="next"/>
						<ul id="banner">
							<li>
								<a href="#" target="_blank">
									<img src="${basePath}/static/img/indeximg/banner1.jpg" />
								</a>
							</li>

						</ul>
					</div>
				</div>
			</div>
			<!-- <div>
				<iframe name="_son" src="indexiframe"  width="98%" height="80%"  style="margin: 0px 12px;overflow: hidden;" scrolling="no" frameborder="0"></iframe>
			</div> -->
			<!--wrapper消息盒子-->
			<div class="wrapper_message">
				<!-- 文字盒子  -->
				<div class="col_l1">
					<div class="col_f1">
						<div class="col_f1_title">
							<h3>网站公告</h3>
							<a href="#" id="morepubs" class="col_more">更多</a>
						</div>
						<div class="col_f1_content">
							<ul class="pubs">
							</ul>
						</div>
					</div>
					<div class="col_f2">
						<div class="col_f2_title">
							<h3>网站新闻</h3>
							<a href="#" id="morenews" class="col_more">更多</a>
						</div>
						<div class="col_f2_content">
							<ul class="news">
							</ul>
						</div>
					</div>
				</div>
				<!-- 友情链接 -->
				<div class="col_l2">
					<div class="col_l2_title">
						<h3>友情链接</h3>
					</div>
					<div class="col_l2_content">
						<ul class="col_g2">
							<li><a href="https://www.baidu.com/" target="_blank">百度一下，你就知道</a></li>
							<li><a href="https://www.csdn.net/" target="_blank">CSDN-专业IT技术社区</a> </li>
							<li><a href="http://www.itheima.com/" target="_blank">黑马程序员</a> </li>
							<li><a href="http://www.runoob.com/" target="_blank">菜鸟教程-学的不仅是即使，而是梦想</a> </li>
							<li><a href="https://www.imooc.com/" target="_blank">慕课网-程序员的梦工厂</a> </li>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 更多新闻 -->
			<div class="morenews hide">
			</div>
			
			<!-- 更多公告 -->
			<div class="morepubs hide">
			</div>
			
			<!-- 新闻/公告详情 -->
			<div class="newsdetail hide">
			</div>
			
			<!-- 网站介绍 -->
			<div class="web_introduce hide">
				<!-- <div class="web_introduce_title">java教学网站</div>
				<div class="web_introduce_content">
					重庆工商大学是一所经济学、管理学、文学、工学、法学、理学、艺术学等学科协调发展的、具有鲜明财经特色的多科性大学。学校由中央和地方共建，以重庆市政府管理为主，被国家确定为“中西部高校基础能力提升计划”重点支持高校。
	      学校设有19个教学学院、76个本科专业，其中有4个国家级特色专业、20个市级特色专业、5个市级特色学科专业群，1个国家卓越农林人才教育培养计划改革试点项目。有1个服务国家特殊需求博士人才培养项目——三峡库区百万移民安稳致富国家战略人才培养项目；有应用经济学、工商管理、环境科学与工程等8个一级学科硕士学位授权点，MBA等5个专业硕士学位点,有应用经济学、工商管理2个市级一流学科，应用经济学等10个市级重点一级学科。
				</div> -->
			</div>
			
			<!-- 课程简介 -->
			<div class="course_introduce hide">
				<div class="single_info">
					<div><img class="single_img" src="${basePath}/static/img/indeximg/banner1.jpg" /></div>
					<div class="single_content">
						<ul>
							<li style="font-size: 16px"><span>课程名：</span><span>java编程基础</span></li>
							<li><span>课程简介：</span><span>Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程。</span></li>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 师资力量 -->
			<div class="teacher_introduce hide">
				<div class="single_info">
					<div><img class="single_img" src="${basePath}/static/img/indeximg/banner1.jpg" /></div>
					<div class="single_content">
						<ul>
							<li style="font-size: 20px"><span>张老师</span></li>
							<li><span>教授</span></li>
							<li><span>从事多年Java软件开发及相关教育工作，熟悉 javaSE，javaEE，Struts1,2，spring，hibernate，Oracle，SQLServer，MySQL，javascript，AJAX。曾在多家公司担任项目工程师，项目经理职务。</span></li>
						</ul>
					</div>
				</div>
				<div class="single_info">
					<div><img class="single_img" src="${basePath}/static/img/indeximg/banner1.jpg" /></div>
					<div class="single_content">
						<ul>
							<li style="font-size: 20px"><span>张老师</span></li>
							<li><span>教授</span></li>
							<li><span>从事多年Java软件开发及相关教育工作，熟悉 javaSE，javaEE，Struts1,2，spring，hibernate，Oracle，SQLServer，MySQL，javascript，AJAX。曾在多家公司担任项目工程师，项目经理职务。</span></li>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 学习资料 -->
			<div class="learning_materials hide">
				<div class="singlemater">
					<div><span class="matertitle">java教学基础</span><span><a href="">下载</a></span><span class="mtime">2018-01-01</span></div>
				</div>
				<div style="clear:both;"></div>
			</div>
			
			<!-- 关于我们 -->
			<!-- <div class="ourinfo hide">
				<div class="single_info">
					<div><img class="single_img" src="${basePath}/static/img/indeximg/banner1.jpg" /></div>
					<div class="single_content">
						<ul>
							<li><span>专业：</span><span>计算机科学与技术</span></li>
							<li><span>班级：</span><span>计算机二班</span></li>
							<li><span>姓名：</span><span>童湖川</span></li>
							<li><span>学号：</span><span>2014131231</span></li>
							<li><span>个性签名：</span><span>重庆工商大学是一所经济学、管理学、文学、工学、法学、理学、</span></li>
						</ul>
					</div>
				</div>
			</div>  -->
		</div>		
		<!--底部-->
		<div class="foot">
			<div class="footer">
				<p>小组成员：重庆工商大学 罗侃、童湖川、王书杰、周晓雅</p>
			</div>
		</div>
		
		<script type="text/javascript" src="${basePath}/static/js/jquery-3.2.0.js" ></script>
		<script type="text/javascript">
			$(function(){
				//显示新闻列表
				$.ajax({
					url:'getlist?j_type=1',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						/*alert(JSON.stringify(result));
						alert(result.length);*/
						var htmls = [];
						for(var i=0;i<result.length&&i<6;i++){//for(var i=0;i<result.length;i++) i<6控制主页只显示6条
							var tpl = '<li><a href="javascript:void(0);" onclick="jouDetail('+ result[i].id +')">'+ result[i].joutitle + '</a><span class="time_span">' + toFormatDateByLong(result[i].starttime, "yyyy-MM-dd") + '</span></li>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".news").append(htmls);
					}
				});
				//显示公告列表
				$.ajax({
					url:'getlist?j_type=2',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						for(var i=0;i<result.length&&i<6;i++){//for(var i=0;i<result.length;i++) i<6控制主页只显示6条
							var tpl = '<li><a href="javascript:void(0);" onclick="jouDetail('+ result[i].id +')">'+ result[i].joutitle + '</a><span class="time_span">' + toFormatDateByLong(result[i].starttime, "yyyy-MM-dd") + '</span></li>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".pubs").append(htmls);
					}
				});
				
				//关于我们
/* 				$.ajax({
					url:'getourinfo',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="single_info">'
									+ '<div><img class="single_img" src="' + result[i].mimg + '" /></div>'
										+ '<div class="single_content">'
											+ '<ul>'
												+ '<li><span>专业：</span><span>' + result[i].mmajor +'</span></li>'
												+ '<li><span>班级：</span><span>' + result[i].mclass + '</span></li>'
												+ '<li><span>姓名：</span><span>' + result[i].mname + '</span></li>'
												+ '<li><span>学号：</span><span>' + result[i].mclassno + '</span></li>'
												+ '<li><span>个性签名：</span><span>' + result[i].msign + '</span></li>'
											+ '</ul>'
										+ '</div>'
									+ '</div>'
									+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".ourinfo").append(htmls);
					}
				}); */
				
				//首页轮播图
				$.ajax({
					url:'getpicture',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						
						var num = result.length * 1100;
						// 轮播图
						var x = 0;
						var poster1 = setInterval(function() {
							if(x < num) {
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
								if(x < num) {
									x += 1100;
									var s = "-" + x + "px";
									$("#banner").animate({left: s});
								} else {
									x = 0;
									$("#banner").animate({left: "0"});
								}
								poster1 = setInterval(function() {
									if(x < num) {
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
									x = num;
									$("#banner").animate({left: '"'+ num +'px"'});
								}
								poster1 = setInterval(function() {
									if(x < num) {
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

						
						var htmls = [];
						for(var i=0;i<result.length;i++){
							var tpl = '<li>'
										+ '<a href="#" target="_blank">'
											+ '<img src="${basePath}/'+result[i].imgurl + '" />'
										+ '</a>'
									+ '</li>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$("#banner").append(htmls);
					}
				});
				
			});
			
			//首页
			$("#index").on("click", function(){
				$(".wrapper_message").removeClass("hide");
				$(".learning_materials").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//网站介绍
			$("#web_introduce").on("click", function(){
				$(".web_introduce").html("");
				//网站介绍
				$.ajax({
					url:'getwebintroduce',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var tpl = '<div class="web_introduce_title">'
								+ result.wtitle
								+ '</div>'
								+ '<div class="web_introduce_content">'
								+ result.wcontent
								+ '</div>'
								+ '<div style="clear:both;"></div>';
						$(".web_introduce").append(tpl);
					}
				});
				
				$(".web_introduce").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//课程简介
			$("#course_introduce").on("click", function(){
				$(".course_introduce").html("");
				//课程简介
				$.ajax({
					url:'getcourseintroduce',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="single_info">'
									+ '<div><img class="single_img" src="' + result[i].cimg + '" /></div>'
										+ '<div class="single_content">'
											+ '<ul>'
												+ '<li><span>课程名：</span><span>' + result[i].cname + '</span></li>'
												+ '<li><span>课程介绍：</span><span>' + result[i].csign + '</span></li>'
											+ '</ul>'
										+ '</div>'
									+ '</div>';
									//+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".course_introduce").append(htmls);
					}
				});
				
				$(".course_introduce").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//师资力量
			$("#teacher_introduce").on("click", function(){
				$(".teacher_introduce").html("");
				//师资力量
				$.ajax({
					url:'getteacherintroduce',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="single_info">'
									+ '<div><img class="single_img" src="' + result[i].teaimage + '" /></div>'
										+ '<div class="single_content">'
											+ '<ul>'
												+ '<li><span style="font-size: 20px">' + result[i].teaname + '</span></li>'
												+ '<li><span style="font-size: 16px">' + result[i].professional + '</span></li>'
												+ '<li><span>' + result[i].prosonal_statement + '</span></li>'
											+ '</ul>'
										+ '</div>'
									+ '</div>';
									//+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".teacher_introduce").append(htmls);
					}
				});
				
				$(".teacher_introduce").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//学习资源
			$("#learning_materials").on("click", function(){
				$(".learning_materials").html("");
				//学习资源
				$.ajax({
					url:'getmaterials',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="singlemater">'
									+ '<div><span class="matertitle">'+result[i].mtitle+'</span>'
									+ '<span><a href="' + 'downloadMaterials?fileName='+ result[i].murl + '">下载</a></span>'
									+ '<span class="mtime">' + toFormatDateByLong(result[i].mtime, "yyyy-MM-dd") + '</span>'
									+ '</div></div>'
									+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						//alert(htmls);
						$(".learning_materials").append(htmls);
					}
				});
				
				$(".learning_materials").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//更多新闻
			$("#morenews").on("click", function(){
				$(".morenews").html("");
				//更多新闻
				$.ajax({
					url:'getlist?j_type=1',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						var title = '<h3 class="newstitle">所有新闻</h3>';
						htmls.push(title);
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="singlenews">'
								+ '<span><a href="javascript:void(0);" onclick="jouDetail('+ result[i].id +')">'+ result[i].joutitle + '</a></span>'
								+ '<span class="mtime">' + toFormatDateByLong(result[i].starttime, "yyyy-MM-dd") + '</span>'
								+ '</div></div>'
								+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						$(".morenews").append(htmls);
					}
				});
				
				$(".morenews").removeClass("hide");
				$(".morepubs").addClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//更多公告
			$("#morepubs").on("click", function(){
				$(".morepubs").html("");
				//更多公告
				$.ajax({
					url:'getlist?j_type=2',
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var htmls = [];
						var title = '<h3 class="newstitle">所有公告</h3>';
						htmls.push(title);
						for(var i=0;i<result.length;i++){
							var tpl = '<div class="singlenews">'
								+ '<span><a href="javascript:void(0);" onclick="jouDetail('+ result[i].id +')">'+ result[i].joutitle + '</a></span>'
								+ '<span class="mtime">' + toFormatDateByLong(result[i].starttime, "yyyy-MM-dd") + '</span>'
								+ '</div></div>'
								+'<div style="clear:both;"></div>';
							htmls.push(tpl);
						};
						$(".morepubs").append(htmls);
					}
				});
				
				$(".morepubs").removeClass("hide");
				$(".morenews").addClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".newsdetail").addClass("hide");
			});
			
			//关于我们
/* 			$("#ourinfo").on("click", function(){			
				$(".ourinfo").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".morenews").addClass("hide");
				$(".newsdetail").addClass("hide");
			}); */
			
	        function toFormatDateByLong(timeMillis, format) {
	            var date = new Date(timeMillis);
	            var o = {
	                "M+" : date.getMonth() + 1,
	                "d+" : date.getDate(),
	                "h+" : date.getHours(),
	                "m+" : date.getMinutes(),
	                "s+" : date.getSeconds(),
	                "q+" : Math.floor((date.getMonth() + 3) / 3),
	                "S" : date.getMilliseconds()
	            }
	            if (/(y+)/.test(format)) {
	                format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	            }
	            for (var k in o) {
	                if (new RegExp("(" + k + ")").test(format)) {
	                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	                }
	            }
	            return format;
	        };
	        
	        function jouDetail(id){
	        	$(".newsdetail").html("");
	        	alert(id);
	        	$.ajax({
					url:'jouDetail?id=' + id,
					type:'get',
					contentType: 'application/json',
					success:function(result){
						var tpl = '<h3>' + result.joutitle + '</h3>'
							+ '<div class="joucontent">' + result.joucontent + '</div>'
							+ '<div class="joutime">' + toFormatDateByLong(result.starttime, "yyyy-MM-dd") + '</div>'
							+'<div style="clear:both;"></div>';
						$(".newsdetail").append(tpl);
					}
				});
				$(".newsdetail").removeClass("hide");
				$(".wrapper_message").addClass("hide");
				$(".teacher_introduce").addClass("hide");
				$(".web_introduce").addClass("hide");
				$(".course_introduce").addClass("hide");
				$(".learning_materials").addClass("hide");
				$(".morenews").addClass("hide");
				$(".morepubs").addClass("hide");
	        	
	        };
		</script>
	</body>
</html>
