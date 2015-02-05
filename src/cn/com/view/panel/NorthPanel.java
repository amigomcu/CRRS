package cn.com.view.panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 主面板北边的面板
 * 
 * 酌情添加相关功能
 * 
 */
public class NorthPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	
	public NorthPanel() {
		init();
		
	}

	private void init() {

		//this.setLayout(null);
		title = new JLabel("会议室预定系统");
		Font font = new Font("宋体",Font.PLAIN,30);
		title.setFont(font);
		this.add(title);
		
	}

}
