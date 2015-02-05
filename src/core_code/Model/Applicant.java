package core_code.Model;



/**
 * 申请者类
 * @author freezhan
 *
 */
public class Applicant {
	
	private String applicantNo; 	//编号
	private String applicantName;	//姓名
	private String applicantPassword;	//密码
	private String applicantEmail;		//邮箱
	private String applicantTelphoneNo; //电话
	private String applicantDepartment;	//所属部门
	private String applicantPosition;	//职位
	
	//此申请者 所有的会议记录 [过期的+预定中的]
	private ConferencePlanSummaryTable conferencePlanSummaryTable;
	
	
	public Applicant(String applicantNo, String applicantName,
			String applicantPassword, String applicantEmail,
			String applicantTelphoneNo, String applicantDepartment,
			String applicantPosition) {
		
		super();
		this.applicantNo = applicantNo;
		this.applicantName = applicantName;
		this.applicantPassword = applicantPassword;
		this.applicantEmail = applicantEmail;
		this.applicantTelphoneNo = applicantTelphoneNo;
		this.applicantDepartment = applicantDepartment;
		this.applicantPosition = applicantPosition;
		
		this.setConferencePlanSummaryTable(new ConferencePlanSummaryTable(this));
		
		 
	}



	public String getApplicantNo() {
		return applicantNo;
	}

	public void setApplicantNo(String applicantNo) {
		this.applicantNo = applicantNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantPassword() {
		return applicantPassword;
	}

	public void setApplicantPassword(String applicantPassword) {
		this.applicantPassword = applicantPassword;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getApplicantTelphoneNo() {
		return applicantTelphoneNo;
	}

	public void setApplicantTelphoneNo(String applicantTelphoneNo) {
		this.applicantTelphoneNo = applicantTelphoneNo;
	}

	public String getApplicantDepartment() {
		return applicantDepartment;
	}

	public void setApplicantDepartment(String applicantDepartment) {
		this.applicantDepartment = applicantDepartment;
	}

	public String getApplicantPosition() {
		return applicantPosition;
	}

	public void setApplicantPosition(String applicantPosition) {
		this.applicantPosition = applicantPosition;
	}

	public ConferencePlanSummaryTable getConferencePlanSummaryTable() {
		return conferencePlanSummaryTable;
	}

	public void setConferencePlanSummaryTable(
			ConferencePlanSummaryTable conferencePlanSummaryTable) {
		this.conferencePlanSummaryTable = conferencePlanSummaryTable;
	}	
	
	public void printConferencePlanSummaryTable() {
		this.getConferencePlanSummaryTable().display();
	}
	
}
