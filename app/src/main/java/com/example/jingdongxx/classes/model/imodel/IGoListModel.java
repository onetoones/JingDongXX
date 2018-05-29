package com.example.jingdongxx.classes.model.imodel;

import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by 不将就 on 2018/5/12.
 */

public interface IGoListModel {
    void getList(String pscid, OnNetListner<GoListBean> onNetListner);

}
