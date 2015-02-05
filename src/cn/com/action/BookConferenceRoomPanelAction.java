package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.Vo.ApplyConferenceRoomInfoVo;
import cn.com.Vo.ConferenceRecordVo;
import cn.com.Vo.ConferenceRoomVo;
import cn.com.dao.ConferenceRecordDao;
import cn.com.dao.FactoryDao;
import cn.com.view.functinPanel.BookConferenceRoomPanel;

public class BookConferenceRoomPanelAction implements ActionListener {

	private BookConferenceRoomPanel bookConferenceRoomPanel;

	public BookConferenceRoomPanelAction(
			BookConferenceRoomPanel bookConferenceRoomPanel) {
		this.bookConferenceRoomPanel = bookConferenceRoomPanel;

	}

	ConferenceRecordDao recordDao = FactoryDao.getConferenceRecordDao();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actionPerformed(ActionEvent e) {
		ApplyConferenceRoomInfoVo applicationInfo = bookConferenceRoomPanel
				.getApplicationInfo();

		String buttonName = e.getActionCommand();

		if("重置".equals(buttonName)) {
			bookConferenceRoomPanel.clearApplicationInfoPanel();
			
		}else if ("提交".equals(buttonName)) {
			

			/*
			 * 检查格式是否合法
			 */

			if ("".equals(applicationInfo.getConferenceTheme())) {
				JOptionPane.showMessageDialog(null, "请输入会议主题");
				return;
			}
			if ("".equals(applicationInfo.getAttendNum())) {
				JOptionPane.showMessageDialog(null,
						"  请正确输入预期参加会议的人数:必须是数字\n 而且大的会议室只能容纳 800 人");
				return;
			}
			if ("".equals(applicationInfo.getConferenceDate())) {
				JOptionPane.showMessageDialog(null, "请正确输入会议日期：类似于 2013-09-25");
				return;
			}

			if ("".equals(applicationInfo.getStartHour())
					|| "".equals(applicationInfo.getStartMinute())) {
				JOptionPane
						.showMessageDialog(null,
								"  会议室开放时间为每天早上 9 点到中午 12 点, 下午 14 点到 17 点\n请正确输入会议开始时间:类似于 9:15");
				return;
			}

			if ("".equals(applicationInfo.getEndHour())
					|| "".equals(applicationInfo.getEndMinute())) {
				JOptionPane
						.showMessageDialog(null,
								"  会议室开放时间为每天早上 9 点到中午 12 点, 下午 14 点到 17 点\n请正确输入会议结束时间:类似于 9:15");
				return;
			}

			/*
			 * 1.检查日期是否合法：只能预订。。。。。。。
			 * 
			 * 2.格式合法判断具体时间输入前后逻辑否合法
			 * 
			 * 判断是否满足朝九晚五模式 =_=朝九晚五貌似不是很合理
			 */

			// check date compare with currentDate

			int differ = getDifferentDay(applicationInfo.getConferenceDate()); // String：yyyy-mm-dd

			if (differ < 0 || differ >= 14) {
				JOptionPane.showMessageDialog(null, "预订失败！ 只能预订从今天开始的未来两周的会议室");
				return;
			}

			/*
			 * 判断具体时间的逻辑是否合法
			 */
			boolean flag = applicationInfo.isAvailable();

			if (!flag) {
				JOptionPane.showMessageDialog(null, "具体时间逻辑不合法, 请仔细检查申请表");

				return;
			}

			// check time
			int startHour = Integer.parseInt(applicationInfo.getStartHour());
			int startMinute = Integer
					.parseInt(applicationInfo.getStartMinute());

			int endHour = Integer.parseInt(applicationInfo.getEndHour());
			int endMinute = Integer.parseInt(applicationInfo.getEndMinute());

			int attendNum = Integer.parseInt(applicationInfo.getAttendNum());

			if (attendNum <= 0 || attendNum > 800) {
				JOptionPane.showMessageDialog(null, "人数不合法,最大的会议室只能容纳 800 人");

				return;
			}

			// flag = false;

			// 如果是当天预定判断是否大于当前时间
			if (differ == 0) {

				Calendar cal = Calendar.getInstance();
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int minute = cal.get(Calendar.MINUTE) + 1;

				if (startHour < hour
						|| (startHour == hour && startMinute < minute)) {
					JOptionPane.showMessageDialog(null,
							"预订失败！ 今天的预定，早于当前时间， 这是不可能的");
					return;
				}
			}

			int start = startHour * 60 + startMinute;
			int end = endHour * 60 + endMinute;
			int index1 = 0;// 用于空闲时间表的下标
			int index2 = 0;

			if (startHour <= 12) { // 上午
				start -= 9 * 60;
				end -= 9 * 60;

			} else {// 下午
				start -= 14 * 60;
				end -= 14 * 60;
			}

			index1 = start / 30;
			if (end % 30 == 0) {
				index2 = end / 30 - 1;
			} else {
				index2 = end / 30;
			}

			if (startHour >= 14) {

				index1 += 6;
				index2 += 6;
			}

			/*
			 * 
			 * 所有信息都合法. 查找是否有合适的会议室
			 * 
			 * 也就是 map[differ][index1] 到 map[differ][index2] = 0
			 * 
			 * 同时人数满足
			 */

			Vector data = new Vector();
			int[][] freeTimeMap = new int[14][12];

			for (ConferenceRoomVo room : bookConferenceRoomPanel.getMainFrame()
					.getAllConferenceRoomInfo().values()) {

				flag = true;
				// 查找人数是否合适, 暂且定义为只要大于就可以

				if (room.getConferenceCapital() < attendNum) {
					continue;
				}

				// 查找空闲时间段是否合适
				freeTimeMap = room.getFreeTimeSegement();
				for (int i = index1; i <= index2; i++) {
					if (freeTimeMap[differ][i] == 1) {
						flag = false;
						break;
					}
				}

				if (flag) {

					Vector rowData = new Vector();

					rowData.add(room.getConferenceId());
					rowData.add(room.getConferenceLocation());
					rowData.add(room.getConferenceCapital());

					data.add(rowData);
				}

			}

			if (data.size() > 0) {
				bookConferenceRoomPanel.setModel(data);

			} else {
				JOptionPane.showMessageDialog(null,
						"预订失败！ 没有合适的会议室, 请查看空闲时间表和会议室信息表后重新安排");
				return;
			}

		} else if ("确定".equals(buttonName)) {
			
			DefaultTableModel model = bookConferenceRoomPanel.getModel();

			/*
			 * 成功预订： 更新数据库 更新空闲时间表
			 */
			JTable table = bookConferenceRoomPanel.getTable();

			int rowIndex = table.getSelectedRow();
			int conferenceId = (Integer) table.getValueAt(rowIndex, 0);

			ConferenceRecordVo recordVo = applicationInfo
					.successfulApplication(conferenceId);
			boolean flag = recordDao.addRecord(recordVo);

			if (flag) {
				JOptionPane.showMessageDialog(null, "成功预订");

				// 更新空闲时间表
				ConferenceRoomVo roomVo = bookConferenceRoomPanel
						.getMainFrame().getAllConferenceRoomInfo()
						.get(conferenceId);
				roomVo.setFreeTimeMap(recordVo, 1);
				
				//移除选择行
				model.removeRow(rowIndex);
				

			}
		}
	}

	/*
	 * 检查预订的会议开始日期是否在 未来两周中 偷懒了...只能判断同一年的Orz
	 */
	private int getDifferentDay(String conferenceDate) {

		int differ = 0; // date - now

		Calendar nowCal = Calendar.getInstance();
		Calendar dateCal = stringToCalendar(conferenceDate);

		differ = dateCal.get(Calendar.DAY_OF_YEAR)
				- nowCal.get(Calendar.DAY_OF_YEAR);

		return differ;
	}

	public Calendar stringToCalendar(String timeStr) {
		String end = timeStr;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		Date date = null;
		try {
			date = df.parse(end);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

}
