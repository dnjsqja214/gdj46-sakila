package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;
import vo.FilmCategory;

public class FilmCategoryDao {
   public List<FilmCategory> selectCategoryList() {
      List<FilmCategory> list = new ArrayList<FilmCategory>();
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      conn = DBUtil.getConnection();
      String sql = "SELECT category_id categoryId, name, last_update lastUpdate FROM category";
      try {
         stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         while(rs.next()) {
        	FilmCategory c = new FilmCategory();
            c.setCategoryId(rs.getInt("categoryId"));
            c.setName(rs.getString("name"));
            c.setLastUpdate(rs.getString("lastUpdate"));
            list.add(c);
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
}