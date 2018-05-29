package com.example.jingdongxx.home.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.view.DetailActivity;
import com.example.jingdongxx.home.adapter.RecommendAdapter;
import com.example.jingdongxx.home.adapter.RecyAdapter;
import com.example.jingdongxx.home.adapter.SecondkillAdapter;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.home.presenter.BroadcastPresenter;
import com.example.jingdongxx.home.presenter.TWOPrensenter;
import com.example.jingdongxx.home.view.iview.IHome;
import com.example.jingdongxx.home.view.iview.ScanActivity;
import com.example.jingdongxx.parentclass.MyGlide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by
 */

public class HomeFragment extends Fragment implements IHome {
    @BindView(R.id.saomiao)
    ImageView saomiao;
    @BindView(R.id.et_cz)
    EditText etCz;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.ll_xsqg)
    LinearLayout llXsqg;
    @BindView(R.id.miao_rlv)
    RecyclerView miaoRlv;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;
    private BroadcastPresenter broadcastPresenter;
    private TWOPrensenter twoPrensenter;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    //时间
    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
          /*  if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tvHour.setText("0"+mHour);
                }else {
                    tvHour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tvMinute.setText("0"+mMin+"");
                }else {
                    tvMinute.setText(mMin+"");
                }
                if (mSecond<10){
                    tvSecond.setText("0"+mSecond+"");
                }else {
                    tvSecond.setText(mSecond+"");
                }
            }*/
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, null);
        unbinder = ButterKnife.bind(this, view);
        broadcastPresenter = new BroadcastPresenter(this);
        broadcastPresenter.geths();
        miaoRlv.setLayoutManager(new GridLayoutManager(getActivity(), 12));

        twoPrensenter = new TWOPrensenter(this);
        twoPrensenter.cata();
        recy.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false));
        rlv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        startRun();
        //搜索框
        etCz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        return view;
    }


    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }


    @Override
    public void onDestroyView() {
        startRun();
        computeTime();
        super.onDestroyView();
        unbinder.unbind();
        broadcastPresenter.onDes();
        twoPrensenter.onDestro();

    }

    //轮播图
    @Override
    public void show(HomepageBean bean) {
        secondkill(bean);
        recommend(bean);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < bean.getData().size(); i++) {
            String icon = bean.getData().get(i).getIcon();
            list.add(icon);
        }
        banner.setImages(list);
        banner.setImageLoader(new MyGlide());
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        //启动banner
        banner.start();
    }


    @Override
    public void catashow(List<CataBean.DataBean> beans) {
        RecyAdapter recyAdapter = new RecyAdapter(beans, getActivity());
        recy.setAdapter(recyAdapter);
    }

    @Override
    public void shows(CataBean bean) {

    }

    //推荐
    private void recommend(HomepageBean bean) {
        List<HomepageBean.TuijianBean.ListBean> list = bean.getTuijian().getList();
        RecommendAdapter recommendAdapter = new RecommendAdapter(list, getActivity());
        rlv.setAdapter(recommendAdapter);
        recommendAdapter.setOnclickSpflAdpter(new RecommendAdapter.OnClickfl() {
            @Override
            public void onClickxq(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("pid", position + "");
                startActivity(intent);
            }
        });
    }

    //秒杀
    private void secondkill(HomepageBean bean) {
        HomepageBean.MiaoshaBean miaosha = bean.getMiaosha();
        SecondkillAdapter secondkillAdapter = new SecondkillAdapter(getActivity(), miaosha.getList());
        miaoRlv.setAdapter(secondkillAdapter);
        secondkillAdapter.setMiaoshaadpter(new SecondkillAdapter.OnClickms() {
            @Override
            public void onClickms(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("pid", position + "");
                startActivity(intent);
            }
        });
    }


    @OnClick(R.id.saomiao)
    public void onViewClicked() {
        new IntentIntegrator(getActivity())
                .setOrientationLocked(false)
                .setCaptureActivity(ScanActivity.class)
                .initiateScan();

    }

    @Override
// 通过 onActivityResult的方法获取扫描回来的值
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getActivity(), "内容为空", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "扫描成功", Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
