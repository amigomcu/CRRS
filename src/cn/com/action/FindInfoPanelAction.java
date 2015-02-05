package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import cn.com.dao.ConferenceRecordDao;
import cn.com.dao.FactoryDao;
import cn.com.view.functinPanel.FindInfoPanel;

public class FindInfoPanelAction implements ActionListener {

	private FindInfoPanel findInfoPanel;

	public FindInfoPanelAction(FindInfoPanel findInfoPanel) {
		this.findInfoPanel = findInfoPanel;
	}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent e) {

		String buttonName = e.getActionCommand();

		String findInfoStr = findInfoPanel.getFindText();
		JComboBox comBox = findInfoPanel.getComboBox();
		String boxName = comBox.getSelectedItem().toString();

		ConferenceRecordDao recordDao = FactoryDao.getConferenceRecordDao();

		Vector data = new Vector();
		if ("用户编号".equals(boxName)) {// 只能是数字[6位以内的正整数]
			data.clear();

			if (!findInfoStr.matches("^\\+?[1-9][0-9]{0,5}$")) {
				findInfoPanel.setModel(data);
				return;
			}

			int userId = Integer.parseInt(findInfoStr);

			data = recordDao.getDetailRecordInfoByUserId(userId);

		} else if ("用户姓名".equals(boxName)) {
			data.clear();

			String userName = findInfoStr;
			data = recordDao.getDetailRecordInfoByUserName(userName);

		} else if ("会议室编号".equals(boxName)) {
			data.clear();

			if (!findInfoStr.matches("^\\+?[1-9][0-9]{0,5}$")) {
				findInfoPanel.setModel(data);
				return;
			}

			int conferenceId = Integer.parseInt(findInfoStr);

			data = recordDao.getDetailRecordInfoByConferenceId(conferenceId);

		} else if ("会议日期".equals(boxName)) {
			data.clear();

			if (!findInfoStr
					.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$")) {
				findInfoPanel.setModel(data);
				return;
			}

			String DateStr = findInfoStr;
			data = recordDao.getDetailRecordInfoByConferenceData(DateStr);
		} else if ("会议室地点".equals(boxName)) {

			data.clear();

			String ConferenceLocation = findInfoStr;
			/*
			 * 模糊查询
			 */
			data = recordDao
					.getDetailRecordInfoByConferenceLocation(ConferenceLocation);

		}

		if ("查询".equals(buttonName)) {
			findInfoPanel.setModel(data);

			if (data.size() <= 0) {
				JOptionPane.showMessageDialog(null, "没有相关信息");
				return;
			}
		}

	}

}
