package com.boot.common.util;

import org.redisson.api.RLock;
import com.boot.common.lock.RedissonManage;

import java.util.Stack;

import org.apache.commons.codec.digest.DigestUtils;

public class ShortUrlUtil {

	public static String shortUrl(String url) {
		RLock lock = RedissonManage.getLock(url);
		// 最终返回的短链接
		String resUrl = "";
		try {
			lock.lock();
			resUrl = "";
			// 可以自定义生成 MD5 加密字符传前的混合 KEY
			String key = "";
			// 要使用生成 URL 的字符
			String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
					"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
					"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
					"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
					"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
					"U", "V", "W", "X", "Y", "Z" };
			// 对传入网址进行 MD5 加密
			String sMD5EncryptResult = DigestUtils.md5Hex(key + url);
			String hex = sMD5EncryptResult;
			for (int i = 0; i < 4; i++) {
				// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
				String sTempSubString = hex.substring(i * 8, i * 8 + 8);
				// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
				long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				resUrl += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
				// 把字符串存入对应索引的输出数组
			}
			resUrl = resUrl+System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return resUrl;
	}

	public static void main(String[] args) {
		String shortUrl = shortUrl("/manage/shareRecords/reciblerUrl?uuid=12e1626ead934f0f89f6f47725004017&shareUserId=4028819667ca3fef0167cf7d37620037&shareTime=1547533065388");
		System.out.println(shortUrl);
	}
	
	
	
	static char[] chars_62 = new char[] { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	static char[] chars_90 = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','~','!','@','#','$','%','^','&','*','(',')','_','+','-','=','[',']','{','}','|',';',':',',','.','/','<','>','?'};

	/**
	 * 
	 * @Title: _10_to_62
	 * @Description: 将10进制数字转换为自定义的62进制
	 * @param number
	 * @return String (这里描述输出参数的作用)
	 * @throws
	 * @author shanchaoran
	 * @date 2019年2月21日 下午1:06:24
	 */
	public static String _10_to_62(long number) {
		/*
		 * char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		 * 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		 * 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		 * 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		 * 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		 */
		Long rest = number;
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			stack.add(chars_62[new Long((rest - (rest / 62) * 62)).intValue()]);
			rest = rest / 62;
		}
		for (; !stack.isEmpty();) {
			result.append(stack.pop());
		}
		return result.toString();

	}
}
