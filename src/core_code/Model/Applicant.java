package core_code.Model;



/**
 * ��������
 * @author freezhan
 *
 */
public class Applicant {
	
	private String applicantNo; 	//���
	private String applicantName;	//����
	private String applicantPassword;	//����
	private String applicantEmail;		//����
	private String applicantTelphoneNo; //�绰
	private String applicantDepartment;	//��������
	private String applicantPosition;	//ְλ
	
	//�������� ���еĻ����¼ [���ڵ�+Ԥ���е�]
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
