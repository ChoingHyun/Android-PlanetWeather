package cn.edu.zju.planetweather.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.frakbot.jumpingbeans.JumpingBeans;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.WeatherApplication;
import cn.edu.zju.planetweather.activity.base.BaseActivity;
import cn.edu.zju.planetweather.entity.Weather;
import cn.edu.zju.planetweather.net.APIConstant;
import cn.edu.zju.planetweather.net.AbsCustomJsonRequest;
import cn.edu.zju.planetweather.net.WeatherJsonRequest;


/**
 *
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTemperatureTextView;
    private TextView mAtmoOpacityTextView;
    private TextView mButton;
    private ImageView mImageView;
    private WeatherApplication helper;
    private JumpingBeans mJumpBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = WeatherApplication.getInstance();
        transparentStatusBar();
        findViews();
        setListeners();
        setTypefaces();
        setAnimations();
        loadWeatherData();
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private void setAnimations() {
        RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(10000);//设置动画持续时间
        /** 常用方法 */
        animation.setRepeatCount(3);//设置重复次数
        animation.setFillAfter(true);
        LinearInterpolator lin = new LinearInterpolator();//匀速旋转
        animation.setInterpolator(lin);
        mImageView.setAnimation(animation);
        animation.start();
        mJumpBeans = JumpingBeans.with(mButton)
                .appendJumpingDots()
                .build();
        mButton.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mButton.getPaint().setAntiAlias(true);//抗锯齿
    }


    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Hairline.ttf");
        Typeface regularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");
        mTemperatureTextView.setTypeface(typeface);
        mAtmoOpacityTextView.setTypeface(regularTypeface);
    }

    private void findViews() {
        mTemperatureTextView = (TextView) findViewById(R.id.tv_temperature);
        mAtmoOpacityTextView = (TextView) findViewById(R.id.tv_atmo_opacity);
        mButton = (TextView) findViewById(R.id.btn_message_list);
        mImageView = (ImageView) findViewById(R.id.iv_center);
    }

    private void loadWeatherData() {
        AbsCustomJsonRequest<Weather> request = new WeatherJsonRequest(Request.Method.GET, APIConstant.RECENT_API_ENDPOINT,
                null,
                new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather response) {
                        mTemperatureTextView.setText(response.getSol() + "°");
                        mAtmoOpacityTextView.setText(response.getAtmo_opacity());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message_list:
                Intent intent = new Intent(this, MessageListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
