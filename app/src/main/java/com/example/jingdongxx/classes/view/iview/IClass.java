package com.example.jingdongxx.classes.view.iview;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;

import java.util.List;

/**
 * Created by 不将就 on 2018/5/10.
 */

public interface IClass {
    void catashow(List<CataBean.DataBean> beans);
    void proshow(List<ProductCataBean.DataBean> group,List<List<ProductCataBean.DataBean.ListBean>> chiled);
}
