package cn.com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import cn.com.Vo.ConferenceRecordVo;

public interface ConferenceRecordDao extends DAO {
	// 查找预定记录, 修改 map [和当前时间比较]
	public ArrayList<ConferenceRecordVo> getBookingConferenceRecordVo();

	// 查找某一用户的预定记录,
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getBookingConferenceRecordVoByUserId(int userId);

	// add Record
	public boolean addRecord(ConferenceRecordVo record);

	// del Record by key
	public boolean delRecordByPrimeKeySet(int userId, int conferenceRoomId,
			Date startDate);

	// 根据用户 Id 查找 “详细的” 会议信息记录
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByUserId(int userId);

	// 根据用户 姓名 查找 "详细" 会议信息记录
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByUserName(String userName);

	// 根据会议室 ID 查找 "详细" 会议信息记录
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceId(int conferenceId);

	// 根据开会日期【不包含具体时间】 查找"详细" 会议信息记录
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceData(String dateStr);

	/*
	 * 模糊查询 根据开会 “地点” 查找 “详细”会议信息记录
	 */
	@SuppressWarnings("rawtypes")
	public Vector getDetailRecordInfoByConferenceLocation(
			String conferenceLocation);

	public ConferenceRecordVo getRecordByPrimeKeySet(int userId, int delRoomId,
			Date startDate);
    
	/*
	 * 根据用户ID删除其使用记录
	 */
	public void delRecordByUserId(int delUserId);
}
