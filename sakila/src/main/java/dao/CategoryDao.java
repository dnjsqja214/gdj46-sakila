package dao;
import java.sql.*;
import java.util.*;

public class CategoryDao {
   //생성자 메서드
   public CategoryDao() {}
   
   //카테고리 이름별 목차 개수 구하기 ===============================
   public ArrayList<HashMap<String, Object>>  categoryCnt() throws Exception {
      
      // 마리아 db 연동
      Class.forName("org.mariadb.jdbc.Driver");
      System.out.println("드라이버 로딩 성공");
      
      Connection conn = null;
      String dburl = "jdbc:mariadb://localhost:3306/blog"; // 주소저장
      String dbuser = "root"; // 아이디 저장
      String dbpw = "java1234"; // 비번 저장
      conn = DriverManager.getConnection(dburl,dbuser,dbpw);
      System.out.println(conn+"<--conn"); // 디버깅 코드
      
      //select문 문자열로 변수에 저장
      String categorySql = "select category_name categoryName, COUNT(*) cnt from board group by category_name";  // category_name과 목차별 합계(cnt)
      
      PreparedStatement categoryStmt = conn.prepareStatement(categorySql); // category_name 들고오기
      System.out.println(categoryStmt + "<--categoryStmt"); // 디버깅

      ResultSet categoryRs = categoryStmt.executeQuery(); // category_name 들고온 값 categoryRs에 저장 
      System.out.println(categoryRs + "<--categoryRs"); // 디버깅
   
      // result 값  arraylist에 넣기
      ArrayList<HashMap<String, Object>> categoryList = new ArrayList<HashMap<String, Object>> (); // category전체 리스트 저장하기 위한 arraylist 만들기
      while(categoryRs.next()) { // 돌리면서 true 값일때마다 categoryRs에 저장 
         HashMap<String, Object> map = new HashMap<String, Object> ();
         map.put("categoryName",categoryRs.getString("categoryName"));
         map.put("cnt", categoryRs.getInt("cnt"));
         categoryList.add(map);
      }
      
      //데이터베이스 자원들 반환
      categoryRs.close();
      categoryStmt.close();
      conn.close();
      
      return categoryList;
   
   }
   public ArrayList<String> categoryName(String categoryName) throws Exception {
	   ArrayList<String> categoryList = new ArrayList<String>();
	   // 마리아 db 연동
	        Class.forName("org.mariadb.jdbc.Driver");
	        System.out.println("드라이버 로딩 성공");
	      
	        Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
	       
			String dburl = "jdbc:mariadb://localhost:3306/blog"; // 주소저장
	        String dbuser = "root"; // 아이디 저장
	        String dbpw = "java1234"; // 비번 저장
	        conn = DriverManager.getConnection(dburl,dbuser,dbpw);
	        System.out.println(conn+"<--conn"); // 디버깅 코드
	        String sql = "SELECT category_name categoryName FROM board GROUP BY category_name ORDER BY category_name asc";
			
			conn = DriverManager.getConnection(dburl, dbuser, dbpw); // DB 접속
			stmt = conn.prepareStatement(sql); // 쿼리 작성
			
			rs = stmt.executeQuery(); // 쿼리 저장
			
			while(rs.next()) {
				categoryList.add(rs.getString("categoryName")); // list에 카테고리 이름 저장
			}
			
			return categoryList;
   }
   
}