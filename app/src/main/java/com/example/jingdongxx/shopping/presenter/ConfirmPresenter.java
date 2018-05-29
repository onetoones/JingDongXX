package com.example.jingdongxx.shopping.presenter;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.model.ConfirmModel;
import com.example.jingdongxx.shopping.model.IModel.IConfirmModel;
import com.example.jingdongxx.shopping.view.iview.IConfirmActivity;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class ConfirmPresenter {
    private IConfirmActivity activity;
    private final IConfirmModel model;

    public ConfirmPresenter(IConfirmActivity activity) {
        this.activity = activity;
        model = new ConfirmModel();
    }
    public void ondestrys() {
        if (activity != null) {
            activity = null;
        }
    }
    public void con(String uid, String price, String token){
        model.confirm(uid, price, token, new OnNetListner<MyBean>() {
            @Override
            public void onSuccess(MyBean bean) {
                if (activity!=null){
                    activity.getShow(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
