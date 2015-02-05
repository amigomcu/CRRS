package core_code.Model;


/**
 * ������ʵ��
 * �൱��ѧ��ע��ϵͳ�е� Section
 * 
 * @author freezhan
 *
 */

public class ConferenceRoomInstance {
	
	private String conferenceRoomInstanceNo; //���
	private ConferenceRoom conferenceRoom; //������ʵ����Ӧ�ľ��������
	private Time startTime; //������ʼ����ʱ��
	private Time endTime; //�����������ʱ��
	//ע�⣺ʱ�䲢��������������
	
	
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
