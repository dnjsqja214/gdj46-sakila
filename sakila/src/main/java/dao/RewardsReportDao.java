package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;

public class RewardsReportDao {
	public Map<String, Object> rewardsReportCall(int purchase, double amount) {
		Map<String,Object> map = new HashMap<String,Object>();
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		Integer count = 0;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call rewards_report(?, ?, ?)}");
			stmt.setInt(1, purchase);
			stmt.setDouble(2, amount);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); 
			}
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	public static void main(String[] args) {
		RewardsReportDao fd = new RewardsReportDao();
		int purchase = 8;
		double amount = 30.00;
		Map<String, Object> map = fd.rewardsReportCall(purchase, amount);
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println("VIP 선정은"+purchase +"번 이상 구매하고"+ amount +"를 쓴 고객이 기준이다 "+count+"명");
		for(int id : list) {
			System.out.println(id);
		}
	}
}
