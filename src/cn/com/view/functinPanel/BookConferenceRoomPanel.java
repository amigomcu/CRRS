package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.Vo.ApplyConferenceRoomInfoVo;
import cn.com.action.BookConferenceRoomPanelAction;
import cn.com.gadget.TimeClock;
import cn.com.view.MainFrame;

/*
 * ����Ԥ��������
 */

public class BookConferenceRoomPanel extends JPanel {

	private TimeClock clock; // ��ʱ�� ���ڶ�̬��ʾ��ǰʱ��

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public MainFrame mainFrame;

	private JPanel applicatPanel;
	private JPanel applicationInfoPanel;

	private JPanel canChoiceRoomPanel;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTable table;
	private JPanel submitPanel;
	@SuppressWarnings("rawtypes")
	private Vector title;

	private JTextField attendConferenceNumText = new JTextField(5);
	private JTextField conferenceThemeText = new JTextField(10);

	private BookConferenceRoomPanelAction action = new BookConferenceRoomPanelAction(
			this);

	
	public BookConferenceRoomPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		init();

	}

	private void init() {

		// -----------��Ӽ�ʱ��-----------------------
		clock = new TimeClock(this);
		clock.start();

		// -----------���������-----------------------
		this.setLayout(new BorderLayout());
		this.add(createApplicatPanel(), BorderLayout.NORTH);
		this.add(createCanChoiceRoomPanel(), BorderLayout.CENTER);

	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	private JPanel createCanChoiceRoomPanel() {
		if (canChoiceRoomPanel == null) {
			canChoiceRoomPanel = new JPanel(new BorderLayout());

			canChoiceRoomPanel.add(new JLabel("����ѡ��Ļ�����"), BorderLayout.NORTH);
			canChoiceRoomPanel.add(createScrollPanel(), BorderLayout.CENTER);
			canChoiceRoomPanel.add(createSubmitPanel(), BorderLayout.SOUTH);
		}
		return canChoiceRoomPanel;
	}

	private JPanel createSubmitPanel() {
		if (submitPanel == null) {
			submitPanel = new JPanel();
			submitPanel.add(createButton("ȷ��"));
		}
		return submitPanel;
	}

	private JScrollPane createScrollPanel() {
		if (scroll == null) {
			scroll = new JScrollPane(createTable());
		}
		return scroll;
	}

	private JTable createTable() {
		if (table == null) {
			model = new DefaultTableModel(getTableTitle(), 0);
			table = new JTable(model);
		}
		return table;
	}

	private JPanel createApplicatPanel() {
		if (applicatPanel == null) {
			applicatPanel = new JPanel(new BorderLayout());
			applicatPanel.add(new JLabel("�������ϸ��Ϣ"), BorderLayout.NORTH);
			applicatPanel
					.add(createApplicationInfoPanel(), BorderLayout.CENTER);
			applicatPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		}
		return applicatPanel;
	}

	private JPanel buttonPanel;

	private JPanel createButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();

			buttonPanel.add(createButton("�ύ"));
			buttonPanel.add(createButton("����"));
			
		}
		return buttonPanel;
	}

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);

		but.addActionListener(action);
		return but;
	}

	/*
	 * ������д���������
	 */

	private JTextField conferenceDateText = new JTextField(10);
	private JLabel currentDateLabel = new JLabel();

	private JPanel startTimePanel;
	private JPanel endTimePanel;

	private JTextField startHourText = new JTextField(3);
	private JTextField startMinuteText = new JTextField(3);

	private JTextField endHourText = new JTextField(3);
	private JTextField endMinuteText = new JTextField(3);

	private JPanel createApplicationInfoPanel() {

		if (applicationInfoPanel == null) {
			
			applicationInfoPanel = new JPanel();
			applicationInfoPanel.setLayout(new GridLayout(3,3));

			applicationInfoPanel.add(new JLabel("��������"));
			applicationInfoPanel.add(conferenceThemeText);
			
			

			applicationInfoPanel.add(new JLabel("��������"));
			applicationInfoPanel.add(attendConferenceNumText);

			applicationInfoPanel.add(new JLabel("��ǰʱ��"));
			applicationInfoPanel.add(currentDateLabel);
			String currentTimeStr = getCurrentTimeStr();
			currentDateLabel.setText(currentTimeStr);

			applicationInfoPanel.add(new JLabel("��������"));
			applicationInfoPanel.add(conferenceDateText);
			String currentDateStr = getCurrentDateStr();
			conferenceDateText.setText(currentDateStr);

			applicationInfoPanel.add(new JLabel("��ʼʱ��"));
			applicationInfoPanel.add(createStartTimePanel());

			applicationInfoPanel.add(new JLabel("����ʱ��"));
			applicationInfoPanel.add(createEndTimePanel());
			//endTimePanel.setBounds(10, 80, 10, 3);

		}
		return applicationInfoPanel;
	}

	private JPanel createEndTimePanel() {
		if (endTimePanel == null) {
			endTimePanel = new JPanel(new GridLayout(1, 4));
			endTimePanel.add(endHourText);
			endTimePanel.add(new JLabel("ʱ"));

			endTimePanel.add(endMinuteText);
			endTimePanel.add(new JLabel("��"));

		}
		return endTimePanel;
	}

	private JPanel createStartTimePanel() {
		if (startTimePanel == null) {
			startTimePanel = new JPanel(new GridLayout(1, 4));
			startTimePanel.add(startHourText);
			startTimePanel.add(new JLabel("ʱ"));

			startTimePanel.add(startMinuteText);
			startTimePanel.add(new JLabel("��"));

		}
		return startTimePanel;
	}

	/*
	 * �õ���ǰʱ����ַ���
	 */
	private String getCurrentTimeStr() {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentTimeStr = f.format(new Date());

		return currentTimeStr;
	}

	private String getCurrentDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = f.format(new Date());

		return currentDateStr;

	}

	public ApplyConferenceRoomInfoVo getApplicationInfo() {

		int userId = mainFrame.getCurrentUserId();

		// int conferenceId = -1; //δ����֮ǰ,����Ϊ�Ƿ�

		String conferenceTheme = conferenceThemeText.getText().trim();
		String attendNum = attendConferenceNumText.getText().trim();
		String conferenceDate = conferenceDateText.getText().trim();
		String startHour = startHourText.getText().trim();
		String startMinute = startMinuteText.getText().trim();
		String endHour = endHourText.getText().trim();
		String endMinute = endMinuteText.getText().trim();

		/*
		 * �ж�����ĺϷ���....�ǵò���
		 * 
		 * ����:
		 * 
		 * ���Ϸ��ĸ�ֵ: һ������Ϊ�մ�...
		 * 
		 * Num:����Ϊ���� Date:yyyy-mm-dd Time:HH:mm
		 */
		/*
		 * 1-999 ʵ�����ֻ�� 800 ��
		 */
		if (!attendNum.matches("^\\+?[1-9][0-9]{0,2}$")) {
			attendNum = "";
		}

		/*
		 * �����Ƿ�Ϸ� YYYY-MM-DD
		 */
		if (!conferenceDate
				.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$")) {
			conferenceDate = "";
		}

		/*
		 * 9.10.11.12 14.15.16.17
		 */
		if (!startHour.matches("^([9]|[1][0-2]|[1][4-7])$")) {
			startHour = "";
		}

		if (!startMinute.matches("^([0-9]|[12345][0-9]|60)$")) {
			startMinute = "";
		}

		if (!endHour.matches("^([9]|[1][0-2]|[1][4-7])$")) {
			endHour = "";
		}

		if (!endMinute.matches("^([0-9]|[12345][0-9]|60)$")) {
			endMinute = "";
		}

		ApplyConferenceRoomInfoVo applicationInfo = new ApplyConferenceRoomInfoVo(
				userId, conferenceTheme, attendNum, conferenceDate, startHour,
				startMinute, endHour, endMinute);

		return applicationInfo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Vector getTableTitle() {
		if (title == null) {
			title = new Vector();
			title.add("�����ұ��");
			title.add("�����ҵص�");
			title.add("����������");
		}
		return title;
	}

	@SuppressWarnings("rawtypes")
	public void setModel(Vector data) {
		model.setDataVector(data, getTableTitle());
	}

	public JTable getTable() {
		return table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setCurrentTimeLable(String currentTimeStr) {

		currentDateLabel.setText(currentTimeStr);
	}

	/*
	 * ����������Ϣ���
	 */
	public void clearApplicationInfoPanel() {
		conferenceThemeText.setText("");
		attendConferenceNumText.setText("");
		
		String currentDateStr = getCurrentDateStr();
		conferenceDateText.setText(currentDateStr);
		
		startHourText.setText("");
		startMinuteText.setText("");
		endHourText.setText("");
		endMinuteText.setText("");
	}
	
	/*
	 * �õ���ǰ����
	 */
	public JLabel getCurrentDateLabel() {
		return currentDateLabel;
	}
	
}
