package com.system.aym.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class RegexMatches {

    /**
     *
     * @param regex
     * @return
     */
    public static boolean regexPattern(String regex){
        System.out.println(regex + "正则验证该字符串是否为纯数字");
        String pattern = "[0-9]{1,}";
        // 创建 Pattern 对象
        Pattern pat = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher matcher = pat.matcher((CharSequence)regex);
        boolean result = matcher.matches();
        System.out.println("正则验证结果为：" + result);
        return result;
    }

    /**
     *
     * @param regex
     * @return
     */
    public static boolean regex(String regex){
        System.out.println(regex + "正则验证该字符串是否为纯数字");
        boolean result = regex.matches("[0-9]+");
        System.out.println("正则验证结果为：" + result);
        return result;
    }

}
