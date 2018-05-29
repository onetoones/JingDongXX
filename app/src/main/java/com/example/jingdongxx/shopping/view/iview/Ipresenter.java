package com.example.jingdongxx.shopping.view.iview;

import android.content.Context;

import com.example.jingdongxx.shopping.bean.Goodbean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Ipresenter {
    void getmv(Context context, Iview iview, Imode imode);

    //上啦加载更多的方法
    void pjiazaigengduo(Context context, Iview iview, Imode imode);

    //修改的方法
    void upgood(Context context, Iview iview, Imode imode,int id);

    //点击图片时的适配器
    void diantu(Context context, Iview iview, Imode imode,List<Goodbean.DataBean> list1);
}
