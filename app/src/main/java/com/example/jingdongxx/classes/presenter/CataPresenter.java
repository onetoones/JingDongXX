package com.example.jingdongxx.classes.presenter;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.classes.model.CataModel;
import com.example.jingdongxx.classes.model.imodel.ICataModel;
import com.example.jingdongxx.classes.view.iview.IClass;
import com.example.jingdongxx.net.OnNetListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 */

public class CataPresenter {
    private IClass activity;
    private final ICataModel model;

    public CataPresenter(IClass activity) {
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
                    getPro(cataBean.getData().get(0).getCid()+"");
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    public void getPro(String cid) {
        model.getpro(cid, new OnNetListner<ProductCataBean>() {
            @Override
            public void onSuccess(ProductCataBean productCataBean) {
                if (activity != null) {
                    List<ProductCataBean.DataBean> group = productCataBean.getData();
                    List<List<ProductCataBean.DataBean.ListBean>> chiled = new ArrayList<>();
                    for (int i = 0; i < group.size(); i++) {
                        List<ProductCataBean.DataBean.ListBean> list = group.get(i).getList();
                        chiled.add(list);
                    }
                    activity.proshow(group,chiled);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

}
