<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css"> <!-- css파일과 링크를 거는것  -->
<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?a=loginform">로그인</a></li>
				<li><a href="/mysite/user?a=joinform">회원가입</a></li>
				
				<!-- 로그인 후 -->
				<!-- 
				<li><a href="">회원정보수정</a></li>
				<li><a href="">로그아웃</a></li> 
				<li> 황일영님 안녕하세요^^;</li>
				-->
			</ul>
		</div> <!-- /header -->
		
		<div id="navigation">
			<ul>
				<li><a href="">조은솔</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="/mysite/assets/images/profile.jpg">
					<h2>안녕하세요.<br> 조은솔의 mysite에 방문하신 것을<br/> 환영합니다.</h2>
					<p>
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.
						<br>
						메뉴는 사이트 소개, 방명록, 게시판이 있구요. JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.
						<br>
						<br>
						<a href="">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div>
</body>
</html>