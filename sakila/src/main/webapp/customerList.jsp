<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	int currentPage = 1; // 초기값
	if (request.getParameter("currentPage") != null) { // 현재 페이지 이동
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+"<-currentPage");
	int rowPerPage = 10; // 초기값
	int beginRow = (currentPage - 1) * rowPerPage;

	CustomerListDao customerListDao = new CustomerListDao();
	List<CustomerList> list = customerListDao.selectCustomerListBypage(beginRow, rowPerPage);
	
	int totalRow = customerListDao.selectCustomerListTotalRow(); // 총 개수
	
	// 마지막 페이지
	int lastPage = 0;
	if(totalRow%rowPerPage == 0) {
		lastPage = totalRow/rowPerPage;
	} else if(totalRow%rowPerPage != 0) {
		lastPage = (totalRow/rowPerPage)+1;
	}
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
	<h1>CustomerList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>address</th>
					<th>zipCode</th>
					<th>phone</th>
					<th>city</th>
					<th>country</th>
					<th>notes</th>
					<th>sid</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(CustomerList c : list) {
				%>
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getName()%></td>
					<td><%=c.getAddress()%></td>
					<td><%=c.getZipCode()%></td>
					<td><%=c.getPhone()%></td>
					<td><%=c.getCity()%></td>
					<td><%=c.getCountry()%></td>
					<td><%=c.getNotes()%></td>
					<td><%=c.getSid()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
			<!-- 숫자페이지 만들기 -->
			<ul class="pagination">
				<!-- 이전 / 다음 페이지 -->
			<%
			// currentPage가 1보다 크면 이전이 보이도록 하고, 이전을 누르면 currentPage에서 -1값을 보냄
				if(currentPage > 1) {
			%>
					<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage-1%>&" class="btn btn-outline-info">이전</a>
			<%
				}
			// currentPage가 lastPage보다 작으면 다음이 보이도록 하고, 다음을 누르면 currentPage에서 +1값을 보냄
				if(currentPage < lastPage) {
			%>
					<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-info">다음</a>
			<%
				}
			%>
			</ul>
</body>
</body>
</html>