package com.example.jingdongxx.shopping.presenter;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.model.AllModel;
import com.example.jingdongxx.shopping.model.IModel.IAllModel;
import com.example.jingdongxx.shopping.view.iview.IAllActivity;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class AllPresenter {
    private IAllActivity activity;
    private final IAllModel model;

    public AllPresenter(IAllActivity activity) {
        this.activity = activity;
        model = new AllModel();
    }
    public void ondestrys() {
        if (activity != null) {
            activity = null;
        }
    }
    public void alls(String uid){
        model.getAll(uid, new OnNetListner<Goodbean>() {
            @Override
            public void onSuccess(Goodbean bean) {
                if (activity!=null){
                    activity.show(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }
}
