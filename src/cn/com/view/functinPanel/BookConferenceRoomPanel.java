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
 * 用于预定会议室
 */

public class BookConferenceRoomPanel extends JPanel {

	private TimeClock clock; // 计时器 用于动态显示当前时间

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

		// -----------添加计时器-----------------------
		clock = new TimeClock(this);
		clock.start();

		// -----------添加相关面板-----------------------
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

			canChoiceRoomPanel.add(new JLabel("可以选择的会议室"), BorderLayout.NORTH);
			canChoiceRoomPanel.add(createScrollPanel(), BorderLayout.CENTER);
			canChoiceRoomPanel.add(createSubmitPanel(), BorderLayout.SOUTH);
		}
		return canChoiceRoomPanel;
	}

	private JPanel createSubmitPanel() {
		if (submitPanel == null) {
			submitPanel = new JPanel();
			submitPanel.add(createButton("确定"));
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
			applicatPanel.add(new JLabel("调查表详细信息"), BorderLayout.NORTH);
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

			buttonPanel.add(createButton("提交"));
			buttonPanel.add(createButton("重置"));
			
		}
		return buttonPanel;
	}

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);

		but.addActionListener(action);
		return but;
	}

	/*
	 * 创建填写申请表的面板
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

			applicationInfoPanel.add(new JLabel("会议主题"));
			applicationInfoPanel.add(conferenceThemeText);
			
			

			applicationInfoPanel.add(new JLabel("参与人数"));
			applicationInfoPanel.add(attendConferenceNumText);

			applicationInfoPanel.add(new JLabel("当前时间"));
			applicationInfoPanel.add(currentDateLabel);
			String currentTimeStr = getCurrentTimeStr();
			currentDateLabel.setText(currentTimeStr);

			applicationInfoPanel.add(new JLabel("会议日期"));
			applicationInfoPanel.add(conferenceDateText);
			String currentDateStr = getCurrentDateStr();
			conferenceDateText.setText(currentDateStr);

			applicationInfoPanel.add(new JLabel("开始时间"));
			applicationInfoPanel.add(createStartTimePanel());

			applicationInfoPanel.add(new JLabel("结束时间"));
			applicationInfoPanel.add(createEndTimePanel());
			//endTimePanel.setBounds(10, 80, 10, 3);

		}
		return applicationInfoPanel;
	}

	private JPanel createEndTimePanel() {
		if (endTimePanel == null) {
			endTimePanel = new JPanel(new GridLayout(1, 4));
			endTimePanel.add(endHourText);
			endTimePanel.add(new JLabel("时"));

			endTimePanel.add(endMinuteText);
			endTimePanel.add(new JLabel("分"));

		}
		return endTimePanel;
	}

	private JPanel createStartTimePanel() {
		if (startTimePanel == null) {
			startTimePanel = new JPanel(new GridLayout(1, 4));
			startTimePanel.add(startHourText);
			startTimePanel.add(new JLabel("时"));

			startTimePanel.add(startMinuteText);
			startTimePanel.add(new JLabel("分"));

		}
		return startTimePanel;
	}

	/*
	 * 得到当前时间的字符串
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

		// int conferenceId = -1; //未分配之前,设置为非法

		String conferenceTheme = conferenceThemeText.getText().trim();
		String attendNum = attendConferenceNumText.getText().trim();
		String conferenceDate = conferenceDateText.getText().trim();
		String startHour = startHourText.getText().trim();
		String startMinute = startMinuteText.getText().trim();
		String endHour = endHourText.getText().trim();
		String endMinute = endMinuteText.getText().trim();

		/*
		 * 判断输入的合法性....记得补充
		 * 
		 * 正则:
		 * 
		 * 不合法的赋值: 一律重置为空串...
		 * 
		 * Num:输入为数字 Date:yyyy-mm-dd Time:HH:mm
		 */
		/*
		 * 1-999 实际最多只能 800 人
		 */
		if (!attendNum.matches("^\\+?[1-9][0-9]{0,2}$")) {
			attendNum = "";
		}

		/*
		 * 日期是否合法 YYYY-MM-DD
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
			title.add("会议室编号");
			title.add("会议室地点");
			title.add("会议室容量");
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
	 * 重置申请信息面板
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
	 * 得到当前日期
	 */
	public JLabel getCurrentDateLabel() {
		return currentDateLabel;
	}
	
}
