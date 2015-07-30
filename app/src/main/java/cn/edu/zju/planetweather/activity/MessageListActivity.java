package cn.edu.zju.planetweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.activity.base.SwipeBaseActivity;
import cn.edu.zju.planetweather.adapter.MessageListAdapter;
import cn.edu.zju.planetweather.entity.Message;
import cn.edu.zju.planetweather.leancloud.Tables;
import cn.edu.zju.planetweather.utils.L;
import cn.edu.zju.planetweather.view.DividerItemDecoration;
import de.greenrobot.event.EventBus;

public class MessageListActivity extends SwipeBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ImageView mBackView;
    private List<Message> mDateset;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mActionButton;
    private CoordinatorLayout mCoordinator;
    private MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        EventBus.getDefault().register(this);
        transparentStatusBar();
        findViews();
        setListeners();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        mDateset = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message();
//            msg.setContent("Hello Mars" + i);
//            mDateset.add(msg);
//        }
        adapter = new MessageListAdapter(mDateset);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollerListener());
        loadData();

    }

    private void loadData() {
        AVQuery<AVObject> query = new AVQuery<>(Tables.TABLE_MESSAGE);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list != null) {
//                    showShortToast(list.toString() + "");
                    for (int i = 0; i < list.size(); i++) {
                        Message msg = new Message();
                        msg.setContent(list.get(i).getString(Tables.ROW_CONTENT));
                        mDateset.add(msg);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    showShortToast("null list");
                }
                if (mRefreshLayout.isRefreshing() == true) {
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void setListeners() {
        mRefreshLayout.setOnRefreshListener(this);
        mActionButton.setOnClickListener(this);
        mBackView.setOnClickListener(this);
    }

    private void findViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_message_list);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab_normal);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);
        mBackView = (ImageView) findViewById(R.id.tv_back);
    }

    @Override
    public void onRefresh() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        mDateset.clear();
        loadData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_normal:
//                Snackbar.make(mCoordinator, "Your message", Snackbar.LENGTH_SHORT)
//                        .show();
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * called by envntbus
     *
     * @param event
     */
    public void onEventMainThread(String event) {
        mDateset.clear();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
