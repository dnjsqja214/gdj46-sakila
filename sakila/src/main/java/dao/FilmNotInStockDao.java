package dao;

import java.util.*;
import java.sql.*;
import util.DBUtil;

public class FilmNotInStockDao {
	public Map<String, Object> filmNotInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs =  null;
		List<Integer> list = new ArrayList<>();
		Integer count = 0;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call film_not_in_stock(?, ?, ?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				list.add(rs.getInt(1)); // 재고가 없는 inventort_id를 list에 담기 
			}
			count = stmt.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	} 
	public static void main (String[] args) {
		FilmNotInStockDao fn = new FilmNotInStockDao();
			int filmId = 5;
			int storeId = 2;
			Map<String, Object> map = fn.filmNotInStockCall(filmId, storeId);
			List<Integer> list = (List<Integer>)map.get("list");
			int count = (Integer)map.get("count");
			
			System.out.println(filmId + "번 영화는 "+ storeId + "번 가게에서"+ count + "개 빌려줬습니다");
			for(int id : list) {
				System.out.println(id);
			}
	}
}
