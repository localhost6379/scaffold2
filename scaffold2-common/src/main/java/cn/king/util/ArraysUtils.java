package cn.king.util;

/**
 * @author: wjl@king.cn
 * @time: 2020/2/22 12:52
 * @version: 1.0.0
 * @description:
 */
public class ArraysUtils {

    private ArraysUtils() {
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/2/22 12:56
     * @param: stringArr
     * @return: java.lang.Integer[]
     * @description: String数组 转换成 Integer数组
     */
    public static Integer[] SArrToIArr(String[] stringArr) {
        Integer[] integerArr = new Integer[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            integerArr[i] = Integer.valueOf(stringArr[i]);
        }
        return integerArr;
    }

    /**
     * @author: wjl@king.cn
     * @createTime: 2020/2/22 12:57
     * @param: stringArr
     * @return: java.lang.Long[]
     * @description: String数组 转换成 Long数组
     */
    public static Long[] SArrToLArr(String [] stringArr) {
        Long[] longArr = new Long[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            longArr[i] = Long.valueOf(stringArr[i]);
        }
        return longArr;
    }

}
