package DateAndCalendarStudy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws ParseException {
//        System.out.println(System.currentTimeMillis());//获取当前系统毫秒
        long cT = System.currentTimeMillis();
        Date date = new Date(cT);
//        System.out.println(date);
//        System.out.println(date.getTime());
        //模式："yyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
        String s = sdf.format(date);
        System.out.println(s);
        long myDate = sdf.parse("2001-12-12").getTime();
        System.out.println(sdf.format(new Date(myDate)));
        System.out.println((cT - myDate) / 1000 / 60 / 24);
    }
}
