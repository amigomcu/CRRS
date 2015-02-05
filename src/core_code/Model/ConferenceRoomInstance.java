package core_code.Model;


/**
 * 会议室实例
 * 相当于学生注册系统中的 Section
 * 
 * @author freezhan
 *
 */

public class ConferenceRoomInstance {
	
	private String conferenceRoomInstanceNo; //编号
	private ConferenceRoom conferenceRoom; //会议室实例对应的具体会议室
	private Time startTime; //会议起始具体时间
	private Time endTime; //会议结束具体时间
	//注意：时间并不包含具体日期
	
	
	public ConferenceRoomInstance(String conferenceRoomInstanceNo,
			ConferenceRoom conferenceRoom, Time startTime, Time endTime) {
		super();
		this.conferenceRoomInstanceNo = conferenceRoomInstanceNo;
		this.conferenceRoom = conferenceRoom;
		this.startTime = startTime;
		this.endTime = endTime;
		
		conferenceRoom.addConferenceRoomInstance(this);
		 
	}

	public String getConferenceRoomInstanceNo() {
		return conferenceRoomInstanceNo;
	}

	public void setConferenceRoomInstanceNo(String conferenceRoomInstanceNo) {
		this.conferenceRoomInstanceNo = conferenceRoomInstanceNo;
	}

	public ConferenceRoom getConferenceRoom() {
		return conferenceRoom;
	}

	public void setConferenceRoom(ConferenceRoom conferenceRoom) {
		this.conferenceRoom = conferenceRoom;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void display() {
		// TODO Auto-generated method stub
		System.out.println("startTime = " + this.getStartTime().toString() + "  endTime = " + this.getEndTime().toString());
		
	}
 
	public char getTimeSegMentBit() {
		// TODO Auto-generated method stub
		return Time.specificTimeToByte(startTime, endTime);
	}
	
	

	 
	
}
