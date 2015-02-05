package cn.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.com.Vo.ConferenceRecordVo;

public class Test {
	//private SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test test = new Test();
		
		test.findRecords();
		/*String str = "2003-12-17 18:06:07";
		Date date = test.strToDate(str);
		
		System.out.println(test.strToDate(str));
		SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		System.out.println(f.format(date));
		*/
		
		 
	 
	 

	}
	
	public void findRecords() {
		
		String sql = "select * from conferenceRecord";
		String[] parameters = {};
		
		ArrayList<Object[]> records = SqlHelper.executeQuery(sql, parameters);
		
		ArrayList<ConferenceRecordVo> recordVos = new ArrayList<ConferenceRecordVo>(); 
		
		for(int i = 0; i < records.size(); i++) {
			Object[] ob = records.get(i);
			ConferenceRecordVo recordVo = new ConferenceRecordVo();
			
			recordVo.setUserId(Integer.parseInt(ob[0].toString()));
			recordVo.setConferenceId(Integer.parseInt(ob[1].toString()));
			recordVo.setAttendNum(Integer.parseInt(ob[2].toString()));
			Date date = strToDate(ob[3].toString());
			recordVo.setStartDate(date);
			date = strToDate(ob[4].toString());
			recordVo.setEndDate(date);
			recordVo.setConferenceTheme(ob[5].toString());
			
			System.out.println(recordVo.toString());
			
			
		 
			
			recordVos.add(recordVo);
		}
	}
	
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
