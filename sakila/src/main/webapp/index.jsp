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
		<h3 >List <i class="fas fa-list"></i></h3>
				<li><a href="<%=request.getContextPath()%>/storeList.jsp" data-toggle="tooltip" title="점포의 직원이름과 주소">StoreList</a></li>
				<li><a href="<%=request.getContextPath()%>/staffList.jsp" data-toggle="tooltip" title="직원의 주소,이메일,비밀번호">StaffList</a></li>
			</o1>
		<h3>Procedure <i class="fas fa-calculator"></i></h3>
			<o1>
				<li><a href="<%=request.getContextPath()%>/procedure/filmInStock.jsp" data-toggle="tooltip" title="재고 확인">FilmInStock</a></li>
				<li><a href="<%=request.getContextPath()%>/procedure/filmNotInStock.jsp" data-toggle="tooltip" title="빌려간 재고 확인">FilmNotInStock</a></li>
				<li><a href="<%=request.getContextPath()%>/procedure/rewardsReport.jsp" data-toggle="tooltip" title="vip 선정">RewardsReport</a></li>
			</o1>
	   <h3>View <i class="fas fa-table"></i></h3>
	  		 <o1>
				<li><a href="<%=request.getContextPath()%>/view/actorInfoList.jsp" data-toggle="tooltip" title="배우가 영화장르별로 무슨 영화에 출현했는지 정리">ActorInfoList</a></li>
				<li><a href="<%=request.getContextPath()%>/view/customerList.jsp" data-toggle="tooltip" title="손님의 개인정보">CustomerList</a></li>
				<li><a href="<%=request.getContextPath()%>/view/filmList.jsp" data-toggle="tooltip" title="영화정보">FilmList</a></li>
				<li><a href="<%=request.getContextPath()%>/view/nicerButSlowerFilmList.jsp" data-toggle="tooltip" title="상점에 있는 영화 목록">NicerButSlowerFilmList</a></li>
				<li><a href="<%=request.getContextPath()%>/view/salesByFilmCategory.jsp" data-toggle="tooltip" title="영화 장르별 매출의 합계정보">salesByFilmCategory</a></li>
				<li><a href="<%=request.getContextPath()%>/view/salesByStore.jsp" data-toggle="tooltip" title="각 매니저의 상점 매출액 정보">salesByStore</a></li>
				<li><a href="<%=request.getContextPath()%>/view/staffList.jsp" data-toggle="tooltip" title="staff정보">staffList</a></li>
			</o1>
</div>
</body>
</html>