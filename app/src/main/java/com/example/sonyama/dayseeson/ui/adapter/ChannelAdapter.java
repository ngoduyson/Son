package com.example.sonyama.dayseeson.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.model.Channel;
import com.example.sonyama.dayseeson.data.model.ChannelCategory;
import com.example.sonyama.dayseeson.data.model.ChannelResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonyama on 3/16/16.
 */
public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int GROUP = 0;
    public static final int CHILD = 1;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Item> data = new ArrayList<>();

    public ChannelAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case GROUP:
                view = mLayoutInflater.inflate(R.layout.item_channel_category, parent, false);
                GroupViewHolder header = new GroupViewHolder(view);
                return header;
            case CHILD:
                view = mLayoutInflater.inflate(R.layout.item_channel, parent, false);
                ChildViewHolder child = new ChildViewHolder(view);
                return child;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case GROUP:
                if (item.object instanceof ChannelCategory) {
                    bindGroupViewHolder((ChannelCategory) item.object, holder);
                }
                break;
            case CHILD:
                if (item.object instanceof Channel) {
                    bindChildViewHolder((Channel) item.object, holder);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    private void bindGroupViewHolder(ChannelCategory category, RecyclerView.ViewHolder holder) {
        final GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
        groupViewHolder.channelName.setText(category.getName());
        Glide.with(mContext)
                .load(category.getIconUrl())
                .fitCenter()
                .into(groupViewHolder.channelThumb);
    }

    private void bindChildViewHolder(Channel channel, RecyclerView.ViewHolder holder) {
        final ChildViewHolder childViewHolder = (ChildViewHolder) holder;
        childViewHolder.tvName.setText(channel.getName());
        Glide.with(mContext)
                .load(channel.getIconUrl())
                .fitCenter()
                .into(childViewHolder.ivIcon);
        if (channel.isAuthorized()) {
            childViewHolder.ivRegist.setImageResource(R.drawable.greencheck);
        } else {
            childViewHolder.ivRegist.setImageResource(R.drawable.next_icon);
        }
    }

    public static class Item {
        public int type;
        public Object object;
        public Item(int type, Object object) {
            this.type = type;
            this.object = object;
        }
    }

    public void setDataResource(ChannelResponse mData) {
        for (ChannelCategory category : mData.getChannelCategories()) {
            data.add(new ChannelAdapter.Item(ChannelAdapter.GROUP,
                    category));
            for (Channel channel : category.getChannels()) {
                data.add(new ChannelAdapter.Item(ChannelAdapter.CHILD,
                        channel));
            }
        }
        notifyDataSetChanged();
    }

    protected static class GroupViewHolder extends RecyclerView.ViewHolder {
        public TextView channelName;
        public ImageView channelThumb;

        public GroupViewHolder(View itemView) {
            super(itemView);
            channelName = (TextView) itemView.findViewById(R.id.image_channel_category_name);
            channelThumb = (ImageView) itemView.findViewById(R.id.image_channel_category_icon);
        }
    }

    protected class ChildViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public RelativeLayout rlChannel;
        public ImageView ivIcon;
        public TextView tvName;
        public ImageView ivRegist;

        public ChildViewHolder(View itemView) {
            super(itemView);
            rlChannel = (RelativeLayout) itemView.findViewById(R.id.relative_channel);
            ivIcon = (ImageView) itemView.findViewById(R.id.image_channel_icon);
            tvName = (TextView) itemView.findViewById(R.id.text_channel_name);
            ivRegist = (ImageView) itemView.findViewById(R.id.image_channel_regist);
            rlChannel.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = data.get(getLayoutPosition());
            if (item.object instanceof Channel) {
                Channel channel = (Channel) item.object;
                Toast.makeText(mContext, channel.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
