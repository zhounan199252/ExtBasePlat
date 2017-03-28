package com.pmcc.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Admin on 2016/1/28.
 */
public class StringUtil {

    public static final String SQL = "sql"; // sql语句
    public static final String PARAMETERS = "parameters"; // 参数
    public static final String COLUMN = "column"; // 字段
    public static final String VALUE = "value"; // 值
    public static final String PROPERTY = "property"; // 排序字段
    public static final String DIRECTION = "direction"; // 排序方式
    public static final String ASC = "asc"; // 正序
    public static final String DESC = "desc"; // 倒序

    public static List toMapValues(String inStr, String splitstr,
                                   String elesplitstr) {
        ArrayList resultlist = new ArrayList();
        if (inStr == null || inStr.equals("")) {
            return null;
        }
        if (inStr.indexOf(splitstr) == -1) {
            return null;
        }
        StringTokenizer str = new StringTokenizer(inStr.trim(), splitstr);
        while (str.hasMoreTokens()) {
            String tmpstr = str.nextToken();
            int i = tmpstr.indexOf(elesplitstr);
            if (i != -1) {
                HashMap map = new HashMap();
                map.put(tmpstr.substring(0, i), tmpstr.substring(i + 1));
                resultlist.add(map);
            }
        }
        return resultlist;
    }

    public static ArrayList<String> toList(String inStr, String splitstr) {

        ArrayList<String> resulist = new ArrayList<String>();
        if (inStr == null || inStr.equals("")) {
            return resulist;
        }
        if (inStr.indexOf(splitstr) == -1) {
            resulist.add(inStr);
            return resulist;
        }
        StringTokenizer str = new StringTokenizer(inStr.trim(), splitstr);
        while (str.hasMoreTokens()) {
            resulist.add(str.nextToken());
        }
        return resulist;
    }
    public static ArrayList toIntList(String inStr, String splitstr) {
        ArrayList resulist = new ArrayList();
        if (inStr == null || inStr.equals("")) {
            return resulist;
        }
        if (inStr.indexOf(splitstr) == -1) {
            if(isInteger(inStr)){
                resulist.add(Integer.valueOf(inStr));
            }
            return resulist;
        }
        String strs[]=inStr.split(splitstr);
        for(String str: strs){
            if(isInteger(str)){
                resulist.add(Integer.valueOf(str));
            }
        }
        return resulist;
    }
    public static boolean isInteger(String str) {
        try{
            Integer.valueOf(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for(int t=0;t<str.length();t++){
            if(!Character.isDigit(str.charAt(t))){
                return false;
            }
        }
        return true;
    }


    public static Calendar toDate(String qktdatestart) {
        Calendar calendar = Calendar.getInstance();
        String tmpkey = qktdatestart;

        int y = 0, m = 1, d = 1;
        if (tmpkey != null) {
            StringTokenizer st = new StringTokenizer(tmpkey, "-");
            if (st.hasMoreTokens()) {
                y = Integer.parseInt(st.nextToken());
            }
            if (st.hasMoreTokens()) {
                m = Integer.parseInt(st.nextToken()) - 1;
            }
            if (st.hasMoreTokens()) {
                d = Integer.parseInt(st.nextToken());
            }
            calendar.set(y, m, d, 0, 0, 0);

        }
        return calendar;
    }
    /**
     * Get float parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static String[] getArray(String str,String splitstr) {
        if(str==null||str.equals("")){
            return null ;
        }
        String[] result=str.split(splitstr);
        return result;
    }

    public static String generator(){
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        int a = (int)(Math.random() * 90000.0D + 10000.0D);
        int b = (int)(Math.random() * 90000.0D + 10000.0D);
        int c = (int)(Math.random() * 90000.0D + 10000.0D);
        return (now.append(a).append(b).append(c)).toString();
    }
    /***
     * 自定义ID
     * @return
     */
    public static String getCustomId(){
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        int a = (int)(Math.random() * 90000.0D + 10000.0D);
        return (now.append(a)).toString();
    }

}
