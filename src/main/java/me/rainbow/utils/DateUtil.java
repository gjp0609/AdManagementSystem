package me.rainbow.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author guojinpeng
 * @date 17.12.20 12:13
 */
public class DateUtil {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 365 * day;// 年

    /**
     * 获取本月最后一天
     *
     * @param month Calendar中常量。如：Calendar.JULY
     */
    public static Date getLastDayOfMonth(int year, int month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, actualMaximum);
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     */
    public static Date getLastDayOfMonth(Date day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return getLastDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    /**
     * 获取本月最后一天
     */
    public static Date getLastDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return getLastDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    /**
     * 获取指定时间与当前时间相隔时长的描述，如：3个小时前
     */
    public static String getTimeFormatText(Date date) {
        Date now = new Date();
        if (date == null || date.after(now)) return "";
        long diff = now.getTime() - date.getTime();
        if (diff > year) return diff / year + "年前";
        if (diff > month) return diff / month + "个月前";
        if (diff > day) return diff / day + "天前";
        if (diff > hour) return diff / hour + "个小时前";
        if (diff > minute) return diff / minute + "分钟前";
        return "刚刚";
    }

    /**
     * 获取月历左上角日期（周日为第一天）
     *
     * @param month Calendar中常量。如：Calendar.JULY
     */
    public static Date getLeftTopDate(int year, int month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return DateUtils.addDays(calendar.getTime(), -i + 1);
    }

    /**
     * 获取月历右下角日期（周日为第一天）
     *
     * @param month Calendar中常量。如：Calendar.JULY
     */
    public static Date getRightBottomDate(int year, int month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return DateUtils.addDays(calendar.getTime(), 7 - i);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getLastDayOfMonth());
    }
}
