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
	 * ���������
	 * 
	 * @return
	 */
	private JPanel createSavePanel() {
		if (savePanel == null) {
			savePanel = new JPanel();
			savePanel.add(createButton("����"));
			savePanel.add(createButton("����"));
		}
		return savePanel;
	}

	/**
	 * ��ӱ��Ĺ������
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
			title.add("�û����");
			title.add("�û�����");
			title.add("�û�����");
			title.add("�û��绰");
			title.add("���ڲ���");
			title.add("ְλ");

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

			addUpdate.add(new JLabel("�û�����"));
			addUpdate.add(userNameText);

			 

			addUpdate.add(new JLabel("�û��绰"));
			addUpdate.add(userTelText);

			addUpdate.add(new JLabel("���ڲ���"));
			addUpdate.add(userDepartText);
			
			addUpdate.add(new JLabel("ְλ"));
			addUpdate.add(userPositionText);

			addUpdate.add(createButton("���"));
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
	 * �ж�������Ϣ�Ƿ�Ϸ�
	 * �ݶ�Ϊ�ǿռ���
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
	 * ��������Ϣ
	 */
	public void clearAddUserInfo() {
		 userNameText.setText("");
		 userTelText.setText("");
		 userPositionText.setText("");
		 userDepartText.setText("");
		
	}
	/**
	 * ÿ�����һ������ ���һ��model
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void clearModel() {
		model.setDataVector(new Vector(), getTableTitle());
		clearAddUserInfo();

	}
}
