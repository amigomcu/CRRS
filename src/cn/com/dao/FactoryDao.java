package cn.com.dao;

import cn.com.dao.impl.ConferenceRoomImpl;
import cn.com.dao.impl.ConfferenceRecordImpl;
import cn.com.dao.impl.UserDaoImpl;

public class FactoryDao {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static ConferenceRoomDao getConferenceRoomDao() {
		return new ConferenceRoomImpl();
	}

	public static ConferenceRecordDao getConferenceRecordDao() {
		return new ConfferenceRecordImpl();
	}

}
