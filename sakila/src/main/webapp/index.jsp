<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"></head>
</head>
<body>	
<div class="container">
	<h1>INDEX</h1>
	<i class="fas fa-file"></i>
	<o1>
		<li><a href="<%=request.getContextPath()%>/storeList.jsp" data-toggle="tooltip" title="점포의 직원이름과 주소">StoreList</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp" data-toggle="tooltip" title="직원의 주소,이메일,비밀번호">StaffList</a></li>
		<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfoList</a></li>
		<li><a href="<%=request.getContextPath()%>/customerList.jsp">CustomerList</a></li>
		<li><a href="<%=request.getContextPath()%>/filmList.jsp">FilmList</a></li>
		<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">NicerButSlowerFilmList</a></li>
	</o1>
		<h3>Procedure</h3>
	<o1>
		<li><a href="<%=request.getContextPath()%>/filmInStock.jsp">FilmInStock</a></li>
		<li><a href="<%=request.getContextPath()%>/filmNotInStock.jsp">FilmNotInStock</a></li>
		<li><a href="<%=request.getContextPath()%>/rewardsReport.jsp">RewardsReport</a></li>
	</o1>
</div>
</body>
</html>