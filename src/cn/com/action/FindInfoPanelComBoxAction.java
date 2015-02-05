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

		if ("用户编号".equals(boxName)) {
			findInfoPanel.setFindText("0");

		} else if ("用户姓名".equals(boxName)) {
			findInfoPanel.setFindText("");

		} else if ("会议室编号".equals(boxName)) {
			findInfoPanel.setFindText("0");

		} else if ("会议日期".equals(boxName)) {
			String dateStr = getCurrentDateStr();
			findInfoPanel.setFindText(dateStr);

		} else if ("会议室地点".equals(boxName)) {
			findInfoPanel.setFindText("");

		}

	}

	private String getCurrentDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = f.format(new Date());

		return currentDateStr;

	}

}
