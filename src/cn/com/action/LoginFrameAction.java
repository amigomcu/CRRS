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

		if ("����".equals(buttonName)) {
			loginFrame.resetText();

		} else if ("��½".equals(buttonName)) {
			int userId = loginFrame.getUserId();
			String userPassWord = loginFrame.getUserPassWord();

			if (userId == 0) {
				JOptionPane.showMessageDialog(null, "����ȷ�����˺�");
				return;
			}

			if (userPassWord == null) {
				JOptionPane.showMessageDialog(null, "����ȷ��������");
				return;
			}

			UserDao userDao = FactoryDao.getUserDao();
			
			boolean flag = userDao.findUser(userId, userPassWord);
			if (flag) {
				UserVo userVo = userDao.findUserByUserId(userId);
				loginFrame.setUserVo(userVo);
				new MainFrame(loginFrame);

				loginFrame.dispose(); // �رյ�½���档������

			} else {
				JOptionPane.showMessageDialog(null, "�����˺Ż��������Ƿ�����");
			}

		}
	}

}
