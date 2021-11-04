package com.example.parents.Homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parents.R;
import com.example.parents.View.SlideRecyclerView;
import com.example.parents.View.TitleLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static com.example.parents.LoginRegister.LoginActivity.makeStatusBarTransparent;

/**
 * 家长布置作业
 */
public class HomeworkActivity extends AppCompatActivity implements View.OnClickListener {

    private TitleLayout homework_tit;
    private FloatingActionButton homework_fb_add;
    private SlideRecyclerView homework_rv_hwlist;
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

        // 设置作业序列
        homework_rv_hwlist = findViewById(R.id.homework_rv_hwlist);
        homework_rv_hwlist.setItemAnimator(new DefaultItemAnimator());
        homework_rv_hwlist.setItemAnimator(new SlideInUpAnimator());        //设置上浮动画
        homework_rv_hwlist.getItemAnimator().setAddDuration(500);
        homework_rv_hwlist.getItemAnimator().setRemoveDuration(500);

        // 垂直滑动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeworkActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homework_rv_hwlist.setLayoutManager(linearLayoutManager);

        // SnapHelper滑动对准
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(homework_rv_hwlist);

        hwlist = initData();
        homeworkAdapter = new HomeworkAdapter(HomeworkActivity.this, hwlist);
        homework_rv_hwlist.setAdapter(homeworkAdapter);
    }

    private List<HomeworkBean> initData(){
        List<HomeworkBean> dataList = new ArrayList<>();
        dataList.add(new HomeworkBean("预习《滕王阁序》", new Time(30)));
        dataList.add(new HomeworkBean("背诵《滕王阁序》", new Time(60)));
        dataList.add(new HomeworkBean("做完数学课堂练习", new Time(45)));
        dataList.add(new HomeworkBean("勾股定理", new Time(30)));

        return dataList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homework_fb_add:
                final InputHWDialog inputHWDialog = new InputHWDialog(this);
                final EditText input = inputHWDialog.getEditText();
                inputHWDialog.setOnSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hwlist.add(new HomeworkBean(input.toString(), new Time(10)));
                        // 通知适配器
                        homeworkAdapter.notifyItemChanged(hwlist.size()-1);
                        // 更新定位
                        homework_rv_hwlist.scrollToPosition(homeworkAdapter.getItemCount()-1);
                        Toast.makeText(HomeworkActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        inputHWDialog.dismiss();
                    }
                });
                inputHWDialog.setOnCanlceListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputHWDialog.dismiss();
                    }
                });
                inputHWDialog.setTile("请输入作业");
                inputHWDialog.show();;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}