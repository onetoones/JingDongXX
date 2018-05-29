package com.example.jingdongxx.shopping.view.iview;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.bean.QueryBean;

import java.util.List;

/**
 * Created by 不将就 on 2018/5/16.
 */

public interface IShopping {
    void queryshow(List<QueryBean.DataBean> group, List<List<QueryBean.DataBean.ListBean>> chiled);

    void deleteshow(MyBean bean);
}
