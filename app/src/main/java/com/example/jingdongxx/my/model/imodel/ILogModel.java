package com.example.jingdongxx.my.model.imodel;

import com.example.jingdongxx.my.bean.LoginBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by 不将就 on 2018/5/10.
 */

public interface ILogModel {

    void getLog(String name, String pwd, OnNetListner<LoginBean> onNetListner);
}
