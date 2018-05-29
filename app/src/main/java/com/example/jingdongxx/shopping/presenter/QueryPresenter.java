package com.example.jingdongxx.shopping.presenter;



import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.bean.QueryBean;
import com.example.jingdongxx.shopping.model.IModel.IQueryModel;
import com.example.jingdongxx.shopping.model.QueryModel;
import com.example.jingdongxx.shopping.view.iview.IShopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 */

public class QueryPresenter {
    private IShopping activity;
    private final IQueryModel model;

    public QueryPresenter(IShopping activity) {
        this.activity = activity;
        model = new QueryModel();
    }
    public void ondestrys(){
        if (activity!=null){
            activity=null;
        }
    }
    public void querys(String uid,String token){
        model.getQuery(uid, "android",token, new OnNetListner<QueryBean>() {
            @Override
            public void onSuccess(QueryBean queryBean) {
                if (activity!=null){
                    List<QueryBean.DataBean> group = queryBean.getData();
                    List<List<QueryBean.DataBean.ListBean>> chiled = new ArrayList<>();
                    for (int i = 0; i <group.size(); i++) {
                        QueryBean.DataBean dataBean = group.get(i);
                        List<QueryBean.DataBean.ListBean> list = dataBean.getList();
                        chiled.add(list);

                    }
                    activity.queryshow(group,chiled);

                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    public void getdelete(String uid, String pid, String token){
        model.delete(uid, pid, token, new OnNetListner<MyBean>() {
            @Override
            public void onSuccess(MyBean bean) {
                if (activity!=null){
                    activity.deleteshow(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

}
