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
			textArea = new JTextArea("ʹ��˵����\n1.������Ԥ��ϵͳ\n2.��ϵͳ����12��������,�����Ǳ���˾Ա������ʹ��\n3.ֻ��Ԥ��δ�����ܵĻ�����\n4.������ÿ��Ŀ���ʱ��Ϊ����9��00-12��00, ����14��00-17��00");
			Font font = new Font("����",Font.PLAIN,20);
			textArea.setFont(font);
			//textArea.setBackground(Color.);
			textArea.setBounds(0, 0, 600, 400);
		}
		return textArea;
	}

}
