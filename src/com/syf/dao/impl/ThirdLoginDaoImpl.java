package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syf.dao.ThirdLoginDao;
import com.syf.model.ThirdLogin;
import com.syf.model.User;
import com.syf.util.DBUtil;

public class ThirdLoginDaoImpl implements ThirdLoginDao{
	DBUtil dbUtil = new DBUtil();
	public int insertThirdBind(String user_uuid, String third_name,
			String third_id) {
		String sql = "insert into _thirdlogin "
				+ "(uuid,"+third_name +")"
				+ "values(?,?)";
		Object[] params = { user_uuid,third_id};
		return dbUtil.executeUpdate(sql, params);
	}
	public int thirdLogin(String third_name, String third_id) {
			String sql = "select * from _thirdlogin where "+third_name+"=?";
		    Object[] params = {third_id};
		    if (findSingleBySQL(sql, params) != null) {
		      return 1;
		    }
		    return 0;
		}
	private Object findSingleBySQL(String sql, Object[] params) {
		    ResultSet rs = this.dbUtil.executeQuery(sql, params);
		    try {
		      if (rs.next()) {
		        ThirdLogin thirdLogin = new ThirdLogin(
		          rs.getInt("id"), 
		          rs.getString("uuid"), 
		          rs.getString("qq_id"), 
		          rs.getString("weixin_id"), 
		          rs.getString("xinlang_id") 
		          );
		        return thirdLogin;
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      this.dbUtil.closeAll(); 
		      } 
		    
		    this.dbUtil.closeAll();

		    return null;
	}
	public ThirdLogin findUserUuid(String third_name,String third_id) {
		
		String sql="select uuid from _thirdlogin where "+third_name+"=?";
		Object[] params={third_id};
		List<ThirdLogin> thirdLoginList=findBySQL(sql, params);
		if (thirdLoginList != null && thirdLoginList.size() > 0) {
			return thirdLoginList.get(0);
		}
		return null;
	}
	
	
	private List<ThirdLogin> findBySQL(String sql, Object[] params) {
			List<ThirdLogin> list = new ArrayList<ThirdLogin>();
			ResultSet rs = dbUtil.executeQuery(sql, params);
			try {
				while (rs.next()) {
					ThirdLogin thirdLogin = new ThirdLogin(rs.getString("uuid"));
					list.add(thirdLogin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtil.closeAll();
			}
			return list;
	}
	public int findByUserUuid(String user_uuid) {
		  String sql = "select * from _thirdlogin where uuid=?";
		    Object[] params = { user_uuid };
		    if (findSingleBySQL(sql, params) != null) {
		      return 1;
		    }
		    return 0;
	}
	public int updateInfo(String user_uuid, String third_name, String third_id) {
		//update _thirdlogin set qq_id='66222266' where uuid="b37a8938-23ef-4033-a5e6-cdf528ac97d3"
		  String sql ="update _thirdlogin set "+third_name+"='"+third_id+"' where uuid='" + user_uuid+"'";
		  int reslut = this.dbUtil.executeUpdate2(sql);
		  return reslut;
	}
	
	public ThirdLogin findThirdInfo(String user_uuid) {
		String sql="select qq_id,weixin_id,xinlang_id from _thirdlogin where uuid=?";
		Object[] params={user_uuid};
		List<ThirdLogin> thirdLoginList=findBySQL2(sql, params);
		if (thirdLoginList != null && thirdLoginList.size() > 0) {
			return thirdLoginList.get(0);
		}
		return null;
	} 
	private List<ThirdLogin> findBySQL2(String sql, Object[] params) {
		List<ThirdLogin> list = new ArrayList<ThirdLogin>();
		ResultSet rs = dbUtil.executeQuery(sql, params);
		try {
			while (rs.next()) {
				ThirdLogin thirdLogin = new ThirdLogin(rs.getString("qq_id"),rs.getString("weixin_id"),rs.getString("xinlang_id"));
				list.add(thirdLogin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeAll();
		}
		return list;
}
	
 }

	
