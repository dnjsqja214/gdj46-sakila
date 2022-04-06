package dao;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.StaffList;
public class StaffListDao {
	public List<StaffList> selectStaffList( ) {
		List<StaffList> list = new ArrayList<StaffList>();
		//DB자원준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		//쿼리문 작성
		String sql = "SELECT id, name,address, `zip code`, city, country,sid FROM staff_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				StaffList s = new StaffList();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
				s.setZipCode(rs.getString("zip code"));
				s.setCity(rs.getString("city"));
				s.setCountry(rs.getString("country"));
				s.setSid(rs.getInt("sid"));
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
}
