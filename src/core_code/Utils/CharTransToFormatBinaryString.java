package core_code.Utils;

/**
 * ��ʽ���ַ�
 * �ϸ��ն����Ƹ�ʽ��ӡ 16 λ
 * 
 * @author freezhan
 *
 */
public class CharTransToFormatBinaryString {
	
	public static String trans(char c) {
		String str = Integer.toBinaryString(c);
		
		int len = 16;
		//ǰ��0
		while(str.length() < len) {
			str = "0" + str;
		}
		
		if(str.length() > 16) {
			str = str.substring(str.length()-16, str.length());
		}
		return str;
	}

}
