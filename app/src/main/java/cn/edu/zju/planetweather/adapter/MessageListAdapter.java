package cn.edu.zju.planetweather.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cn.edu.zju.planetweather.R;
import cn.edu.zju.planetweather.entity.Message;
import cn.edu.zju.planetweather.utils.L;

/**
 * Created by changhuiyuan on 15/7/28.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    List<Message> dataset = Collections.emptyList();
    private int count = 0;

    public MessageListAdapter(List<Message> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_message, null);
//        view.setBackgroundColor(Color.argb(new Random(System.currentTimeMillis()).nextInt(255), new Random(System.currentTimeMillis()).nextInt(255), new Random(System.currentTimeMillis()).nextInt(255), new Random().nextInt(255)));
        int[] picIDs = {R.mipmap.ic_sky_03_resize, R.mipmap.ic_sky_07_resize, R.mipmap.ic_sky_11_resize};
        view.setBackgroundResource(picIDs[count % 3]);
        count++;
        L.v("i" + i);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.contentTextView.setText(dataset.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView timeTextView;
        public TextView contentTextView;
        public TextView usernameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            contentTextView = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}
