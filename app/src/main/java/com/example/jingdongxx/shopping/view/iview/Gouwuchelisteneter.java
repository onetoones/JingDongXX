package com.example.jingdongxx.shopping.view.iview;

import com.example.jingdongxx.shopping.bean.Goodbean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface Gouwuchelisteneter {
    //购物车数据监听事件
    void gouwucheListeneter(List<Goodbean.DataBean> data);
}
