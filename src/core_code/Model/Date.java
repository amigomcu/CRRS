package core_code.Model;

import java.util.Calendar;

public class Date {
	
	private int year;
	private int month;
	private int day; //day of month
	
	//默认是闰年 二月有 29 天
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
	 * 添加天数
	 * 
	 * 因为主要是用于预定的处理 
	 * 因为当天是可以预定的第一天
	 * 所以  num 的范围是 [1,13]
	 * 
	 * 
	 * 
	 * @param num
	 * @return
	 */
	public Date addDay(int num) {
 
		if(num <= 0 || num >= 14) return this;
		
		if(this.getMonth() < 12) { //不跨年
			
			if(this.judgeYear() == false) {
				Date.arryOfMonth[2] = 28; //平年
			} else {
				Date.arryOfMonth[2] = 29;
			}
			
			int dayNum = this.getDay() + num;
			if(dayNum <= Date.arryOfMonth[this.getMonth()]) { //不跨月
				this.setDay(dayNum);
			} else { //跨月
				this.setMonth(this.getMonth()+1);
				this.setDay(dayNum-Date.arryOfMonth[this.getMonth()-1]);
			}
			
			
		} else if (this.getMonth() == 12 && (this.getDay() + num) <= 31) { //不跨年[12]
			
			this.setDay(this.getDay()+num);
			
		} else { //跨年 [18+13 = 31]
			
			this.setYear(this.getYear()+1);
			this.setMonth(1);
			this.setDay((this.getDay()+num-31));
		}
		 
		return this;
	}
	
	 
	/**
	 * 
	 * 判断预定日期是否合法：不能超过未来两周
	 * 
	 * 如果日期合法, 则返回预定的是第几天 numOfDay >= 1 and numOfDay <= 14
	 * 否则返回 -1
	 * 
	 * @return
	 */
	public int varifyDate() {
		Date currentDate = new Date();
  
		if(this.getYear() != currentDate.getYear()) { //不同年
 	
			if(this.getYear() < currentDate.getYear()) return -1; //预定年小于当前年份
			
			if((this.getYear() - currentDate.getYear()) != 1) return -1; //预定时间比当前时间多出一年
			
			//不同的年预定成功 相差必定不超过一年
			//且当前月必定为十二月, 预定月必定为 一月 
			if(currentDate.getMonth() != 12) return -1;
			
			if(this.getMonth() != 1) return -1;
			
			//预定的未来第几天 今天是第一天
			int numOfDay = 31-currentDate.getDay()+1 + this.getDay();
			if(numOfDay > 14) return -1;
			else return numOfDay;
			
		} else { //同年
 	
		
			if(this.getMonth() != currentDate.getMonth()) { //不同月
				
				if(this.getMonth() < currentDate.getMonth()) return -1; //预定日期已过
				if((this.getMonth() - currentDate.getMonth()) != 1) return -1; //明显如果是不同月,最多只能相差一个月
				
				int numOfDay = Date.arryOfMonth[currentDate.getMonth()] - currentDate.getDay() + 1 + this.getDay();
				//do something...
				//判断是否是闰年 闰年二月 29 天
				if(this.judgeYear() == false && currentDate.getMonth() == 2) {
					numOfDay -= 1; 
				}
				 
				
				if(numOfDay > 14) return -1;
				else return numOfDay;
				
			} else { //同月
				
				if(this.getDay() < currentDate.getDay()) return -1;
				
				int numOfDay = this.getDay() - currentDate.getDay() + 1;
				if(1 <= numOfDay && numOfDay <= 14) return numOfDay;
				
				return -1;
				
			}
		}
		
	}
	
	 
	/**
	 * 
	 * 判断是否是闰年：
	 * 	闰年：年数能被 4 整出 但是不能被 100 整出, 或者能够被 400 整除
	 * 
	 * 闰年二月 29 天
	 * 平年 28 天 程序中的 month 默认是闰年制
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
