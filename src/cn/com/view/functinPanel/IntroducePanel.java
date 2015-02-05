package cn.com.view.functinPanel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class IntroducePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;

	public IntroducePanel() {
		init();
	}

	private void init() {
		
		this.setLayout(null);
		this.add(createTextArea());
		
	}

	private JTextArea createTextArea() {
		if(textArea == null) {
			textArea = new JTextArea("使用说明：\n1.会议室预定系统\n2.本系统包含12个会议室,必须是本公司员工才能使用\n3.只能预定未来两周的会议室\n4.会议室每天的开放时间为上午9：00-12：00, 下午14：00-17：00");
			Font font = new Font("宋体",Font.PLAIN,20);
			textArea.setFont(font);
			//textArea.setBackground(Color.);
			textArea.setBounds(0, 0, 600, 400);
		}
		return textArea;
	}

}
