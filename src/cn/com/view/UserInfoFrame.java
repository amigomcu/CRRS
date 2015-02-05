package cn.com.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.com.Vo.UserVo;
import cn.com.action.UserInfoFrameAction;
import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;

public class UserInfoFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel userInfoPanel;
	
	private JTextField userIdText = new JTextField(10);
	private JTextField userNameText = new JTextField(10);
	private JTextField userTelText = new JTextField(10);
	private JTextField userDepartmentText = new JTextField(10);
	private JTextField userPositionText = new JTextField(10);
	private JTextField userPassWordText = new JTextField(10);

	
	private MainFrame frame;
	
	private UserInfoFrameAction action = new UserInfoFrameAction(this);
	public UserInfoFrame(MainFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		
		this.setLayout(new BorderLayout());
		this.add(createUserInfoPanel(), BorderLayout.CENTER);
		this.add(createUpdatePassWordPanel(), BorderLayout.SOUTH);

		//-------------主面板----------------------
		this.setTitle("用户个人信息面板");
		this.setSize(350,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	
	private JPanel updatePassWordPanel;
	private JPanel createUpdatePassWordPanel() {
		if(updatePassWordPanel == null) {
			updatePassWordPanel = new JPanel();
			updatePassWordPanel.add(creatButton("修改"));
			updatePassWordPanel.add(creatButton("确定"));
		}
		return updatePassWordPanel;
	}

	private JButton creatButton(String buttonName) {
		JButton but = new JButton(buttonName);
		but.addActionListener(action);
		return but;
	}

	private JPanel createUserInfoPanel() {
		if(userInfoPanel == null) {
			userInfoPanel = new JPanel(new GridLayout(6,2));
			userInfoPanel.add(new JLabel("用户编号"));
			userInfoPanel.add(userIdText);
			
			userInfoPanel.add(new JLabel("用户姓名"));
			userInfoPanel.add(userNameText);
			
			userInfoPanel.add(new JLabel("用户电话"));
			userInfoPanel.add(userTelText);
			
			userInfoPanel.add(new JLabel("用户所在部门"));
			userInfoPanel.add(userDepartmentText);
			
			userInfoPanel.add(new JLabel("用户职位"));
			userInfoPanel.add(userPositionText);
			
			userInfoPanel.add(new JLabel("用户密码"));
			userInfoPanel.add(userPassWordText);
		}
		
		fillUserTotalInfo();
		return userInfoPanel;
	}

	private void fillUserTotalInfo() {

		int userId = frame.getCurrentUserId();
		UserDao userDao = FactoryDao.getUserDao();
		UserVo userVo = userDao.findUserByUserId(userId);
		
		userIdText.setText(userVo.getUserId()+"");
		userNameText.setText(userVo.getUserName());
	    userTelText.setText(userVo.getUserTelphoneNo());
	    userDepartmentText.setText(userVo.getUserDepartment());
		userPositionText.setText(userVo.getUserPosition());
		userPassWordText.setText(userVo.getUserPassWord());
		
		
		userIdText.setEditable(false);
		userNameText.setEditable(false);
	    userTelText.setEditable(false);
	    userDepartmentText.setEditable(false);
		userPositionText.setEditable(false);
		userPassWordText.setEditable(false);
		
		
	}
	/*
	 * 修改前设置为可编辑
	 */
	public void updateInfo() {
		userNameText.setEditable(true);
	    userTelText.setEditable(true);
	    userDepartmentText.setEditable(true);
		userPositionText.setEditable(true);
		userPassWordText.setEditable(true);
		
	}
	/*
	 * 改回不可编辑
	 */
	public void saveInfo() {
		userNameText.setEditable(false);
	    userTelText.setEditable(false);
	    userDepartmentText.setEditable(false);
		userPositionText.setEditable(false);
		userPassWordText.setEditable(false);
		
	}
	
	public UserVo getUpdateUserVo() {
		UserVo userVo = frame.getCurrentUser();
		
		userVo.setUserName(userNameText.getText().trim());
		userVo.setUserDepartment(userDepartmentText.getText().trim());
		userVo.setUserPassWord(userPassWordText.getText().trim());
		userVo.setUserTelphoneNo(userTelText.getText().trim());
		userVo.setUserPosition(userPositionText.getText().trim());
		
		return userVo;
	}

//	public static void main(String[] args) {
//		new UserInfoFrame(new MainFrame(1));
//		 
//	}

}
