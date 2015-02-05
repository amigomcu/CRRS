package cn.com.dao;

import java.util.HashMap;

import cn.com.Vo.ConferenceRoomVo;

public interface ConferenceRoomDao extends DAO {

	// �õ����еĻ����ҵ��û���Ϣ
	public HashMap<Integer, ConferenceRoomVo> getAllConferenceRoomInfoToHash();

	// ͨ�������� ID ���� ConferenceVO
	public ConferenceRoomVo getConferenceRoomByConferenceId(int conferenceId);

}
