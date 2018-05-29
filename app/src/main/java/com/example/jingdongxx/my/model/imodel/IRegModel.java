package com.example.jingdongxx.my.model.imodel;

import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by 不将就 on 2018/5/10.
 */

public interface IRegModel {

    void getreg(String name, String pwd, OnNetListner<MyBean> onNetListner);
}
