package com.example.parents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.parents.Homework.HomeworkActivity;
import com.example.parents.Login.LoginActivity;
import com.example.parents.Message.MessageActivity;
import com.example.parents.Upload.UploadActivity;
import com.example.parents.Watch.WatchActivity;

import static com.example.parents.Login.LoginActivity.makeStatusBarTransparent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button main_btn_toupload, main_btn_towatch, main_btn_tohomework, main_btn_tomessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        makeStatusBarTransparent(MainActivity.this);

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
        }
    }
}