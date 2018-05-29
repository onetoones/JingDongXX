package com.example.jingdongxx.shopping.model.IModel;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IConfirmModel {
    void confirm(String uid, String price, String token, OnNetListner<MyBean> onNetListner);
}
