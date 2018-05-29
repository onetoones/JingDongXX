package com.example.jingdongxx.classes.presenter;

import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.classes.bean.DetailBean;
import com.example.jingdongxx.classes.model.DetailModel;
import com.example.jingdongxx.classes.model.imodel.IDtetailModel;
import com.example.jingdongxx.classes.view.iview.IDetailActivity;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by
 */

public class DetailPresenter {
    private IDetailActivity activity;
    private final IDtetailModel model;

    public DetailPresenter(IDetailActivity activity) {
        this.activity = activity;
        model = new DetailModel();
    }

    public void onDestroys() {
        if (activity != null) {
            activity = null;
        }
    }

    public void getDetai(String pid) {
        model.getDetail(pid, "android", new OnNetListner<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                if (activity != null) {

                    activity.detailshow(detailBean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    public void getadds(String uid, String pid, String token) {
        model.getAdd(uid, pid, "android", token, new OnNetListner<MyBean>() {
            @Override
            public void onSuccess(MyBean myBean) {
                if (activity != null) {
                    activity.addshow(myBean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

}
