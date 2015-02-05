package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.com.view.MainFrame;
import cn.com.view.UserInfoFrame;

public class MainFrameMenuItemAction implements ActionListener {

	private MainFrame mainFrame;
	public MainFrameMenuItemAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String itemName = e.getActionCommand();
		
		if("个人信息 ".equals(itemName)) { 
			new UserInfoFrame(mainFrame); 
			
		} else if("退出".equals(itemName)) {
			mainFrame.dispose();
		}
	}

}
