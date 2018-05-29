package com.example.jingdongxx.home.presenter;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.model.CataModel;
import com.example.jingdongxx.classes.model.imodel.ICataModel;
import com.example.jingdongxx.classes.view.iview.IClass;
import com.example.jingdongxx.home.view.iview.IHome;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class TWOPrensenter {
    private IHome activity;
    private final ICataModel model;

    public TWOPrensenter(IHome activity) {
        this.activity = activity;
        model = new CataModel();
    }
    public void onDestro(){
        if (activity!=null){
            activity=null;
        }
    }
    public void cata(){
        model.getcata(new OnNetListner<CataBean>() {
            @Override
            public void onSuccess(CataBean cataBean) {
                if (activity!=null){
                    activity.catashow(cataBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
            }
        });
    }
}
