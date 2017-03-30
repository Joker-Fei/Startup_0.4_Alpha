package com.syf.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.syf.dao.UpdateDao;
import com.syf.model.Update;
import com.syf.util.DBUtil;

public class UpdateDaoImpl implements UpdateDao{
	DBUtil dbUtil = new DBUtil();
	@Override
	public int find(Update update) {
		String sql = "select * from _update where version=?";
	    Object[] params = { update.getVersion() };
	    if (findSingleBySQL(sql, params) != null) {
	      return 1;
	    }
	    return 0;
	}
	public Update findSingleBySQL(String sql, Object[] params){
	    ResultSet rs = this.dbUtil.executeQuery(sql, params);
	    try {
	      if (rs.next()) {
	    	  Update update = new Update(
	          rs.getString("version"));
	        return update;
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      this.dbUtil.closeAll(); 
	      } 
	    this.dbUtil.closeAll();
	    return null;
	  }
	@Override
	public Update findByVersion(Update update) {
		 String sql = "select * from _update where version=? ";
		  Object[] params = { update.getVersion() };
		  return findUpdate(sql, params);
	}
	public Update findUpdate(String sql, Object[] params){
	    ResultSet rs = this.dbUtil.executeQuery(sql, params);
	    try {
	      if (rs.next()) {
	    	  Update update = new Update(
	          rs.getString("version"),rs.getString("update_info"),rs.getString("url"));
	        return update;
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      this.dbUtil.closeAll(); 
	      } 
	    this.dbUtil.closeAll();
	    return null;
	  }
	@Override
	public Update findNewVersion() {
		  String sql = "select * from _update where id= (select max(id) from _update) ";
		  Object[] params = { };
		  return findUpdate(sql, params);
	}

}
