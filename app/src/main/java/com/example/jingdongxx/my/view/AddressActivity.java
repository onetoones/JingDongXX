package com.example.jingdongxx.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.my.presenter.AddressPresenter;
import com.example.jingdongxx.my.view.iview.IAddressActivity;
import com.example.jingdongxx.parentclass.MyBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity<AddressPresenter> implements IAddressActivity {

   @BindView(R.id.ressadd_name)
        EditText ressName;
        @BindView(R.id.ress_phone)
        EditText ressPhone;
        @BindView(R.id.ress_dz)
        EditText ressDz;
        @BindView(R.id.ress_tj)
        Button ressTj;
        @BindView(R.id.ress_ck)
        Button ressCk;
        @BindView(R.id.textView6)
        TextView textView6;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
    private String token;
    private String uid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected AddressPresenter setPresenter() {
        return new AddressPresenter();
    }

    @Override
    void initView() {

    }

    @Override
    void initData() {
        SharedPreferences sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        uid = sp.getString("uid", "");
    }

    @OnClick({R.id.ress_tj, R.id.ress_ck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ress_tj:
                String dz = ressDz.getText().toString();
                String name = ressName.getText().toString();
                String phone = ressPhone.getText().toString();
                b.getress(uid, dz, phone, name, token);
                break;
            case R.id.ress_ck:
                startActivity(new Intent(this, SeeOrderActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void show(MyBean bean) {
        String code = bean.getCode();
        int i = Integer.parseInt(code);
        if (i == 0) {
            Toast.makeText(this, bean.getMsg(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        b.ondestr();
    }


}
