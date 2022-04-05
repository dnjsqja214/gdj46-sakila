<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%	
	// ++ 나중에 에러코드도 넣기
	FilmInStockDao filmInStockDao = new FilmInStockDao();
	Map<String, Object> map = new HashMap<>();
	
	// 출력란에 filmId, storeId 입력한 값을 넘겨 받아서 함수에 넣기
	int filmId = 0; // 초기값
	if(request.getParameter("filmId")!=null){
		filmId= Integer.parseInt(request.getParameter("filmId"));
	}
	System.out.println(filmId + "filmId 디버깅");
	int storeId = 0; // 초기값
	if(request.getParameter("storeId")!=null){
		storeId= Integer.parseInt(request.getParameter("storeId"));
	}
	System.out.println(storeId + "storeId 디버깅");
	
	map=filmInStockDao.filmInStockCall(filmId, storeId);
	List<Integer> list = (List<Integer>)(map.get("list"));
	int count =0;
	count = (Integer)(map.get("count"));
	System.out.println(count+"<-count");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 재고</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>DVD 재고 확인</h1>
	<form method="post" action="<%=request.getContextPath() %>/filmInStock.jsp">
		<div>가게Id</div>
		<div><input type="number" name="filmId"></div>
		<div>영화번호</div>
		<div><input type="number" name="storeId"></div>
		<button type ="submit" class="btn btn-secondary">제출</button>
		<%
			if(filmId!=0) { // 첫화면부터 0번 지점 재고0이라는 문구가 보이는게 안이뻐보여서 조건문을 넣었다
		%>
			<h3><div><%=storeId %>지점 재고: <%=count %></div></h3>
			<table>
				<thead>
					<th>영화번호</th>
				</thead>
				<tbody>
					<%
						// 번호를 입력했는데 재고가 없을 때 영화번호에 번호가 안뜨면 버그처럼 보이기때문에
						// inventory_id가 없을때는 '재고가 없습니다'문구를 넣어 정상작동하는걸 보여줌
						if(count == 0){
					%>
							<td><%=filmId%>번 영화의 재고가 없습니다</td>
					<%
						}
						for(int i : list) { 
					%>
						<tr>
							<td class="text-primary"><%=i %></td>
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