package com.example.jingdongxx.my.model.imodel;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface INickNameModel {
    void getname(String uid, String name, OnNetListner<MyBean> onNetListner);

}
