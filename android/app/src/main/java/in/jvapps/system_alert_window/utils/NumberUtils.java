package in.jvapps.system_alert_window.utils;

import android.util.Log;

public class NumberUtils {

    private static final String TAG = "NumberUtils";

    public static float getFloat(Object object) {
        return getNumber(object).floatValue();
    }

    public static int getInt(Object object) {
        return getNumber(object).intValue();
    }

    public static long getLong(Object object) {
        return getNumber(object).longValue();
    }

    private static Number getNumber(Object object) {
        Number val = 0;
        if (object != null) {
            try {
                val = ((Number) object);
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return val;
    }


}
