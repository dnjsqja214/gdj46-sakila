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
	// 합계
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
	public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String categoryName, String rating, double price, int length, String title, String actor) {		
			List<FilmList> list = new ArrayList<FilmList>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DBUtil.getConnection();
			//동적쿼리 
			try {
			String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list where title LIKE ? AND actors LIKE ?";
			if (categoryName.equals("") && rating.equals("") && price==-1 && length ==-1) {
				sql +=" ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}
			// 4개중 하나만 선택했을때 
			  else if (categoryName.equals("") && rating.equals("") && price==-1 && length !=-1) { // length만 입력
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1){
					sql += " AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if (categoryName.equals("") && rating.equals("") && price!=-1 && length ==-1) { //price
				sql += " AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!categoryName.equals("") && rating.equals("") && price ==-1 && length ==-1) { //카테고리만 입력
				sql += " AND category=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (categoryName.equals("") && !rating.equals("") && price==-1 && length ==-1) { //rating 값만 넣었을때
				sql += " AND rating=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} // 4개중 2개를 입력했을때
			  else if (categoryName.equals("") && rating.equals("") && price != -1 && length !=-1) { // rating, length
				if(length ==0 ) {
				sql += " AND length<60 AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
				sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (categoryName.equals("") && !rating.equals("") && price==-1 && length!=-1){ //rating과 length값만 입력
				if (length ==0) {
				sql += " AND rating=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
				sql += " AND rating=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (categoryName.equals("") && !rating.equals("") && price !=-1 && length ==-1) { // rating, price
				sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!categoryName.equals("") && rating.equals("") && price ==-1 && length !=-1) { // category,length만 입력
				if(length == 0) {
					sql += " AND category=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!categoryName.equals("") && rating.equals("") && price !=-1 && length ==-1) { // category랑 price입력
				sql += " AND category=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!categoryName.equals("") && !rating.equals("") && price ==-1 && length ==-1) { //category,rating 입력
				sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} // 4개중 3개만 입력했을때
			  else if (!categoryName.equals("") && rating.equals("") && price !=-1 && length !=-1) { //rating 뺴고 다입력
				if (length == 0) {
					sql += " AND category=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if  (length ==1) {
					sql += " AND category=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!categoryName.equals("") && !rating.equals("") && price ==-1 && length !=-1) { //price 뺴고 다입력
				if (length == 0) {
					sql += " AND category=? AND rating=? length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? length>=60 ORDER By fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!categoryName.equals("") && !rating.equals("") && price !=-1 && length ==-1) { //length 뺴고 다입력
				sql += " AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			} else if (categoryName.equals("") && !rating.equals("") && price !=-1 && length !=-1) { 
				if (length ==0) {
					sql +=" AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!categoryName.equals("") && !rating.equals("") && price !=-1 && length !=-1) { //다입력했을떄
				if (length == 0) {
					sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			}
			rs =stmt.executeQuery();
			 while (rs.next()) {
				 FilmList f = new FilmList();
				 f.setFid(rs.getInt("fid"));
				 f.setTitle(rs.getString("title"));
				 f.setDescription(rs.getString("description"));
				 f.setCategory(rs.getString("category"));
				 f.setPrice(rs.getDouble("price"));
				 f.setLength(rs.getInt("length"));
				 f.setRating(rs.getString("rating"));
				 f.setActors(rs.getString("actors"));
				 list.add(f);
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

	public List<Double> selectFilmPriceList() {
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getDouble("price"));
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
	public int searchTotalRow (String categoryName, String rating, double price, int length, String title, String actor) {
		int	totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			String sql ="SELECT count(*) cnt FROM film_list where title LIKE ? AND actors LIKE ?";
			if (categoryName.equals("") && rating.equals("") && price==-1 && length ==-1) {
			 stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
			} else if (categoryName.equals("") && rating.equals("") && price==-1 && length !=-1) {
				if (length == 0) {
					sql +=" AND length<60";
				} else if (length == 1) {
					sql +=" AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
			} else if (categoryName.equals("") && rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND price =?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
			} else if (categoryName.equals("") && rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND price =? AND length<60";
				} else if (length == 1) {
					sql += " AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
			} else if (categoryName.equals("") && !rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
			} else if (categoryName.equals("") && !rating.equals("") && price ==-1 && length !=-1) {
				if (length == 0) {
					sql += " AND rating=? AND length<60";
				} else if (length == 1) {
					sql += " AND rating=? AND length<60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
			} else if (categoryName.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND rating=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
			} else if (categoryName.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND rating=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND rating=? AND price=? AND length>=60"; 
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
			} else if (!categoryName.equals("") && rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND category=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
			} else if (!categoryName.equals("") && rating.equals("") && price ==-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND length>=60";
				} 
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
			} else if (!categoryName.equals("") && rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND category=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setDouble(4,price);
			} else if (!categoryName.equals("") && rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setDouble(4,price);
			} else if (!categoryName.equals("") && !rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND category=? AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4,rating);
			}  else if (!categoryName.equals("") && !rating.equals("") && price ==-1 && length !=-1) {
				if (length ==0) {
					sql += " AND category=? AND rating=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4,rating);
			} else if (!categoryName.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND category=? AND rating=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4,rating);
				stmt.setDouble(5, price);
			} else if (!categoryName.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND rating=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, categoryName);
				stmt.setString(4,rating);
				stmt.setDouble(5, price);
			}

			rs =stmt.executeQuery();
			 if(rs.next()) {
				 totalCount = rs.getInt("cnt");
			 } 
		}catch (SQLException e) {
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
