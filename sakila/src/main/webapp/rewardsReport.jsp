<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	RewardsReportDao rewardsReportDao = new RewardsReportDao();
	Map<String, Object> map = new HashMap<>();
	
	// 출력란에 purchase와 amount를 입력한 값을 넘겨 받아서 함수에 넣기
	int purchase = 0;
	if(request.getParameter("purchase")!=null){
		purchase= Integer.parseInt(request.getParameter("purchase"));
	}
	System.out.println(purchase + "purchase 디버깅");

	double amount = 0;
	if(request.getParameter("amount")!=null){
		amount= Double.parseDouble(request.getParameter("amount"));
	}
	System.out.println(amount + "amount 디버깅");
	
	map = rewardsReportDao.rewardsReportCall(purchase, amount);
	List<Integer> list = (List<Integer>)(map.get("list"));
	int count =0;
	count = (Integer)(map.get("count"));
	System.out.println(count+"<-count");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVP 선정</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1>VIP 기준 값을 넣어주세요</h1>
	<form method="post" action="<%=request.getContextPath() %>/rewardsReport.jsp">
		<div>구매횟수</div>
		<div><input type="number" name="purchase"></div>
		<div>한달동안 사용한 금액</div>
		<div><input type="number" name="amount"></div>
		<button type ="submit" class="btn btn-secondary">제출</button>
		<%
			if(purchase!=0) { // 첫화면부터 글씨들이 보이면 안이뻐보여서 조건문을 넣었다
		%>
			<h3><div>VIP <%=count %> 명</div></h3>
			<table>
				<thead>
					<th>vip customer list</th>
				</thead>
				<tbody>
					<%
						// 조건문이 너무 높아서 만족하는 고객이 없어 문구를 넣고 조건을 낮춰서 다시 검색해보라는 의미로
						if(count == 0){
					%>
							<td>VIP고객이 없습니다. 기준을 낮쳐주세요</td>
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