package com.example.jingdongxx.shopping.view.iview;

import android.content.Context;

import com.example.jingdongxx.shopping.bean.Goodbean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Iview {
    //设置适配器的方法
    void  setmyadapter(Context context, List<Goodbean.DataBean> list);
    //上拉加载更多的方法
    void vjiazaigengduo(Context context,List<Goodbean.DataBean> list);

    //点击图片设置适配器的方法
    void  settuadapter(Context context,List<Goodbean.DataBean> list);
}
