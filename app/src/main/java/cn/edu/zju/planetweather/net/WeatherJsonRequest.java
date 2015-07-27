package cn.edu.zju.planetweather.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.edu.zju.planetweather.entity.Weather;

/**
 * Created by changhuiyuan on 15/7/27.
 */
public class WeatherJsonRequest extends AbsCustomJsonRequest<Weather> {

    public static final String REPORT_TAG = "report";

    public WeatherJsonRequest(int method, String url, String requestBody, Response.Listener<Weather> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    protected Response<Weather> parseNetworkResponse(NetworkResponse response) {
        try {
            Gson gson = new Gson();
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.optJSONObject(REPORT_TAG);
            json = jsonObject.toString();
            return Response.success(
                    gson.fromJson(json, Weather.class), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}
