package com.example.jingdongxx.home.view.iview;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.home.bean.HomepageBean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IHome {
    void show(HomepageBean bean);
    void catashow(List<CataBean.DataBean> beans);
    void shows(CataBean bean);
}
