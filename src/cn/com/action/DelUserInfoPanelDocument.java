package cn.com.action;

import java.util.Vector;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;
import cn.com.view.functinPanel.DelUserPanel;

public class DelUserInfoPanelDocument implements DocumentListener {

	private DelUserPanel panel;
	private UserDao dao = FactoryDao.getUserDao();
	public DelUserInfoPanelDocument(DelUserPanel delUserPanel) {
		this.panel = delUserPanel;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		find();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		find();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		find();

	}
	
	@SuppressWarnings("rawtypes")
	private void find() {
		Vector data = dao.findUserInfoByNameLike(panel.getStuName());
		panel.setModel(data);
		
	}

}
