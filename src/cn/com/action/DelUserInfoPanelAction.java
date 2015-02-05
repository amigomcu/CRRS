package cn.com.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.com.dao.FactoryDao;
import cn.com.dao.UserDao;
import cn.com.view.functinPanel.DelUserPanel;

public class DelUserInfoPanelAction implements ActionListener {

	private DelUserPanel panel;
	public DelUserInfoPanelAction(DelUserPanel delUserPanel) {
		this.panel = delUserPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonName = e.getActionCommand();
		
		UserDao dao = FactoryDao.getUserDao();
		
		if("ɾ��".equals(buttonName)) {
			JTable table = panel.getTable();
			DefaultTableModel model = panel.getModel();
			
			int[] rowIndex = table.getSelectedRows(); //�õ�ѡ�������е��е�����
			for(int i = rowIndex.length-1; i >= 0; i--) {
				//System.out.println("test "+rowIndex[i]);
				//���ÿһ�����ݵĵ�һ��, ��Ϊѧ�� ID ���
				
				 int delUserId = Integer.parseInt(table.getValueAt(rowIndex[i], 0).toString()) ;
				 //��ɾ��¼,��ɾ�û�...�Ѿ���ͬһ������д���
				 dao.delUserInfobyId(delUserId);
				 
				 model.removeRow(rowIndex[i]); //ɾ��һ��, ���һ��
			}

			if(rowIndex.length > 0) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
			} else {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ��ѧ��");
			}
			
		}
	}

}
