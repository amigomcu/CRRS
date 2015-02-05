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
		if ("日期查询".equals(buttonName)) {

			 
			String dateStr = totalConferenceInfoPanel.getCheckDate();

			/*
			 * 日期格式合法后
			 */

			Calendar cal = getCalendar(dateStr);
			int differ = checkDiffer(cal); // 预定记录 cal 与当前时间所隔离的天数

			if (differ < 0 || differ >= 14) {
				JOptionPane.showMessageDialog(null, "只能查询未来两周空闲时间表,请重新输入查询时间");

				totalConferenceInfoPanel.clearFreeTimeModel();
				return;
			}

			// 得到空闲时间表
			HashMap<Integer, ConferenceRoomVo> allConferenceRoomInfo = totalConferenceInfoPanel
					.getMainFrame().getAllConferenceRoomInfo();
            int numOfConference = allConferenceRoomInfo.size();
			Vector data = new Vector();
			int[][] map = new int[14][numOfConference]; // 两周 12 个会议室 定死了 感觉不好
			int[][] queryDay = new int[numOfConference][12]; // 12个会议室, 12 个时间段

			/*
			 * 遍历会议室信息 查询空闲时间表
			 */
			for (ConferenceRoomVo room : allConferenceRoomInfo.values()) {
				Vector rowDate = new Vector();
				rowDate.add(room.getConferenceId());

				map = room.getFreeTimeSegement();
				int conId = room.getConferenceId();

				// 匹配编号
				conId--;
				queryDay[conId] = map[differ];

				data.add(rowDate);
			}

			// 重置会议室空闲时间表
			totalConferenceInfoPanel.setFreeTimeModel(data, queryDay);

		}
	}

	/*
	 * 预定记录 cal 与当前时间所隔离的天数
	 */
	private int checkDiffer(Calendar cal) {
		Calendar currentCal = Calendar.getInstance();
		int differ = cal.get(Calendar.DAY_OF_YEAR)
				- currentCal.get(Calendar.DAY_OF_YEAR);
		return differ;
	}

	/*
	 * 根据日期【格式为 ： yyyy-mm-dd】得到相应的 Calendar 的值
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
