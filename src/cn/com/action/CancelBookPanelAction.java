package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.Vo.ConferenceRecordVo;
import cn.com.Vo.ConferenceRoomVo;
import cn.com.dao.ConferenceRecordDao;
import cn.com.dao.FactoryDao;
import cn.com.view.functinPanel.CancelBookPanel;

public class CancelBookPanelAction implements ActionListener {

	private CancelBookPanel cancelBookPanel;

	public CancelBookPanelAction(CancelBookPanel cancelBookPanel) { 
		this.cancelBookPanel = cancelBookPanel;
	}

	ConferenceRecordDao recordDao = FactoryDao.getConferenceRecordDao();

	public void actionPerformed(ActionEvent e) {

		String buttonName = e.getActionCommand();
		if ("删除".equals(buttonName)) {
			JTable table = cancelBookPanel.getTable();
			DefaultTableModel model = cancelBookPanel.getModel();

			int[] rowIndex = table.getSelectedRows(); // 得到选定的所有的行的索引
			int num = 0;

			for (int i = rowIndex.length - 1; i >= 0; i--) {
           //System.out.println(table.getValueAt(rowIndex[i], 0));
				int delRoomId = Integer.parseInt( (String) table.getValueAt(rowIndex[i], 0));

//				int delRoomId = (Integer) table.getValueAt(rowIndex[i], 0);
				System.out.println(delRoomId);
				
				int userId = cancelBookPanel.getMainFrame().getCurrentUserId();
				Date startDate = strToDate((String)table.getValueAt(rowIndex[i], 2));
//				Date startDate = (Date) table.getValueAt(rowIndex[i], 2); // 直接

				/*
				 * 更改每行删除记录的对应的空闲时间表 PS：针对未来的记录
				 */
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);

				Date currentDate = cal.getTime();

				if (startDate.after(currentDate)) {

					ConferenceRecordVo delRecord = recordDao
							.getRecordByPrimeKeySet(userId, delRoomId,
									startDate);
					// map[][] = 0 重置为空闲
					refreshFreeTime(delRecord);
				}

				boolean flag = recordDao.delRecordByPrimeKeySet(userId,
						delRoomId, startDate);

				if (flag) {
					num++;
					model.removeRow(rowIndex[i]);
				}
			}

			if (num == rowIndex.length) {

				JOptionPane.showMessageDialog(null, "删除成功"+ num +"条记录！");

				 

			}
		}
	}

	/*
	 * 判断时间合法的情况下 取消会议后更新空闲时间表 map[][] = 0
	 */
	private void refreshFreeTime(ConferenceRecordVo delRecordVo) {
		//System.out.println("取消预定" + delRecordVo.toString());
		int conferenceId = delRecordVo.getConferenceId();

		ConferenceRoomVo roomVo = new ConferenceRoomVo();
		roomVo = cancelBookPanel.getMainFrame().getAllConferenceRoomInfo()
				.get(conferenceId);
		roomVo.setFreeTimeMap(delRecordVo, 0);

	}
	
	/*
	 *  字符串转换成 util.date
	 */
	public Date strToDate(String start) {

		String end = start;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(end);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return date;
	}

}
