package cn.com.view.functinPanel;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 * 只限定于管理员管理用户
 * 
 */
public class ManagePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] tabTitleArr;

	private String[] classNameArr;

	private JTabbedPane tab;
	
	public ManagePanel() {
		init();
		
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.add(createTab(), BorderLayout.CENTER);// 添加选项卡面板
		
	}
	
	/**
	 * 创建选项卡面板
	 * 
	 * @return
	 */
	private JTabbedPane createTab() {
		if (tab == null) {

			tab = new JTabbedPane();

			configTap();// 读取配置文件

			JPanel[] panelArr = createPanel();// 根据类创建对象
			for (int i = 0; i < panelArr.length; i++) {
				tab.addTab(tabTitleArr[i], panelArr[i]);
			}
		}

		return tab;
	}
	
	/**
	 * 根据类名动态创建对象
	 * 
	 * @return
	 */
	private JPanel[] createPanel() {
		JPanel[] panelArr = new JPanel[classNameArr.length];
		JPanel obj = null;

		for (int i = 0; i < panelArr.length; i++) {
			System.out.println(classNameArr[i]);
			try {
				obj = (JPanel) Class.forName(classNameArr[i]).newInstance();// 使用反射机制来动态创建对象
				panelArr[i] = obj;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return panelArr;
	}
	
	/**
	 * 将配置文件中的选项卡面板的相关信息存储
	 * 
	 */
	private void configTap() {
		Properties p = new Properties();
		try {
			p.load(new FileReader("lib/tab.txt"));
			tabTitleArr = p.getProperty("tabTitle").split(",");
			classNameArr = p.getProperty("className").split(",");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
