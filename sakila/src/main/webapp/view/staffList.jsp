<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
		
	StaffListDao dao = new StaffListDao();
	List<StaffList> list = dao.selectStaffList(); // 페이징 메서드
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>staffList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>address</th>
				<th>zipCode</th>
				<th>city</th>
				<th>country</th>
				<th>SID</th>
			</tr>
		</thead>
		<tbody>
				<%
					for(StaffList a : list){
				%>
			<tr>
				<td><%=a.getId()%></td>
				<td><%=a.getName()%></td>	
				<td><%=a.getAddress()%></td>
				<td><%=a.getZipCode()%></td>
				<td><%=a.getCity()%></td>	
				<td><%=a.getCountry()%></td>
				<td><%=a.getSid()%></td>
			</tr>
				<%
					}
				 %>
		</tbody>
	</table>
</body>
</html>