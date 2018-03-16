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
						<img src="${basePath}/static/img/ctbulogo.gif" alt="重庆工商大学" />
					</a>
				</div>
				<!--顶部右侧选项-->
				<div class="header_r">
					<button><a href="login">教务管理</a></button>
				</div>
			</div>
			<!--wrapper菜单-->
			<div class="wrapper_nav">
				<div class="nav_style">
					<ul>
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								学校概况
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">学校简介</a>
								</li>
								<li>
									<a href="#">校史沿革</a>
								</li>
								<li>
									<a href="#">现任领导</a>
								</li>
								<li>
									<a href="#">校徽校训校歌</a>
								</li>
								<li>
									<a href="#">校园风光</a>
								</li>
								<li>
									<a href="#">学校地图</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" style="padding-right: 23px;">
								机构设置
								<img src="${basePath}/static/img/icon/down.gif" class="downarowclass"/>
							</a>
							<ul class="nav_downmenu">
								<li>
									<a href="#">教学单位</a>
								</li>
								<li>
									<a href="#">科研机构</a>
								</li>
								<li>
									<a href="#">管理服务</a>
								</li>
							</ul>
						</li>
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
									<img src="${basePath}/static/img/banner/banner1.jpg" />
								</a>
							</li>
							<li>
								<a href="#" target="_blank">
									<img src="${basePath}/static/img/banner/banner2.jpg" />
								</a>
							</li>
							<li>
								<a href="#" target="_blank">
									<img src="${basePath}/static/img/banner/banner4.jpg" />
								</a>
							</li>
							<li>
								<a href="#" target="_blank">
									<img src="${basePath}/static/img/banner/banner5.jpg" />
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!--wrapper消息盒子-->
			<div class="wrapper_message">
				<!--文字盒子-->
				<div class="col_l1">
					<div class="col_f1">
						<div class="col_f1_title">
							<h3>学校公告</h3>
							<a href="#" target="_blank" class="col_more">更多</a>
						</div>
						<div class="col_f1_content">
							<ul>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的温馨提示">关于2018年寒假后勤服务保障工作的温馨提示</a>
									<span class="time_span">01-16</span>
								</li>
								<li>
									<a href="#" target="_blank" title="关于2018年寒假后勤服务保障工作的">关于2018年寒假后勤服务保障工作的</a>
									<span class="time_span">01-16</span>
								</li>
							</ul>
						</div>
					</div>
					<div class="col_f2">
						<div class="col_f2_title">
							<h3>学校新闻</h3>
							<a href="#" target="_blank" class="col_more">更多</a>
						</div>
						<div class="col_f2_content">
							<ul>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
								<li>
									<a href="#" target="_blank" title="迎着新时代 踏上新征程——新年献词">迎着新时代 踏上新征程——新年献词</a>
									<span class="time_span">12-31</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!--图片轮播盒子-->
				<div class="col_l2">
					<div class="col_g1" id="col_g1">
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/2018001.jpg" width="300" height="200" class="showimg1"/>
						</a>
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/2017ctbu.jpg" width="300" height="200" class="showimg2"/>
						</a>
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/2017ctbu2.jpg" width="300" height="200" class="showimg2"/>
						</a>
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/2017ctbusjd.jpg" width="300" height="200" class="showimg2"/>
						</a>
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/gdxy2017.jpg" width="300" height="200" class="showimg2"/>
						</a>
						<a href="#" target="_blank">
							<img src="${basePath}/static/img/show/xzryj2017.jpg" width="300" height="200" class="showimg2"/>
						</a>
						<ul id="p2">
							<li>
								<span class="shownum1">1</span>
							</li>
							<li>
								<span class="shownum2">2</span>
							</li>
							<li>
								<span class="shownum2">3</span>
							</li>
							<li>
								<span class="shownum2">4</span>
							</li>
							<li>
								<span class="shownum2">5</span>
							</li>
							<li>
								<span class="shownum2">6</span>
							</li>
						</ul>
					</div>
					<ul class="col_g2">
						<li><a href="#" target="_blank">经济管理实验教学中心</a></li>
						<li><a href="#" target="_blank">本科教学审核评估</a> </li>
						<li><a href="#" target="_blank">环境资源化学实验中心</a> </li>
						<li><a href="#" target="_blank">信息公开</a> </li>
						<li><a href="#" target="_blank">中外合作办学</a> </li>
						<li><a href="#" target="_blank">工商微博</a> </li>
					</ul>
				</div>
			</div>
		</div>
		<!--底部-->
		<div class="foot">
			<div class="footer">
				<p>地址:中国 重庆市 南岸区学府大道19号 | 邮政编码:400067 | 校办电话:023-62769900 | 传真:023-62769515</p>
			</div>
		</div>
		
		<script type="text/javascript" src="${basePath}/static/js/index.js" ></script>
	</body>
</html>
