package cn.king.util;

import java.util.UUID;

/**
 * 字符串工具类
 * @author zsm@shuyuntu.com
 *
 */
public class StringUtils {

	/**
	 * 判空操作
	 * @param str
	 * @return 如果是空, 返回true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str) || "null".equals(str) || "undefined".equals(str);
	}

	/**
	 * @author: wjl@king.cn
	 * @createTime: 2020/2/16 0:06
	 * @param:
	 * @return: java.lang.String
	 * @description: 获取10位随机字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
	}

}
