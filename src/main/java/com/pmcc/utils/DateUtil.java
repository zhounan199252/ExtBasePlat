package com.pmcc.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static int YEAR = 1;
	public final static int MONTH = 2;

	public static SimpleDateFormat getDateFormat(String parttern)
			throws RuntimeException {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 时间转指定格式字符串
	 * 
	 * @param date
	 * @param parttern
	 * @return
	 */
	public static String DateToString(Timestamp date, String parttern) {
		String formatDate = null;
		if (date != null) {
			try {
				formatDate = getDateFormat(parttern).format(date);
			} catch (Exception e) {
				formatDate = new String();
			}
		}
		return formatDate;
	}

	/**
	 * 字符串转指定格式时间
	 * 
	 * @param date
	 * @param parttern
	 * @return
	 */
	public static Timestamp StringToDate(String date, String parttern) {
		Date formatDate = null;
		if (date != null) {
			try {
				formatDate = getDateFormat(parttern).parse(date);
			} catch (Exception e) {
				formatDate = new Date();
			}
		}
		return new Timestamp(formatDate.getTime());
	}

	/**
	 * 获取一月的开始和结束日期
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp[] getBetweenMonthTime(Timestamp time) {
		Timestamp[] times = new Timestamp[2];
		times[0] = getMonthStartTime(time);
		times[1] = getMonthEndTime(time);
		return times;
	}

	/**
	 * 获取一天的开始和结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp[] getBetweenDayTime(Timestamp time) {
		Timestamp[] times = new Timestamp[2];
		times[0] = getStartDayTime(time);
		times[1] = getEndDayTime(time);
		return times;
	}

	/**
	 * 获取一年的开始和结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp[] getBetweenYearTime(Timestamp time) {
		Timestamp[] times = new Timestamp[2];
		times[0] = getYearStartTime(time);
		times[1] = getYearEndTime(time);
		return times;
	}

	/**
	 * 获取某天的开始时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getStartDayTime(Timestamp time) {
		// TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某天的结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getEndDayTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某月的开始时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getMonthStartTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某月的结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getMonthEndTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某年的开始时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getYearStartTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某年的结束时间
	 * 
	 * @param time
	 * @return
	 */
	public static Timestamp getYearEndTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DATE, 31);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	private static Timestamp isNullTimestamp(Timestamp time) {
		if (time == null) {
			return getCurrentTime();
		}
		return time;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取时间
	 * 
	 * @param year
	 *            数字
	 * @param month
	 *            数字
	 * @return
	 */
	public static Timestamp getTime(int year, int month) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	public static Timestamp getTime(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		if (year != 0) {
			c.set(Calendar.YEAR, year);
		}
		if (month != 0) {
			c.set(Calendar.MONTH, month - 1);
		}
		if (day != 0) {
			c.set(Calendar.DATE, day);
		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取时间
	 * 
	 * @param time
	 * @return 2014-04-1 00:00:00
	 */
	public static Timestamp getYMTime(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.DATE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取某个时间所在月的天数
	 * 
	 * @param time
	 * @return
	 */
	public static int getNumberOfDay(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取年
	 * 
	 * @param time
	 * @return
	 */
	public static int getYear(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取月
	 * 
	 * @param time
	 * @return
	 */
	public static int getMonth(Timestamp time) {
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		c.setTime(time);
		return (c.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取天
	 * @param time
	 * @return
	 */
	public static int getDay(Timestamp time){
		Calendar c = Calendar.getInstance();
		c.setTime(isNullTimestamp(time));
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 比较两个时间的大小
	 * 
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static int compareYM(Timestamp oldDate, Timestamp newDate) {
		long res = getYMTime(newDate).getTime() - getYMTime(oldDate).getTime();
		if (res > 0) {
			// 比之前日期大
			return 1;
		} else if (res == 0) {
			// 与之前日期相等
			return 0;
		} else {
			// 与之前日期小
			return -1;
		}

	}

	public static int compareDate(Date date, Date now) {
		long res = now.getTime() - date.getTime();
		if (res > 0) {
			// 比之前日期大
			return 1;
		} else if (res == 0) {
			// 与之前日期相等
			return 0;
		} else {
			// 与之前日期小
			return -1;
		}

	}
	
	public static int compareTime(Date oldDate, Date newDate) {
		long res = newDate.getTime() - oldDate.getTime();
		if (res > 0) {
			// 比之前日期大
			return 1;
		} else if (res == 0) {
			// 与之前日期相等
			return 0;
		} else {
			// 与之前日期小
			return -1;
		}

	}

	/**
	 * 增加天数
	 * 
	 * @param time
	 *            时间
	 * @param days
	 *            天数
	 * @return
	 */
	public static Timestamp addDay(Timestamp time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DATE, days);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);

	}
	
	/**
	 * 增加月数
	 * 
	 * @param time
	 *            时间
	 * @param days
	 *            天数
	 * @return
	 */
	public static Timestamp addMonth(Timestamp time, int monthes) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MONTH, monthes);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);

	}
	
	/**
	 * 增加天数
	 * @param time
	 * @param days
	 * @return
	 * @author lxl
	 */
	public static Date addDay(Date time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.DATE, days);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}
	/**
	 * 增加月
	 * @param time
	 * @param monthes
	 * @return
	 * @author lxl
	 */
	public static Date addMonth(Date time, int monthes) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MONTH, monthes);
		return StringToDate(
				DateToString(new Timestamp(c.getTime().getTime()),
						YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
	 * 计算天数
	 * 
	 * @return  
	 */
	public static int betweenOfDay(Timestamp time1, Timestamp time2) {
		Calendar to1 = new GregorianCalendar();
		to1.setTime(time1);
		Calendar to2 = new GregorianCalendar();
		to2.setTime(time2);
		int res = to2.get(Calendar.DAY_OF_YEAR) - to1.get(Calendar.DAY_OF_YEAR);
		if (res < 0) {
			res = (res * -1);
		}
		return res;

	}
	
	public static void main(String[] args) {
		
		// TODO
		Date d = addMonth(new Date(), 6);
		System.out.println("------" + DateToString(new Timestamp(d.getTime()), YYYY_MM_DD_HH_MM_SS));
	}

}