package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.action.FindInfoPanelAction;
import cn.com.action.FindInfoPanelComBoxAction;

/*
 * 用于查询会议信息
 * 
 */
public class FindInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane scroll;
	private DefaultTableModel model;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private Vector title;

	private String[] boxName = { "用户编号", "用户姓名", "会议室编号", "会议日期", "会议室地点" };
	@SuppressWarnings("rawtypes")
	private JComboBox comBox;
	private JPanel findPanel;
	private JTextField findText = new JTextField(11);

	public FindInfoPanel() {
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.add(createFindPanel(), BorderLayout.NORTH);
		this.add(createScroll(), BorderLayout.CENTER);

	}

	private JScrollPane createScroll() {
		if (scroll == null) {
			scroll = new JScrollPane(createTable());
		}
		return scroll;
	}

	private JTable createTable() {
		if (table == null) {
			model = new DefaultTableModel(getTableTitle(), 0);
			table = new JTable(model);

			setTableColumnWidth();

		}
		return table;
	}

	private void setTableColumnWidth() {

		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(13);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Vector getTableTitle() {
		if (title == null) {
			title = new Vector();
			title.add("用户姓名");
			title.add("会议室编号");
			title.add("会议室地点");
			title.add("参加人数");
			title.add("会议主题");
			title.add("开始日期");
			title.add("结束日期");

		}
		return title;
	}

	@SuppressWarnings("rawtypes")
	public void setModel(Vector data) {

		model.setDataVector(data, getTableTitle());
		setTableColumnWidth();
	}

	private JPanel createFindPanel() {
		if (findPanel == null) {
			findPanel = new JPanel();
			findPanel.add(createComboBox(boxName));
			findPanel.add(findText);
			findPanel.add(createButton("查询"));
		}
		return findPanel;
	}

	private FindInfoPanelAction action = new FindInfoPanelAction(this);

	private JButton createButton(String butName) {
		JButton but = new JButton(butName);
		but.addActionListener(action);
		return but;
	}

	private FindInfoPanelComBoxAction comBoxAction = new FindInfoPanelComBoxAction(
			this);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox createComboBox(String[] boxName) {
		if (comBox == null) {
			comBox = new JComboBox(boxName);
			comBox.addActionListener(comBoxAction);
		}
		return comBox;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comBox;
	}

	public String getFindText() {
		return findText.getText();
	}

	public void setFindText(String textInfo) {

		findText.setText(textInfo);
	}
}
