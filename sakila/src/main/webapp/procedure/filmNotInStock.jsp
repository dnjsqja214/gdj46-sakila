<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	FilmNotInStockDao filmNotInStockDao = new FilmNotInStockDao();
	Map<String, Object> map = new HashMap<>();
	
	int filmId = 0;
	if(request.getParameter("filmId")!=null){
		filmId= Integer.parseInt(request.getParameter("filmId"));
	}
	System.out.println(filmId + "filmId 디버깅");
	int storeId = 0; // 초기값
	if(request.getParameter("storeId")!=null){
		storeId= Integer.parseInt(request.getParameter("storeId"));
	}
	System.out.println(storeId + "storeId 디버깅");
	
	map= filmNotInStockDao.filmNotInStockCall(filmId,storeId);
	List<Integer> list = (List<Integer>)(map.get("list"));
	int count = 0;
	count = (Integer)(map.get("count"));
	System.out.println(count+"<-count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<form method="post" action="<%=request.getContextPath()%>/procedure/filmNotInStock.jsp">
		<h1>빌려간 DVD 조회</h1>
		<div>가게ID</div>
		<div><input type="number" name="storeId"></div>
		<div>영화번호</div>
		<div><input type="number" name="filmId"></div>
		<button type ="submit" class="btn btn-secondary">제출</button>
		<%
			if(filmId!=0) { // 첫화면부터 0번 빌려준 재고 0이라는 문구가 보이는게 안이뻐보여서 조건문을 넣었다
		%>
			<h3><div><%=storeId%> 빌려간 재고: <%=count%></div></h3>
			<table>
				<thead>
					<th>영화번호</th>
				</thead>
				<tbody>
					<%
						// 대여한 기록이 없을 때 영화번호에 아무것도 안뜨면 버그처럼 보이기때문에
						// 빌려준 inventory가 없을때는 '영화를 대여한 기록이 없습니다'문구를 넣어 정상작동하는걸 보여줌
						if(count == 0){
					%>
							<td><%=filmId%>번 영화를 빌려간 기록이 없습니다</td>
					<%
						}
						for(int i : list) { 
					%>
						<tr>
							<td class="text-danger"><%=i %></td>
						</tr>
					<%
						} 
					%>
				</tbody>
			</table>
		<%
			}
		%>
	</form>
</div>
</body>
</html> 