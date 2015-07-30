package cn.edu.zju.planetweather.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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

    /**
     * 点击返回键退出程序
     */
    private static Boolean isExit = false;
    private TextView mTemperatureTextView;
    private TextView mAtmoOpacityTextView;
    private TextView mButton;
    private TextView mMarsCartoonView;
    private ImageView mImageView;
    private WeatherApplication helper;
    private JumpingBeans mJumpBeans;
    private Handler mHandler = new Handler();
    private CoordinatorLayout mCoordinator;
    private TranslateAnimation translateAnimation;

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

        Snackbar snackbar = Snackbar.make(mCoordinator, "大家好,我是Mars,也就是大家熟知的火星,右上方转动着的星球就是我的真容啦!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("继续看", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondSnackbar();
                translateAnimation.cancel();
            }
        }).show();
        translateAnimation = new TranslateAnimation(0, 300, 0, 0);
        translateAnimation.setDuration(3000);               //设置动画持续时间
        translateAnimation.setRepeatCount(3);               //设置重复次数
        translateAnimation.setRepeatMode(Animation.REVERSE);    //反方向执行
        mMarsCartoonView.setAnimation(translateAnimation);             //设置动画效果
        translateAnimation.start();
    }

    private void showSecondSnackbar() {
        Snackbar snackbar = Snackbar.make(mCoordinator, "在星球上面就是Mars的天气,火星看着红红的,但气温一般都是零下哦~", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("继续看", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowThirdSnackbar();
            }
        }).show();
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);//设置动画持续时间

        animation.setRepeatCount(0);//设置重复次数
        animation.setFillAfter(false);//动画执行完后是否停留在执行完的状态
        animation.setStartOffset(500);//执行前的等待时间
        mTemperatureTextView.setAnimation(animation);
        mAtmoOpacityTextView.setAnimation(animation);
        animation.start();
    }

    private void ShowThirdSnackbar() {
        Snackbar snackbar = Snackbar.make(mCoordinator, "看!右下方有个神奇会动的按钮,点击探索发现更多,超乎你得想象", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("继续看", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
//        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
//                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(3000);//设置动画持续时间
//
//        animation.setRepeatCount(1);//设置重复次数
//        animation.setFillAfter(false);//动画执行完后是否停留在执行完的状态
//        animation.setStartOffset(1000);//执行前的等待时间
//        mButton.setAnimation(animation);
//        animation.start();
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private void setAnimations() {
        RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(15000);//设置动画持续时间
        /** 常用方法 */
        animation.setRepeatCount(30);//设置重复次数
        animation.setFillAfter(true);
        LinearInterpolator lin = new LinearInterpolator();//匀速旋转
        animation.setInterpolator(lin);
        mImageView.setAnimation(animation);
        animation.start();
        mJumpBeans = JumpingBeans.with(mButton)
                .appendJumpingDots()
                .build();
//        mButton.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
//        mButton.getPaint().setAntiAlias(true);//抗锯齿
    }

    private void setTypefaces() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Hairline.ttf");
        Typeface hairlineItalicTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-HairlineItalic.ttf");
        Typeface regularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Lato-Regular.ttf");
        mTemperatureTextView.setTypeface(typeface);
        mAtmoOpacityTextView.setTypeface(hairlineItalicTypeface);
        mButton.setTypeface(regularTypeface);
    }

    private void findViews() {
        mTemperatureTextView = (TextView) findViewById(R.id.tv_temperature);
        mAtmoOpacityTextView = (TextView) findViewById(R.id.tv_atmo_opacity);
        mButton = (TextView) findViewById(R.id.btn_message_list);
        mImageView = (ImageView) findViewById(R.id.iv_center);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.cl_main_content);
        mMarsCartoonView = (TextView) findViewById(R.id.tv_mars_cartoon);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再按一次离开火星人专属的天气预报", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
