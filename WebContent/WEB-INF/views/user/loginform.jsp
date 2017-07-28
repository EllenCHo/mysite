<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("result");
	//String result = (String)request.getAttribute("result");
	System.out.println(result);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		<div id="wrapper">
			<div id="content">
				<div id="user">
					
					<form id="login-form" name="loginform" method="post" action="/mysite/user">
						<input type="hidden" name="a" value="login" /> 
						
						<label class="block-label" for="email">이메일</label> 
						<input id="email" name="email" type="text" value="" /> 

						<label class="block-label">패스워드</label> 
						<input name="password" type="password" value="" />
						
						<%if("fail".equals(result)){ %>		
								<P>로그인이 실패했습니다. 다시입력해주세요</P>
						<%} %>
	
						<input type="submit" value="로그인">
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
	</div> <!-- /container -->


</body>
</html>