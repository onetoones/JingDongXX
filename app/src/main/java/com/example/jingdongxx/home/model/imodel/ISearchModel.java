package com.example.jingdongxx.home.model.imodel;

import com.example.jingdongxx.home.bean.SearchBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface ISearchModel {
    void getSearch(String keywords,String source,OnNetListner<SearchBean> onNetListner);
}
