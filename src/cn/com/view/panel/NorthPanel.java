package cn.com.view.panel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * ����山�ߵ����
 * 
 * ���������ع���
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
		title = new JLabel("������Ԥ��ϵͳ");
		Font font = new Font("����",Font.PLAIN,30);
		title.setFont(font);
		this.add(title);
		
	}

}
