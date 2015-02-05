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

		if("���".equals(butName)) {
			centerCardPanel.show("first");
		}
		else if ("�ֿ�".equals(butName)) {
			centerCardPanel.show("total");

		} else if ("��ѯ".equals(butName)) {
			centerCardPanel.show("find");

		} else if ("ԤԼ".equals(butName)) {
			centerCardPanel.show("book");

		} else if ("ȡ��".equals(butName)) {
			centerCardPanel.show("cancel");
		} else if("����".equals(butName)) {
			if(mainFrame.getCurrentUserId() == 1) {
				centerCardPanel.show("manage");
			} else {
				JOptionPane.showMessageDialog(null, "���Թ���Ա��ݵ�¼");

			}		
		}
	}

}
