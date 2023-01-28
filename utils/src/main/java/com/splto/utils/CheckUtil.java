package com.splto.utils;


import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param mobile 移动、联通、电信运营商的号码段
     *               <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *               、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *               <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *               <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        String regex = "(\\+\\d+)?1[3456789]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *              <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *              数字之后是空格分隔的国家（地区）代码。</p>
     *              <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *              对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *              <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        if (StringUtils.isEmpty(digit)) {
            return false;
        }
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        if (StringUtils.isEmpty(decimals)) {
            return false;
        }
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        if (StringUtils.isEmpty(blankSpace)) {
            return false;
        }
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        if (StringUtils.isEmpty(chinese)) {
            return false;
        }
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        if (StringUtils.isEmpty(birthday)) {
            return false;
        }
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * <pre>
     * 获取网址 URL 的一级域名
     * http://detail.tmall.com/item.htm?spm=a230r.1.10.44.1xpDSH&id=15453106243&_u=f4ve1uq1092 ->> tmall.com
     * </pre>
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        if (StringUtils.isEmpty(postcode)) {
            return false;
        }
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(String ipAddress) {
        if (StringUtils.isEmpty(ipAddress)) {
            return false;
        }
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    /**
     * 验证身份证号码是否合法
     *
     * @param input
     * @return
     */
    public static boolean isIdCard(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        }
        return ChineseIdCardUtil.validateCard(input);
    }

    public static Boolean isPassport(String card) {
        String reg = "^([a-zA-z]|[0-9]){5,17}$";
        if (!card.matches(reg)) {
            //护照号码不合格
            return false;
        } else {
            //校验通过
            return true;
        }
    }

    /**
     * 台湾居民来往大陆通行证
     * 规则： 新版8位或18位数字 或 旧版9位数字 + 英文字母 或 8位数字 + 英文字母
     * 样本： 12345678
     */
    public static Boolean isTWPassCard(String card) {
        String reg = "^\\d{8}|^[a-zA-Z0-9]{10}|^[a-zA-Z0-9]{9}|^\\d{18}$";
        if (!card.matches(reg)) {
            //台湾居民来往大陆通行证号码不合格
            return false;
        } else {
            //校验通过
            return true;
        }
    }

    /**
     * 港澳居民来往内地通行证
     * 规则： H/M + 10位或6位数字
     * 例：H1234567890
     */
    public static Boolean isHKPassCard(String card) {
        String reg = "^([A-Z]\\d{6,10}(\\(\\w{1}\\))?)$";
        if (!card.matches(reg)) {
            //港澳居民来往内地通行证号码不合格
            return false;
        } else {
            //校验通过
            return true;
        }
    }

    public static Boolean isHKIdCard(String card){
        return ChineseIdCardUtil.validateHKCard(card);
    }

    public static Boolean isTWCard(String card){
        return ChineseIdCardUtil.validateTWCard(card);
    }


}
