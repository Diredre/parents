package com.example.parents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.parents.Homework.HomeworkActivity;
import com.example.parents.LoginRegister.LoginActivity;
import com.example.parents.Message.MessageActivity;
import com.example.parents.Upload.UploadActivity;
import com.example.parents.Watch.WatchActivity;

import static com.example.parents.LoginRegister.LoginActivity.makeStatusBarTransparent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button main_btn_toupload, main_btn_towatch, main_btn_tohomework, main_btn_tomessage;
    private ImageView main_iv_off, main_iv_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);    //设置手机应用内部状态栏字体图标为黑色

        initView();
    }

    private void initView(){
        main_btn_toupload = findViewById(R.id.main_btn_toupload);
        main_btn_toupload.setOnClickListener(this);

        main_btn_towatch = findViewById(R.id.main_btn_towatch);
        main_btn_towatch.setOnClickListener(this);

        main_btn_tohomework = findViewById(R.id.main_btn_tohomework);
        main_btn_tohomework.setOnClickListener(this);

        main_btn_tomessage = findViewById(R.id.main_btn_tomessage);
        main_btn_tomessage.setOnClickListener(this);

        main_iv_setting = findViewById(R.id.main_iv_setting);
        main_iv_setting.setOnClickListener(this);
        Glide.with(MainActivity.this)
                .load("https://z3.ax1x.com/2021/11/01/ICMZlQ.png")
                .into(main_iv_setting);

        main_iv_off = findViewById(R.id.main_iv_off);
        main_iv_off.setOnClickListener(this);
        Glide.with(MainActivity.this)
                .load("https://z3.ax1x.com/2021/11/01/ICMSOA.png")
                .into(main_iv_off);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn_toupload:
                startActivity(new Intent(MainActivity.this, UploadActivity.class));
                break;
            case R.id.main_btn_towatch:
                startActivity(new Intent(MainActivity.this, WatchActivity.class));
                break;
            case R.id.main_btn_tohomework:
                startActivity(new Intent(MainActivity.this, HomeworkActivity.class));
                break;
            case R.id.main_btn_tomessage:
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
            case R.id.main_iv_setting:
                Toast.makeText(MainActivity.this, "设置界面还没写", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_iv_off:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();
                break;
        }
    }
}