package com.example.jingdongxx.my.presenter;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.model.CataModel;
import com.example.jingdongxx.classes.model.imodel.ICataModel;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.home.model.HomePageModel;
import com.example.jingdongxx.home.model.imodel.IHomePageModel;
import com.example.jingdongxx.home.view.iview.IHome;
import com.example.jingdongxx.my.view.iview.IMfragment;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class BottomPrensenter {
    private IMfragment activity;
    private final IHomePageModel model;

    public BottomPrensenter(IMfragment activity) {
        this.activity = activity;
        model = new HomePageModel();
    }
    public void onDes(){
        if (activity!=null){
            activity=null;
        }
    }
    public void geths(){
        model.gethome(new OnNetListner<HomepageBean>() {
            @Override
            public void onSuccess(HomepageBean bean) {
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
