package cn.edu.zju.planetweather.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.activity.base.BaseActivity;
import cn.edu.zju.planetweather.databinding.ActivityPublishBinding;
import cn.edu.zju.planetweather.leancloud.Tables;
import cn.edu.zju.planetweather.pojo.Title;
import de.greenrobot.event.EventBus;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    private TextView mPublishTextView;
    private EditText mPublishEditText;
    private ImageView mBackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPublishBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_publish);
        Title title = new Title(getString(R.string.publish_message));
        binding.setTitle(title);
//        transparentStatusBar();
        initViews();
        setListeners();

    }


    private void initViews() {
        mPublishTextView = (TextView) findViewById(R.id.tv_publish);
        mPublishEditText = (EditText) findViewById(R.id.et_message);
        mBackView = (ImageView) findViewById(R.id.tv_back);
    }

    private void setListeners() {
        mPublishTextView.setOnClickListener(this);
        mBackView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String content = mPublishEditText.getText().toString();
        switch (v.getId()) {
            case R.id.tv_publish:
                if (!TextUtils.isEmpty(content) && content.length() > 5) {
                    mPublishTextView.setClickable(false);
                    mPublishTextView.setTextColor(Color.GRAY);
                    publish(content);
                } else {
                    showShortToast(getString(R.string.words_too_less));
                }
                break;
            case R.id.tv_back:
                if (TextUtils.isEmpty(content)) {
                    finish();
                } else {
                    new AlertDialog.Builder(this).setTitle(R.string.not_publish_yet)
                            .setMessage(R.string.give_up)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
                break;
            default:
                break;
        }
    }

    private void publish(String text) {

        AVObject testObject = new AVObject(Tables.TABLE_MESSAGE);
        testObject.put(Tables.ROW_CONTENT, text);
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    showShortToast(getString(R.string.publish_success));
                    finish();
                    EventBus.getDefault().post(new String(""));
                } else {
                    showShortToast(getString(R.string.publish_fail));
                    Resources resource = getBaseContext().getResources();
                    ColorStateList csl = resource.getColorStateList(android.R.color.holo_blue_light);
                    mPublishTextView.setTextColor(csl);
                    mPublishTextView.setClickable(true);
                }
            }
        });
    }
}
