package cn.edu.zju.planetweather;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by changhuiyuan on 15/7/27.
 */
public class WeatherApplication extends Application {

    private static WeatherApplication mWeatherApplication;
    private String TAG;
    private RequestQueue mRequestQueue;

    public static synchronized WeatherApplication getInstance() {
        return mWeatherApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = WeatherApplication.class.getSimpleName();
        mWeatherApplication = this;
        AVOSCloud.initialize(this, "1x0l1fckthfx62yxr9tv69o23gpdbt33eo5rg7s4xj0sc0c4", "s1op6mfgio715w9e7s0ekolx4oagofrodvk2z0wum2vzf4uh");
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void add(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    /**
     * Cancels all requests in this queue with the given tag
     */
    public void cancel() {
        getRequestQueue().cancelAll(TAG);
    }

}
