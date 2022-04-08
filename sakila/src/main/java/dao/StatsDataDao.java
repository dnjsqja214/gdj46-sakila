package dao;

import java.sql.*;
import java.util.*;


import util.DBUtil;

public class StatsDataDao {
	public List<Map<String,Object>> countryCategory() {
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    // 나라별 가장 선호하는 카테고리
	    String sql = "SELECT t.country,t.categoryName,t.total\r\n"
	    		+ "FROM country co,\r\n"
	    		+ "	(SELECT co.country country,ca.name categoryName,COUNT(*) total\r\n"
	    		+ "		FROM country co\r\n"
	    		+ "		INNER JOIN city ci\r\n"
	    		+ "		ON ci.country_id = co.country_id\r\n"
	    		+ "		INNER JOIN address ad\r\n"
	    		+ "		ON ad.city_id = ci.city_id\r\n"
	    		+ "		INNER JOIN customer cu\r\n"
	    		+ "		ON cu.address_id = ad.address_id\r\n"
	    		+ "		INNER JOIN rental r\r\n"
	    		+ "		ON r.customer_id = cu.customer_id\r\n"
	    		+ "		INNER JOIN inventory i\r\n"
	    		+ "		ON r.inventory_id = i.inventory_id\r\n"
	    		+ "		INNER JOIN film f\r\n"
	    		+ "		ON i.film_id = f.film_id\r\n"
	    		+ "		INNER JOIN film_category fc\r\n"
	    		+ "		ON fc.film_id = f.film_id\r\n"
	    		+ "		INNER JOIN category ca\r\n"
	    		+ "		ON ca.category_id = fc.category_id\r\n"
	    		+ "		GROUP BY co.country, f.title\r\n"
	    		+ "		ORDER BY COUNT(*) DESC) t\r\n"
	    		+ "GROUP BY t.country\r\n"
	    		+ "ORDER BY t.total DESC";
	    try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
			Map<String, Object> m7 = new HashMap<>();
            m7.put("country",rs.getString("country"));
            m7.put("categoryName",rs.getString("categoryName"));
            m7.put("total",rs.getInt("total"));
            list.add(m7);
			}
		} catch (Exception e) {
			e.printStackTrace();;
		} finally {
		}try {
			rs.close();
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}	public List<Map<String,Object>> actorAppearDown() {
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql = "SELECT a.actor_id , CONCAT(a.first_name,' ', a.last_name) NAME,COUNT(*) cnt \r\n"
	    		+ "FROM film_actor f INNER JOIN actor a ON f.actor_id = a.actor_id \r\n"
	    		+ "GROUP BY actor_id ORDER BY cnt desc LIMIT 0,10";

	    try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
			Map<String, Object> m6 = new HashMap<>();
            m6.put("actorId",rs.getInt("actor_id"));
            m6.put("NAME",rs.getString("name"));
            m6.put("cnt",rs.getInt("cnt"));
            list.add(m6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}try {
			rs.close();
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String,Object>> actorAppearUp() {
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql = "SELECT a.actor_id , CONCAT(a.first_name,' ', a.last_name) NAME,COUNT(*) cnt \r\n"
	    		+ "FROM film_actor f INNER JOIN actor a ON f.actor_id = a.actor_id \r\n"
	    		+ "GROUP BY actor_id ORDER BY cnt asc LIMIT 0,10";

	    try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
			Map<String, Object> m6 = new HashMap<>();
            m6.put("actorId",rs.getInt("actor_id"));
            m6.put("NAME",rs.getString("name"));
            m6.put("cnt",rs.getInt("cnt"));
            list.add(m6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}try {
			rs.close();
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> dayOfTheWeek(){
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql="SELECT s.store_id,t.w, case t.w\r\n"
	    		+ "	when 0 then '월'\r\n"
	    		+ "	when 1 then '화'\r\n"
	    		+ "	when 2 then '수'\r\n"
	    		+ "	when 3 then '목'\r\n"
	    		+ "	when 4 then '금'\r\n"
	    		+ "	when 5 then '토'\r\n"
	    		+ "	when 6 then '일'\r\n"
	    		+ "END DAYOFWEEK, t.c\r\n"
	    		+ "FROM(SELECT staff_id,weekday(payment_date) w, COUNT(*) c\r\n"
	    		+ "	FROM payment\r\n"
	    		+ "	GROUP BY staff_id,WEEKDAY(payment_date)) t\r\n"
	    		+ "	INNER JOIN staff s\r\n"
	    		+ "	ON t.staff_id = s.staff_id\r\n"
	    		+ "ORDER BY s.store_id,t.w ASC";
	    try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> m5 = new HashMap<>();
				m5.put("storeId",rs.getInt("store_id"));
				m5.put("dayOfWeek",rs.getString("dayofweek"));
				m5.put("cnt",rs.getInt("c"));
				list.add(m5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		} try {
			rs.close();
			conn.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Map<String, Object>> filmLengthDivision() {
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql ="SELECT t.length2 tl, COUNT(*) cnt \r\n"
	    		+ "FROM(SELECT title, LENGTH, \r\n"
	    		+ "	case when LENGTH<60 then 'less60'\r\n"
	    		+ "		when LENGTH BETWEEN 61 AND 120 then 'between 61 and 120' \r\n"
	    		+ "		when LENGTH between 121 AND 180 then 'between 121 and 180' \r\n"
	    		+ "		ELSE 'more 180'\r\n"
	    		+ "	end LENGTH2\r\n"
	    		+ "FROM film) t\r\n"
	    		+ "GROUP BY t.length2";
	    try {
	    	stmt = conn.prepareStatement(sql);
	    	rs = stmt.executeQuery();
	    	while(rs.next()) {
	    		Map<String, Object> m4 = new HashMap<>();
	    		m4.put("length", rs.getString("tl"));
	    		m4.put("cnt", rs.getInt("cnt"));
	    		list.add(m4);
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
	            rs.close();
	            stmt.close();
	            conn.close();
		}catch (Exception e) {
			 e.printStackTrace();
			}
		}
		return list;
 	}
	public List<Map<String, Object>> amountByCoustomer() {
	      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT p.customer_id customerId,"
	            + "      concat(c.first_name,' ', c.last_name) name,"
	            + "      sum(p.amount) total"
	            + "      FROM payment p INNER JOIN customer c"
	            + "      ON p.customer_id = c.customer_id"
	            + "      GROUP BY p.customer_id" 
	            + "      Having sum(p.amount) >=180"
	            + "      ORDER BY SUM(p.amount) DESC";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("customerId",rs.getInt("customerId"));
	            m.put("name",rs.getString("name"));
	            m.put("total",rs.getInt("total"));
	            list.add(m);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	
	
	public List<Map<String, Object>> filmCountByRentalRate() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT rental_rate, COUNT(*) cnt FROM film GROUP BY rental_rate ORDER BY COUNT(*) DESC";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m2 = new HashMap<>();
	            m2.put("rentalRate",rs.getDouble("rental_rate"));
	            m2.put("cnt",rs.getString("cnt"));
	            list.add(m2);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	}
	
	public List<Map<String, Object>> filmCountByRating() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql="SELECT rating, COUNT(*) cnt FROM film GROUP BY rating ORDER BY COUNT(*) DESC";
	      
	      try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m3 = new HashMap<>();
				m3.put("rating",rs.getString("rating"));
				m3.put("cnt",rs.getInt("cnt"));
				list.add(m3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
	            rs.close();
	            stmt.close();
	            conn.close();
		} catch (SQLException e) {
          e.printStackTrace();
       }
    }
		return list;
	}
	
	
}
