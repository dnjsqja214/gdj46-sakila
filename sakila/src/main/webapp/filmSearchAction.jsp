<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%
	String categoryName = request.getParameter("categoryName");
		
	System.out.println(categoryName+"<-categoryName");
	String rating = request.getParameter("rating");
	System.out.println(rating+"<-rating");
	double price = -1; // price데이터가 입력되지 않았을때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	System.out.println(price+"<-price");
	int length = -1; // length데이터가 입력되지 않았을때
	if(!request.getParameter("length").equals("")) {
			length=Integer.parseInt(request.getParameter("length"));
	}
	System.out.println(length+"<-length");
	String title = request.getParameter("title");
	String actor = request.getParameter("actor");
	System.out.println(title+"<-title");
	System.out.println(actor+"<-actor");
	
	// 페이지 작성
	// 페이지 작성을 위한 함수값 등록
	int totalCount = 0; // film총개수 초기값
	int currentPage = 1; // 디폴트 1
	if (request.getParameter("currentPage") != null) { // 현재 페이지 이동
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+"<-currentPage");
	int rowPerPage = 10;
	int beginRow = 0;
	beginRow = (currentPage-1)*rowPerPage;
	FilmListDao c = new FilmListDao();
	totalCount = c.searchTotalRow(categoryName,rating,price,length,title,actor);
	
	int lastPage = 0;
	if(totalCount/rowPerPage != 0) {
		lastPage = totalCount/rowPerPage +1;
	} else {
		lastPage = totalCount/rowPerPage;
	}
	
	FilmListDao filmListDao = new FilmListDao();
	List<FilmList> list = filmListDao.selectFilmListSearch(beginRow ,rowPerPage ,categoryName, rating, price, length, title, actor);
	System.out.println(list.size()); // 0
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>검색</h1>
		<table border="1">
				<tr>
					<td>fid</td>
					<td>title</td>
					<td>description</td>
					<td>category</td>
					<td>price</td>
					<td>length</td>
					<td>rating</td>
					<td>actors</td>
				</tr>
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
	</table>
	<!-- 숫자페이지 만들기 -->
		<ul class="pagination">
			<!-- 이전 / 다음 페이지 -->
		<%
		// currentPage가 1보다 크면 이전이 보이도록 하고, 이전을 누르면 currentPage에서 -1값을 보냄
			if(currentPage > 1) {
		%>
				<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage-1%>&categoryName=<%=categoryName%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>" class="btn btn-outline-info">이전</a>
		<%
			}
		// currentPage가 lastPage보다 작으면 다음이 보이도록 하고, 다음을 누르면 currentPage에서 +1값을 보냄
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage+1%>&categoryName=<%=categoryName%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actor=<%=actor%>" class="btn btn-outline-info">다음</a>
		<%
			}
		%>
		</ul>
</body>
</html>