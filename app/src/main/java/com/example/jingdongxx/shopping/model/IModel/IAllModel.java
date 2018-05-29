package com.example.jingdongxx.shopping.model.IModel;

import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.shopping.bean.Goodbean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IAllModel {

    void getAll(String uid, OnNetListner<Goodbean> onNetListner);

}
