package cn.edu.zju.planetweather.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.activity.base.BaseActivity;
import cn.edu.zju.planetweather.adapter.MessageListAdapter;
import cn.edu.zju.planetweather.entity.Message;
import cn.edu.zju.planetweather.view.DividerItemDecoration;

public class MessageListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<Message> mDateset;
    private SwipeRefreshLayout mRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        transparentStatusBar();
        findViews();
        setListeners();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        mDateset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message();
            msg.setContent("Hello Mars" + i);
            mDateset.add(msg);
        }
        MessageListAdapter adapter = new MessageListAdapter(mDateset);
        mRecyclerView.setAdapter(adapter);
    }

    private void setListeners() {
        mRefreshLayout.setOnRefreshListener(this);
    }

    private void findViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_message_list);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_list, menu);
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

    @Override
    public void onRefresh() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        mRefreshLayout.setRefreshing(false);
    }
}
