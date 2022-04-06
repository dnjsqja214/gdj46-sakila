<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>

<%
		
		SalesByStoreDao dao = new SalesByStoreDao();
		List<SalesByStore> list = dao.selectSalesByStore(); // 페이징 메서드

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<body class = "container">
	<h1>salesByStore</h1>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>store</th>
				<th>manager</th>
				<th>totalSales</th>
			</tr>
		</thead>
		<tbody>
				<%
					for(SalesByStore a : list){
				%>
			<tr>
				<td><%=a.getStore()%></td>
				<td><%=a.getManager()%></td>	
				<td><%=a.getTotalSales()%></td>
			</tr>
				<%
					}
				 %>
		</tbody>
	</table>
</body>
</html>