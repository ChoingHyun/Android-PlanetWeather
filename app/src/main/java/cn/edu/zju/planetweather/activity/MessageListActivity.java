package cn.edu.zju.planetweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.activity.base.SwipeBaseActivity;
import cn.edu.zju.planetweather.adapter.MessageListAdapter;
import cn.edu.zju.planetweather.entity.Message;
import cn.edu.zju.planetweather.utils.L;
import cn.edu.zju.planetweather.view.DividerItemDecoration;

public class MessageListActivity extends SwipeBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<Message> mDateset;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mActionButton;
    private CoordinatorLayout mCoordinator;
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
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollerListener());
    }

    private void setListeners() {
        mRefreshLayout.setOnRefreshListener(this);
        mActionButton.setOnClickListener(this);
    }

    private void findViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_message_list);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab_normal);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_normal:
                Snackbar.make(mCoordinator, "Your message", Snackbar.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
        }
    }

    class RecyclerViewScrollerListener extends RecyclerView.OnScrollListener {
        /**
         * @param recyclerView
         * @param newState     The RecyclerView is not currently scrolling.
         *                     SCROLL_STATE_IDLE = 0;
         *                     The RecyclerView is currently being dragged by outside input such as user touch input.
         *                     SCROLL_STATE_DRAGGING = 1;
         *                     The RecyclerView is currently animating to a final position while not under
         *                     outside control.
         *                     SCROLL_STATE_SETTLING = 2;
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            L.i("onScrollStateChanged", newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
//            L.i("onScrolled,dx:", dx);
//            L.i("onScrolled,dy:", dy);
        }
    }
}
