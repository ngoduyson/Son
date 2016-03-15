package com.example.sonyama.dayseeson.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.model.ChannelResponse;
import com.example.sonyama.dayseeson.data.remote.DayseeCallback;
import com.example.sonyama.dayseeson.data.remote.DayseeError;
import com.example.sonyama.dayseeson.data.remote.DayseeService;
import com.example.sonyama.dayseeson.ui.activity.interfaces.ChannelActivityInterface;
import com.example.sonyama.dayseeson.ui.adapter.ChannelAdapter;
import com.example.sonyama.dayseeson.ui.base.BaseActivity;
import com.example.sonyama.dayseeson.util.DayseeServiceUtil;
import com.example.sonyama.dayseeson.util.DialogListener;
import com.example.sonyama.dayseeson.util.DialogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sonyama on 3/16/16.
 */
public class ChannelActivity extends BaseActivity
        implements ChannelActivityInterface {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler_channel) RecyclerView mRecyclerViewChannel;
    @Bind(R.id.image_loading) ImageView mImageLoading;
    private ChannelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                showConfirmRegistDialog();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        mToolbar.inflateMenu(R.menu.menu_close);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mAdapter = new ChannelAdapter(this);
        mRecyclerViewChannel.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerViewChannel.setAdapter(mAdapter);
        Glide.with(this)
                .load(R.drawable.load3)
                .fitCenter()
                .into(mImageLoading);
        loadData();
    }

    @Override
    public void loadData() {
        showLoading();
        DayseeServiceUtil.getInstance()
                .getTriggerChannels()
                .enqueue(new DayseeCallback<ChannelResponse>() {
                    @Override
                    public void onResponse(ChannelResponse data) {
                        super.onResponse(data);
                        onLoadDataSucess(data);
                    }

                    @Override
                    public void onFailure(List<DayseeError> errors) {
                        super.onFailure(errors);
                        onLoadDataFail(errors);
                    }
                });
    }

    @Override
    public void onLoadDataSucess(ChannelResponse data) {
        hideLoading();
        mAdapter.setDataResource(data);
    }

    @Override
    public void onLoadDataFail(List<DayseeError> errors) {
        hideLoading();
        DialogUtil.showErrorDialog(this, errors);
    }

    @Override
    public void showLoading() {
        if(mImageLoading != null && mImageLoading.getVisibility() == View.INVISIBLE) {
            mImageLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mImageLoading != null && mImageLoading.getVisibility() == View.VISIBLE) {
            mImageLoading.setVisibility(View.INVISIBLE);
        }
    }

    private void showConfirmRegistDialog() {
        DialogUtil.show(this, R.string.msg_create_idea, new DialogListener() {
            @Override
            public void onOK() {
                ChannelActivity.this.finish();
            }

            @Override
            public void onCancel() {
            }
        });
    }
}
