package dao;

import java.sql.*;
import java.util.*;
import vo.*;


import util.DBUtil;

public class SalesByFilmCategoryDao {
	public List<SalesByFilmCategory> selectSalesByFilmCategoryPage(int beginRow, int rowPerPage) {
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT category ,total_sales totalSales FROM sales_by_film_category ORDER BY category LIMIT ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				SalesByFilmCategory s = new SalesByFilmCategory();
				s.setCategory(rs.getString("category"));
				s.setTotalSales(rs.getInt("totalSales"));
				list.add(s);
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
	public int selectSalesByFilmCategoryTotalRow() {
		int row = 0;
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt  = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM sales_by_film_category";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 데이터베이스 자원 반환
			try {
				rs.close();
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
}
