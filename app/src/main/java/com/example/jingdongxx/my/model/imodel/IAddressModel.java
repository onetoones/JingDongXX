package com.example.jingdongxx.my.model.imodel;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IAddressModel {
    void address(String uid, String addr, String mobile, String name,String token, OnNetListner<MyBean> onNetListner);

}
