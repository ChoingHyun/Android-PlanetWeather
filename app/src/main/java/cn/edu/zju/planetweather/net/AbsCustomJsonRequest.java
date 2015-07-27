package cn.edu.zju.planetweather.net;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;

/**
 * Created by changhuiyuan on 15/7/27.
 */
public abstract class AbsCustomJsonRequest<T> extends JsonRequest<T> {

    private Priority mPriority;

    public AbsCustomJsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }


    public Priority getPriority() {
        return mPriority == null ? Priority.NORMAL : mPriority;
    }

    public void setPriority(Priority mPriority) {
        this.mPriority = mPriority;
    }
}
