package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.com.Vo.ConferenceRoomVo;
import cn.com.view.functinPanel.TotalConferenceInfoPanel;

public class TotolInfoPanelFindFreeTimeAction implements ActionListener {

	private TotalConferenceInfoPanel totalConferenceInfoPanel;

	public TotolInfoPanelFindFreeTimeAction(
			TotalConferenceInfoPanel totalConferenceInfoPanel) {

		this.totalConferenceInfoPanel = totalConferenceInfoPanel;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e) {

		String buttonName = e.getActionCommand();
		if ("���ڲ�ѯ".equals(buttonName)) {

			 
			String dateStr = totalConferenceInfoPanel.getCheckDate();

			/*
			 * ���ڸ�ʽ�Ϸ���
			 */

			Calendar cal = getCalendar(dateStr);
			int differ = checkDiffer(cal); // Ԥ����¼ cal �뵱ǰʱ�������������

			if (differ < 0 || differ >= 14) {
				JOptionPane.showMessageDialog(null, "ֻ�ܲ�ѯδ�����ܿ���ʱ���,�����������ѯʱ��");

				totalConferenceInfoPanel.clearFreeTimeModel();
				return;
			}

			// �õ�����ʱ���
			HashMap<Integer, ConferenceRoomVo> allConferenceRoomInfo = totalConferenceInfoPanel
					.getMainFrame().getAllConferenceRoomInfo();
            int numOfConference = allConferenceRoomInfo.size();
			Vector data = new Vector();
			int[][] map = new int[14][numOfConference]; // ���� 12 �������� ������ �о�����
			int[][] queryDay = new int[numOfConference][12]; // 12��������, 12 ��ʱ���

			/*
			 * ������������Ϣ ��ѯ����ʱ���
			 */
			for (ConferenceRoomVo room : allConferenceRoomInfo.values()) {
				Vector rowDate = new Vector();
				rowDate.add(room.getConferenceId());

				map = room.getFreeTimeSegement();
				int conId = room.getConferenceId();

				// ƥ����
				conId--;
				queryDay[conId] = map[differ];

				data.add(rowDate);
			}

			// ���û����ҿ���ʱ���
			totalConferenceInfoPanel.setFreeTimeModel(data, queryDay);

		}
	}

	/*
	 * Ԥ����¼ cal �뵱ǰʱ�������������
	 */
	private int checkDiffer(Calendar cal) {
		Calendar currentCal = Calendar.getInstance();
		int differ = cal.get(Calendar.DAY_OF_YEAR)
				- currentCal.get(Calendar.DAY_OF_YEAR);
		return differ;
	}

	/*
	 * �������ڡ���ʽΪ �� yyyy-mm-dd���õ���Ӧ�� Calendar ��ֵ
	 */
	private Calendar getCalendar(String dateStr) {

		Calendar cal = Calendar.getInstance();
		Date date = null;

		// dateStr to util.Date
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		try {

			date = f.parse(dateStr);

			cal.setTime(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

}
