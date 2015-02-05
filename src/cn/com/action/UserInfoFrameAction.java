package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.Vo.UserVo;
import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;
import cn.com.view.UserInfoFrame;

public class UserInfoFrameAction implements ActionListener {

	private UserInfoFrame userInfoFrame;
	public UserInfoFrameAction(UserInfoFrame userInfoFrame) {
		this.userInfoFrame =   userInfoFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String butName = e.getActionCommand();
		
		if("ÐÞ¸Ä".equals(butName)) {
			userInfoFrame.updateInfo();
			
		} else if("È·¶¨".equals(butName)) {
			UserVo userVo = userInfoFrame.getUpdateUserVo();
			
			UserDao dao = FactoryDao.getUserDao();
			
			boolean flag = dao.updateUserInfoByUserId(userVo.getUserId(), userVo);
			
			if(flag) {
				userInfoFrame.saveInfo();
				userInfoFrame.dispose();
			}
			
		}

	}

}
