<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookStore login</title>

<link href="resources/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="resources/images/favicon.gif"
	type="image/x-icon">
</head>
<body>
	<div id="container">

		<h1>ColleagureStore site</h1>
		<!-- Roles: LOGIN, VIEW, ADMIN -->
		<!-- User:view, Pw:view, Roles:LOGIN VIEW -->
		<!-- User:admin, Pw:admin, Roles:LOGIN ADMIN -->
		<div id="loginbox">
			<form method="post" action="j_security_check">
				<div class="row">
					<span class="text">Username</span> <span><input type="text"
						size="25" name="j_username" /></span>
				</div>

				<div class="row">
					<span class="text">Password</span> <span><input
						type="password" size="25" name="j_password" /></span>
				</div>

				<div class="row">
					<input type="submit" value="Login" />
				</div>

				<input type="hidden" name="sessionTimeout" id="sessionTimeout"
					value="333140bf41d78a46c83d60c0bc1387cf" />
			</form>

		</div>
		<%
			String retry = request.getParameter("retry");
			if ("true".equals(retry)) {
		%>
		<!-- IN CASE OF ERROR SHOW THIS MESSAGE -->
		<h1 class="error_text">Wrong username or password.</h1>
		<%
			}
			String unauthorized = request.getParameter("unauthorized");
			if ("true".equals(unauthorized)) {
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "?retry=true");
			}
		%>
	</div>
</body>
</html>
