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

import cn.com.action.DelUserInfoPanelAction;
import cn.com.action.DelUserInfoPanelDocument;

public class DelUserPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private JPanel del;

	private JPanel updatePanel;

	private JTextField stuNameText = new JTextField(10);

	private DelUserInfoPanelAction action = new DelUserInfoPanelAction(this);

	private JScrollPane scroll;

	private JTable table;

	private DefaultTableModel model;

	@SuppressWarnings("rawtypes")
	private Vector title;
	
	private DelUserInfoPanelDocument textAction = new DelUserInfoPanelDocument(this);

	public DelUserPanel() {
		initText();
		init();
	}

	private void initText() {
		javax.swing.text.Document d = stuNameText.getDocument();
		d.addDocumentListener(textAction);
		
	}

	private void init() {
		// this.setBackground(Color.CYAN);
		this.setLayout(new BorderLayout());
		this.add(createJpanel(), BorderLayout.NORTH);
		this.add(createScroll(), BorderLayout.CENTER);
		this.add(createUpdateJPanel(), BorderLayout.SOUTH);

	}

	/**
	 * 根据姓名查询到的删除表单
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

	private JPanel createUpdateJPanel() {
		if (updatePanel == null) {
			updatePanel = new JPanel();
			updatePanel.add(createButton("删除"));

		}
		return updatePanel;
	}

	private JPanel createJpanel() {
		if (del == null) {
			del = new JPanel();
			del.add(new JLabel("用户姓名"));
			del.add(stuNameText);
		}
		return del;
	}

	private JButton createButton(String buttonName) {
		JButton but = new JButton(buttonName);
		but.addActionListener(action);

		return but;
	}

	public String getStuName() {

		return stuNameText.getText();
	}

	@SuppressWarnings("rawtypes")
	public void setModel(Vector data) {
		model.setDataVector(data, getTableTitle());
	}

	/**
	 * 每删除完一次数据 清空一次model
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void clearModel() {
		model.setDataVector(new Vector(), getTableTitle());

	}

	public JTable getTable() {

		return table;
	}

	public DefaultTableModel getModel() {

		return model;
	}


}
