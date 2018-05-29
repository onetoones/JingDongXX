package com.example.jingdongxx.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.my.adapter.SeeOrderAdapter;
import com.example.jingdongxx.my.bean.SeeorderBean;
import com.example.jingdongxx.my.presenter.SeeOrderPresenter;
import com.example.jingdongxx.my.view.iview.ISeeOrderActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeeOrderActivity extends AppCompatActivity implements ISeeOrderActivity {

    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.seeoder_rcl)
    RecyclerView seeoderRcl;
    @BindView(R.id.seeoder_tj)
    Button seeoderTj;

    private SeeOrderPresenter seeOrderPresenter;
    private SharedPreferences sp;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_order);
        ButterKnife.bind(this);
        sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "");
        seeOrderPresenter = new SeeOrderPresenter(SeeOrderActivity.this);
        seeOrderPresenter.seeod(uid);

    }

    @Override
    public void ISeeor(SeeorderBean bean) {
        List<SeeorderBean.DataBean> data = bean.getData();
        SeeOrderAdapter seeOrderAdapter = new SeeOrderAdapter(this, data);
        linearLayoutManager = new LinearLayoutManager(this);
        seeoderRcl.setLayoutManager(linearLayoutManager);
        seeoderRcl.setAdapter(seeOrderAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        seeOrderPresenter.ondestr();
    }

    @OnClick(R.id.seeoder_tj)
    public void onViewClicked() {
        startActivity(new Intent(this,AddressActivity.class));


    }
}
