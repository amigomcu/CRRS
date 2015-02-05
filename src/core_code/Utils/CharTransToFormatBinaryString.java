package core_code.Utils;

/**
 * 格式化字符
 * 严格按照二进制格式打印 16 位
 * 
 * @author freezhan
 *
 */
public class CharTransToFormatBinaryString {
	
	public static String trans(char c) {
		String str = Integer.toBinaryString(c);
		
		int len = 16;
		//前补0
		while(str.length() < len) {
			str = "0" + str;
		}
		
		if(str.length() > 16) {
			str = str.substring(str.length()-16, str.length());
		}
		return str;
	}

}
