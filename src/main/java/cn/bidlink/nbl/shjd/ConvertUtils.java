package cn.bidlink.nbl.shjd;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/7/31 10:57$
 */
public class ConvertUtils {
    public static String convert(String val,String vallist,String textlist){
        String[] vlist = vallist.split(",");
        String[] tlist = textlist.split(",");
        if(vlist.length != tlist.length)
            return "";
        for(int i=0;i<vlist.length;i++){
            if(val.equals(vlist[i])){
                return tlist[i];
            }
        }
        return "";
    }

    public static String convert(String str) {
        return StringUtils.isBlank(str) ? "" : format4JSON(str);
    }

    private static boolean isalive = false;
    private static boolean isFormatOk = true;

    public static boolean isFormatOk() {
        return isFormatOk;
    }

    public static void setFormatOk(boolean isFormatOk) {
        ConvertUtils.isFormatOk = isFormatOk;
    }

    public static void setIsalive(boolean isalive) {
        ConvertUtils.isalive = isalive;
    }

    public static String formatNull(String str) {
        return StringUtils.isBlank(str) ? "" : str;
    }

	/*
	 * public static Date convert(Date val) { return val == null ? new Date() :
	 * val; }
	 */

    public static String convert(Date val) {
        return val == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(val);
    }

    public static String convert(Date val, boolean shortFormat) {
        if (shortFormat)
            return val == null ? "" : new SimpleDateFormat("yyyy-MM-dd")
                    .format(val);
        else
            return val == null ? "" : new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss").format(val);
    }

    public static String convert(Date val, String format) {
        return val == null ? "" : new SimpleDateFormat(format).format(val);
    }

    public static float convert(Float val) {
        return val == null ? 0 : val;
    }

    public static double convert(Double val) {
        return val == null ? 0 : val;
    }

    public static int convert(Integer val) {
        return val == null ? 0 : val;
    }

    public static void convert(String sei, Object x) {

    }

    public static String md5(String source) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            // logger.error("Can not encode the string '" + source +
            // "' to MD5!", e);
            return null;
        }
        return sb.toString().toLowerCase();
    }

    public static String format4JSON(String ors) {
        String retstr = ors;

        try {
            if(retstr.indexOf("'")!=-1){
                //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                retstr = retstr.replaceAll("'", "`");
            }
            if(retstr.indexOf("\"")!=-1){
                //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的
                retstr = retstr.replaceAll("\"", "\\\"");
            }

            if(retstr.indexOf("\r\n")!=-1){
                //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行
                retstr = retstr.replaceAll("\r\n", "\\\\u000d\\\\u000a");
            }
            if(retstr.indexOf("\n")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                retstr = retstr.replaceAll("\n", "\\\\u000a");
            }
            if(retstr.indexOf("\\")!=-1){
                //将换行转换一下，因为JSON串中字符串不能出现显式的换行
                retstr = retstr.replaceAll("\\\\", "\\\\\\\\");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retstr;
    }
}
