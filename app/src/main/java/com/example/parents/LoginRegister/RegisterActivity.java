package com.example.parents.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.parents.MainActivity;
import com.example.parents.R;
import com.example.parents.Widget.Code;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView reg_iv_phone, reg_iv_password, reg_iv_code, reg_iv_logo, reg_iv_phcode;
    private Button reg_btn_reg;
    private EditText reg_et_phone, reg_et_code, reg_et_password;

    private String real_code;       // 生成验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        initView();
    }


    private void initView(){
        reg_et_phone = findViewById(R.id.reg_et_phone);
        reg_et_code = findViewById(R.id.reg_et_code);
        reg_et_password = findViewById(R.id.reg_et_password);
        real_code = Code.getInstance().getCode().toLowerCase();

        reg_iv_phcode = findViewById(R.id.reg_iv_phcode);
        reg_iv_phcode.setImageBitmap(Code.getInstance().createBitmap());
        reg_iv_phcode.setOnClickListener(this);

        // reg按钮
        reg_btn_reg = findViewById(R.id.reg_btn_reg);
        reg_btn_reg.setOnClickListener(this);

        // 一些图标
        reg_iv_phone = findViewById(R.id.reg_iv_phone);
        Glide.with(RegisterActivity.this)
                .load("https://z3.ax1x.com/2021/10/24/5WrkMF.png")
                .into(reg_iv_phone);

        reg_iv_code = findViewById(R.id.reg_iv_code);
        Glide.with(RegisterActivity.this)
                .load("https://z3.ax1x.com/2021/11/01/IPS5on.png")
                .into(reg_iv_code);

        reg_iv_password = findViewById(R.id.login_iv_password);
        Glide.with(RegisterActivity.this)
                .load("https://z3.ax1x.com/2021/10/24/5WrP2T.png")
                .into(reg_iv_password);

        reg_iv_logo = findViewById(R.id.login_iv_logo);
        Glide.with(RegisterActivity.this)
                .load("https://z3.ax1x.com/2021/10/24/5WK2jS.png")
                .into(reg_iv_logo);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_btn_reg:
                String phoneCode = reg_et_code.getText().toString().toLowerCase();
                if (phoneCode.equals(real_code)) {
                    Toast.makeText(RegisterActivity.this, phoneCode + "注册成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(RegisterActivity.this, phoneCode + "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reg_iv_phcode:
                reg_iv_phcode.setImageBitmap(Code.getInstance().createBitmap());
                real_code = Code.getInstance().getCode().toLowerCase();
        }
    }
}