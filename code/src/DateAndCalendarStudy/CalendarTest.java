package DateAndCalendarStudy;

import java.util.Calendar;

public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
//        System.out.println(year);
        int mouth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        System.out.println(day);
    }
}
