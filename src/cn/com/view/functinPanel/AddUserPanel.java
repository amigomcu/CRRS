package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.com.Vo.UserVo;
import cn.com.action.AddUserPanelAction;

public class AddUserPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel savePanel;

	private JPanel addUpdate;

	private JTextField userNameText = new JTextField(10);
	
	private JTextField userTelText = new JTextField(10);

	private JTextField userPositionText = new JTextField(10);
	
	private JTextField userDepartText = new JTextField(10);


	private JScrollPane scroll;

	private JTable table;

	private DefaultTableModel model;

	@SuppressWarnings("rawtypes")
	private Vector title;

	@SuppressWarnings("rawtypes")
	Vector rowData;

	UserVo userVo;

	private AddUserPanelAction action = new AddUserPanelAction(this);


	public AddUserPanel() {
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.add(createPanel(), BorderLayout.NORTH);
		this.add(createScroll(), BorderLayout.CENTER);
		this.add(createSavePanel(), BorderLayout.SOUTH);
	}

	
	/**
	 * 批量保存键
	 * 
	 * @return
	 */
	private JPanel createSavePanel() {
		if (savePanel == null) {
			savePanel = new JPanel();
			savePanel.add(createButton("保存"));
			savePanel.add(createButton("重置"));
		}
		return savePanel;
	}

	/**
	 * 添加表单的滚动面板
	 * 
	 * @return
	 */
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
		}
		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Vector getTableTitle() {
		if (title == null) {
			title = new Vector();
			title.add("用户编号");
			title.add("用户姓名");
			title.add("用户密码");
			title.add("用户电话");
			title.add("所在部门");
			title.add("职位");

		}
		return title;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public void addRow(int userId) {

		rowData = new Vector();

		rowData.add(userId);
		rowData.add(userNameText.getText());
		rowData.add("111111");
		rowData.add(userTelText.getText());
		rowData.add(userDepartText.getText());
		rowData.add(userPositionText.getText());

		model.addRow(rowData);

	}

	private JPanel createPanel() {
		if (addUpdate == null) {
			addUpdate = new JPanel();

			addUpdate.add(new JLabel("用户姓名"));
			addUpdate.add(userNameText);

			 

			addUpdate.add(new JLabel("用户电话"));
			addUpdate.add(userTelText);

			addUpdate.add(new JLabel("所在部门"));
			addUpdate.add(userDepartText);
			
			addUpdate.add(new JLabel("职位"));
			addUpdate.add(userPositionText);

			addUpdate.add(createButton("添加"));
		}
		return addUpdate;
	}

	private JButton createButton(String butName) {
		JButton but = new JButton(butName);
		but.addActionListener(action);
		return but;
	}


	@SuppressWarnings("rawtypes")
	public Vector getDataVector() {
		return model.getDataVector();
	}

	/*
	 * 判断输入信息是否合法
	 * 暂定为非空即可
	 */
	public boolean isAvailable() {
		if("".equals(userNameText.getText().trim())
				|| "".equals(userTelText.getText().trim())
				|| "".equals(userPositionText.getText().trim())
				|| "".equals(userDepartText.getText().trim())) {
			return false;
		}
		return true;
	}
	/*
	 * 清空添加信息
	 */
	public void clearAddUserInfo() {
		 userNameText.setText("");
		 userTelText.setText("");
		 userPositionText.setText("");
		 userDepartText.setText("");
		
	}
	/**
	 * 每添加完一次数据 清空一次model
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void clearModel() {
		model.setDataVector(new Vector(), getTableTitle());
		clearAddUserInfo();

	}
}
