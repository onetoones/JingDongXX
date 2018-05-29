package com.example.jingdongxx.shopping.model.IModel;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.bean.QueryBean;

/**
 * Created by
 */

public interface IQueryModel {
    void getQuery(String uid, String source,String token, OnNetListner<QueryBean> onNetListner);
    void delete(String uid, String pid, String token, OnNetListner<MyBean> onNetListner);
}
