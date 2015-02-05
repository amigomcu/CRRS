package core_code.Model;

import java.util.Calendar;

public class Date {
	
	private int year;
	private int month;
	private int day; //day of month
	
	//Ĭ�������� ������ 29 ��
	private static int[] arryOfMonth = {0,31,29,31,30,31,30,31,31,30,31,30,31};
	
	public Date() {
		
		super();
		
		Calendar calendar = Calendar.getInstance();
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = (calendar.get(Calendar.MONTH)) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		 
	}


	public Date(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		 
	}



	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	
	 
	public void display() {
		System.out.println("-------------date------------");
		System.out.println(this.getYear() + "-" + this.getMonth() + "-" + this.getDay());
		 
	}
	
	public String toString() {
		return this.getYear() + "-" + this.getMonth() + "-" + this.getDay();
	}
	
	public Calendar dateToCalendar() {
		Calendar calendar = Calendar.getInstance();
		//hour = hourofday
		//day = dayofmonth
		calendar.set(year, month, day); 
									
		return calendar;
	}
	
	//test-wait
	public Date calendarToDate(Calendar calendar) {
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = (calendar.get(Calendar.MONTH)) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return this;
		 
	}
	
	public static Date getCurrentDate() {
		return new Date();
	}
	
	/**
	 * �������
	 * 
	 * ��Ϊ��Ҫ������Ԥ���Ĵ��� 
	 * ��Ϊ�����ǿ���Ԥ���ĵ�һ��
	 * ����  num �ķ�Χ�� [1,13]
	 * 
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public Date addDay(int num) {
 
		if(num <= 0 || num >= 14) return this;
		
		if(this.getMonth() < 12) { //������
			
			if(this.judgeYear() == false) {
				Date.arryOfMonth[2] = 28; //ƽ��
			} else {
				Date.arryOfMonth[2] = 29;
			}
			
			int dayNum = this.getDay() + num;
			if(dayNum <= Date.arryOfMonth[this.getMonth()]) { //������
				this.setDay(dayNum);
			} else { //����
				this.setMonth(this.getMonth()+1);
				this.setDay(dayNum-Date.arryOfMonth[this.getMonth()-1]);
			}
			
			
		} else if (this.getMonth() == 12 && (this.getDay() + num) <= 31) { //������[12]
			
			this.setDay(this.getDay()+num);
			
		} else { //���� [18+13 = 31]
			
			this.setYear(this.getYear()+1);
			this.setMonth(1);
			this.setDay((this.getDay()+num-31));
		}
		 
		return this;
	}
	
	 
	/**
	 * 
	 * �ж�Ԥ�������Ƿ�Ϸ������ܳ���δ������
	 * 
	 * ������ںϷ�, �򷵻�Ԥ�����ǵڼ��� numOfDay >= 1 and numOfDay <= 14
	 * ���򷵻� -1
	 * 
	 * @return
	 */
	public int varifyDate() {
		Date currentDate = new Date();
  
		if(this.getYear() != currentDate.getYear()) { //��ͬ��
 	
			if(this.getYear() < currentDate.getYear()) return -1; //Ԥ����С�ڵ�ǰ���
			
			if((this.getYear() - currentDate.getYear()) != 1) return -1; //Ԥ��ʱ��ȵ�ǰʱ����һ��
			
			//��ͬ����Ԥ���ɹ� ���ض�������һ��
			//�ҵ�ǰ�±ض�Ϊʮ����, Ԥ���±ض�Ϊ һ�� 
			if(currentDate.getMonth() != 12) return -1;
			
			if(this.getMonth() != 1) return -1;
			
			//Ԥ����δ���ڼ��� �����ǵ�һ��
			int numOfDay = 31-currentDate.getDay()+1 + this.getDay();
			if(numOfDay > 14) return -1;
			else return numOfDay;
			
		} else { //ͬ��
 	
		
			if(this.getMonth() != currentDate.getMonth()) { //��ͬ��
				
				if(this.getMonth() < currentDate.getMonth()) return -1; //Ԥ�������ѹ�
				if((this.getMonth() - currentDate.getMonth()) != 1) return -1; //��������ǲ�ͬ��,���ֻ�����һ����
				
				int numOfDay = Date.arryOfMonth[currentDate.getMonth()] - currentDate.getDay() + 1 + this.getDay();
				//do something...
				//�ж��Ƿ������� ������� 29 ��
				if(this.judgeYear() == false && currentDate.getMonth() == 2) {
					numOfDay -= 1; 
				}
				 
				
				if(numOfDay > 14) return -1;
				else return numOfDay;
				
			} else { //ͬ��
				
				if(this.getDay() < currentDate.getDay()) return -1;
				
				int numOfDay = this.getDay() - currentDate.getDay() + 1;
				if(1 <= numOfDay && numOfDay <= 14) return numOfDay;
				
				return -1;
				
			}
		}
		
	}
	
	 
	/**
	 * 
	 * �ж��Ƿ������꣺
	 * 	���꣺�����ܱ� 4 ���� ���ǲ��ܱ� 100 ����, �����ܹ��� 400 ����
	 * 
	 * ������� 29 ��
	 * ƽ�� 28 �� �����е� month Ĭ����������
	 * 
	 * @return
	 */
	public boolean judgeYear() {
		
		if((this.year % 4 == 0  && this.year % 100 != 0) ||  this.year % 400 == 0) {
			return true;
		}
		return false;
	}
	
	public boolean equals(Date date) {
		
		if(this.getYear() == date.getYear() && this.getMonth() == date.getMonth() && this.getDay() == date.getDay()) {
			return true;
		}
		
		return false;
	}


	 
}
