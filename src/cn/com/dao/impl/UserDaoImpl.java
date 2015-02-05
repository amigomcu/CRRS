package cn.com.dao.impl;

import java.util.ArrayList;
import java.util.Vector;

import cn.com.Vo.UserVo;
import cn.com.dao.UserDao;
import cn.com.util.SqlHelper;

public class UserDaoImpl implements UserDao {

	/*
	 * 通过用户 ID 编号查找用户信息
	 */
	public UserVo findUserByUserId(int id) {
		UserVo userVo = new UserVo();

		Integer userId = id;
		String sql = "select * from userInfo where user_id = ?";
		String[] parameters = { userId.toString() };

		ArrayList<Object[]> users = SqlHelper.executeQuery(sql, parameters);
		
		//System.out.println(users.size());
		
		Object[] objects = users.get(0);

		userVo.setUserId(Integer.parseInt(objects[0].toString()));
		userVo.setUserName(objects[1].toString());
		userVo.setUserPassWord(objects[2].toString());
		userVo.setUserTelphoneNo(objects[3].toString());
		userVo.setUserDepartment(objects[4].toString());
		userVo.setUserPosition(objects[5].toString());

		// System.out.println(userVo.toString());

		return userVo;
	}

	@Override
	public boolean updateUserInfoByUserId(int userId, UserVo userVo) {

		String sql = "update userInfo set user_name = ? , user_password = ? , user_telephoneno = ?,"+ 
                     " user_department = ? , user_position = ? where user_id = ?";
		String[] parameters = {
				userVo.getUserName(),
				userVo.getUserPassWord(),
				userVo.getUserTelphoneNo(),
				userVo.getUserDepartment(),
				userVo.getUserPosition(),
				Integer.valueOf(userId).toString()
				};
		int num = SqlHelper.executeNonQuery(sql, parameters);
		if(num > 0) {
			return true;
		}
		return false;
	}

	/*
	 * 在确认已经存在该用户的情况下,判断密码是否正确
	 */
	public boolean findUser(int userId, String userPassWord) {
		boolean flag = false;
		UserVo userVo = findUserByUserId(userId);

		if (userVo.getUserPassWord().equals(userPassWord)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public int findMaxUserID() {
		int maxId = 0;
		String sql = "select max (user_id) from userInfo";
		String[] parameters = {};

		ArrayList<Object[]> users = SqlHelper.executeQuery(sql, parameters);
		
		maxId = Integer.parseInt(users.get(0)[0].toString());
		return maxId;
	}

	@Override
	public boolean addUserByUserVo(UserVo userVo) {
		boolean flag = false;
		
//		String sql = "insert into userInfo values(?, 'fz', '123456', '15507482833', '开发部', '软件工程师')";
		String sql = "insert into userInfo values(?, ?, ?, ?, ?, ?)";
		String[] parameters = {
				Integer.valueOf(userVo.getUserId()).toString(),
				userVo.getUserName(),
				userVo.getUserPassWord(),
				userVo.getUserTelphoneNo(),
				userVo.getUserDepartment(),
				userVo.getUserPosition()
		};
       
		int num = SqlHelper.executeNonQuery(sql,parameters);

		if(num == 1) flag = true;
		return flag;
	}
	
	/*
	 * 通过用户姓名模糊查询用户(non-Javadoc)
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector findUserInfoByNameLike(String stuName) {
//		String sql = "select * from userinfo t where user_name like '%fz%'";
		String sql = "select * from userinfo t where user_name like ?";
		String[] parameters = {"%" +stuName+ "%"};

		ArrayList<Object[]> users = SqlHelper.executeQuery(sql, parameters);
		
		Vector Data = new Vector();
		for(int i = 0; i < users.size(); i++) {
			Vector rowData = new Vector();
			Object[] ob = users.get(i);
			for(int j = 0; j < 6; j++) {
				rowData.add(ob[j]);
				
			}
			Data.add(rowData);
		}
		return Data;
	}

	/*
	 * (non-Javadoc)
	 * 根据用户编号删除用户
	 * 在此之前先删除用户记录
	 * 
	 */
	public void delUserInfobyId(int delUserId) {
		String sql = "begin delete conferenceRecord where user_id = ?; delete userInfo where user_id = ?; end;";
		String[] parameters = {
				Integer.valueOf(delUserId).toString(),
				Integer.valueOf(delUserId).toString()
				};
		SqlHelper.executeNonQuery(sql, parameters);
		
	}

//	public static void main(String[] args) {
//		UserDaoImpl test = new UserDaoImpl();
//		for (int i = 1; i < 4; i++) {
//			test.findUserByUserId(i);
//			System.out.println(i);
//		}
//		int id = test.findMaxUserID();
//		System.out.println(id);
//		 
//		UserVo userVo = new UserVo(4,"fz4", "123456", "15507482833", "开发部", "算法设计师");
//		
//		test.addUserByUserVo(userVo);
//		System.out.println(test.findMaxUserID());
//		test.findUserInfoByNameLike("fz2");
//		test.delUserInfobyId(3);
//
//	}
//	
	 

}
