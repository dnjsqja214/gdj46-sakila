<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%
	// boardList 테이블
	int currentPage = 1; // 현재 페이지 디폴트 값
	int rowPerPage = 10; // 행갯수 디폴트값
	if (request.getParameter("currentPage") != null) { // 현재 페이지 이동
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	
	if(request.getParameter("rowPerPage") != null) { // 행갯수 설정 값
		rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
	}
	System.out.println(currentPage+"<--currentPage");
	int beginRow = (currentPage - 1) * rowPerPage;
	System.out.println(rowPerPage+"<--rowPerPage");
	
	
	// categoryName 값 받기
	String categoryName = ""; // categoryName 디폴트값
	int categoryCnt = 0;		// categoryCnt 디폴트값
	if(request.getParameter("categoryName") != null) { // 페이지에서 category를 눌렀다면 정보값을 변수에 등록하기
		categoryName = request.getParameter("categoryName");
	}
	if(request.getParameter("categoryCnt") != null) { // cnt가 null아니라면 categoryCnt에 값넣기
		categoryCnt=Integer.parseInt(request.getParameter("categoryCnt")); 
	}
	System.out.println(categoryName+ " <-- categoryName");
	System.out.println(categoryCnt+"<--categoryCnt");
	
	// BoardDao list
	BoardDao boardDao = new BoardDao();
	ArrayList<Board> list = boardDao.selectBoardListByPage(categoryName, beginRow, rowPerPage);
	
	// category테이블 만들기 
	CategoryDao categoryDao = new CategoryDao();
	ArrayList<HashMap<String,Object>> categoryList = categoryDao.categoryCnt();
	
	// 마지막 페이지만들기
	
	int totalCount = boardDao.selectboardTotalRow();  // 전체 행갯수
	if(categoryCnt != 0) { // categoryName이 선택 되었다면 출력되는 totalCount에 값넣어주기 이유: 출력되는 total을 바꿔주기 위해 그리고 카테고리 선택했을때 lastPage등 값 바꿀려고
		totalCount = categoryCnt;
	}
	System.out.println(totalCount+"<--totalCount");
	
	int lastPage = totalCount/rowPerPage;
	System.out.println(lastPage+"<--lastPage");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid" >
	 <nav class="navbar navbar-expand-sm bg- navbar-dark">
	  <h1>B L O G</h1>
		<div class="spinner-grow text-danger"></div>
		<div class="spinner-grow text-warning"></div>
		<div class="spinner-grow text-success"></div>
		<div class="spinner-grow text-info"></div>
		<div class="spinner-grow text-primary"></div>
		<div class="spinner-grow text-muted"></div>
		<div class="spinner-grow text-secondary"></div>
		<div class="spinner-grow text-dark"></div>
	</nav>  

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
 	 <a class="navbar-brand">Function</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link"  href="<%=request.getContextPath()%>/board/insertBoardForm.jsp">입력</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/board/searchBoardForm.jsp">검색</a> <!-- 찾기 -->
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/board/deleteBoardForm.jsp?boardNo=<%=beginRow%>">삭제</a> <!-- 찾기 -->
      </li>
    </ul>
   </div>
</nav>

 </div>
	<!-- category별 게시글 링크 메뉴 -->
	<div>
		<ul class="list-group list-group-horizontal"> <!--  리스트 그룹으로 함 -->
					<li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center ">
						<a href="<%=request.getContextPath()%>/board/boardList.jsp">전체 게시물</a>
					</li>
			<%
				for(HashMap<String, Object> m : categoryList) { // 카테고리를 선택하면 카테고리 테이블페이지가 에러가 남 -->cnt값도 넘겨주자!
			%>
					<li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center ">
						<a href="<%=request.getContextPath()%>/board/boardList.jsp?categoryName=<%=m.get("categoryName")%>&categoryCnt=<%=m.get("cnt")%>"><%=m.get("categoryName")%> </a>
						<span class="badge badge-danger badge-pill"><%=m.get("cnt")%></span> <!--  게시물의 수 -->
					</li>
			<%
				}
			%>
		</ul>
	</div>

	<div>
	<h2 class="font-italic">게시글 목록<span class="badge badge-dark">(total : <%=totalCount%>)</span></h2>
	</div>
	
	<!-- rowPerPage 갯수 정하기 -->
	<div class="dropdown">
	  <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
		 행 갯수
	  </button>
	<div class="dropdown-menu">
		<a class="dropdown-item" href="<%=request.getContextPath()%>/board/boardList.jsp?rowPerPage=5">5</a>
		<a class="dropdown-item" href="<%=request.getContextPath()%>/board/boardList.jsp?rowPerPage=10">10</a>
	 </div>
	</div>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>categoryName</th>
				<th>boardTitle</th>
				<th>createDate</th>
			</tr>
		</thead>
		<!-- boardTitle링크 -->
		<tbody>
			<%
				for(Board b : list) {
			%>
					<tr>
						<td><%=b.categoryName%></td>
						<td><a href="<%=request.getContextPath()%>/board/boardOne.jsp?boardNo=<%=b.boardNo%>"class="text-info" data-toggle="tooltip" title="check information"><%=b.boardTitle%></a></td>
						<td><%=b.createDate%></td>
					</tr>
			<%		
				}
			%>
		</tbody>
	</table>
	
	<!-- 숫자페이지 만들기 -->
		<ul class="pagination">
			<% 
				if(currentPage >1) { 
			%>
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">Previous</a></li>
			<%
				} else if(currentPage == 1) { // 첫페이지일때 previous버튼 안눌리게 하기
			%>
					<li class="page-item disabled"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=currentPage-1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">Previous</a></li>
			<%
				}
				if(lastPage-currentPage<10){ // 설명:페이지 파워링크를 lastPage까지 보여주기 위한 조건문 이걸 안하면 파워링크가 계속 생긴다!
					for(int i=1; i<=lastPage%10;i=i+1) { 
			%>
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=(currentPage/10)*10+i%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">[<%=(currentPage/10)*10+i%>]</a></li>
			<%
					}
				} else {
					for(int i=1;i<=10;i=i+1) { // 기본10개씩페이지 숫자링크 보여주기 ex)1~10, 6~15, 81~90
			%>
		
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=(currentPage/10)*10+i%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">[<%=(currentPage/10)*10+i%>]</a></li>
		
			<%
					}
				}
			%>
			
			<%
				 if(currentPage == lastPage) { // 마지막페이지일때 next버튼 안눌리게하기
			%>
					<li class="page-item disabled"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">Next</a></li>
			<%		
				 } else if(currentPage < lastPage) { // 마지막페이지가 나오면 다음버튼이 안나오게 하기
			%>
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/board/boardList.jsp?currentPage=<%=currentPage+1%>&rowPerPage=<%=rowPerPage%>&categoryName=<%=categoryName%>&categoryCnt=<%=categoryCnt%>">Next</a></li>	
			<%
				}
			%>
		</ul>
		<a href="<%=request.getContextPath()%>/insertBoardForm.jsp" class="btn btn-outline-success" role="button">게시글 입력</a>
</body>
</html>