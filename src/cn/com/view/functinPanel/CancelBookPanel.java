package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.action.CancelBookPanelAction;
import cn.com.dao.ConferenceRecordDao;
import cn.com.dao.FactoryDao;
import cn.com.view.MainFrame;

/*
 * ����ȡ���Լ�Ԥ���Ļ�����
 * 
 */

public class CancelBookPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;                   

	private ConferenceRecordDao conferenceRecordDao = FactoryDao
			.getConferenceRecordDao();
	@SuppressWarnings("rawtypes")
	private Vector<Vector> currentUserBookingRecords;

	public CancelBookPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		config(); // ��ȡ�û�������Ϣ
		init();
		setModel(currentUserBookingRecords);
		
	}

	private void config() {
		currentUserBookingRecords = conferenceRecordDao
				.getBookingConferenceRecordVoByUserId(mainFrame
						.getCurrentUserId());
		//System.out.println("config " +  currentUserBookingRecords.size());
	}
	
	/*
	 * ÿ�ε��ȡ�����ʱˢ������
	 */
	public void refreshBookData() {
		
		config();
		
		setModel(currentUserBookingRecords);
		
	}

	private void init() {

		this.setLayout(new BorderLayout());
		this.add(createInfoPanel(), BorderLayout.NORTH);
		this.add(createScroll(), BorderLayout.CENTER);
		this.add(creatButtonPanel(), BorderLayout.SOUTH);

	}

	private JPanel buttonPanel;

	private JPanel creatButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(createButton("ɾ��"));
		}
		return buttonPanel;
	}

	private CancelBookPanelAction action = new CancelBookPanelAction(this);

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);
		but.addActionListener(action);
		return but;
	}

	private JScrollPane allRecordScroll;
	private DefaultTableModel model;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private Vector title;

	private JScrollPane createScroll() {
		if (allRecordScroll == null) {
			allRecordScroll = new JScrollPane(createTable());
		}
		return allRecordScroll;
	}

	private JTable createTable() {
		if (table == null) {
			model = new DefaultTableModel(getTableTitle(), 5);
			table = new JTable(model);
			setTableColumnWidth();
		}
		return table;
	}

	private void setTableColumnWidth() {

		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Vector getTableTitle() {
		if (title == null) {
			title = new Vector();
			title.add("�����ұ��");
			// title.add("�����ҵص�");
			title.add("�μ�����");
			title.add("��ʼ����");
			title.add("��������");
			title.add("��������");
		}
		return title;
	}

	@SuppressWarnings("rawtypes")
	public void setModel(Vector data) {
		model.setDataVector(data, getTableTitle());
		setTableColumnWidth();
	}

	private JPanel userInfoPanel;

	private JPanel createInfoPanel() {

		if (userInfoPanel == null) {
			userInfoPanel = new JPanel();
		}
		return userInfoPanel;
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
