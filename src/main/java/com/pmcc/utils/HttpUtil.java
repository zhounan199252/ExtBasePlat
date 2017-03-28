package com.pmcc.utils;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Admin on 2016/1/29.
 */
public final class HttpUtil {

    /***
     * 返回请求路径
     * @param request
     * @return
     */
    public static String getURL(HttpServletRequest request) {
        StringBuffer sb = request.getRequestURL();
        String queryString = request.getQueryString();
        if(queryString!=null)
            return sb.toString() + "?" + queryString;
        return sb.toString();
    }

    /**
     * 获取整型参数值
     * 如果没有发现则设置为默认值
     */
    public static int getInt(HttpServletRequest request, String paramName, int defaultValue) {
        String s = request.getParameter(paramName);
        if(s==null || s.equals(""))
            return defaultValue;
        return Integer.parseInt(s);
    }

    /**
     * Get String parameter from request. If specified parameter name
     * is not found, the default value will be returned.
     */
    public static String getString(HttpServletRequest request, String paramName, String defaultValue) {
        String str = request.getParameter(paramName);
        if(str==null || str.equals(""))
            return defaultValue.trim();
        String encoding = request.getCharacterEncoding();
        try {
            if("ISO-8859-1".equalsIgnoreCase(encoding)){
                str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
            }
            str = str.trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    /**
     * Get String parameter from request. If specified parameter name
     * is not found or empty, an Exception will be thrown.
     */
    public static String getString(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        if(s==null || s.equals(""))
            throw new NullPointerException("Null parameter: " + paramName);
        return s;
    }

    /**
     * Get Boolean parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static boolean getBoolean(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        return Boolean.parseBoolean(s);
    }

    /**
     * Get Boolean parameter from request. If specified parameter name
     * is not found, the default value will be returned.
     */
    public static boolean getBoolean(HttpServletRequest request, String paramName, boolean defaultValue) {
        String s = request.getParameter(paramName);
        if(s==null || s.equals(""))
            return defaultValue;
        return Boolean.parseBoolean(s);
    }

    /**
     * Get float parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static float getFloat(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        return Float.parseFloat(s);
    }

    /**
     * 获取浮点型参数值
     * 如果没有发现则设置为默认值
     */
    public static float getFloat(HttpServletRequest request, String paramName, float defaultValue) {
        String s = request.getParameter(paramName);
        if(s==null || s.equals(""))
            return defaultValue;
        return Float.parseFloat(s);
    }
    /**
     * Get float parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static double getDouble(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        return Double.parseDouble(s);
    }

    /**
     * 获取浮点型参数值
     * 如果没有发现则设置为默认值
     */
    public static double getDouble(HttpServletRequest request, String paramName, float defaultValue) {
        String s = request.getParameter(paramName);
        if(s==null || s.equals(""))
            return defaultValue;
        return Double.parseDouble(s);
    }

    /**
     * Get float parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static List<String> getList(HttpServletRequest request, String paramName, String splitstr) {
        String str = request.getParameter(paramName);
        if(str==null||str.equals("")){
            return new ArrayList<String>();
        }
        String encoding = request.getCharacterEncoding();
        try {
            if("ISO-8859-1".equalsIgnoreCase(encoding)){
                str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
            }
            str = str.trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return StringUtil.toList(str, splitstr);
    }

    /**
     * Get float parameter from request. If specified parameter name
     * is not found, an Exception will be thrown.
     */
    public static String[] getArray(HttpServletRequest request, String paramName,String splitstr) {
        String str = request.getParameter(paramName);
        if(str==null||str.equals("")){
            return null ;
        }
        String[] result=str.split(splitstr);
        return result;
    }
    /**
     * 获取时间类型参数
     * @param request
     * @param paramName
     * @param defaultValue
     * @return Timestamp类型
     */
    public static Timestamp getTimestamp(HttpServletRequest request, String paramName, String defaultValue){
        Timestamp result = null;
        String str = request.getParameter(paramName);
        if (str == null || str.equals("")) {
            return null;
        } else if (str.length() == 10) {
            result = DateUtil.StringToDate(str, DateUtil.YYYY_MM_DD);
        }else if(str.length() > 10){
            result = DateUtil.StringToDate(str, DateUtil.YYYY_MM_DD_HH_MM_SS);
        }
        return result;
    }
    /***
     * 获取所有的参数
     * @param request
     * @return
     */
    public static Map<String,Object> convertModel(HttpServletRequest request){
        Map<String,Object> model = new HashMap<String,Object>();
        Enumeration<String> names=request.getParameterNames();
        while(names.hasMoreElements()){
            String key=names.nextElement();
            Object value=request.getParameter(key);
            model.put(key, value);
        }
        return model;
    }

}