package com.example.jingdongxx.classes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.adapter.GoListAdapter;
import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.classes.presenter.GoListPresenter;
import com.example.jingdongxx.classes.view.iview.IGoListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoListActivity extends AppCompatActivity implements IGoListActivity {

    @BindView(R.id.li_jt)
    ImageView liJt;
    @BindView(R.id.li_recyclerview)
    ImageView liRecyclerview;
    @BindView(R.id.tvZhonghe)
    TextView tvZhonghe;
    @BindView(R.id.tvXiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvShaixuan)
    TextView tvShaixuan;
    @BindView(R.id.rv)
    RecyclerView rv;
    private GoListPresenter goListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        goListPresenter = new GoListPresenter(this);
        Intent intent = getIntent();
        String pscid = intent.getStringExtra("pscid");
        goListPresenter.getlist(pscid);
        rv.setLayoutManager(new LinearLayoutManager(this));

        liJt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void show(List<GoListBean.DataBean> beans) {
        GoListAdapter adapter = new GoListAdapter(GoListActivity.this, beans);
        rv.setAdapter(adapter);

    }
}
