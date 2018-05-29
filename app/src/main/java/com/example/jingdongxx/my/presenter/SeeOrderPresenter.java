package com.example.jingdongxx.my.presenter;

import android.util.Log;

import com.example.jingdongxx.my.bean.SeeorderBean;
import com.example.jingdongxx.my.model.SeeOrderModel;
import com.example.jingdongxx.my.model.imodel.ISeeModel;
import com.example.jingdongxx.my.view.iview.ISeeOrderActivity;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class SeeOrderPresenter {
    private ISeeOrderActivity activity;
    private final ISeeModel model;

    public SeeOrderPresenter(ISeeOrderActivity activity) {
        this.activity = activity;
        model = new SeeOrderModel();
    }
    public void ondestr(){
        if (activity!=null){
            activity=null;
        }

    }

    public void seeod(String uid){
        Log.e("",uid);
        model.see(uid, new OnNetListner<SeeorderBean>() {
            @Override
            public void onSuccess(SeeorderBean bean) {
                if (activity!=null){
                    activity.ISeeor(bean);

                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }
}
