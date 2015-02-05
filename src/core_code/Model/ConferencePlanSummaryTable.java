package core_code.Model;

import java.util.ArrayList;

/**
 * ÿ�������߶�Ӧ�Ļ��鰲���ܱ�
 * 
 * @author freezhan
 *
 */

public class ConferencePlanSummaryTable {
	
	private ArrayList<ConferencePlanEntry> conferencePlanEntries;
	private Applicant applicantOwner;
	
	public ConferencePlanSummaryTable(Applicant applicant) {
		
		// TODO Auto-generated constructor stub
		
		this.setApplicantOwner(applicant);
		
		conferencePlanEntries = new ArrayList<ConferencePlanEntry>();
		 
	}

	public ArrayList<ConferencePlanEntry> getConferencePlanEntries() {
		return conferencePlanEntries;
	}

	public void setConferencePlanEntries(
			ArrayList<ConferencePlanEntry> conferencePlanEntries) {
		this.conferencePlanEntries = conferencePlanEntries;
	}

	public Applicant getApplicantOwner() {
		return applicantOwner;
	}

	public void setApplicantOwner(Applicant applicantOwner) {
		this.applicantOwner = applicantOwner;
	}
	
	public void addConferencePlanEntry(ConferencePlanEntry ce) {
		conferencePlanEntries.add(ce);
		
	}
	
	/**
	 * �Զ��巽��
	 * �ж��û����ö�
	 * @return
	 */
	public boolean judgeConfidence() {
		
		//double sum = this.getConferencePlanEntries().size();
		double successFinish = 0; //�ɹ�����
		double miss = 0; //ˬԼ
		
		for(ConferencePlanEntry ce : conferencePlanEntries) {
			if("ˬԼ".equals(ce.getState())) {
				miss += 1;
			} else if("���".equals(ce.getState())) {
				successFinish += 1;
			}
		}
		
		if(miss <= 3) return true;
		
		if(miss > successFinish) return false;
		
		return true;
	}
	
	 
	public void display() {
		System.out.println("All ConferencePlans for : " + this.getApplicantOwner().getApplicantName());
		
		if(conferencePlanEntries.size() == 0) {
			System.out.println("\t (no entries)");
		} else {
			for(ConferencePlanEntry ce : conferencePlanEntries) {
				ce.display();
				
			}
		}
		
	}

	public void removePlanEntry(ConferencePlanEntry conferencePlanEntry) {
		// TODO Auto-generated method stub
		this.getConferencePlanEntries().remove(conferencePlanEntry);
		
	}
	
	
}
