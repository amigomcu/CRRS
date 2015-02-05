package core_code.Model;

import java.util.ArrayList;

/**
 * 每个申请者对应的会议安排总表
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
	 * 自定义方法
	 * 判断用户信用度
	 * @return
	 */
	public boolean judgeConfidence() {
		
		//double sum = this.getConferencePlanEntries().size();
		double successFinish = 0; //成功开完
		double miss = 0; //爽约
		
		for(ConferencePlanEntry ce : conferencePlanEntries) {
			if("爽约".equals(ce.getState())) {
				miss += 1;
			} else if("完成".equals(ce.getState())) {
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
