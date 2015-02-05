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
		if ("ɾ��".equals(buttonName)) {
			JTable table = cancelBookPanel.getTable();
			DefaultTableModel model = cancelBookPanel.getModel();

			int[] rowIndex = table.getSelectedRows(); // �õ�ѡ�������е��е�����
			int num = 0;

			for (int i = rowIndex.length - 1; i >= 0; i--) {
           //System.out.println(table.getValueAt(rowIndex[i], 0));
				int delRoomId = Integer.parseInt( (String) table.getValueAt(rowIndex[i], 0));

//				int delRoomId = (Integer) table.getValueAt(rowIndex[i], 0);
				System.out.println(delRoomId);
				
				int userId = cancelBookPanel.getMainFrame().getCurrentUserId();
				Date startDate = strToDate((String)table.getValueAt(rowIndex[i], 2));
//				Date startDate = (Date) table.getValueAt(rowIndex[i], 2); // ֱ��

				/*
				 * ����ÿ��ɾ����¼�Ķ�Ӧ�Ŀ���ʱ��� PS�����δ���ļ�¼
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
					// map[][] = 0 ����Ϊ����
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

				JOptionPane.showMessageDialog(null, "ɾ���ɹ�"+ num +"����¼��");

				 

			}
		}
	}

	/*
	 * �ж�ʱ��Ϸ�������� ȡ���������¿���ʱ��� map[][] = 0
	 */
	private void refreshFreeTime(ConferenceRecordVo delRecordVo) {
		//System.out.println("ȡ��Ԥ��" + delRecordVo.toString());
		int conferenceId = delRecordVo.getConferenceId();

		ConferenceRoomVo roomVo = new ConferenceRoomVo();
		roomVo = cancelBookPanel.getMainFrame().getAllConferenceRoomInfo()
				.get(conferenceId);
		roomVo.setFreeTimeMap(delRecordVo, 0);

	}
	
	/*
	 *  �ַ���ת���� util.date
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
