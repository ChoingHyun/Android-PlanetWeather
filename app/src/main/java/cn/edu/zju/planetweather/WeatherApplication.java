package cn.edu.zju.planetweather;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by changhuiyuan on 15/7/27.
 */
public class WeatherApplication extends Application {

    private static WeatherApplication mWeatherApplication;
    private String TAG;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = WeatherApplication.class.getSimpleName();
        mWeatherApplication = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public WeatherApplication getInstance() {
        return mWeatherApplication;
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
