package com.example.jingdongxx.classes.model.imodel;

import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.classes.bean.DetailBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by
 */

public interface IDtetailModel {
    void getAdd(String uid, String pid, String source, String token, OnNetListner<MyBean> onNetListner);

    void getDetail(String pid, String source, OnNetListner<DetailBean> onNetListner);

}
