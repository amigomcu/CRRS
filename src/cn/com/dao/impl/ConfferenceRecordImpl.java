package cn.com.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import cn.com.Vo.ConferenceRecordVo;
import cn.com.dao.ConferenceRecordDao;
import cn.com.util.SqlHelper;

public class ConfferenceRecordImpl implements ConferenceRecordDao {


	/*
	 * 和当前时间比较, 查找未来两周的预定记录
	 * 
	 * PS:开始存入时已经保证不会超过未来两周
	 */

	public ArrayList<ConferenceRecordVo> getBookingConferenceRecordVo() {

		ArrayList<ConferenceRecordVo> bookRecords = new ArrayList<ConferenceRecordVo>();

		// ------------格式化得到系统的时间---------------------------
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0); // 设置为凌晨
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		Date date = cal.getTime();
		String start = df.format(date); // 2013-09-22 12:28:38

		// ------------------------------------------------------------------------

		String sql = "select * from conferencerecord where startDate >= to_date(?, 'yyyy-MM-dd hh24:mi:ss') "; 
		String[] parameters = {start};
		
		ArrayList<Object[]> records = SqlHelper.executeQuery(sql, parameters);
		
		
		for(int i = 0; i < records.size(); i++) {
			Object[] ob = records.get(i);
			ConferenceRecordVo recordVo = new ConferenceRecordVo();
			
			recordVo.setUserId(Integer.parseInt(ob[0].toString()));
			recordVo.setConferenceId(Integer.parseInt(ob[1].toString()));
			recordVo.setAttendNum(Integer.parseInt(ob[2].toString()));
			
		    date = strToDate(ob[3].toString());
			recordVo.setStartDate(date);
			date = strToDate(ob[4].toString());
			recordVo.setEndDate(date);
			
			recordVo.setConferenceTheme(ob[5].toString());
			
			//System.out.println(recordVo.toString());
			
			bookRecords.add(recordVo);
		}
		 
