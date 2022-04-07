<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "dao.*" %>
<%
   int storeId = 0; 
   if(!request.getParameter("storeId").equals("")) { // 매장을 선택했다면 선택한 매장값 받아서 int로 형변환
      storeId = Integer.parseInt(request.getParameter("storeId"));   
   }
   String customerName = request.getParameter("customerName");
   String beginDate = request.getParameter("beginDate");
   String endDate = request.getParameter("endDate");
   // 디버깅
   System.out.println(storeId+"<-storeId");
   System.out.println(customerName+"<-customerName");
   System.out.println(beginDate+"<-beginDate");
   System.out.println(endDate+"<-endDate");
	
	//이전 페이징 구현
	int currentPage=1;
	if(request.getParameter("currentPage") != null) { //이전, 다음 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage")); // currentPage에 요청받은 currentPage 넣기
	}
	int rowPerPage = 10;
	int beginRow = (currentPage-1)*rowPerPage;
  
	RentalDao rentalDao = new RentalDao();
  	List<Map<String,Object>> list = rentalDao.selectRentalSearchList(beginRow, rowPerPage, storeId, customerName, beginDate, endDate);
    
  	// 페이지를 만들기위한 총개수 dao작성
  	int totalCount = rentalDao.RentalTotalRow(storeId, customerName, beginDate, endDate);
  	System.out.println(totalCount+"<--totalCount");
  	
  	// 페이지
  	int lastPage = totalCount/rowPerPage;
  	if(totalCount%rowPerPage != 0) {
  		lastPage = (totalCount/rowPerPage) +1;
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
 	<div class = "container">
		<h1 class = "text-center" style = "margin-bottom:20px">검색 결과 리스트</h1>
		<table class="table table-bordered">
			<thead>
				<tr class = "table-primary text-center">
					<th>rentalId</th>
					<th>rentalDate</th>
					<th>inventoryId</th>
					<th>customerId</th>
					<th>returnDate</th>
					<th>staffId</th>
					<th>lastUpdate</th>
					<th>customerName</th>
					<th>storeId</th>
					<th>filmId</th>
					<th>title</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Map<String, Object> m : list) {
				%>
						<tr>
							<td><%=m.get("rentalId")%></td>
							<td><%=m.get("rentalDate")%></td>
							<td><%=m.get("inventoryId")%></td>
							<td><%=m.get("customerId")%></td>
							<td><%=m.get("returnDate")%></td>
							<td><%=m.get("staffId")%></td>
							<td><%=m.get("lastUpdate")%></td>
							<td><%=m.get("customerName")%></td>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("filmId")%></td>
							<td><%=m.get("title")%></td>
						</tr>
				<%	
					}
				%>
			</tbody>
		</table>
		<!-- 숫자페이지 만들기 -->
		<ul class="pagination">
		<%
			if(currentPage > 1) {
		%>
				<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/rental/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">이전</a></li>
		<%
			}
		
			if(currentPage < lastPage) {
		%>	
				<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/rental/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&endDate=<%=endDate%>">다음</a></li>
		<%
			}
		%>
		</ul>
	</div>
</body>
</html>