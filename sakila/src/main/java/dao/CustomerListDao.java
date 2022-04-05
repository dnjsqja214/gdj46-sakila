package dao;

import java.sql.*;
import java.util.*;
import vo.*;

import util.DBUtil;

public class CustomerListDao {
	public List<CustomerList> selectCustomerListBypage(int begin, int rowPerPage) {
		List<CustomerList> list = new ArrayList<CustomerList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql ="SELECT id , name , address, `zip code` zipCode, phone, city, country, notes, sid FROM customer_list ORDER BY id LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, begin);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				CustomerList customer = new CustomerList();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setZipCode(rs.getString("zipCode"));
				customer.setPhone(rs.getString("phone"));
				customer.setCity(rs.getString("city"));
				customer.setCountry(rs.getString("country"));
				customer.setNotes(rs.getString("notes"));
				customer.setSid(rs.getInt("sid"));
				list.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스 자원 반환
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int selectCustomerListTotalRow() throws Exception {
		int row = 0;
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM customer_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row= rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 데이터베이스 자원 반환
			try {
				rs.close();
				stmt.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
