package com.example.parents.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.parents.Login.LoginActivity;
import com.example.parents.R;
import com.example.parents.View.TitleLayout;

import static com.example.parents.Login.LoginActivity.makeStatusBarTransparent;

public class MessageActivity extends AppCompatActivity {

    private TitleLayout message_tit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message);
        makeStatusBarTransparent(MessageActivity.this);

        initView();
    }

    private void initView(){
        message_tit = findViewById(R.id.message_tit);
        message_tit.setTitle("消息通知");
    }

}