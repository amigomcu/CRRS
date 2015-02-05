package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sunking.swing.JDatePicker;

import cn.com.Vo.ConferenceRoomVo;
import cn.com.action.TotolInfoPanelFindFreeTimeAction;
import cn.com.gadget.EvenOddRenderer;
import cn.com.view.MainFrame;

/*
 * 总体会议室信息
 * 
 * 主要包含两张表
 * 第一张：所有会议室信息
 * 第二张：空闲时间表的查询
 * 
 */
public class TotalConferenceInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;

	// -----------主版面相关-------------------------------

	private JPanel conferenceRoomPanel;
	private JPanel conferenceRoomFreeTimePanel;

	private JScrollPane conferenceRoomScroll;
	private JTable conferenceRoomTable;
	private String[] conferenceRoomInfoTitle = { "会议室编号", "会议室地点", "会议室容量" };

	private JScrollPane conferenceRoomFreeTimeScroll;
	private JTable conferenceRoomFreeTimeTable;
	private DefaultTableModel conferenceRoomFreeTimeModel;
	@SuppressWarnings("rawtypes")
	private Vector conferenceRoomFreeTimeTitle;

	// -----------------关于JTable----------------------------
	private EvenOddRenderer evenOddRenderer;// JTable渲染器 = new
											// EvenOddRenderer();

	private JPanel findFreeTimePanel;
	//private JTextField findDateText = new JTextField(11);
	JDatePicker datePickerTo;

	private TotolInfoPanelFindFreeTimeAction findFreeTimeAction = new TotolInfoPanelFindFreeTimeAction(
			this);

	// ------------------------------------------------------------
	private Object[][] allConfrenceRoomInfoBody;

	public TotalConferenceInfoPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		init();
	}

	private void init() {

		this.setLayout(new GridLayout(2, 1));
		this.add(createConferenceRoomPanel());
		this.add(createConferenceRoomFreeTimePanel());

	}

	private JPanel createConferenceRoomFreeTimePanel() {
		if (conferenceRoomFreeTimePanel == null) {
			conferenceRoomFreeTimePanel = new JPanel(new BorderLayout());
			conferenceRoomFreeTimePanel.add(createfindFreeTimeByDate(),
					BorderLayout.NORTH);
			conferenceRoomFreeTimePanel.add(
					createConferenceRoomFreeTimeScroll(), BorderLayout.CENTER);
			conferenceRoomFreeTimePanel.add(new JLabel("红色表示被占用"),
					BorderLayout.SOUTH);

		}
		return conferenceRoomFreeTimePanel;
	}

	private JScrollPane createConferenceRoomFreeTimeScroll() {
		if (conferenceRoomFreeTimeScroll == null) {
			conferenceRoomFreeTimeScroll = new JScrollPane(
					creatConferenceRoomFreeTimeTable());
		}
		return conferenceRoomFreeTimeScroll;
	}

	private JTable creatConferenceRoomFreeTimeTable() {
		if (conferenceRoomFreeTimeTable == null) {
			conferenceRoomFreeTimeModel = new DefaultTableModel(
					getConferenceRoomFreeTimeTitle(), 0);
			conferenceRoomFreeTimeTable = new JTable(
					conferenceRoomFreeTimeModel);
			conferenceRoomFreeTimeTable.setDefaultRenderer(Object.class,
					creatEvenOddRenderer());
		}
		return conferenceRoomFreeTimeTable;
	}

	/*
	 * 颜色渲染
	 */
	private EvenOddRenderer creatEvenOddRenderer() {
		if (evenOddRenderer == null) {
			evenOddRenderer = new EvenOddRenderer();
		}
		return evenOddRenderer;
	}

	/*
	 * 空闲时间表的标题
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector getConferenceRoomFreeTimeTitle() {
		if (conferenceRoomFreeTimeTitle == null) {
			conferenceRoomFreeTimeTitle = new Vector();
			conferenceRoomFreeTimeTitle.add("会议室编号");

			conferenceRoomFreeTimeTitle.add("9点-9点半");
			conferenceRoomFreeTimeTitle.add("9点半-10点");
			conferenceRoomFreeTimeTitle.add("10点-10点半");
			conferenceRoomFreeTimeTitle.add("10点半-11点");
			conferenceRoomFreeTimeTitle.add("11点-11点半");
			conferenceRoomFreeTimeTitle.add("11点半-12点");
			conferenceRoomFreeTimeTitle.add("2点-2点半");
			conferenceRoomFreeTimeTitle.add("2点半-3点");
			conferenceRoomFreeTimeTitle.add("3点-4点半");
			conferenceRoomFreeTimeTitle.add("4点半-5点");

		}
		return conferenceRoomFreeTimeTitle;
	}

	/*
	 * 查询空闲时间表
	 */
	private JPanel createfindFreeTimeByDate() {
		if (findFreeTimePanel == null) {
			findFreeTimePanel = new JPanel();

			findFreeTimePanel.add(new JLabel("请输入查询日期"));

			Date date = new Date();
			datePickerTo = new JDatePicker(JDatePicker.STYLE_CN_DATE1, date);
			
			findFreeTimePanel.add(datePickerTo);
			//String currentDateStr = getCurrentDateStr();
			//findDateText.setText(currentDateStr);

			findFreeTimePanel.add(createButton("日期查询"));
		}
		return findFreeTimePanel;
	}

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);
		but.addActionListener(findFreeTimeAction);
		return but;
	}

	/*
	 * 会议室详细信息面板
	 */
	private JPanel createConferenceRoomPanel() {
		if (conferenceRoomPanel == null) {
			conferenceRoomPanel = new JPanel(new BorderLayout());
			conferenceRoomPanel
					.add(new JLabel("会议室详细信息列表"), BorderLayout.NORTH);
			conferenceRoomPanel.add(createConferenceRoomScroll(),
					BorderLayout.CENTER);

		}
		return conferenceRoomPanel;
	}

	private JScrollPane createConferenceRoomScroll() {
		if (conferenceRoomScroll == null) {
			conferenceRoomScroll = new JScrollPane(createConferenceRoomTable());
		}
		return conferenceRoomScroll;
	}

	private JTable createConferenceRoomTable() {
		if (conferenceRoomTable == null) {

			conferenceRoomTable = new JTable(getConferenceRoomInfoBody(),
					conferenceRoomInfoTitle);
		}
		return conferenceRoomTable;
	}

	private Object[][] getConferenceRoomInfoBody() {

		if (allConfrenceRoomInfoBody == null) {

			allConfrenceRoomInfoBody = new Object[this.mainFrame
					.getAllConferenceRoomInfo().size()][conferenceRoomInfoTitle.length];

			int index = 0;
			for (ConferenceRoomVo room : this.mainFrame
					.getAllConferenceRoomInfo().values()) {
				allConfrenceRoomInfoBody[index][0] = room.getConferenceId();
				allConfrenceRoomInfoBody[index][1] = room
						.getConferenceLocation();
				allConfrenceRoomInfoBody[index][2] = room
						.getConferenceCapital();

				index++;
			}
		}

		return allConfrenceRoomInfoBody;
	}

	/*
	 * 正则判断 查找的日期
	 * 
	 * YYYY-MM-DD
	 */
	public String getCheckDate() {
//		String str = findDateText.getText();
//		boolean flag = str
//				.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"); // 判断年
//
//		if (!flag) {
//			str = "";
//		}
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		Date date = null;
		try {
			date = datePickerTo.getSelectedDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(f.format(date));

		return f.format(date);

	}

	/*
	 * 重置 JTable中的信息 如果该空闲时间段被占用，则变成红色
	 */
	@SuppressWarnings("rawtypes")
	public void setFreeTimeModel(Vector data, int[][] arry) {
		conferenceRoomFreeTimeModel.setDataVector(data,
				getConferenceRoomFreeTimeTitle());
		evenOddRenderer.setArry(arry);
	}

	/*
	 * 清空空闲时间段的 JTable 的信息
	 */
	public void clearFreeTimeModel() {

		@SuppressWarnings("rawtypes")
		Vector data = new Vector();
		data.clear();
		conferenceRoomFreeTimeModel.setDataVector(data,
				getConferenceRoomFreeTimeTitle());
	}

	public MainFrame getMainFrame() {

		return mainFrame;
	}

	@SuppressWarnings("unused")
	private String getCurrentDateStr() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateStr = f.format(new Date());

		return currentDateStr;

	}

}
