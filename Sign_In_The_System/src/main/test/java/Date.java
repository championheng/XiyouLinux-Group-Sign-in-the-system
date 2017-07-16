import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-7-15.
 */
public class Date {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String nowTime = sdf.format(now.getTime());

        String newNowTime = nowTime.substring(0, 10);

        out.println(newNowTime);
        out.println(nowTime);
    }
}
