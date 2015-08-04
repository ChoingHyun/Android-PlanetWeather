package cn.edu.zju.planetweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

/**
 * Created by changhuiyuan on 15/8/4.
 */
public class LaunchUtil {
    private static final String VERSION_KEY = "VERSION_KEY";

    /**
     * @param context
     * @return ture If return ture shows that app launch at the first time
     */
    public static boolean isFirstLaunch(Context context) {

        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            L.e(e.toString());
            return false;
        }
        int currentVersion = info.versionCode;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int lastVersion = prefs.getInt(VERSION_KEY, 0);
        if (currentVersion > lastVersion) {
            //如果当前版本大于上次版本，该版本属于第一次启动

            //将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
            prefs.edit().putInt(VERSION_KEY, currentVersion).commit();
            return true;
        } else {
            return false;
        }
    }
}
