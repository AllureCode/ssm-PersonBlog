package Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对时间格式的转换
 */
public class DateTestDemo {
    public static void main(String[] args) {
        Date date = new Date();
        long time = date.getTime();
        date.setTime(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
