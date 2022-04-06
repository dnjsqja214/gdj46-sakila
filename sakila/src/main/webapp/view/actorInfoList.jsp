<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="vo.ActorInfo"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>

<%
	// 페이지 만들기
	int currentPage = 1; // 초기값
	if (request.getParameter("currentPage") != null) { // 현재 페이지 이동
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+"<-currentPage");
	int rowPerPage = 10; // 초기값
	int beginRow = (currentPage - 1) * rowPerPage;
	
	ActorInfoDao actorInfoDao = new ActorInfoDao();
	List<ActorInfo> list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage); // 페이징 메서드
	
	int totalRow = actorInfoDao.selectActorInfoTotalRow(); // 총 개수
	
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
<body class = "container">
	<h1>ActorInfoList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp" >index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ActorId</th>
				<th>firstName</th>
				<th>lastName</th>
				<th>filmInfo</th>
			</tr>
		</thead>
		<tbody>
				<%
					for(ActorInfo a : list){
				%>
			<tr>
				<td><%=a.getActorId()%></td>
				<td><%=a.getFirstName()%></td>	
				<td><%=a.getLastName()%></td>
				<td><%=a.getFilmInfo()%></td>
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
				<a href="<%=request.getContextPath()%>/view/actorInfoList.jsp?currentPage=<%=currentPage-1%>&" class="btn btn-outline-info">이전</a>
		<%
			}
		// currentPage가 lastPage보다 작으면 다음이 보이도록 하고, 다음을 누르면 currentPage에서 +1값을 보냄
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/view/actorInfoList.jsp?currentPage=<%=currentPage+1%>" class="btn btn-outline-info">다음</a>
		<%
			}
		%>
		</ul>
</body>
</html>