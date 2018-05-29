package com.example.jingdongxx.my.presenter;

import android.util.Log;

import com.example.jingdongxx.my.model.NickNameModel;
import com.example.jingdongxx.my.model.imodel.INickNameModel;
import com.example.jingdongxx.my.view.iview.IMfragment;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class NickNamePresenter {
    private IMfragment activity;
    private final INickNameModel model;

    public NickNamePresenter(IMfragment activity) {
        this.activity = activity;
        model = new NickNameModel();
    }
    public void ondestr(){
        if (activity!=null){
            activity=null;
        }

    }
public void getnick(String uid, String name){
        model.getname(uid, name, new OnNetListner<MyBean>() {
            @Override
            public void onSuccess(MyBean bean) {
                if (activity!=null){
                    activity.nicknameshow(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
}

}
