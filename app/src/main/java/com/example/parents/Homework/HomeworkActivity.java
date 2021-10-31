package com.example.parents.Homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.parents.R;
import com.example.parents.View.TitleLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.example.parents.Login.LoginActivity.makeStatusBarTransparent;

public class HomeworkActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleLayout homework_tit;
    private FloatingActionButton homework_fb_add;
    private RecyclerView homework_rv_hwlist;
    private HomeworkAdapter homeworkAdapter;
    private List<HomeworkBean> hwlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homework);
        makeStatusBarTransparent(HomeworkActivity.this);

        initView();
    }

    private void initView(){
        homework_tit = findViewById(R.id.homework_tit);
        homework_tit.setTitle("布置作业");

        homework_fb_add = findViewById(R.id.homework_fb_add);
        homework_fb_add.setOnClickListener(this);

        homework_rv_hwlist = findViewById(R.id.homework_rv_hwlist);
        hwlist.add(new HomeworkBean("预习《滕王阁序》", new Time(30)));
        hwlist.add(new HomeworkBean("背诵《滕王阁序》", new Time(60)));
        hwlist.add(new HomeworkBean("做完数学课堂练习", new Time(45)));
        hwlist.add(new HomeworkBean("勾股定理", new Time(30)));

        homework_rv_hwlist.setItemAnimator(new DefaultItemAnimator());
        homework_rv_hwlist.getItemAnimator().setAddDuration(1000);
        homework_rv_hwlist.getItemAnimator().setRemoveDuration(1000);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeworkActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homework_rv_hwlist.setLayoutManager(linearLayoutManager);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(homework_rv_hwlist);
        homeworkAdapter = new HomeworkAdapter(HomeworkActivity.this, hwlist);
        homework_rv_hwlist.setAdapter(homeworkAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homework_fb_add:
                hwlist.add(new HomeworkBean("新增", new Time(10)));
                homeworkAdapter.notifyItemChanged(hwlist.size()-1);
        }
    }
}