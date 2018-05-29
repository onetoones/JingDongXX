package com.example.jingdongxx.classes.presenter;

import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.classes.model.GoListMidel;
import com.example.jingdongxx.classes.model.imodel.IGoListModel;
import com.example.jingdongxx.classes.view.iview.IGoListActivity;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by 不将就 on 2018/5/12.
 */

public class GoListPresenter {
private IGoListActivity activity;
    private final IGoListModel model;

    public GoListPresenter(IGoListActivity activity) {
        this.activity = activity;
        model = new GoListMidel();
    }
    public void onDestroys(){
        if (activity!=null){
            activity=null;
        }
    }
    public void getlist(String pscid){
        model.getList(pscid, new OnNetListner<GoListBean>() {
            @Override
            public void onSuccess(GoListBean goListBean) {
                if (activity!=null){
                    activity.show(goListBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

}
