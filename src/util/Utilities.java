package util;

import process.Preprocesser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author IDo!
 * @version 1.0
 */
public class Utilities {
    public static boolean is_Church(String exp){
        String pattern = "(\\\\\\D\\.\\\\\\D\\.)(\\(\\D )+(\\D)(\\))+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(exp);
        if (m.find()){
            return true;
        }
        return false;
    }

    /**
     * 将邱奇数转换为自然数形式
     */
    public static int num_StringToInteger(String line) {
        String pattern = "(\\\\\\D\\.\\\\\\D\\.)(\\(\\D )+(\\D)(\\))+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()) {
            int j = 0;
            for (int i = line.length() - 1; line.charAt(i) == ')'; i--) {
                j++;
            }
            return j;
        }
        throw new IllegalArgumentException("参数错误！");
    }

    /**
     * 将自然数转成邱奇数lambda表达式
     */
    public static String num_IntegerToString(int number) {
        if (number >= 0) {
            StringBuffer stringBuffer = new StringBuffer("\\f.\\x.");
            for (int i = 0; i < number; i++) {
                stringBuffer.append('(').append("f ");
            }
            stringBuffer.append('x');
            for (int i = 0; i < number; i++) {
                stringBuffer.append(')');
            }

            return stringBuffer.toString();
        } else {
            throw new IllegalArgumentException("参数错误：邱奇数必须大于零!");
        }
    }

    /**
     * 返回字符串中的第一个关键字
     * @param epr 待检查表达式
     * @return 关键字的字符串
     */
    public static String which_keyword(String epr) {
        StringBuffer buffer = new StringBuffer();

        int index = epr.indexOf('_');

        while ((index < epr.length()) && ((Character.isLetter(epr.charAt(index))) || epr.charAt(index) == '_')) {
            buffer.append(epr.charAt(index));
            index++;
        }
        String s = buffer.toString();
        if (Preprocesser.keywords.containsKey(s)) {
            return s;
        } else {
            throw new IllegalArgumentException(s + "关键字错误！");
        }
    }

    /**
     * 返回字符串中的第一个数字
     * @param epr 待检测字符串
     * @return 数字
     */
    public static int get_a_number(String epr){
        StringBuffer buffer = new StringBuffer();
        //先找到第一个数字的索引位置
        int index = 0;
        while(!Character.isDigit(epr.charAt(index))){
            index++;
        }
        //获取该数的字符串
        while((index < epr.length()) && (Character.isDigit(epr.charAt(index)))){
            buffer.append(epr.charAt(index));
            index++;
        }
        //返回结果
        return Integer.parseInt(buffer.toString());
    }

    /**
     *判断一个字符串是否含有数字
     */
    public static boolean hasDigit(String epr){
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(epr);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
}
