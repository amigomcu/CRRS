package cn.com.gadget;

import java.util.Calendar;

import javax.swing.JOptionPane;

import cn.com.view.MainFrame;

public class CurrentTime extends Thread {

	private MainFrame mainframe;

	public CurrentTime() {

	}

	public CurrentTime(MainFrame mainframe) {
		this.mainframe = mainframe;
	}

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			refreshData();
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "�߳��ж�");
			}
		}
	}

	/*
	 * �����賿����¿���ʱ���
	 */
	public void refreshData() {
		Calendar cal = Calendar.getInstance();

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		if (hour == 0 && minute == 0 && second == 0) {
			mainframe.config();
		}

	}

}
