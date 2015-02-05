package cn.com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import cn.com.Vo.ConferenceRecordVo;

public interface ConferenceRecordDao extends DAO {
	// ����Ԥ����¼, �޸� map [�͵�ǰʱ��Ƚ�]
	public ArrayList<ConferenceRecordVo> getBookingConferenceRecordVo();

	// ����ĳһ�û���Ԥ����¼,
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getBookingConferenceRecordVoByUserId(int userId);

	// add Record
	public boolean addRecord(ConferenceRecordVo record);

	// del Record by key
	public boolean delRecordByPrimeKeySet(int userId, int conferenceRoomId,
			Date startDate);

	// �����û� Id ���� ����ϸ�ġ� ������Ϣ��¼
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByUserId(int userId);

	// �����û� ���� ���� "��ϸ" ������Ϣ��¼
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByUserName(String userName);

	// ���ݻ����� ID ���� "��ϸ" ������Ϣ��¼
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceId(int conferenceId);

	// ���ݿ������ڡ�����������ʱ�䡿 ����"��ϸ" ������Ϣ��¼
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceData(String dateStr);

	/*
	 * ģ����ѯ ���ݿ��� ���ص㡱 ���� ����ϸ��������Ϣ��¼
	 */
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceLocation(
			String conferenceLocation);

	public ConferenceRecordVo getRecordByPrimeKeySet(int userId, int delRoomId,
			Date startDate);
    
	/*
	 * �����û�IDɾ����ʹ�ü�¼
	 */
	public void delRecordByUserId(int delUserId);
}
