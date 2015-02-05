package core_code.Model;

import java.util.Calendar;

public class Time {
	
	private int hour; // 24 Сʱ��
	private int minute;
	
	public Time() {
		Calendar calendar = Calendar.getInstance();
		
		this.hour = calendar.get(Calendar.HOUR_OF_DAY); //Ĭ�ϵ�ǰʱ��
		this.minute = calendar.get(Calendar.MINUTE);
		
	}
	
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}


	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public int getMinute() {
		return minute;
	}


	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public String toString() {
		return this.getHour() + ":" + this.getMinute();
	}
	
	/**
	 * ����Ԥ��������
	 * 
	 * ����Ԥ����ʱ�������Ӧ��λ����
	 * 
	 * ע�� ���ú������õ��� ���� ����
	 * 		 Ҳ����˵ 8:00-8:00 ��Ӧ���� char �����һλ
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static char specificTimeToByte(Time startTime, Time endTime) {
		
		int startHour = startTime.getHour();
		int endHour = endTime.getHour();
		
		int startMinute = startTime.getMinute();
		int endMinute = endTime.getMinute();
		
		int numOfContinueByte = (endHour - startHour) << 1;
		if(startMinute >= 30) {
			numOfContinueByte -= 1;
		}
		
		if(1 <= endMinute && endMinute <= 30) {
			numOfContinueByte += 1;
		} else if(endMinute > 30) {
			numOfContinueByte += 2;
		}

		char valOfContinueByte = 0x1;
		valOfContinueByte = (char) (valOfContinueByte * (Math.pow(2, numOfContinueByte) - 1));
		
		int shift = 0;
		//solve hour
		if(8 <= startHour && startHour <= 12) {
			shift = (startHour-8) << 1;
			
		} else if(startHour >= 14) {
			shift = 8 + ((startHour-14) << 1);
		}
		//solve minute
		if(startMinute < 30) {
			shift += 1;
		}else if(startMinute >= 30) {
			shift += 2;
		}  

		shift -= 1;
		 
		return (char) (valOfContinueByte << shift);
		 
	}

	public static boolean compare(Time endTime, Time endTime2) {
		// TODO Auto-generated method stub
		
		if(endTime.getHour() == endTime2.getHour() && endTime.getMinute() == endTime2.getMinute()) {
			return true;
		}
		return false;
	}
	
}
