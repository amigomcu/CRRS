package cn.com.gadget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import cn.com.view.functinPanel.BookConferenceRoomPanel;

public class TimeClock extends Thread {

	private BookConferenceRoomPanel bookPanel;

	public TimeClock() {

	}

	public TimeClock(BookConferenceRoomPanel bookPanel) {
		this.bookPanel = bookPanel;

	}

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			displayTime();
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "œﬂ≥Ã÷–∂œ");
			}
		}
	}

	public void displayTime() {

		Calendar cal = Calendar.getInstance();
		int second = cal.get(Calendar.SECOND);

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		if (second == 0) {

			String currentTimeStr = f.format(new Date());

			bookPanel.setCurrentTimeLable(currentTimeStr);

		}

	}

}
