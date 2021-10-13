<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<%
Account account=(Account)session.getAttribute("account");
String pay=(String)session.getAttribute("pay");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ログイン完了</h1>
<% if (account != null) { %>
	<h3>こんにちは<%=account.getId() %>さん</h3>
<% } else { %>
	<h3>ゲストさんいらっしゃい</h3>
<% } %>

<% if (pay != null) { %>
	<h3><%=pay %></h3>
<% } %>

<a href="/Login/Main?action=logout">ログアウトする</a>
</body>
</html>