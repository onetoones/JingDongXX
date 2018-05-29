package com.example.jingdongxx.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.MainActivity;
import com.example.jingdongxx.R;
import com.example.jingdongxx.my.bean.LoginBean;
import com.example.jingdongxx.my.presenter.LoginPresenter;
import com.example.jingdongxx.my.view.iview.ILoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginActivity {

    @BindView(R.id.dl_fh)
    ImageView dlFh;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.dl_mm)
    EditText dlMm;
    @BindView(R.id.dl_zh)
    EditText dlZh;
    @BindView(R.id.dl)
    Button dl;
    @BindView(R.id.zc)
    TextView zc;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }

    @OnClick({R.id.dl, R.id.zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dl:
                presenter.log();
                break;
            case R.id.zc:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                break;
        }
    }

    @Override
    public String getName() {
        return dlZh.getText().toString().trim();
    }

    @Override
    public String getmm() {
        return dlMm.getText().toString().trim();
    }

    @Override
    public void TZ(LoginBean loginBean) {
        SharedPreferences sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", loginBean.getData().getUid() + "");
        edit.putString("token",loginBean.getData().getToken());
        edit.putString("names",loginBean.getData().getNickname());
        edit.commit();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public void show(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.dl_fh)
    public void onViewClicked() {
        finish();
    }
}
