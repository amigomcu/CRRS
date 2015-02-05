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
	private int y = 0; // ��������������а�ť��λ��

	private String[] westPanelButtonName = {"���", "�ֿ�", "��ѯ", "ԤԼ", "ȡ��" , "����" };

	// ----------------action-----------------------
	private MainFrameMenuItemAction itemAction = new MainFrameMenuItemAction(this);
	private WestPanelAction action = new WestPanelAction(this);
	

	// -------------�˵�ѡ�����------------------
		private JMenuBar menuBar;
		private String[] menuNameArr = { "����",  }; // ����װ
		private String[][] itemNameArr = { { "������Ϣ ",  "�˳�" }};
	// -----------------��ȡ�������Ϣ----------------------------------

	// -------------�õ����л�������Ϣ-------------------------
	private ConferenceRoomDao conferenceRoomDao = FactoryDao
			.getConferenceRoomDao();
	private ConferenceRecordDao conferenceRecordDao = FactoryDao
			.getConferenceRecordDao();

	private HashMap<Integer, ConferenceRoomVo> allConfrenceRoomInfo = new HashMap<Integer, ConferenceRoomVo>();
	private ArrayList<ConferenceRecordVo> bookingRecords = new ArrayList<ConferenceRecordVo>();

	private CurrentTime currentTime; // ��ʱ�� �ж��Ƿ���� config()

	public MainFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;

		config();
		init();
	}

	/*
	 * ��ȡ��������Ϣ ��������ʱ���
	 * 1.�ó����еĻ�������Ϣ HashMap 
	 * 2.�õ�δ�����ܵ�Ԥ����¼ ArrayList
	 * 
	 * �ٶ�Ӧ����ÿһ��������δ�����ܵĿ���ʱ���¼�� map
	 * 
	 * map[i][j] ��ʾ����Ӧ�Ļ����Ҿ������� i ��ĵ� j ��ʱ���״̬
	 * map[i][j] = 1 ��ʾ��ռ��
	 * map[i][j] = 0��ʾ�û����Ҹ�ʱ��ο���
	 * 
	 */
	public void config() {

		// ���л�������Ϣ
		allConfrenceRoomInfo = conferenceRoomDao
				.getAllConferenceRoomInfoToHash();

		// δ������Ԥ����¼
		bookingRecords = conferenceRecordDao.getBookingConferenceRecordVo();

		// ��ȡ map PS:�洢ʱ��ȫ�ϸ��ճ��������ģʽ�Ұ�СʱΪһ����λ, ���Զ�ȡ map ֱ�Ӵ�����
		for (int i = 0; i < bookingRecords.size(); i++) {
			ConferenceRecordVo recordVo = bookingRecords.get(i);
			int conferenceRoomId = recordVo.getConferenceId();

			ConferenceRoomVo roomVo = allConfrenceRoomInfo.get(Integer
					.valueOf(conferenceRoomId));
			roomVo.setFreeTimeMap(recordVo, 1);

		}

	}

	

	/*
	 * ���沼��
	 */
	private void init() {

		// --------------��Ӷ�ʱ��----------------------------
		currentTime = new CurrentTime(this);
		currentTime.start();

		// -------------���۽���------------------------------------
//		try {
//			String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//			UIManager.setLookAndFeel(lookAndFeel);
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e) {
//
//			e.printStackTrace();
//		}
		// ---------------�˵���-----------------------

		this.setJMenuBar(createMenuBar());
		// --------------���沼��---------------------------
		this.setLayout(null);
		this.add(createSplitPane()); // ��ӷָ����
		this.add(createNorthPanel()); // ���ڲ�����Բ���ô��Ҫ�Ĺ���

		// -------------���������---------------------------

		//this.setTitle("������Ԥ��ϵͳ");
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
	 * �����˵���
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
	 * ��������山�ߵ���� ������ع���
	 */
	private NorthPanel createNorthPanel() {

		if (northPanel == null) {
			// ��� action

			northPanel = new NorthPanel();
			northPanel.setBounds(0, 0, 1000, 80);
		}
		return northPanel;
	}

	private JSplitPane createSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true); // ��ˮƽ�ָ������������
			splitPane.setBounds(8, 80, 980, 560); // ����(8, 80) width = 980, high
													// = 560
			// -----------------------------------------
			// splitPane.setBorder(BorderFactory.createCompoundBorder(
			// BorderFactory.createLineBorder(Color.GRAY), null));

			// -------------------����������-----------------------------------------
			splitPane.setLeftComponent(creatWestButPanel());
			splitPane.setRightComponent(createCenterCardPanel());
			splitPane.setDividerSize(6);// ���÷ָ�����һ����С
			splitPane.setDividerLocation(150);// �����������ոճ��ֵ�һ�����
		}
		return splitPane;
	}

	/*
	 * �ұ���ӿ�Ƭ���ֵ����
	 * 
	 * ��Ӧ��ߵ� ��ť
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
	 * ����ķָ��������� ���ܰ�ť���
	 * 
	 * ��ҳ ��ѯ ԤԼ ȡ��
	 */
	private JPanel creatWestButPanel() {

		if (westButPanel == null) {

			// action ������ action

			westButPanel = new JPanel(null); // ��Ҫ
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
	 * �õ����еĻ�������Ϣ����������ʱ���
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
