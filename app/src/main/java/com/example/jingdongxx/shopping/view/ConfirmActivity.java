package com.example.jingdongxx.shopping.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.presenter.ConfirmPresenter;
import com.example.jingdongxx.shopping.view.iview.IConfirmActivity;

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener, IConfirmActivity {
    private TextView mTvLeft;
    /**
     * 立即下单
     */
    private Button mBt;
    private ConfirmPresenter confirmPresenter;
    private SharedPreferences sp;
    private String uid;
    private String token;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        token = sp.getString("token", "");


        //接收传过来实付款
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        confirmPresenter = new ConfirmPresenter(this);
        confirmPresenter.con(uid, money, token);
        initView();
        mTvLeft.setText("实付款：¥" + money);
    }

    private void initView() {
        mTvLeft = (TextView) findViewById(R.id.tvLeft);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                int i = Integer.parseInt(code);
                if (i==0) {
                    //跳转到订单页面
                    Intent intent = new Intent(ConfirmActivity.this, OrderActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"价格错误",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void getShow(MyBean bean) {
        code = bean.getCode();
    }
}

