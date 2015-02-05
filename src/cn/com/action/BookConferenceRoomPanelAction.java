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

		if("����".equals(buttonName)) {
			bookConferenceRoomPanel.clearApplicationInfoPanel();
			
		}else if ("�ύ".equals(buttonName)) {
			

			/*
			 * ����ʽ�Ƿ�Ϸ�
			 */

			if ("".equals(applicationInfo.getConferenceTheme())) {
				JOptionPane.showMessageDialog(null, "�������������");
				return;
			}
			if ("".equals(applicationInfo.getAttendNum())) {
				JOptionPane.showMessageDialog(null,
						"  ����ȷ����Ԥ�ڲμӻ��������:����������\n ���Ҵ�Ļ�����ֻ������ 800 ��");
				return;
			}
			if ("".equals(applicationInfo.getConferenceDate())) {
				JOptionPane.showMessageDialog(null, "����ȷ����������ڣ������� 2013-09-25");
				return;
			}

			if ("".equals(applicationInfo.getStartHour())
					|| "".equals(applicationInfo.getStartMinute())) {
				JOptionPane
						.showMessageDialog(null,
								"  �����ҿ���ʱ��Ϊÿ������ 9 �㵽���� 12 ��, ���� 14 �㵽 17 ��\n����ȷ������鿪ʼʱ��:������ 9:15");
				return;
			}

			if ("".equals(applicationInfo.getEndHour())
					|| "".equals(applicationInfo.getEndMinute())) {
				JOptionPane
						.showMessageDialog(null,
								"  �����ҿ���ʱ��Ϊÿ������ 9 �㵽���� 12 ��, ���� 14 �㵽 17 ��\n����ȷ����������ʱ��:������ 9:15");
				return;
			}

			/*
			 * 1.��������Ƿ�Ϸ���ֻ��Ԥ����������������
			 * 
			 * 2.��ʽ�Ϸ��жϾ���ʱ������ǰ���߼���Ϸ�
			 * 
			 * �ж��Ƿ����㳯������ģʽ =_=��������ò�Ʋ��Ǻܺ���
			 */

			// check date compare with currentDate

			int differ = getDifferentDay(applicationInfo.getConferenceDate()); // String��yyyy-mm-dd

			if (differ < 0 || differ >= 14) {
				JOptionPane.showMessageDialog(null, "Ԥ��ʧ�ܣ� ֻ��Ԥ���ӽ��쿪ʼ��δ�����ܵĻ�����");
				return;
			}

			/*
			 * �жϾ���ʱ����߼��Ƿ�Ϸ�
			 */
			boolean flag = applicationInfo.isAvailable();

			if (!flag) {
				JOptionPane.showMessageDialog(null, "����ʱ���߼����Ϸ�, ����ϸ��������");

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
				JOptionPane.showMessageDialog(null, "�������Ϸ�,���Ļ�����ֻ������ 800 ��");

				return;
			}

			// flag = false;

			// ����ǵ���Ԥ���ж��Ƿ���ڵ�ǰʱ��
			if (differ == 0) {

				Calendar cal = Calendar.getInstance();
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int minute = cal.get(Calendar.MINUTE) + 1;

				if (startHour < hour
						|| (startHour == hour && startMinute < minute)) {
					JOptionPane.showMessageDialog(null,
							"Ԥ��ʧ�ܣ� �����Ԥ�������ڵ�ǰʱ�䣬 ���ǲ����ܵ�");
					return;
				}
			}

			int start = startHour * 60 + startMinute;
			int end = endHour * 60 + endMinute;
			int index1 = 0;// ���ڿ���ʱ�����±�
			int index2 = 0;

			if (startHour <= 12) { // ����
				start -= 9 * 60;
				end -= 9 * 60;

			} else {// ����
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
			 * ������Ϣ���Ϸ�. �����Ƿ��к��ʵĻ�����
			 * 
			 * Ҳ���� map[differ][index1] �� map[differ][index2] = 0
			 * 
			 * ͬʱ��������
			 */

			Vector data = new Vector();
			int[][] freeTimeMap = new int[14][12];

			for (ConferenceRoomVo room : bookConferenceRoomPanel.getMainFrame()
					.getAllConferenceRoomInfo().values()) {

				flag = true;
				// ���������Ƿ����, ���Ҷ���ΪֻҪ���ھͿ���

				if (room.getConferenceCapital() < attendNum) {
					continue;
				}

				// ���ҿ���ʱ����Ƿ����
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
						"Ԥ��ʧ�ܣ� û�к��ʵĻ�����, ��鿴����ʱ���ͻ�������Ϣ������°���");
				return;
			}

		} else if ("ȷ��".equals(buttonName)) {
			
			DefaultTableModel model = bookConferenceRoomPanel.getModel();

			/*
			 * �ɹ�Ԥ���� �������ݿ� ���¿���ʱ���
			 */
			JTable table = bookConferenceRoomPanel.getTable();

			int rowIndex = table.getSelectedRow();
			int conferenceId = (Integer) table.getValueAt(rowIndex, 0);

			ConferenceRecordVo recordVo = applicationInfo
					.successfulApplication(conferenceId);
			boolean flag = recordDao.addRecord(recordVo);

			if (flag) {
				JOptionPane.showMessageDialog(null, "�ɹ�Ԥ��");

				// ���¿���ʱ���
				ConferenceRoomVo roomVo = bookConferenceRoomPanel
						.getMainFrame().getAllConferenceRoomInfo()
						.get(conferenceId);
				roomVo.setFreeTimeMap(recordVo, 1);
				
				//�Ƴ�ѡ����
				model.removeRow(rowIndex);
				

			}
		}
	}

	/*
	 * ���Ԥ���Ļ��鿪ʼ�����Ƿ��� δ�������� ͵����...ֻ���ж�ͬһ���Orz
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
