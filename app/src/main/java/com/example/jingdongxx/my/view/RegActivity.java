package com.example.jingdongxx.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.my.presenter.RegPresenter;
import com.example.jingdongxx.my.view.iview.IRegActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements IRegActivity {

    @BindView(R.id.zc_fh)
    ImageView zcFh;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.zc_mm)
    EditText zcMm;
    @BindView(R.id.zc_zh)
    EditText zcZh;
    @BindView(R.id.zc)
    Button zc;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        presenter = new RegPresenter(this);

    }

    @Override
    public String getName() {
        return zcZh.getText().toString().trim();
    }

    @Override
    public String getmm() {
        return zcMm.getText().toString().trim();
    }

    @Override
    public void TZ() {
        startActivity(new Intent(RegActivity.this, LoginActivity.class));
    }


    @Override
    public void show(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.zc_fh, R.id.zc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_fh:
                finish();
                break;
            case R.id.zc:
                presenter.sign();

                break;
        }
    }
}
