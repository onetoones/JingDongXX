package com.example.jingdongxx.my.model.imodel;

import com.example.jingdongxx.my.bean.SeeorderBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface ISeeModel {

    void see(String uid, OnNetListner<SeeorderBean> onNetListner);

}
