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
	 * �͵�ǰʱ��Ƚ�, ����δ�����ܵ�Ԥ����¼
	 * 
	 * PS:��ʼ����ʱ�Ѿ���֤���ᳬ��δ������
	 */

	public ArrayList<ConferenceRecordVo> getBookingConferenceRecordVo() {

		ArrayList<ConferenceRecordVo> bookRecords = new ArrayList<ConferenceRecordVo>();

		// ------------��ʽ���õ�ϵͳ��ʱ��---------------------------
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0); // ����Ϊ�賿
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
	 * ��Ӽ�¼
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
	 * ɾ��Ԥ����¼
	 * 
	 * 
	 * ȡ��ԤԼ
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
	 * �õ�Ԥ����¼
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
	 * ͨ�����е�Ԥ����¼ �õ���ǰ�û��� Ԥ����¼
	 * 
	 * success
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector<Vector> getBookingConferenceRecordVoByUserId(int userId) {
		Vector data = new Vector();

		// �õ���ǰʱ��
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
	 * �����û� ��� �õ���ϸ�Ļ����¼ ����ʱ��Ľ�������
	 * 
	 * �û����� �����ұ�� �����ҵص� �μ����� ��ʼʱ�� ����ʱ�� ��������
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
	 * �����û���Ų��� �û��������ϸ�����¼ �������ڽ������� ��������
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
	 * ���ݻ����� Id ������ϸ��Ϣ
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
	 * ���ݿ������ڲ��һ��� ����ϸ�� ��Ϣ ����������
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
	 * ģ����ѯ ���ݻ����ҵص����"��ϸ" ��Ϣ
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
	 * �Է�װ��ѯ����Ĳ���
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
	 * util.date ת�����ַ���
	 */
	public String dateToString(Date date) {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = df.format(date);
		return str;
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
