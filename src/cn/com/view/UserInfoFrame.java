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

		//-------------�����----------------------
		this.setTitle("�û�������Ϣ���");
		this.setSize(350,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	
	private JPanel updatePassWordPanel;
	private JPanel createUpdatePassWordPanel() {
		if(updatePassWordPanel == null) {
			updatePassWordPanel = new JPanel();
			updatePassWordPanel.add(creatButton("�޸�"));
			updatePassWordPanel.add(creatButton("ȷ��"));
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
			userInfoPanel.add(new JLabel("�û����"));
			userInfoPanel.add(userIdText);
			
			userInfoPanel.add(new JLabel("�û�����"));
			userInfoPanel.add(userNameText);
			
			userInfoPanel.add(new JLabel("�û��绰"));
			userInfoPanel.add(userTelText);
			
			userInfoPanel.add(new JLabel("�û����ڲ���"));
			userInfoPanel.add(userDepartmentText);
			
			userInfoPanel.add(new JLabel("�û�ְλ"));
			userInfoPanel.add(userPositionText);
			
			userInfoPanel.add(new JLabel("�û�����"));
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
	 * �޸�ǰ����Ϊ�ɱ༭
	 */
	public void updateInfo() {
		userNameText.setEditable(true);
	    userTelText.setEditable(true);
	    userDepartmentText.setEditable(true);
		userPositionText.setEditable(true);
		userPassWordText.setEditable(true);
		
	}
	/*
	 * �Ļز��ɱ༭
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
