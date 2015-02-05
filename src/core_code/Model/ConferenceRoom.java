package core_code.Model;

import java.util.ArrayList;

import core_code.Utils.CharTransToFormatBinaryString;

/**
 * ��������
 * �൱����ѧע��ϵͳ�е� class��
 * @author freezhan
 *
 */
public class ConferenceRoom {
	
	private String conferenceRoomNo; //���
	private String conferenceRoomName; //����������
	private String conferenceAddress; //�����ҵص�
	private Integer seatingCapacity; //����������
	
	private char[] bitMap = new char[14]; //״̬λͼ
	
	//�����Ұ����Ļ�����ʵ��
	private ArrayList<ConferenceRoomInstance> conferenceRoomInstanceList;
	
	public ConferenceRoom(String conferenceRoomNo, String conferenceRoomName,
			String conferenceAddress, Integer seatingCapacity ) {
		
		super();
		this.conferenceRoomNo = conferenceRoomNo;
		this.conferenceRoomName = conferenceRoomName;
		this.conferenceAddress = conferenceAddress;
		this.seatingCapacity = seatingCapacity;
		
		
		this.bitMap = new char[14];
		this.conferenceRoomInstanceList = new ArrayList<ConferenceRoomInstance>();
	}

	public String getConferenceRoomNo() {
		return conferenceRoomNo;
	}

	public void setConferenceRoomNo(String conferenceRoomNo) {
		this.conferenceRoomNo = conferenceRoomNo;
	}

	public String getConferenceRoomName() {
		return conferenceRoomName;
	}

	public void setConferenceRoomName(String conferenceRoomName) {
		this.conferenceRoomName = conferenceRoomName;
	}

	public String getConferenceAddress() {
		return conferenceAddress;
	}

	public void setConferenceAddress(String conferenceAddress) {
		this.conferenceAddress = conferenceAddress;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public char[] getBitMap() {
		return bitMap;
	}

	public void setBitMap(char[] bitMap) {
		this.bitMap = bitMap;
	}

	public ArrayList<ConferenceRoomInstance> getConferenceRoomInstanceList() {
		return conferenceRoomInstanceList;
	}

	public void setConferenceRoomInstanceList(
			ArrayList<ConferenceRoomInstance> conferenceRoomInstanceList) {
		this.conferenceRoomInstanceList = conferenceRoomInstanceList;
	}
	
	public void display() {
		System.out.println("ConferenceRoom Informations: ");
		System.out.println("\tConferenceRoom No:	" + this.getConferenceRoomNo());
		System.out.println("\tConferenceRoom Name:	" + this.getConferenceRoomName());
		System.out.println("\tConferenceRoom Address: " + this.getConferenceAddress());
		System.out.println("\tCapacity:     " + this.getSeatingCapacity());
		
//		System.out.println("-----------timeMap-------------");
//		this.displaybitMap();
		
		System.out.println();
	}
	
	public void displaybitMap() {
		for(int i = 0; i < bitMap.length; i++) {
			System.out.println(CharTransToFormatBinaryString.trans(bitMap[i]));
		}
		
	}
	
	public String toString() {
		return this.getConferenceRoomNo() + ": " + this.getConferenceAddress();
	}
	
	public ConferenceRoomInstance scheduleConferenceRoomInstance(String conferenceRoomInstanceNo, Time startTime, Time endTime) {
		
		ConferenceRoomInstance cr = new ConferenceRoomInstance(conferenceRoomInstanceNo, this, startTime, endTime);
		
		this.addConferenceRoomInstance(cr);
		return cr;
	}
	
	public void addConferenceRoomInstance(ConferenceRoomInstance cr) {
		 
		this.conferenceRoomInstanceList.add(cr);
		 
	}

	/**
	 * ��ӡ����ʱ��
	 * num in [0, 13]
	 * @param num
	 */
	public void displayFreeTimeMapByTime(int num) {
		// TODO Auto-generated method stub
		
		System.out.print(this.getConferenceRoomName());
		char c = this.getBitMap()[num];
		for(int i = 0; i < 16; i++) {
			int val = c&1;
			c >>= 1;
			if(val == 0) {
				System.out.print("  ��          ");
			} else {
				System.out.print("  æ         ");
			}
		}
		
		System.out.println();
		
	}

	/**
	 * ���� BitMap
	 * 
	 * ע�⣺�����±�� 0 ��ʼ
	 * Ԥ��ʱ����δ������
	 * ������� numOfDay in [1,14]
	 * @param numOfDay �ڼ���
	 * @param specificTimeByte ����õ�ʱ���
	 * 
	 */
	public void setBitMap(int numOfDay, char specificTimeByte) {
		// TODO Auto-generated method stub
		
		if(numOfDay < 1 || numOfDay > 14) return;
		
		numOfDay -= 1;
		 
		this.getBitMap()[numOfDay] = (char) (this.getBitMap()[numOfDay] | specificTimeByte);
		
		
		
	}

	public ConferenceRoomInstance checkInstance(Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.getConferenceRoomInstanceList().size(); i++) {
			ConferenceRoomInstance instance = this.getConferenceRoomInstanceList().get(i);
			if(Time.compare(instance.getStartTime(), startTime) && Time.compare(instance.getEndTime(), endTime)) {
				return instance;
			}
		}
		return null;
	}


	public void updateTimeBitMap(Date date, char timeSegmentBit, int val) {
		// TODO Auto-generated method stub
		int numOfDay = date.varifyDate() - 1;
		
		if(val == 0) {
			this.getBitMap()[numOfDay] = (char) (this.getBitMap()[numOfDay] ^ timeSegmentBit);
		} else if(val == 1) {
			this.getBitMap()[numOfDay] =  (char) (this.getBitMap()[numOfDay] | timeSegmentBit);

		}
	}

}