		return bookRecords;
	}

	/*
	 * 添加记录
	 * success
	 */

	public boolean addRecord(ConferenceRecordVo record) {
		boolean flag = false;
		
		String sql = "insert into conferenceRecord values(?, ?, ?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date( ?,'yyyy-mm-dd hh24:mi:ss'), ?)";
		String[] parameters = { 
				Integer.valueOf(record.getUserId()).toString(),
				Integer.valueOf(record.getConferenceId()).toString(),
				Integer.valueOf(record.getAttendNum()).toString(),
				dateToString(record.getStartDate()),
				dateToString(record.getEndDate()),
				record.getConferenceTheme()
		};
		
		int num = SqlHelper.executeNonQuery(sql, parameters);
		
		if(num == 1) {
			flag = true;
		}

		return flag;
	}

	/*
	 * 删除预定记录
	 * 
	 * 
	 * 取消预约
	 */
	public boolean delRecordByPrimeKeySet(int userId, int conferenceRoomId,
			Date startDate) {
		boolean flag = false;
		
		String sql = "delete  from conferencerecord where conferenceroom_id = ? and user_id = ? and startDate = to_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		String[] parameters = { 
				Integer.valueOf(conferenceRoomId).toString(),
				Integer.valueOf(userId).toString(),
				 
				dateToString(startDate)
		};
				 
		
		int num = SqlHelper.executeNonQuery(sql, parameters);
		
		if(num == 1) {
			flag = true;
		}

		return flag;
	}

	/*
	 * 得到预定记录
	 * success
	 */
	public ConferenceRecordVo getRecordByPrimeKeySet(int userId, int delRoomId,
			Date startDate) {
		ConferenceRecordVo recordVo = new ConferenceRecordVo();
		
		String startStr = dateToString(startDate);
		String sql = " select * from conferencerecord where conferenceroom_id = ? and user_id = ? and startDate = to_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		String[] parameters = {Integer.valueOf(delRoomId).toString(),
				Integer.valueOf(userId).toString(),
				startStr};
		ArrayList<Object[]> records = SqlHelper.executeQuery(sql, parameters);
		Object[] ob = records.get(0);
		
		recordVo.setUserId(Integer.parseInt(ob[0].toString()));
		recordVo.setConferenceId(Integer.parseInt(ob[1].toString()));
		recordVo.setAttendNum(Integer.parseInt(ob[2].toString()));
		
	    Date date = strToDate(ob[3].toString());
		recordVo.setStartDate(date);
		date = strToDate(ob[4].toString());
		recordVo.setEndDate(date);
		
		recordVo.setConferenceTheme(ob[5].toString());
		
		return recordVo;
	}

	/**
	 * 通过所有的预订记录 得到当前用户的 预定记录
	 * 
	 * success
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector<Vector> getBookingConferenceRecordVoByUserId(int userId) {
		Vector data = new Vector();

		// 得到当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		String start = df.format(date); // 2013-09-22 12:28:38
		
		String sql = "select * from conferencerecord where startDate >= to_date(?, 'yyyy-MM-dd hh24:mi:ss') and user_id = ?" ;
		String[] parameters = {start, Integer.valueOf(userId).toString()};
		
		ArrayList<Object[]> records = SqlHelper.executeQuery(sql, parameters);
		
		for(int i = 0; i < records.size(); i++) {
			Object[] ob = records.get(i);
			Vector rowDate = new Vector();
			
			for(int j = 1; j <= 5; j++) {
				rowDate.add(ob[j].toString());
			}
			data.add(rowDate);
		}
		
		return data;
	}

	

	@SuppressWarnings({ "rawtypes" })
	@Override
	/*
	 * 根据用户 编号 得到详细的会议记录 按照时间的降序排序
	 * 
	 * 用户姓名 会议室编号 会议室地点 参加人数 开始时间 结束时间 会议主题
	 * 
	 * success
	 */
	public Vector getDetailRecordInfoByUserId(int userId) {
		String sql = "select user_name,A.conferenceRoom_id,conferenceRoom_location,attendNum,conferenceTheme,startDate,endDate"
		+ " from conferenceRecord A, conferenceRoom B, userInfo C "
		+ "where A.user_id = ? and A.conferenceRoom_id = B.Conferenceroom_Id and C.user_id = A.user_id "
		+ "order by startDate desc";
		String[] parameters = {Integer.valueOf(userId).toString()};
		
		return getData(sql,parameters);
	}

	/*
	 * 根据用户编号查找 用户申请的详细会议记录 按照日期降序排序 类型如上
	 * 
	 * success
	 */
	@SuppressWarnings({ "rawtypes" })
	public Vector getDetailRecordInfoByUserName(String userName) {
		String sql = "select user_name,A.conferenceRoom_id,conferenceRoom_location,attendNum,conferenceTheme,startDate,endDate"
				+ " from conferenceRecord A, conferenceRoom B, userInfo C "
				+ " where C.user_name = ? and A.conferenceRoom_id = B.Conferenceroom_Id and C.user_id = A.user_id "
				+ "order by startDate desc";
		String[] parameters = {userName};
		
		return getData(sql, parameters);
	}

	/**
	 * 根据会议室 Id 查找详细信息
	 * success
	 */
	@SuppressWarnings({ "rawtypes" })
	public Vector getDetailRecordInfoByConferenceId(int conferenceId) {
		String sql = "select user_name,A.conferenceRoom_id,conferenceRoom_location,attendNum,conferenceTheme,startDate,endDate"
				+ " from conferenceRecord A, conferenceRoom B, userInfo C "
				+ "where B.conferenceRoom_id = ? and A.conferenceRoom_id = B.Conferenceroom_Id and C.user_id = A.user_id "
				+ "order by startDate desc";
		String[] parameters = {Integer.valueOf(conferenceId).toString()};

		return getData(sql,parameters);
	}

	/*
	 * 根据开会日期查找会议 “详细” 信息 不包含具体
	 * success
	 */
	@SuppressWarnings({ "rawtypes" })
	public Vector getDetailRecordInfoByConferenceData(String dateStr) {
		 
		String sql = "select user_name,A.conferenceRoom_id,conferenceRoom_location,attendNum,conferenceTheme,startDate,endDate"
				+ " from conferenceRecord A, conferenceRoom B, userInfo C "
				+ "where startDate like  to_date( ?,'yyyy-mm-dd')"
				+ "and A.conferenceRoom_id = B.Conferenceroom_Id and C.user_id = A.user_id "
				+ "order by startDate desc";
		String[] parameters = {dateStr};
		
		return getData(sql, parameters);
	}

	/*
	 * 模糊查询 根据会议室地点查找"详细" 信息
	 * success
	 */
	@SuppressWarnings({ "rawtypes" })
	public Vector getDetailRecordInfoByConferenceLocation(
			String conferenceLocation) {
 
		String sql = "select user_name,A.conferenceRoom_id,conferenceRoom_location,attendNum,conferenceTheme,startDate,endDate"
		+ " from conferenceRecord A, conferenceRoom B, userInfo C "
		+ "where B.conferenceRoom_location like  ?"
		+ "and A.conferenceRoom_id = B.Conferenceroom_Id and C.user_id = A.user_id "
		+ "order by startDate desc";
		String[] parameters = {"%" +conferenceLocation+"%"};
		
		return  getData(sql, parameters);
	}
	
	@Override
	public void delRecordByUserId(int delUserId) {
		String sql = "delete conferencerecord where user_id = ?";
		String[] parameters = {Integer.valueOf(delUserId).toString()};
		SqlHelper.executeNonQuery(sql, parameters);
	}
	
	/*
	 * 略封装查询版面的操作
	 * success
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector getData(String sql,String[] parameters) {
		Vector data = new Vector();
		
		ArrayList<Object[]> records = SqlHelper.executeQuery(sql, parameters);
		
		for(int i = 0; i < records.size(); i++) {
			Object[] ob = records.get(i);
			Vector rowDate = new Vector();
			
			for(int j = 0; j <= 6; j++) {
				rowDate.add(ob[j].toString());
			}
			data.add(rowDate);
		}
		
		return data;
	}

	/*
	 * util.date 转换成字符串
	 */
	public String dateToString(Date date) {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = df.format(date);
		return str;
	}
	
	/*
	 *  字符串转换成 util.date
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

	

//	public static void main(String[] args) {
//		ConfferenceRecordImpl test = new ConfferenceRecordImpl();
//		String str = "2013-10-24 14:03:00";
//		Date date = test.strToDate(str);
//		Vector<Vector> vo = test.getBookingConferenceRecordVoByUserId(3);
//		for(int i = 0; i < vo.size(); i++) {
//			Vector rowDate = vo.get(i);
//			System.out.println(rowDate.toString());
//		}
//		test.delRecordByUserId(2);
//	}

}
