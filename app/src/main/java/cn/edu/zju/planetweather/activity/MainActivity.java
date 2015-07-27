package cn.edu.zju.planetweather.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.WeatherApplication;
import cn.edu.zju.planetweather.entity.Weather;
import cn.edu.zju.planetweather.net.APIConstant;
import cn.edu.zju.planetweather.net.AbsCustomJsonRequest;
import cn.edu.zju.planetweather.net.WeatherJsonRequest;

/**
 *
 */
public class MainActivity extends Activity {

    private TextView mTemperatureTextView;
    private WeatherApplication helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = WeatherApplication.getInstance();
        findViews();
        setTypefaces();
        loadWeatherData();
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Hairline.ttf");
        mTemperatureTextView.setTypeface(typeface);
    }

    private void findViews() {
        mTemperatureTextView = (TextView) findViewById(R.id.tv_temperature);
    }

    private void loadWeatherData() {
        AbsCustomJsonRequest<Weather> request = new WeatherJsonRequest(Request.Method.GET, APIConstant.RECENT_API_ENDPOINT,
                null,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather response) {
                        mTemperatureTextView.setText(response.getSol() + "°");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setPriority(Request.Priority.HIGH);
        helper.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
