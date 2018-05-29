package com.example.jingdongxx.classes.model.imodel;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * Created by 不将就 on 2018/5/10.
 */

public interface ICataModel {
    void getcata(OnNetListner<CataBean> onNetListner);
    void getpro(String cid, OnNetListner<ProductCataBean> onNetListner);

}
