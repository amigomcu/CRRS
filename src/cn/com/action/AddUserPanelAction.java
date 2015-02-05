package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

import cn.com.Vo.UserVo;
import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;
import cn.com.view.functinPanel.AddUserPanel;

public class AddUserPanelAction implements ActionListener {

	private AddUserPanel panel;
	public AddUserPanelAction(AddUserPanel addUserPanel) {
		this.panel = addUserPanel;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent e) {
		UserDao dao = FactoryDao.getUserDao();

		String butName = e.getActionCommand();

		if ("���".equals(butName) && panel.isAvailable()) {
			
			int currentId = dao.findMaxUserID() + 1;
			Vector data = panel.getDataVector();
			
			panel.addRow(currentId+data.size());
		} else if("����".equals(butName)) {
			panel.clearModel();
		}else if ("����".equals(butName)) {
		
			int num = 0;
			Vector data = panel.getDataVector();
			UserVo userVo = new UserVo();
			Vector rowData;
			for (int i = 0; i < data.size(); i++) {
				rowData = (Vector) data.get(i);

				userVo.setUserId(Integer.parseInt(rowData.get(0).toString()));
				userVo.setUserName(rowData.get(1).toString());
				userVo.setUserPassWord(rowData.get(2).toString());
				userVo.setUserTelphoneNo(rowData.get(3).toString());
				userVo.setUserDepartment(rowData.get(4).toString());
				userVo.setUserPosition(rowData.get(5).toString());
 

				boolean flag = dao.addUserByUserVo(userVo);
				if(flag) {
					num++;
				}

			}
			
			if(num == data.size()) {
				// ���model
				JOptionPane.showMessageDialog(null, "��ӳɹ� "+num+" ���û���¼��");
				panel.clearModel();
			}
			
		}

	}

}
