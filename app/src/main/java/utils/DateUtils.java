package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by LFZ on 2017/4/26.
 * 日期类
 */

public class DateUtils {

    public static final String FMT = "yyyy/MM/dd";

    /**
     * 字符转日期
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date str2date(String str, String pattern) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转字
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2str(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 是否是今天
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean isToday(String str, String pattern) {
        Date today = Calendar.getInstance().getTime();
        return date2str(today, pattern).equals(str);
    }

    public static String getNextDay(String str, String pattern) {
        return getDay(str, pattern, 1);
    }

    public static String getPreviousDay(String str, String pattern) {
        return getDay(str, pattern, -1);
    }

    /**
     * 获得指定日期的前后
     *
     * @param str
     * @param pattern
     * @param n
     * @return
     */
    public static String getDay(String str, String pattern, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str2date(str, pattern));
        calendar.add(Calendar.DATE, n);
        Date date = calendar.getTime();
        return date2str(date, pattern);
    }

    /**
     * 获取今日的前后
     *
     * @param n
     * @return
     */
    public static String getDay(String pattern, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, n);
        Date date = calendar.getTime();
        return date2str(date, pattern);
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        GregorianCalendar cal1 = new GregorianCalendar();

        GregorianCalendar cal2 = new GregorianCalendar();

        cal1.setTime(startDate);

        cal2.setTime(endDate);

        double dayCount = (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);
        return (int) dayCount;
    }

    /**
     * 获得星期几
     *
     * @return
     */
    public static String getDateOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return getDateOfWeek(dayOfWeek);
    }

    /**
     * 获得星期几
     *
     * @return
     */
    public static String getDateOfWeek(int day) {
        String dayOfWeek = null;
        switch (day) {
            case 1:
                dayOfWeek = "星期日";
                break;
            case 2:
                dayOfWeek = "星期一";
                break;
            case 3:
                dayOfWeek = "星期二";
                break;
            case 4:
                dayOfWeek = "星期三";
                break;
            case 5:
                dayOfWeek = "星期四";
                break;
            case 6:
                dayOfWeek = "星期五";
                break;
            case 7:
                dayOfWeek = "星期六";
                break;
        }
        return dayOfWeek;
    }

    /**
     * 获得日期的几年前后的日期
     * @param date
     * @param count
     * @return
     */
    public static Date getServeralYearsDate(Date date ,int count){
        Date newDate = date;
        newDate.setYear(date.getYear()  + count);
        return newDate;
    }
}
