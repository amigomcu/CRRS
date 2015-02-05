package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;

import cn.com.view.functinPanel.FindInfoPanel;

public class FindInfoPanelComBoxAction implements ActionListener {

	private FindInfoPanel findInfoPanel;

	public FindInfoPanelComBoxAction(FindInfoPanel findInfoPanel) {
		this.findInfoPanel = findInfoPanel;
	}

	public void actionPerformed(ActionEvent arg0) {

		@SuppressWarnings("rawtypes")
		JComboBox comBox = findInfoPanel.getComboBox();

		String boxName = comBox.getSelectedItem().toString();
		System.out.println(boxName);

		if ("�û����".equals(boxName)) {
			findInfoPanel.setFindText("0");

		} else if ("�û�����".equals(boxName)) {
			findInfoPanel.setFindText("");

		} else if ("�����ұ��".equals(boxName)) {
			findInfoPanel.setFindText("0");

		} else if ("��������".equals(boxName)) {
			String dateStr = getCurrentDateStr();
			findInfoPanel.setFindText(dateStr);

		} else if ("�����ҵص�".equals(boxName)) {
			findInfoPanel.setFindText("");

		}

	}

	private String getCurrentDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = f.format(new Date());

		return currentDateStr;

	}

}
