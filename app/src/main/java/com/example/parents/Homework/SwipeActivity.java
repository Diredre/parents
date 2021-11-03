package com.example.parents.Homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.parents.R;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearSnapHelper;


public class SwipeActivity extends AppCompatActivity implements OnItemClickListener{

    protected Toolbar mToolbar;
    protected ActionBar mActionBar;
    protected SwipeRecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecyclerView.ItemDecoration mItemDecoration;

    protected SwipeAdapter mAdapter;
    protected List<HomeworkBean> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler_view);


        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (displayHomeAsUpEnabled()) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mLayoutManager = createLayoutManager();
        mItemDecoration = createItemDecoration();
        mDataList = createDataList();
        mAdapter = createAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mItemDecoration);

        mRecyclerView.setOnItemClickListener(this);
    }

    protected int getContentView() {
        return R.layout.activity_swipe;
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DefaultItemDecoration(ContextCompat.getColor(this, R.color.divider_color));
    }

    protected List<HomeworkBean> createDataList() {
        List<HomeworkBean> dataList = new ArrayList<>();
        dataList.add(new HomeworkBean("预习《滕王阁序》", new Time(30)));
        dataList.add(new HomeworkBean("背诵《滕王阁序》", new Time(60)));
        dataList.add(new HomeworkBean("做完数学课堂练习", new Time(45)));
        dataList.add(new HomeworkBean("勾股定理", new Time(30)));

        return dataList;
    }

    protected SwipeAdapter createAdapter() {
        return new HomeworkAdapter(this);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(this, "第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}