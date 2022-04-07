package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import util.DBUtil;

public class RentalDao {
   public List<Map<String, Object>> selectRentalSearchList(int beginRow,int rowPerPage, int storeId, String customerName, String beginDate, String endDate) {
	  // 디버깅
	  System.out.println(storeId+"<--storeId");
	  System.out.println(customerName+"<--customerName");
	  System.out.println(beginDate+"<--beginDate");
	  System.out.println(endDate+"<--endDate");
	  System.out.println(rowPerPage+"rowPerPage");
	  
	  
      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      conn = DBUtil.getConnection();
      try {
         String sql = "SELECT r.*,"
                     + "         CONCAT(c.first_name,' ',c.last_name) cName,"
                     + "            s.store_id storeId,"
                     + "            i.film_id,"
                     + "         f.title"
                     + "    FROM rental r INNER JOIN customer c"
                     + "    ON r.customer_id = c.customer_id"
                     + "    INNER JOIN staff s"
                     + "    ON r.staff_id = s.staff_id"
                     + "         INNER JOIN inventory i"
                     + "         ON r.inventory_id = i.inventory_id"
                     + "            INNER JOIN film f"
                     + "           ON i.film_id = f.film_id"
                     + " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
                   
         if(storeId==0 && beginDate.equals("") && endDate.equals("")){ // 셋다 입력이 안될때
				sql += "ORDER BY rental_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
       
         } else if(storeId != 0 && beginDate.equals("")&& endDate.equals("")) { //store만 입력했을때
				sql += "AND s.store_id=? ORDER BY rental_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
     
         } else if(storeId == 0 && !beginDate.equals("") && !endDate.equals("")) { // date만 입력된 경우
				sql += "AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') ORDER BY rental_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
       
       
         } else if(storeId != 0 && !beginDate.equals("")&& !endDate.equals("")) { // 가게랑 날짜를 입력했을때
				sql += "AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') ORDER BY rental_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
        
         }
          rs = stmt.executeQuery();
          while(rs.next()) {
				Map<String, Object> map = new HashMap<> (); // 다형성
				map.put("rentalId", rs.getInt("rental_id"));
				map.put("rentalDate", rs.getString("rental_date"));
				map.put("inventoryId", rs.getInt("inventory_id"));
				map.put("customerId", rs.getInt("customer_id"));
				map.put("returnDate", rs.getString("return_date"));
				map.put("staffId", rs.getInt("staff_id"));
				map.put("lastUpdate", rs.getString("last_update"));
				map.put("customerName", rs.getString("cName"));
				map.put("storeId", rs.getInt("storeid"));
				map.put("filmId", rs.getString("film_id"));
				map.put("title", rs.getString("title"));
				list.add(map);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB자원 해지 - try절에서 예외가 발생하면 자원해지 못한상태에서 코드가 종료된다. finally절이 필요
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
   }
   public int RentalTotalRow(int storeId, String customerName, String beginDate, String endDate) {
		int	totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			String sql = "SELECT count(*) cnt FROM rental r INNER JOIN customer c ON r.customer_id = c.customer_id INNER JOIN staff s ON r.staff_id = s.staff_id INNER JOIN inventory i ON r.inventory_id = i.inventory_id INNER JOIN film f ON i.film_id = f.film_id WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
              
			if(storeId==0 && beginDate.equals("") && endDate.equals("")){ // 셋다 입력이 안될때
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+customerName+"%");
		  
		    } else if(storeId != 0 && beginDate.equals("")&& endDate.equals("")) { //store만 입력했을때
					sql += "AND s.store_id=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+customerName+"%");
					stmt.setInt(2, storeId);
		
		    } else if(storeId == 0 && !beginDate.equals("") && !endDate.equals("")) { // date만 입력된 경우
					sql += "AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') ";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+customerName+"%");
					stmt.setString(2, beginDate);
					stmt.setString(3, endDate);
		  
		  
		    } else if(storeId != 0 && !beginDate.equals("")&& !endDate.equals("")) { // 가게랑 날짜를 입력했을때
					sql += "AND r.rental_date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+customerName+"%");
					stmt.setInt(2, storeId);
					stmt.setString(3, beginDate);
					stmt.setString(4, endDate);
		    }
		
			rs =stmt.executeQuery();
			 if(rs.next()) {
				 totalCount = rs.getInt("cnt");
				 System.out.println(totalCount+"<--totalCount");
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
			return totalCount;
			}
}