package com.syf.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syf.dao.UserDao;
import com.syf.model.Project;
import com.syf.model.ThirdLogin;
import com.syf.model.User;
import com.syf.util.DBUtil;

public class UserDaoImpl implements UserDao{

	DBUtil dbUtil = new DBUtil();
	
	public int save(User user) {
		String sql = "insert into _user "
				+ "(name,pwd,phone_num,img_url,sex,token,uuid,register_time)"
				+ "values(?,?,?,?,?,?,?,?)";
		Object[] params = { user.getName(),user.getPwd(),user.getPhone_num(),user.getImg_url(),user.getSex(),user.getToken(),user.getUuid(),user.getRegister_time()};
		return dbUtil.executeUpdate(sql, params);
	}

	public boolean checkUserExistByName(String name) {
		return false;
	}

	public List<User> getUsers() {
		return null;
	}
	//查看手机号是否已经注册过
	public int checkPhoneNum(String phone_num) {
		String sql = "select * from _user where phone_num=?";
	    Object[] params = { phone_num };
	    if (findSingleBySQL(sql, params) != null) {
	      return 1;
	    }
	    return 0;
	}

	public User findSingleBySQL(String sql, Object[] params){
    ResultSet rs = this.dbUtil.executeQuery(sql, params);
    try {
      if (rs.next()) {
        User user = new User(
          rs.getInt("id"), 
          rs.getString("name"), 
          rs.getString("phone_num"), 
          rs.getString("skill_id"), 
          rs.getString("focus"), 
          rs.getString("img_url"),
          rs.getInt("info_get"),
          rs.getString("pwd"), 
          rs.getInt("sex"),
          rs.getString("school"), 
          rs.getString("major"), 
          rs.getInt("experience_id"),
          rs.getInt("online"),
          rs.getString("uuid"), 
          rs.getString("new_comment"), 
          rs.getString("token"),
          rs.getDate("register_time"));
        return user;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.dbUtil.closeAll(); 
      } 
    
    this.dbUtil.closeAll();

    return null;
  }

	public User findByPNumberAndPwd(String phone_num, String pwd) {
	  String sql = "select * from _user where phone_num=? and pwd=?";
	  Object[] params = { phone_num, pwd };
	  return findSingleBySQL(sql, params);
	}


	public List<User> findProIdByPhoneNum(String phone_num) {
		//(select group_concat(DISTINCT p.id) from _user u,_project p where  p.user_uuid=(select _user.uuid from _user where _user.phone_num=pp.phone_num)) as project_ids
		String sql="select pp.uuid,pp.token,pp.img_url,pp.name,pp.id,pp.sex "
					+"from _user pp "
					+"where pp.phone_num=?";
		 Object[] params = { phone_num };
		  //return findSingleBySQL(sql, params);
		 return findBySql(sql, params);
	}

	   //查找project_ids的通用方法
		private List<User> findBySql(String sql, Object[] params) {
			
			List<User> list=new ArrayList<User>();
			ResultSet rs=dbUtil.executeQuery(sql, params);
			try {
				while(rs.next()){
					User user=new User(rs.getString("uuid"),rs.getString("token"),rs.getString("img_url"),rs.getString("name"),rs.getInt("id"),rs.getInt("sex"));
					list.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbUtil.closeAll();
			}
			return list;
		}

		public int onlineChange(String phone_num) {
			String sql = "select * from _user where phone_num=?";
			  Object[] params = { phone_num};
			  if (findSingleBySQL2(sql, params) != null) {
				  return 1;
			  }
			  return 0;
		}
		 public User findSingleBySQL2(String sql, Object[] params)
		  {
		    ResultSet rs = this.dbUtil.executeQuery(sql, params);
		    try {
		      if (rs.next()) {
		    	  User location = new User(
		          rs.getString("phone_num"),
		          rs.getInt("online"));
		        return location;
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      this.dbUtil.closeAll(); } this.dbUtil.closeAll();

		    return null;
		  }

		public int insertOnline(User user) {
			  String sql = "insert into _user (phone_num,online) values(?,?)";
			  Object[] params = { user.getPhone_num(), user.getOnline() };
			  return this.dbUtil.executeUpdate(sql, params);
		}

		public int updateOnline(String phone_num,int online) {
			  String sql = "update _user set online='" + online + "'" +" where phone_num=" + phone_num;
			  int iii = this.dbUtil.executeUpdate2(sql);
			  return iii;
		}

	 	public int checkPhoneAndThird(String phone_num, String third_login) {
			String sql = "select t."+third_login+" "
						+"from _thirdlogin t,_user u "
						+"where (select uuid from _user where phone_num=u.phone_num ) =t.uuid and u.phone_num="+phone_num+";";
			//Object[] params = { phone_num };
			ResultSet rs=dbUtil.executeQuery(sql,null);
			try {
				while(rs.next()){
					if(rs.getString(third_login) != null){
						return 1;
				}
				return 0;	
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
		}
	 	//查询我关注的项目
		public User findMyProFocus(String user_id) {
			String sql="select focus,school,skill_id,id,info_get,img_url,phone_num,token from _user where uuid=?";
		Object[] params={user_id};
		List<User> usertList=findBySQL(sql, params);
		if (usertList != null && usertList.size() > 0) {
			return usertList.get(0);
		}
		return null;
		} 
		
		private List<User> findBySQL(String sql, Object[] params) {
						List<User> list = new ArrayList<User>();
						ResultSet rs = dbUtil.executeQuery(sql, params);
						try {
							while (rs.next()) {
								/*User user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("phone_num"),rs.getString("skill_id"),rs.getString("focus"),rs.getString("img_url"),
										rs.getInt("info_get"),rs.getString("pwd"),rs.getInt("sex"),rs.getString("school"),rs.getString("major"),rs.getInt("experience_id"),rs.getInt("online"),rs.getString("uuid"),rs.getString("new_comment"),rs.getString("token"),rs.getDate("register_time"));*/
								User user = new User(rs.getString("focus"),rs.getString("school"),rs.getString("skill_id"),rs.getInt("id"),rs.getInt("info_get"),rs.getString("img_url"),rs.getString("phone_num"),rs.getString("token"));
								list.add(user);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							dbUtil.closeAll();
						}
						return list;
		}
		//添加关注项目
		public int updateProFocus(String user_id, String focusAll) {
			  String sql = "update _user set focus='" + focusAll + "'" +" where uuid='" + user_id+"'";
			  int reslut = this.dbUtil.executeUpdate2(sql);
			  return reslut;
		}
		//修改学校
		public int updateSchool(String user_id, String school) {
			  String sql = "update _user set school='" + school + "'" +" where uuid='" + user_id+"'";
			  int reslut = this.dbUtil.executeUpdate2(sql);
			  return reslut;
		}

		public int updateSkill(String user_id, String skill_id) {
			 String sql = "update _user set skill_id='" + skill_id + "'" +" where uuid='" + user_id+"'";
			  int reslut = this.dbUtil.executeUpdate2(sql);
			  return reslut;
		}

		public int updatePush(String user_id, String push) {
			 String sql = "update _user set info_get='" + push + "'" +" where uuid='" + user_id+"'";
			  int reslut = this.dbUtil.executeUpdate2(sql);
			  return reslut;
		}

		public int checkPhoneNumAndPwd(String user_id, String pwd) {
			  String sql = "select * from _user where uuid=? and pwd=?";
			    Object[] params = { user_id,pwd };
			    if (findSingleBySQL(sql, params) != null) {
			      return 1;
			    }
			    return 0;
		}

		public int changePwd(String user_id, String newPwd) {
			 String sql = "update _user set pwd=? where uuid=?";
			    Object[] params = { newPwd, user_id };
			    int i = this.dbUtil.executeUpdate(sql, params);
			    if (i > 0) {
			      return 1;
			    }
			    return 0;
		}

		public int onlineUpdate(String user_id, String online) {
			 String sql = "update _user set online='" + online + "'" +" where uuid='" + user_id+"'";
			  int reslut = this.dbUtil.executeUpdate2(sql);
			  return reslut;
		}

		//查询我User的UUID
		public User myProFocusInfoByPhone(String phone_num) {
			String sql="select uuid,name from _user where phone_num=?";
			Object[] params={phone_num};
			List<User> usertList=findBySQL2(sql, params);
			if (usertList != null && usertList.size() > 0) {
				return usertList.get(0);
			}
			return null;
		}
				private List<User> findBySQL2(String sql, Object[] params) {
								List<User> list = new ArrayList<User>();
								ResultSet rs = dbUtil.executeQuery(sql, params);
								try {
									while (rs.next()) {
										User user = new User(rs.getString("uuid"),rs.getString("name"));
										list.add(user);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								} finally {
									dbUtil.closeAll();
								}
								return list;
				}

				public int updateThirdLogin(String user_id, String third_name) {
					String sql = "update _thirdlogin set "+third_name+"=null where uuid=?";
				    Object[] params = { user_id };
				    return this.dbUtil.executeUpdate(sql, params);
				}

				public int updatePwd(String phone_num, String pwd) {
					String sql = "update _user set pwd=? where phone_num=?";
				    Object[] params = { pwd,phone_num };
				    return this.dbUtil.executeUpdate(sql, params);
				}

				public int updateInfo(String user_id, String name,String img_url,String sex) {
					String sql = "update _user set name=?,img_url=?,sex=? where uuid=?";
				    Object[] params = { name,img_url,sex,user_id };
				    return this.dbUtil.executeUpdate(sql, params);
				}

				public int tokenUpdate(String user_id, String token) {
					String sql = "update _user set token=? where uuid=?";
				    Object[] params = { token,user_id };
				    return this.dbUtil.executeUpdate(sql, params);
				}

				public List<User> findProjectIdsByUuid(String user_id) {
					String sql="select pp.uuid,pp.token,pp.img_url,pp.name,pp.id,pp.sex,pp.phone_num "
							+"from _user pp "
							+"where pp.uuid=?";
				 Object[] params = { user_id };
				  //return findSingleBySQL(sql, params);
				 return findBySql2(sql, params);
				}

				private List<User> findBySql2(String sql, Object[] params) {
						
						List<User> list=new ArrayList<User>();
						ResultSet rs=dbUtil.executeQuery(sql, params);
						try {
							while(rs.next()){
								User user=new User(rs.getString("uuid"),rs.getString("token"),rs.getString("img_url"),rs.getString("name"),rs.getInt("id"),rs.getInt("sex"),rs.getString("phone_num"));
								list.add(user);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}finally{
							dbUtil.closeAll();
						}
						return list;
				}
}
