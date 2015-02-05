package cn.com.dao;

import java.util.Vector;

import cn.com.Vo.UserVo;

public interface UserDao extends DAO {

	// 通过 用户 ID 查找 UserVo
	public UserVo findUserByUserId(int userId);

	// 通过用户 ID 更新数据库中的用户信息 UserVo
	public boolean updateUserInfoByUserId(int userId, UserVo userVo);

	// 通过用户 ID 和 用户 密码判断是否存在此用户
	public boolean findUser(int userId, String userPassWord);
	
	//得到当前最大用户ID号
	public int findMaxUserID();
	
	//通过用户Vo增添用户
	public boolean addUserByUserVo(UserVo userVo);

	//通过用户姓名模糊查询用户
	@SuppressWarnings("rawtypes")
	public Vector findUserInfoByNameLike(String stuName);

	//通过用户编号删除用户  注意先删除记录
	public void delUserInfobyId(int delUserId);

}
