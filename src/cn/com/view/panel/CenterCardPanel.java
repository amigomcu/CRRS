package cn.com.view.panel;

import java.awt.CardLayout;

import javax.swing.JPanel;

import cn.com.view.MainFrame;
import cn.com.view.functinPanel.BookConferenceRoomPanel;
import cn.com.view.functinPanel.CancelBookPanel;
import cn.com.view.functinPanel.FindInfoPanel;
import cn.com.view.functinPanel.IntroducePanel;
import cn.com.view.functinPanel.ManagePanel;
import cn.com.view.functinPanel.TotalConferenceInfoPanel;

/*
 * ������ϵ� ��Ƭ�������
 * 
 * ��Ӧ��Ӧ���ߵİ�ť�����¼�
 * ���� ������Ԥ��ϵͳ ����Ҫ����ʵ��
 * 
 */
public class CenterCardPanel extends JPanel {

	/**
	 * 
	 */

	private MainFrame mainFrame;

	private static final long serialVersionUID = 1L;
	private CardLayout card = new CardLayout();

	private IntroducePanel introducePanel;
	private TotalConferenceInfoPanel totalInfoPanel;
	private FindInfoPanel findInfoPanel;
	private BookConferenceRoomPanel bookConferenceRoomPanel;
	private CancelBookPanel cancelBookPanel;
	private ManagePanel managePanel;

	public CenterCardPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {

		this.setLayout(card);

		this.add("first", createIntroducePanel());
		this.add("total", createTotalConferenceInfoPanel());
		this.add("find", createFindInfoJPanel());
		this.add("book", createBookConferenceRoomPanel());
		this.add("cancel", createCancelBookPanel());
		this.add("manage", createManagePanel());

		card.show(this, "first");
	}

	

	private IntroducePanel createIntroducePanel() {
		
		if(introducePanel == null) {
			introducePanel = new IntroducePanel();
		}
		return introducePanel;
	}

	private CancelBookPanel createCancelBookPanel() {

		if (cancelBookPanel == null) {
			cancelBookPanel = new CancelBookPanel(mainFrame);
		}
		return cancelBookPanel;
	}

	private BookConferenceRoomPanel createBookConferenceRoomPanel() {
		if (bookConferenceRoomPanel == null) {
			bookConferenceRoomPanel = new BookConferenceRoomPanel(mainFrame);
		}
		return bookConferenceRoomPanel;
	}

	private FindInfoPanel createFindInfoJPanel() {

		if (findInfoPanel == null) {
			findInfoPanel = new FindInfoPanel();
		}
		return findInfoPanel;
	}

	private TotalConferenceInfoPanel createTotalConferenceInfoPanel() {
		if (totalInfoPanel == null) {
			totalInfoPanel = new TotalConferenceInfoPanel(mainFrame);
		}
		return totalInfoPanel;
	}
	
	private ManagePanel createManagePanel() {
		if(managePanel == null) {
			managePanel = new ManagePanel();
//			managePanel.add("tianjia", new AddUserPanel());
//			managePanel.add("xiugai", new DelUserPanel());

		}
		return managePanel;
	}

	public void show(String panelName) {
		
		 
		if("cancel".equals(panelName)) {//ˢ�µ�ǰ����
			cancelBookPanel.refreshBookData();
		} else if("book".equals(panelName)) { //����Ԥ��������Ϣ
			bookConferenceRoomPanel.clearApplicationInfoPanel();
			bookConferenceRoomPanel.setModel(null);
		}
		
		card.show(this, panelName);
		 
	}

}
