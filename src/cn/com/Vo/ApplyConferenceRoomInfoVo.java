package cn.com.Vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplyConferenceRoomInfoVo {

	private int userId;

	private String conferenceTheme;
	private String attendNum; // �������� ��ע�⣺���ת���� int��
	private String conferenceDate; // �������� ��ע��:�жϳɹ���ת���� date��
	private String startHour; // ��ʼʱ��
	private String startMinute;
	private String endHour;
	private String endMinute;

	public ApplyConferenceRoomInfoVo() {
		super();
	}

	public ApplyConferenceRoomInfoVo(int userId, String conferenceTheme,
			String attendNum, String conferenceDate, String startHour,
			String startMinute, String endHour, String endMinute) {
		super();
		this.userId = userId;
		this.conferenceTheme = conferenceTheme;
		this.attendNum = attendNum;
		this.conferenceDate = conferenceDate;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getConferenceTheme() {
		return conferenceTheme;
	}

	public void setConferenceTheme(String conferenceTheme) {
		this.conferenceTheme = conferenceTheme;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public String getConferenceDate() {
		return conferenceDate;
	}

	public void setConferenceDate(String conferenceDate) {
		this.conferenceDate = conferenceDate;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}

	public String toString() {
		String str = this.userId + " - " + this.conferenceTheme + "-"
				+ this.conferenceDate + "[" + this.startHour + "-"
				+ this.endHour + "]";

		return str;
	}

	/*
	 * �ж�������Ƿ�Ϸ���Ҳ�����Ƿ��к��ʵġ�
	 * 
	 * ֻ���жϾ����ʱ����߼��Ƿ�Ϸ�����
	 * 
	 * ע��:�� action ���Ѿ��ж����е����붼�Ϸ�
	 */
	public boolean isAvailable() {
		// boolean flag = true;

		int startH = Integer.parseInt(this.startHour);
		int endH = Integer.parseInt(this.endHour);

		int startM = Integer.parseInt(this.startMinute);
		int endM = Integer.parseInt(this.endMinute);

		// �ж�ʱ���Ⱥ�˳��
		if (startH == endH && startM >= endM) {
			return false;
		}

		if (startH > endH) {
			return false;
		}

		// ��ʼʱ��������, ����ʱ��ȴ������ PS��ǰ���Ѿ��ж���ʼʱ�� < ����ʱ��
		if (startH >= 9 && startH <= 12 && endH > 12) {
			return false;
		}

		if (startH == 12 || startH == 17) {
			return false;
		}

		if (endH == 12 || endH == 17) {
			if (endM != 0) {
				return false;
			}
		}

		return true;
	}

	/*
	 * �Ϸ�������� �õ������ұ�ź�ת���ɻ����Ҽ�¼
	 */
	public ConferenceRecordVo successfulApplication(int conferenceRoomId) {
		ConferenceRecordVo record = new ConferenceRecordVo();
		record.setUserId(this.userId);
		record.setConferenceId(conferenceRoomId);
		record.setAttendNum(Integer.parseInt(this.attendNum));
		record.setConferenceTheme(this.conferenceTheme);

		Date startDate = this.toDate(this.conferenceDate, this.startHour,
				this.startMinute);
		Date endDate = this.toDate(this.conferenceDate, this.endHour,
				this.endMinute);
		;
		record.setStartDate(startDate);
		record.setEndDate(endDate);
		return record;
	}

	public Date toDate(String dateStr, String hourStr, String minuteStr) {
		Date date = new Date();
		dateStr = dateStr + " " + hourStr + ":" + minuteStr;

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			date = f.parse(dateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
