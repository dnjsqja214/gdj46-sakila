package dao;
import java.sql.*;
import java.util.*;

import vo.Board;


public class BoardDao {
	// 생성자 메서드
	public BoardDao() {}
	
	// 입력
	public void insertBoard(Board board) throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/blog";
		String dbuser = "root";
		String dbpw = "java1234";
		conn = DriverManager.getConnection(dburl, dbuser, dbpw);
		// DML insert문
		String sql = "SELECT INTO board(category_name , board_title , board_content , create_date ,update_date ) VALUES (?,?,?,now(), now())";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.categoryName);
		stmt.setString(2, board.boardTitle);
		stmt.setString(3, board.boardContent);
		int row = stmt.executeUpdate();
		if(row == 1) {
			System.out.println("입력성공");
		} else {
			System.out.println("입력실패");
		}
		stmt.close();
		conn.close();
	}
	
	// List
	public ArrayList<Board> selectBoardListByPage(String categoryName, int beginRow, int rowPerPage) throws Exception {		
		ArrayList<Board> list = new ArrayList<Board>();
		// book 10명 변환되도록 구현
		Class.forName("org.mariadb.jdbc.Driver");
		
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement boardStmt = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/blog";
		String dbuser = "root";
		String dbpw = "java1234";
		
		conn = DriverManager.getConnection(dburl, dbuser, dbpw);
		
		// 카테고리를 누르시 카데고리에 대한 내용만 출력하기
		String boardSql = null;
		if(categoryName.equals("")) {
			// 명령문(board테이블를 SELECT 정렬) 
			boardSql = "SELECT board_no boardNo, category_name categoryName, board_title boardTitle, create_date createDate FROM board ORDER BY create_date DESC LIMIT ?, ?";
			boardStmt = conn.prepareStatement(boardSql);
			boardStmt.setInt(1, beginRow);
			boardStmt.setInt(2, rowPerPage);
		} else {
			boardSql = "SELECT board_no boardNo, category_name categoryName, board_title boardTitle, create_date createDate FROM board WHERE category_name =? ORDER BY create_date DESC LIMIT ?, ?";
			boardStmt = conn.prepareStatement(boardSql);
			boardStmt.setString(1, categoryName);
			boardStmt.setInt(2, beginRow);
			boardStmt.setInt(3, rowPerPage);
		}
		ResultSet boardRs = boardStmt.executeQuery();
		// 데이터 변환(가공)
		while(boardRs.next()) {
			Board b = new Board();
			b.boardNo = boardRs.getInt("boardNo");
			b.categoryName = boardRs.getString("categoryName");
			b.boardTitle = boardRs.getString("boardTitle");
			b.createDate = boardRs.getString("createDate");
			list.add(b);
		}
		// 데이터베이스 자원들 반환
		boardRs.close();
		boardStmt.close();
		conn.close();
	
		return list;
	}

	// board 전체 행의 수를 반환 메서드
	public int selectboardTotalRow() throws Exception {
		int row = 0;
		Class.forName("org.mariadb.jdbc.Driver");
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/blog";
		String dbuser = "root";
		String dbpw = "java1234";
		
		String sql = "SELECT COUNT(*) cnt FROM board";
		conn = DriverManager.getConnection(dburl, dbuser, dbpw); 
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("cnt");
		}
		return row;
	}
	// boardOne
	public Board selectBoardOne(int boardNo) throws Exception {
		Board board = null;
		Class.forName("org.mariadb.jdbc.Driver");
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/blog";
		String dbuser = "root";
		String dbpw = "java1234";
		
		String sql = "SELECT board_no boardNo, category_name categoryName, board_title boardTitle, board_content boardContent, create_date createDate, update_date updateDate FROM board WHERE board_no=?";
		conn = DriverManager.getConnection(dburl, dbuser, dbpw); 
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, boardNo); // 상세정보를 볼 게시물 번호 받아서 저장
		rs = stmt.executeQuery();
		if(rs.next()) { // true값일때만 커서 옮기면서
			board = new Board(); // board값 담을 새로운 리스트 생성
			board.boardNo = rs.getInt("boardNo");
			board.categoryName = rs.getString("categoryName");
			board.boardTitle =  rs.getString("boardTitle");
			board.boardContent = rs.getString("boardContent");
			board.createDate =  rs.getString("createDate");
			board.updateDate =  rs.getString("updateDate");
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return board;
	}
	public int updateBoard(Board board) throws Exception {
		int row = 0;
		Class.forName("org.mariadb.jdbc.Driver");
		// 데이터베이스 자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String dburl = "jdbc:mariadb://localhost:3306/blog";
		String dbuser = "root";
		String dbpw = "java1234";
		
		String sql = "UPDATE board SET board_title=? boardContent=? WHERE book_no=? AND book_pw=?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.boardTitle);
		stmt.setString(2, board.boardContent);
		stmt.setInt(3, board.boardNo);
		stmt.setString(4, board.boardPw);
		row = stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return row;
	}
}
