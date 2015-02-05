package core_code.Model;


/**
 * ����ƻ�
 * һ������ƻ���Ӧһ�������߶�Ӧһ������Ļ�����ʵ��
 * 
 * @author freezhan
 *
 */
public class ConferencePlanEntry {
	
	private Applicant applicant; //��������ƻ���������
	private Date date; //�����������
	private String theme; //��������
	private Integer attendNum; //��������
	private ConferenceRoomInstance conferenceRoomInstance; //��Ӧ�ľ��������ʵ��
	private String state; //��¼����״̬�������Ѿ������ˣ�����ԤԼ���ǹ���ʱ���û�п��ᣬ����ԤԼ�˻�û������ʱ��
							// ���, ˬԼ, ԤԼ ------ �������öȵ��ж�

	private ConferencePlanSummaryTable conferencePlanSummaryTable;

	public ConferencePlanEntry(Applicant applicant, Date date, String theme, Integer attendNum, 
			ConferenceRoomInstance conferenceRoomInstance, String state) {
		super();
		this.applicant = applicant;
		this.date = date;
		this.theme = theme;
		this.attendNum = attendNum;
		this.conferenceRoomInstance = conferenceRoomInstance;
		this.state = state;
		
		ConferencePlanSummaryTable table = applicant.getConferencePlanSummaryTable();
		this.conferencePlanSummaryTable = table;
		table.addConferencePlanEntry(this);
		
		//���� instance new Entryʱ���� bitMap �ھ����߼���ʵ�� 
		 
		
		
	}
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}

	public ConferenceRoomInstance getConferenceRoomInstance() {
		return conferenceRoomInstance;
	}

	public void setConferenceRoomInstance(
			ConferenceRoomInstance conferenceRoomInstance) {
		this.conferenceRoomInstance = conferenceRoomInstance;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ConferencePlanSummaryTable getConferencePlanSummaryTable() {
		return conferencePlanSummaryTable;
	}

	public void setConferencePlanSummaryTable(
			ConferencePlanSummaryTable conferencePlanSummaryTable) {
		this.conferencePlanSummaryTable = conferencePlanSummaryTable;
	}


	public void display() {
		
		System.out.println("\tDate:		" + this.getDate().toString());
		System.out.println("\tTheme:	" + this.getTheme());
		System.out.println("\tStartTime : " +conferenceRoomInstance.getStartTime().toString() + "  endTime: " + conferenceRoomInstance.getEndTime().toString());
		System.out.println("\tState:    " + this.getState());
		
	}
	
	public  boolean validateConferenceRoomInstance() {
		 
		//check capacity
		if(this.getAttendNum() > this.getConferenceRoomInstance().getConferenceRoom().getSeatingCapacity()) {
			return false;
		}
		
		//check time
		char timePeriodToBit = Time.specificTimeToByte(this.getConferenceRoomInstance().getStartTime(), this.getConferenceRoomInstance().getEndTime());
		int numOfDay = this.getDate().varifyDate();
		if(numOfDay == -1) return false;
		
		char val = (char) (timePeriodToBit & (this.getConferenceRoomInstance().getConferenceRoom().getBitMap()[numOfDay-1]));
		if(val == timePeriodToBit) return true;
		
		return false;
	}
 
}
