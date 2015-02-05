package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cn.com.view.MainFrame;
import cn.com.view.panel.CenterCardPanel;

public class WestPanelAction implements ActionListener {

	private MainFrame mainFrame;
	private CenterCardPanel centerCardPanel;

	public WestPanelAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

	}

	public void actionPerformed(ActionEvent e) {
		centerCardPanel = mainFrame.getCenterCardPanel();

		String butName = e.getActionCommand();

		if("简介".equals(butName)) {
			centerCardPanel.show("first");
		}
		else if ("现况".equals(butName)) {
			centerCardPanel.show("total");

		} else if ("查询".equals(butName)) {
			centerCardPanel.show("find");

		} else if ("预约".equals(butName)) {
			centerCardPanel.show("book");

		} else if ("取消".equals(butName)) {
			centerCardPanel.show("cancel");
		} else if("管理".equals(butName)) {
			if(mainFrame.getCurrentUserId() == 1) {
				centerCardPanel.show("manage");
			} else {
				JOptionPane.showMessageDialog(null, "请以管理员身份登录");

			}		
		}
	}

}
