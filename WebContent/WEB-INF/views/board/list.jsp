<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/bs?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:forEach items="${list }" var="vo">			
					<tr>
						<td>${vo.num }</td>
						<td><a href="/mysite/bs?a=read&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.name }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<c:if test="${authUser.no == vo.userNo }">
							<td><a href="/mysite/bs?a=delete&boardNo=${vo.no }" class="del">삭제</a></td>
						</c:if>
						<c:if test="${authUser.no != vo.userNo }">
							<td></td>
						</c:if>
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<!-- 처음 페이지 번호가 첫페이지보다 작을 경우 나타나도록 하자 -->
						<li><a href="">◀</a></li>
						<li><a href="/mysite/bs&pageNo=1">1</a></li>
						<!-- 선택된 페이지일 경우 밑의 줄처럼 하기 -->
						<li class="selected">3</li>
						<!-- 마지막 페이지 번호가 총 페이지번호보다 작을 경우 나타나도록 하자 -->
						<li><a href="">▶</a></li>
					</ul>
				</div>	
				<c:if test="${!(empty authUser) }">			
				<div class="bottom">
					<a href="/mysite/bs?a=writeform" id="new-book">글쓰기</a>
				</div>
				</c:if>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>