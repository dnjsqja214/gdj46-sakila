<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!DOCTYPE html>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<%
	// dao
	StaffDao staffDao = new StaffDao();
	List<Map<String,Object>> list = staffDao.selectStaffList();
%>
<html>
<head>
<meta charset="UTF-8">
<title>staff List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
		<h1 class="font-italic">Staff List</h1>
		<table border ="1" class="table table-dark table-striped">
			<thead>
				<tr>
					<th>staffId</th>
					<th>staffName</th>
					<th>addressId</th>
					<th>staffAddress</th> 
					<th>email</th>
					<th>storeId</th>
					<th>active</th>
					<th>userName</th>
					<th>PASSWORD</th>
					<th>lastUpdate</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Map m : list) {  // 출력하기 !picture부분은 글이 너무 길어 테이블 칸이 너무 이상해 뺐음
				%>
						<tr>
							<td><%=m.get("staffId")%></td>
							<td><%=m.get("staffName")%></td>
							<td><%=m.get("addressId")%></td>
							<td><%=m.get("staffAddress")%></td>
							<td><%=m.get("email")%></td>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("active")%></td>
							<td><%=m.get("userName")%></td>
							<td><%=m.get("PASSWORD")%></td>
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