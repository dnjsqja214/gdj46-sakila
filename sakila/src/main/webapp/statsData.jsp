<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();
	// 1. customer별 총 amount
	List<Map<String, Object>> amountByCoustomer = statsDataDao.amountByCoustomer();
	// 2. rental_ratal별 영화 개수
	List<Map<String, Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	// 3. rating별 영화 개수
	List<Map<String, Object>> filmCountByRating = statsDataDao.filmCountByRating();
	// 4.length별 영화 개수
	List<Map<String, Object>> filmLengthDivision = statsDataDao.filmLengthDivision();
	// 5.요일별 판매수
	List<Map<String, Object>> dayOfTheWeek = statsDataDao.dayOfTheWeek();
	// 배우당 출연횟수(많은순)
	List<Map<String, Object>> actorAppear = statsDataDao.actorAppearDown();
	// 배두당 출연횟수(적은순)
	List<Map<String, Object>> actorAppearUp = statsDataDao.actorAppearUp();
	// 나라별 가장 선호 하는 카테고리
	List<Map<String, Object>> countryCategory = statsDataDao.countryCategory();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>amountByCoustomer</h1>
		<table border="1">
			<tr>
				<td>고객아이디</td>
				<td>고객이름</td>
				<td>총지불액</td>
			</tr>
		<%
			for(Map<String, Object> m : amountByCoustomer) {
		%>
				<tr>
					<td><%=m.get("customerId")%></td>
					<td><%=m.get("name") %></td>
					<td><%=m.get("total") %></td>
				</tr>
		<%
			}
		%>
		</table>
	<h1>rental_ratal별 영화 개수</h1>
		<table border="1">
			<tr>
				<td>관람 등급</td>
				<td>개수</td>
			</tr>
			<%
				for(Map<String, Object> m2 : filmCountByRentalRate) {
			%>
				<tr>
					<td><%=m2.get("rentalRate") %></td>
					<td><%=m2.get("cnt") %></td>
				</tr>
			<%
				}
			%>
		</table>
	<h1>rating별 영화 개수</h1>
		<table border="1">
			<tr>
				<td>관람 등급</td>
				<td>개수</td>
			</tr>
			<%
				for(Map<String, Object> m3 : filmCountByRating) {
			%>
				<tr>
					<td><%=m3.get("rating") %></td>
					<td><%=m3.get("cnt") %></td>
				</tr>
			<%
				}
			%>
		</table>
	<h1>rating별 영화 개수</h1>
		<table border="1">
			<tr>
				<td>영화 길이</td>
				<td>개수</td>
			</tr>
			<%
				for(Map<String, Object> m4 : filmLengthDivision) {
			%>
				<tr>
					<td><%=m4.get("length") %></td>
					<td><%=m4.get("cnt") %></td>
				</tr>
			<%
				}
			%>
		</table>
	<h1>요일별 매출</h1>
	<table border="1">
		<tr>
			<td>지점</td>
			<td>요일</td>
			<td>판매수</td>
		</tr>
		<%
			for(Map<String, Object> m5 : dayOfTheWeek) {
		%>
			<tr>
				<td><%=m5.get("storeId") %>
				<td><%=m5.get("dayOfWeek") %></td>
				<td><%=m5.get("cnt") %></td>
			</tr>
		<%
			}
		%>
	</table>
	<h1>배우 출연횟수(많은 순)</h1>
	<table border="1">
		<tr>
			<td>actorId</td>
			<td>배우 이름</td>
			<td>영화 출연횟수</td>
		</tr>
		<%
			for(Map<String, Object> m6 : actorAppear) {
		%>
			<tr>
				<td><%=m6.get("actorId") %></td>
				<td><%=m6.get("NAME") %></td>
				<td><%=m6.get("cnt") %></td>
			</tr>
		<%
			}
		%>
	</table>
	<h1>배우 출연횟수(적은 순)</h1>
	<table border="1">
		<tr>
			<td>actorId</td>
			<td>배우 이름</td>
			<td>영화 출연횟수</td>
		</tr>
		<%
			for(Map<String, Object> m6 : actorAppearUp) {
		%>
			<tr>
				<td><%=m6.get("actorId") %></td>
				<td><%=m6.get("NAME") %></td>
				<td><%=m6.get("cnt") %></td>
			</tr>
		<%
			}
		%>
	</table>
	<h1>나라별 가장 선호하는 카테고리</h1>
	<table border="1">
		<tr>
			<td>나라</td>
			<td>카테고리</td>
			<td>시청횟수</td>
		</tr>
		<%
			for(Map<String, Object> m7 : countryCategory) {
		%>
			<tr>
				<td><%=m7.get("country") %></td>
				<td><%=m7.get("categoryName") %></td>
				<td><%=m7.get("total") %></td>
			</tr>
		<%
			}
		%>		
	</table>
</body>
</html>