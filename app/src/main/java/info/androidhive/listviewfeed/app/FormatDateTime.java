package info.androidhive.listviewfeed.app;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jason on 09/07/2015.
 */
public class FormatDateTime {
    public static String CurrentDate() {
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat = new SimpleDateFormat("d MMM yyyy");
        String s = timeFormat.format(today.getTime());
        return s;
    }

    public static String CurrentDateTime() {
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat = new SimpleDateFormat("EEE, d MMM yyyy - K:mm a");
        String s = timeFormat.format(today.getTime());
        return s;
    }


}
