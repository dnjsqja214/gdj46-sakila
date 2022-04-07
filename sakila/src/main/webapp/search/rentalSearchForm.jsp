<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	StoreDao storeDao = new StoreDao();
	List<Integer> storeIdList = storeDao.selectStoreIdList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.jb-wrap { padding: 100px 10px; } /*세로 길이 조절*/
</style>
</head>
<body>
	<div class = "container jb-wrap">
		<h1 style = "margin-bottom:50px" class ="text-center">대여 상세 검색</h1>
		<form action= "<%=request.getContextPath()%>/search/rentalSearchAction.jsp" method="post">
			<table class="table table-bordered">
				<colgroup>
						<col width = "20%">
						<col width = "*">
				</colgroup>
				<!-- storeId 검색 -->
				<tr>
				 	<th class = "table-primary text-center">스토어 ID</th>
				 	<td>
				 		<div><input type="radio" name="storeId" value="" checked="checked">선택안함</div>
				 		<%
				 			for(int i : storeIdList) {
				 		%>
				 			<div><input type="radio" name="storeId" value="<%=i%>" ><%=i%>번 가게</div>
				 		<%
				 			}
				 		%>
				 	</td>
				</tr>
				<!-- 고객이름 검색 -->
				<tr>
					<th class = "table-primary text-center">고객이름</th>
					<td>
						<input type="text" name="customerName" class = "form-control">
					</td>
				</tr>
				<!-- 대여일자 -->
				<tr>
					<th class = "table-primary text-center">대여일자</th>
					<td>
						<input type="date" name="beginDate"> ~ <input type="date" name="endDate">
					</td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-outline-dark btn-light float-right">검색</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html> 