package com.example.parents.Homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.parents.R;
import com.example.parents.View.TitleLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.DefaultItemDecoration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static com.example.parents.LoginRegister.LoginActivity.makeStatusBarTransparent;
/**
 * 家长布置作业
 */
public class HomeworkActivity extends SwipeActivity implements View.OnClickListener, OnItemClickListener {

    private TitleLayout homework_tit;
    private FloatingActionButton homework_fb_add;
    private SwipeRecyclerView homework_rv_hwlist;
    private SwipeAdapter homeworkAdapter;
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

        /*homework_rv_hwlist.setItemAnimator(new DefaultItemAnimator());
        homework_rv_hwlist.setItemAnimator(new SlideInUpAnimator());        //设置上浮动画*/
        homework_rv_hwlist.getItemAnimator().setAddDuration(500);
        homework_rv_hwlist.getItemAnimator().setRemoveDuration(500);

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeworkActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView.ItemDecoration itemDecoration = creat_itemDecoration();*/

        homework_rv_hwlist.setOnItemClickListener(this);    // 点击item事件

        homeworkAdapter = new HomeworkAdapter(HomeworkActivity.this);
        homeworkAdapter.notifyDataSetChanged(hwlist);
        //homework_rv_hwlist.setAdapter(homeworkAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homework_fb_add:
                hwlist.add(new HomeworkBean("新增", new Time(10)));
                homeworkAdapter.notifyItemChanged(hwlist.size()-1);
        }
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