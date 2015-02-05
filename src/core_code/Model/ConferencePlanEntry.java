package core_code.Model;


/**
 * 会议计划
 * 一个会议计划对应一个申请者对应一个具体的会议室实例
 * 
 * @author freezhan
 *
 */
public class ConferencePlanEntry {
	
	private Applicant applicant; //单个会议计划的申请者
	private Date date; //开会具体日期
	private String theme; //会议主题
	private Integer attendNum; //参与人数
	private ConferenceRoomInstance conferenceRoomInstance; //对应的具体会议室实例
	private String state; //记录三种状态：会议已经开完了，会议预约但是过了时间后没有开会，会议预约了还没到开会时间
							// 完成, 爽约, 预约 ------ 用于信用度的判定

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
		
		//根据 instance new Entry时更新 bitMap 在具体逻辑中实现 
		 
		
		
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
