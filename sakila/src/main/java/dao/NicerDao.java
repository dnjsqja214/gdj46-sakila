package dao;

import java.sql.*;

import java.util.*;
import vo.*;

import util.DBUtil;

public class NicerDao {
	public List<Nicer> selectNicerListByPage(int beginRow, int rowPerPage) {
		List<Nicer> list = new ArrayList<Nicer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql= "SELECT fid, title, description, category, price, length,rating, actors FROM nicer_but_slower_film_list ORDER BY fid LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Nicer nicer = new Nicer();
				nicer.setFid(rs.getInt("fid"));
				nicer.setTitle(rs.getString("title"));
				nicer.setDescription(rs.getString("description"));
				nicer.setCategory(rs.getString("category"));
				nicer.setPrice(rs.getDouble("price"));
				nicer.setLength(rs.getInt("length"));
				nicer.setRating(rs.getString("rating"));
				nicer.setActors(rs.getString("actors"));
				list.add(nicer);
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
	public int nicerTotalRow() throws Exception{
		int row = 0;
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		String sql="SELECT COUNT(*) cnt FROM nicer_but_slower_film_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row=rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 데이터베이스 자원 반환
			try {
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
