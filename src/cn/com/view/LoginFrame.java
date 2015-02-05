package cn.com.view;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import cn.com.Vo.UserVo;
import cn.com.action.LoginFrameAction;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel accountNumberLabel;
	private JLabel accountPassWordLabel;

	private JTextField loginAccountText;
	private JPasswordField loginPassWordText;

	private LoginFrameAction action = new LoginFrameAction(this);
	
	private UserVo userVo = null;

	public LoginFrame() {
		init();
	}

	private void init() {

		this.add(createPanel());

		// ---------------设置主面板------------------
		//this.setTitle("登录界面");
		this.setSize(400, 300);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel createPanel() {
		if (panel == null) {
			panel = new JPanel(null);
			panel.setBorder(BorderFactory.createTitledBorder(null, "会议室预订系统登录",
					TitledBorder.CENTER, TitledBorder.TOP, new Font(
							Font.DIALOG_INPUT, Font.PLAIN, 20)));

			panel.setBounds(20, 20, 340, 220);

			setLabel();
			panel.add(accountNumberLabel);
			panel.add(accountPassWordLabel);

			panel.add(createNumber());
			panel.add(createPassword());
			panel.add(createButton("登陆"));
			panel.add(createButton("重置"));
		}
		return panel;
	}

	private JTextField createNumber() {
		if (loginAccountText == null) {
			loginAccountText = new JTextField();
			loginAccountText.setBounds(125, 65, 120, 25);
		}
		return loginAccountText;
	}

	private JPasswordField createPassword() {
		if (loginPassWordText == null) {
			loginPassWordText = new JPasswordField();
			loginPassWordText.setBounds(125, 105, 120, 25);
		}
		return loginPassWordText;
	}

	private void setLabel() {

		accountNumberLabel = new JLabel("账号:");
		accountNumberLabel.setBounds(70, 60, 100, 30);
		accountNumberLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));

		accountPassWordLabel = new JLabel("密码:");
		accountPassWordLabel.setBounds(70, 100, 100, 30);
		accountPassWordLabel
				.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
	}

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);

		if ("登陆".equals(buttonName)) {

			but.setBounds(100, 150, 60, 25);
		} else if ("重置".equals(buttonName)) {
			but.setBounds(170, 150, 60, 25);
		}
		but.addActionListener(action);
		return but;
	}

	/*
	 * 返回账号
	 * 
	 * 只能是数字[6位以内的正整数]
	 */
	public int getUserId() {
		int userId = 0;
		String str = loginAccountText.getText().trim();

		if (str.matches("^\\+?[1-9][0-9]{0,5}$")) {
			userId = Integer.parseInt(str);
		}
		return userId;
	}

	/*
	 * 返回密码
	 * 
	 * 
	 * 密码为 6-18 位的数字或字母组成 可以以字母或者非零的数字开头
	 */
	public String getUserPassWord() {
		String userPassWord = null;
		String strPw = new String(loginPassWordText.getPassword());
		strPw = strPw.trim();

		if (strPw.matches("^[a-zA-Z1-9]\\w{5,17}$")) {
			userPassWord = strPw;
		}
		return userPassWord;
	}

	/*
	 * 重置输入
	 */
	public void resetText() {
		loginAccountText.setText("");
		loginPassWordText.setText("");
	}
	

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public static void main(String[] args) {
		
		try {
 
			String lookAndFeel = "org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel";
	        
			UIManager.setLookAndFeel(lookAndFeel) ;
	      } catch (Exception e1) {
	        System.out.println("Substance Raven Graphite failed to initialize");
	      }
	      SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	new LoginFrame();	          
	        }
	      });
	}

}
