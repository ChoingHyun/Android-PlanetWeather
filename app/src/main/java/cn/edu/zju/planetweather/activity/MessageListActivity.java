package cn.edu.zju.planetweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.adapter.MessageListAdapter;
import cn.edu.zju.planetweather.entity.Message;

public class MessageListActivity extends Activity {

    private RecyclerView mRecycleView;
    private List<Message> mDateset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        findViews();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mDateset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message();
            msg.setContent("Hello Mars" + i);
            mDateset.add(msg);
        }
        MessageListAdapter adapter = new MessageListAdapter(mDateset);
        mRecycleView.setAdapter(adapter);
    }

    private void findViews() {
        mRecycleView = (RecyclerView) findViewById(R.id.rv_message_list);
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
}
