<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="vo.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%
	// boardOne에서 boardNo값 받아오기
	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	//category테이블 만들기 
	BoardDao boardDao = new BoardDao(); // 메서드 사용을 위한 객체 생성
	Board board = boardDao.selectBoardOne(boardNo); // 정보를 상세보기 해주는 메서드 사용 후 객체에 저장
	
	CategoryDao categoryDao = new CategoryDao();
	ArrayList<String> categoryList = categoryDao.categoryName(board.categoryName);
	
	for(String s : categoryList) {
		System.out.println(s);
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
	<div class="col-sm-8">
 	<h1 class="text-primary">UPDATE</h1>
 	<p class="text-info">insert text</p>
		<form method="post" action="<%=request.getContextPath()%>/updateBoardAction.jsp">
		<table class="table table-bordered">
			<tr class="table-primary">
				<td><strong>boardNo</strong></td>
				<td><input type="text" name="boardNo" value="<%=boardNo%>" readonly="readonly"></td>
			</tr>
			<tr class="table-info">
				<td><strong>categoryName</strong></td>
				<td>
					<select name="categoryName">
						<%
							for(String s : categoryList) {
								if(s.equals(board.categoryName)) {
						%>
									<option selected="selected" value="<%=s%>"><%=s%></option>
						<%
								} else {
						%>
									<option value="<%=s%>"><%=s%></option>
						<%		
								}
							}
						%>
					</select>
				</td>
			</tr>
			<tr class="table-primary">
				<td><strong>boardTitle</strong></td>
				<td><input type="text" name="boardTitle" value="<%=board.boardTitle%>"></td>
			</tr>
			<tr class="table-info">
				<td><strong>boardContent</strong></td>
				<td>
					<textarea rows="5" cols="50" name="boardContent"><%=board.boardContent%></textarea>
				</td>
			</tr>
			<tr class="table-primary">	
				<td><strong>boardPw</strong></td>
				<td><input type="password" name="boardPw" value=""></td>
			</tr>
		</table>
		<div>
			<button type="submit" class="btn btn-outline-primary" role="button">수정</button>
		</div>
		<div>
			<a href="<%=request.getContextPath()%>/boardList.jsp" class="btn btn-outline-secondary" role="button" class="text-right">리스트로 돌아가기</a>
		</div>
		</form>
	</div>
</body>
</html>