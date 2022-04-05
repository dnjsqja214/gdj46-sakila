package dao;
import vo.*;
import java.sql.*;
import java.util.*;
import util.DBUtil;

public class FilmListDao {
	public List<FilmList> selectFilmListByPage(int beginRow, int rowPerPage) {
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql= "SELECT fid, title, description, category, price, length,rating, actors FROM film_list ORDER BY fid LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs=stmt.executeQuery();
			while(rs.next()) {
				FilmList filmList = new FilmList();
				filmList.setFid(rs.getInt("fid"));
				filmList.setTitle(rs.getString("title"));
				filmList.setDescription(rs.getString("description"));
				filmList.setCategory(rs.getString("category"));
				filmList.setPrice(rs.getDouble("price"));
				filmList.setLength(rs.getInt("length"));
				filmList.setRating(rs.getString("rating"));
				filmList.setActors(rs.getString("actors"));
				list.add(filmList);
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
	public int filmTotalRow() throws Exception{
		int row = 0;
		Connection conn = null;
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		String sql="SELECT COUNT(*) cnt FROM film_list";
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
