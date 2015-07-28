package cn.edu.zju.planetweather.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.activity.base.BaseActivity;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    private TextView mPublishTextView;
    private EditText mPublishEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        transparentStatusBar();
        initViews();
        setListeners();
    }


    private void initViews() {
        mPublishTextView = (TextView) findViewById(R.id.tv_publish);
        mPublishEditText = (EditText) findViewById(R.id.et_message);
    }

    private void setListeners() {
        mPublishTextView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                publish(mPublishEditText.getText().toString());
                break;
            default:
                break;
        }
    }

    private void publish(String text) {
        AVObject testObject = new AVObject("Message");
        testObject.put("content", text);
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    showShortToast("发表成功到火星");
                } else {
                    showShortToast("与火星通讯失败,请检查网络");
                }
            }
        });
    }
}
