package com.example.jingdongxx.classes.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.MainActivity;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.parentclass.MyGlide;
import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.DetailBean;
import com.example.jingdongxx.classes.presenter.DetailPresenter;
import com.example.jingdongxx.classes.view.iview.IDetailActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements IDetailActivity {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.rbGoods)
    RadioButton rbGoods;
    @BindView(R.id.rbDetails)
    RadioButton rbDetails;
    @BindView(R.id.rbAppraise)
    RadioButton rbAppraise;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @BindView(R.id.ivMsg)
    ImageView ivMsg;
    @BindView(R.id.detail_bn)
    Banner detailBn;
    @BindView(R.id.detail_titel)
    TextView detailTitel;
    @BindView(R.id.detail_price)
    TextView detailPrice;
    @BindView(R.id.vp)
    LinearLayout vp;
    @BindView(R.id.llSupplier)
    LinearLayout llSupplier;
    @BindView(R.id.llShop)
    LinearLayout llShop;
    @BindView(R.id.llAttention)
    LinearLayout llAttention;
    @BindView(R.id.llCard)
    LinearLayout llCard;
    @BindView(R.id.tvAddCard)
    TextView tvAddCard;
    private List<String> list;
    private DetailPresenter detailPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        detailPresenter = new DetailPresenter(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");

        detailPresenter.getDetai(pid);

    }

    @Override
    public void detailshow(DetailBean dataBeans) {

        detailTitel.setText(dataBeans.getData().getSubhead());
        detailPrice.setText("¥" + dataBeans.getData().getPrice());

        list = new ArrayList<>();
        String images = dataBeans.getData().getImages();
        String[] split = images.split("\\|");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        detailBn.setImages(list)
                .setImageLoader(new MyGlide())
                .isAutoPlay(true)
                .start();

    }

    @Override
    public void addshow(MyBean bean) {
        Toast.makeText(this, bean.getMsg() + "", Toast.LENGTH_LONG).show();

    }

    @OnClick({R.id.llCard, R.id.tvAddCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCard:
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("flag", 3);

                startActivity(intent);

               finish();
                break;
            case R.id.tvAddCard:
                SharedPreferences sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
                detailPresenter.getadds(sp.getString("uid", ""), pid,sp.getString("token",""));

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.onDestroys();
    }

    @OnClick(R.id.ivBack)
    public void onViewClicked() {
        finish();
    }
}
