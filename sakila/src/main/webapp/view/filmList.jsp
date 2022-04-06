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
	
	FilmListDao filmListDao = new FilmListDao();
	List<FilmList> list = filmListDao.selectFilmListByPage(beginRow, rowPerPage);
	
	int totalRow = filmListDao.filmTotalRow(); // 총 개수
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
	<h1>FilmeList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>fid</th>
					<th>title</th>
					<th>description</th>
					<th>category</th>
					<th>price</th>
					<th>length</th>
					<th>rating</th>
					<th>actors</th>
				</tr>
			</thead>
			
			<tbody>
				<%
					for(FilmList f : list) {
				%>
				<tr>
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle()%></td>
					<td><%=f.getDescription()%></td>
					<td><%=f.getCategory()%></td>
					<td><%=f.getPrice()%></td>
					<td><%=f.getLength()%></td>
					<td><%=f.getRating()%></td>
					<td><%=f.getActors()%></td>
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
					<a href="<%=request.getContextPath()%>/view/filmList.jsp?currentPage=<%=currentPage-1%>&" class="btn btn-outline-info">이전</a>
			<%
				}
			// currentPage가 lastPage보다 작으면 다음이 보이도록 하고, 다음을 누르면 currentPage에서 +1값을 보냄
				if(currentPage < lastPage) {
			%>
					<a href="<%=request.getContextPath()%>/view/filmeList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-info">다음</a>
			<%
				}
			%>
			</ul>
</body>
</html>