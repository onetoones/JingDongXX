package com.example.jingdongxx.home.model.imodel;

import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IHomePageModel {
    void gethome(OnNetListner<HomepageBean> onNetListner);

}
