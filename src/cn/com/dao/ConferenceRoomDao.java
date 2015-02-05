package cn.com.dao;

import java.util.HashMap;

import cn.com.Vo.ConferenceRoomVo;

public interface ConferenceRoomDao extends DAO {

	// 得到所有的会议室的用户信息
	public HashMap<Integer, ConferenceRoomVo> getAllConferenceRoomInfoToHash();

	// 通过会议室 ID 查找 ConferenceVO
	public ConferenceRoomVo getConferenceRoomByConferenceId(int conferenceId);

}
