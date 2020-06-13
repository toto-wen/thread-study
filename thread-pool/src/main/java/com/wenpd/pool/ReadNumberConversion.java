package com.wenpd.pool;

import jdk.internal.dynalink.beans.StaticClass;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 本题要求写出一个转换函数，能把一个阿拉伯数字转换为中文字符。比如 1 转换为“一”，
 * 11 转换为“一十一”，111 转换为“一百一十一”，
 * 1011 转换为“一千零一十一”……（至少 支持十亿以内的转换）
 */
public class ReadNumberConversion {


    /**
     * 阿拉伯数字与大写数字转换
     */
    private static Map<Character, String> numToConversion = new HashMap<Character, String>();
    /**
     * 倍数转换
     */
    private static Map<String, String> multipleConversion = new HashMap<String, String>();

    static {
        numToConversion.put('0', "零");
        numToConversion.put('1', "一");
        numToConversion.put('2', "二");
        numToConversion.put('3', "三");
        numToConversion.put('4', "四");
        numToConversion.put('5', "五");
        numToConversion.put('6', "六");
        numToConversion.put('7', "七");
        numToConversion.put('8', "八");
        numToConversion.put('9', "九");

        multipleConversion.put("0", "");
        multipleConversion.put("1", "十");
        multipleConversion.put("2", "百");
        multipleConversion.put("3", "千");
        multipleConversion.put("4", "万");
        multipleConversion.put("5", "十");
        multipleConversion.put("6", "百");
        multipleConversion.put("7", "千");
        multipleConversion.put("8", "亿");
        multipleConversion.put("9", "十");
    }

    public static String conversionNumber(Long number){

        StringBuilder stringBuilder = new StringBuilder("");
        if(number != null){
            char[] chars = String.valueOf(number).toCharArray();
            char previous = ' ';

            for(int i = chars.length - 1; i >= 0; i --) {

                if(chars[i] == '0' && chars[i] == previous){
                    continue;
                }
                if (chars[i] == '0' && stringBuilder.toString().equals("")) {
                    continue;
                }
                if (chars[i] != '0' && i != chars.length - 1) {
                    stringBuilder.append(multipleConversion.get(String.valueOf(chars.length - i -1)));
                }
                stringBuilder.append(numToConversion.get(chars[i]));
                previous = chars[i];
            }

            return stringBuilder.reverse().toString();
        }

        return numToConversion.get(0);
    }

    public static void main(String[] args){

        long num = 1123400789;
        String numStr = ReadNumberConversion.conversionNumber(num);
        System.out.println(numStr);

    }

}
