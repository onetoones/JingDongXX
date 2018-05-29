package com.example.jingdongxx.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jingdongxx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetupActivity extends AppCompatActivity {

    @BindView(R.id.setup_dz)
    TextView setupDz;
    @BindView(R.id.setup_tc)
    Button setupTc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setup_dz, R.id.setup_tc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setup_dz:
                startActivity(new Intent(this,SeeOrderActivity.class));
                break;
            case R.id.setup_tc:
                break;
        }
    }
}
