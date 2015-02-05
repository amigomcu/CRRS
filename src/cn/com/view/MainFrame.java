package cn.com.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import cn.com.Vo.ConferenceRecordVo;
import cn.com.Vo.ConferenceRoomVo;
import cn.com.Vo.UserVo;
import cn.com.action.MainFrameMenuItemAction;
import cn.com.action.WestPanelAction;
import cn.com.dao.ConferenceRecordDao;
import cn.com.dao.ConferenceRoomDao;
import cn.com.dao.FactoryDao;
import cn.com.gadget.CurrentTime;
import cn.com.view.panel.CenterCardPanel;
import cn.com.view.panel.NorthPanel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginFrame loginFrame;

	private NorthPanel northPanel;
	private JSplitPane splitPane;
	private JPanel westButPanel;
	private CenterCardPanel centerCardPanel;
	private int y = 0; // 用来控制西面板中按钮的位置

	private String[] westPanelButtonName = {"简介", "现况", "查询", "预约", "取消" , "管理" };

	// ----------------action-----------------------
	private MainFrameMenuItemAction itemAction = new MainFrameMenuItemAction(this);
	private WestPanelAction action = new WestPanelAction(this);
	

	// -------------菜单选项相关------------------
		private JMenuBar menuBar;
		private String[] menuNameArr = { "功能",  }; // 待封装
		private String[][] itemNameArr = { { "个人信息 ",  "退出" }};
	// -----------------读取的相关信息----------------------------------

	// -------------得到所有会议室信息-------------------------
	private ConferenceRoomDao conferenceRoomDao = FactoryDao
			.getConferenceRoomDao();
	private ConferenceRecordDao conferenceRecordDao = FactoryDao
			.getConferenceRecordDao();

	private HashMap<Integer, ConferenceRoomVo> allConfrenceRoomInfo = new HashMap<Integer, ConferenceRoomVo>();
	private ArrayList<ConferenceRecordVo> bookingRecords = new ArrayList<ConferenceRecordVo>();

	private CurrentTime currentTime; // 计时器 判断是否更新 config()

	public MainFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;

		config();
		init();
	}

	/*
	 * 读取会议室信息 创建空闲时间表
	 * 1.得出所有的会议室信息 HashMap 
	 * 2.得到未来两周的预定记录 ArrayList
	 * 
	 * 再对应处理每一个会议室未来两周的空闲时间记录表 map
	 * 
	 * map[i][j] 表示其相应的会议室距离今天第 i 天的第 j 个时间段状态
	 * map[i][j] = 1 表示被占用
	 * map[i][j] = 0表示该会议室该时间段空闲
	 * 
	 */
	public void config() {

		// 所有会议室信息
		allConfrenceRoomInfo = conferenceRoomDao
				.getAllConferenceRoomInfoToHash();

		// 未来两周预定记录
		bookingRecords = conferenceRecordDao.getBookingConferenceRecordVo();

		// 读取 map PS:存储时完全严格按照朝九晚五的模式且半小时为一个单位, 所以读取 map 直接处理即可
		for (int i = 0; i < bookingRecords.size(); i++) {
			ConferenceRecordVo recordVo = bookingRecords.get(i);
			int conferenceRoomId = recordVo.getConferenceId();

			ConferenceRoomVo roomVo = allConfrenceRoomInfo.get(Integer
					.valueOf(conferenceRoomId));
			roomVo.setFreeTimeMap(recordVo, 1);

		}

	}

	

	/*
	 * 界面布局
	 */
	private void init() {

		// --------------添加定时器----------------------------
		currentTime = new CurrentTime(this);
		currentTime.start();

		// -------------美观界面------------------------------------
//		try {
//			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//			UIManager.setLookAndFeel(lookAndFeel);
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e) {
//
//			e.printStackTrace();
//		}
		// ---------------菜单栏-----------------------

		this.setJMenuBar(createMenuBar());
		// --------------界面布局---------------------------
		this.setLayout(null);
		this.add(createSplitPane()); // 添加分割面板
		this.add(createNorthPanel()); // 用于补充相对不怎么重要的功能

		// -------------创建主面板---------------------------

		//this.setTitle("会议室预订系统");
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    
		this.setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			for (int i = 0; i < menuNameArr.length; i++) {
				menuBar.add(createMenu(menuNameArr[i], itemNameArr[i]));
			}
		}
		return menuBar;
	}

	private JMenu createMenu(String menuName, String[] itemArr) {
		JMenu menu = new JMenu(menuName);
		for (int i = 0; i < itemArr.length; i++) {
			menu.add(createItem(itemArr[i]));
		}
		return menu;
	}

	/**
	 * 创建菜单项
	 * 
	 * @param itemName
	 * @return
	 */

	private JMenuItem createItem(String itemName) {
		JMenuItem item = new JMenuItem(itemName);
		item.addActionListener(itemAction);

		return item;
	}

	/*
	 * 创建主面板北边的面板 补充相关功能
	 */
	private NorthPanel createNorthPanel() {

		if (northPanel == null) {
			// 相关 action

			northPanel = new NorthPanel();
			northPanel.setBounds(0, 0, 1000, 80);
		}
		return northPanel;
	}

	private JSplitPane createSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true); // 先水平分割成上下两部分
			splitPane.setBounds(8, 80, 980, 560); // 坐标(8, 80) width = 980, high
													// = 560
			// -----------------------------------------
			// splitPane.setBorder(BorderFactory.createCompoundBorder(
			// BorderFactory.createLineBorder(Color.GRAY), null));

			// -------------------添加左右组件-----------------------------------------
			splitPane.setLeftComponent(creatWestButPanel());
			splitPane.setRightComponent(createCenterCardPanel());
			splitPane.setDividerSize(6);// 设置分割条的一个大小
			splitPane.setDividerLocation(150);// 设置两个面板刚刚出现的一个配比
		}
		return splitPane;
	}

	/*
	 * 右边添加卡片布局的面板
	 * 
	 * 响应左边的 按钮
	 */
	private CenterCardPanel createCenterCardPanel() {

		if (centerCardPanel == null) {
			centerCardPanel = new CenterCardPanel(this);
			centerCardPanel.setBorder(BorderFactory
					.createLineBorder(Color.LIGHT_GRAY));
		}
		return centerCardPanel;
	}

	/*
	 * 下面的分割面板西面的 功能按钮面板
	 * 
	 * 首页 查询 预约 取消
	 */
	private JPanel creatWestButPanel() {

		if (westButPanel == null) {

			// action 西面板的 action

			westButPanel = new JPanel(null); // 重要
			westButPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

			for (int i = 0; i < westPanelButtonName.length; i++) {
				JButton but = createButton(westPanelButtonName[i]);
				westButPanel.add(but);

			}

		}
		return westButPanel;
	}

	private JButton createButton(String butName) {
		JButton but = new JButton(butName);
		but.setBounds(20, y, 100, 60);
		y += 100;

		// add Action
		but.addActionListener(action);
		return but;
	}

	public int getCurrentUserId() {
		return loginFrame.getUserId();
	}

	/*
	 * 得到所有的会议室信息：包括空闲时间表
	 */

	public HashMap<Integer, ConferenceRoomVo> getAllConferenceRoomInfo() {

		return this.allConfrenceRoomInfo;

	}
	
	public CenterCardPanel getCenterCardPanel() {
		return centerCardPanel;
	}

	public UserVo getCurrentUser() {
		return loginFrame.getUserVo();
	}


}
