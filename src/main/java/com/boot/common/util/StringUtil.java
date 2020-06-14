package com.boot.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; 
    
    public static String escapeScript(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); 
        return htmlStr.trim(); 
    }
 
	/**
	 * unicode字符串转为明文
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}
	
	
	public static String produceCode(Integer length){
		String code = "";
		for (int i = 0; i < length; i++) {
			int n = (int) (Math.random() * 10);
			code+=n;
		}
		return code;
	}
	
	/**
	 * 生成订单号
	 * @return
	 */
	public static String getOrderNum(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String num = sdf.format(new Date());
		for (int i = 0; i < 3; i++) {
			int n = (int) (Math.random() * 10);
			num+=n;
		}
		return num;
	}
}
