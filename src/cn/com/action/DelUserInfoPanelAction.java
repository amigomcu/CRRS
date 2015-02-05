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
		
		if("删除".equals(buttonName)) {
			JTable table = panel.getTable();
			DefaultTableModel model = panel.getModel();
			
			int[] rowIndex = table.getSelectedRows(); //得到选定的所有的行的索引
			for(int i = rowIndex.length-1; i >= 0; i--) {
				//System.out.println("test "+rowIndex[i]);
				//获得每一行数据的第一列, 即为学生 ID 编号
				
				 int delUserId = Integer.parseInt(table.getValueAt(rowIndex[i], 0).toString()) ;
				 //先删记录,再删用户...已经在同一条语句中处理
				 dao.delUserInfobyId(delUserId);
				 
				 model.removeRow(rowIndex[i]); //删除一行, 清除一行
			}

			if(rowIndex.length > 0) {
				JOptionPane.showMessageDialog(null, "删除成功！");
			} else {
				JOptionPane.showMessageDialog(null, "请选择要删除学生");
			}
			
		}
	}

}
