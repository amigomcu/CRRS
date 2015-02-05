package cn.com.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.Vo.ConferenceRoomVo;
import cn.com.dao.ConferenceRoomDao;
import cn.com.util.SqlHelper;

public class ConferenceRoomImpl implements ConferenceRoomDao {

	// 通过会议室 ID 号查找会议室信息
	public ConferenceRoomVo getConferenceRoomByConferenceId(int conferenceId) {
		return null;
	}

	// 得到所有的会议室信息
	public HashMap<Integer, ConferenceRoomVo> getAllConferenceRoomInfoToHash() {
		HashMap<Integer, ConferenceRoomVo> AllConferenceRooms = new HashMap<Integer, ConferenceRoomVo>();

		String sql = "select * from conferenceRoom";
		String[] parameters = {};

		ArrayList<Object[]> conferences = SqlHelper.executeQuery(sql,
				parameters);

		for (int i = 0; i < conferences.size(); i++) {
			Object[] ob = conferences.get(i);

			Integer key = Integer.valueOf(ob[0].toString());

			ConferenceRoomVo conferenceRoomVo = new ConferenceRoomVo();
			conferenceRoomVo
					.setConferenceId(Integer.parseInt(ob[0].toString()));
			conferenceRoomVo.setConferenceCapital(Integer.parseInt(ob[1]
					.toString()));
			conferenceRoomVo.setConferenceLocation(ob[2].toString());

			AllConferenceRooms.put(key, conferenceRoomVo);

		}

		return AllConferenceRooms;
	}

	/*public static void main(String args[]) {
		ConferenceRoomImpl test = new ConferenceRoomImpl();
		HashMap<Integer, ConferenceRoomVo> AllConferenceRooms = test
				.getAllConferenceRoomInfoToHash();

		for (ConferenceRoomVo room : AllConferenceRooms.values()) {
			System.out.println(room.getConferenceId() + "-"
					+ room.getConferenceLocation());
			// System.out.println(room.getFreeTimeSegement(1, 1));
		}

	}*/

}
