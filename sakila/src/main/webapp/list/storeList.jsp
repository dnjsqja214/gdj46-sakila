<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%
	StoreDao storeDao = new StoreDao();
	List<Map<String, Object>> list = storeDao.selectStoreList();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Store List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
		<h1 class="font-italic">Store List</h1>
		<table border="1" class="table table-striped">
			<thead>
				<tr>
					<th>storeId</th>
					<th>staffId</th>
					<th>staffName</th>
					<th>addressId</th>
					<th>staffAddress</th>
					<th>lastUpdate</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Map m : list) {
				%>
						<tr>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("staffId")%></td>
							<td><%=m.get("staffName")%></td>
							<td><%=m.get("addressId")%></td>
							<td><%=m.get("staffAddress")%></td>
							<td><%=m.get("lastUpdate")%></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>