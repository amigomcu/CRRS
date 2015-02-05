package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.com.Vo.UserVo;
import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;
import cn.com.view.LoginFrame;
import cn.com.view.MainFrame;

public class LoginFrameAction implements ActionListener {

	private LoginFrame loginFrame;

	public LoginFrameAction(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public void actionPerformed(ActionEvent e) {
		String buttonName = e.getActionCommand();

		if ("重置".equals(buttonName)) {
			loginFrame.resetText();

		} else if ("登陆".equals(buttonName)) {
			int userId = loginFrame.getUserId();
			String userPassWord = loginFrame.getUserPassWord();

			if (userId == 0) {
				JOptionPane.showMessageDialog(null, "请正确输入账号");
				return;
			}

			if (userPassWord == null) {
				JOptionPane.showMessageDialog(null, "请正确输入密码");
				return;
			}

			UserDao userDao = FactoryDao.getUserDao();
			
			boolean flag = userDao.findUser(userId, userPassWord);
			if (flag) {
				UserVo userVo = userDao.findUserByUserId(userId);
				loginFrame.setUserVo(userVo);
				new MainFrame(loginFrame);

				loginFrame.dispose(); // 关闭登陆界面。。。。

			} else {
				JOptionPane.showMessageDialog(null, "请检查账号或者密码是否有误");
			}

		}
	}

}
