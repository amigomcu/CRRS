package cn.com.dao;

import java.util.Vector;

import cn.com.Vo.UserVo;

public interface UserDao extends DAO {

	// ͨ�� �û� ID ���� UserVo
	public UserVo findUserByUserId(int userId);

	// ͨ���û� ID �������ݿ��е��û���Ϣ UserVo
	public boolean updateUserInfoByUserId(int userId, UserVo userVo);

	// ͨ���û� ID �� �û� �����ж��Ƿ���ڴ��û�
	public boolean findUser(int userId, String userPassWord);
	
	//�õ���ǰ����û�ID��
	public int findMaxUserID();
	
	//ͨ���û�Vo�����û�
	public boolean addUserByUserVo(UserVo userVo);

	//ͨ���û�����ģ����ѯ�û�
	@SuppressWarnings("rawtypes")
	public Vector findUserInfoByNameLike(String stuName);

	//ͨ���û����ɾ���û�  ע����ɾ����¼
	public void delUserInfobyId(int delUserId);

}
