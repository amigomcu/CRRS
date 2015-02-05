package cn.com.Vo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConferenceRecordVo {
	private int userId;
	private int conferenceId;
	private Date startDate;
	private Date endDate;

	private String conferenceTheme;
	private int attendNum;

	public ConferenceRecordVo() {
		super();
	}

	public ConferenceRecordVo(int userId, int conferenceId, Date startDate,
			Date endDate, String conferenceTheme, int attendNum) {
		super();
		this.userId = userId;
		this.conferenceId = conferenceId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.conferenceTheme = conferenceTheme;
		this.attendNum = attendNum;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(int conferenceId) {
		this.conferenceId = conferenceId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getConferenceTheme() {
		return conferenceTheme;
	}

	public void setConferenceTheme(String conferenceTheme) {
		this.conferenceTheme = conferenceTheme;
	}

	public int getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(int attendNum) {
		this.attendNum = attendNum;
	}

	public String toString() {
		return "[" + this.getUserId() + "-" + this.getConferenceId() + "]"
				+ this.getAttendNum() + " " + this.getConferenceTheme() + " "
				+ this.getStartDate() + "-" + this.getEndDate();
	}

	/*
	 * 记录日期超出当前日期几天
	 */
	public int getDifferDay() {
		int differ = 0;
		Calendar bookCal = new GregorianCalendar();
		bookCal.setTime(this.startDate);

		Calendar currentCal = new GregorianCalendar();
		differ = bookCal.get(Calendar.DAY_OF_YEAR)
				- currentCal.get(Calendar.DAY_OF_YEAR);
		return differ;
	}

	public int getStartHour() {
		Calendar startCal = getStartCalendar();
		int startHour = startCal.get(Calendar.HOUR_OF_DAY);
		return startHour;
	}

	public int getEndHour() {
		Calendar endCal = getEndCalendar();
		int endHour = endCal.get(Calendar.HOUR_OF_DAY);
		return endHour;
	}

	public int getStartMinute() {

		Calendar startCal = getStartCalendar();
		int startMinute = startCal.get(Calendar.MINUTE);
		return startMinute;
	}

	public int getEndMinute() {
		Calendar endCal = getEndCalendar();
		int endMinute = endCal.get(Calendar.MINUTE);
		return endMinute;
	}

	public Calendar getStartCalendar() {
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(this.startDate);
		return startCal;
	}

	public Calendar getEndCalendar() {
		Calendar endCal = new GregorianCalendar();
		endCal.setTime(this.endDate);
		return endCal;
	}

}
