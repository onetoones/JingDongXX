package com.example.jingdongxx.shopping.view;

import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.bean.UpGoodbean;
import com.example.jingdongxx.shopping.view.iview.Gouwuchelisteneter;
import com.example.jingdongxx.shopping.view.iview.Imode;
import com.example.jingdongxx.shopping.view.iview.Okhttp;
import com.example.jingdongxx.shopping.view.iview.Xiugailisteneter;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class Mymode implements Imode {

    //获取购物车数据的方法
    Okhttp okhttp=new Okhttp();
    @Override
    public void getnetjsonbean(String uri, Map<String, String> map, final Gouwuchelisteneter gouwuchelisteneter) {
        okhttp.getnetpost(uri,map);
        okhttp.setOnOKHttpGetListener(new Okhttp.OkhttpgetListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(String json) {
                Gson gson = new Gson();
                Goodbean goodbean = gson.fromJson(json, Goodbean.class);
                List<Goodbean.DataBean> data = goodbean.getData();
                gouwuchelisteneter.gouwucheListeneter(data);

            }
        });

    }

    //修改的方法
    @Override
    public void xiugai(String uri, Map<String, String> map, final Xiugailisteneter xiugailisteneter) {
        okhttp.getnetpost(uri,map);
        okhttp.setOnOKHttpGetListener(new Okhttp.OkhttpgetListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(String json) {
                Gson gson = new Gson();
                UpGoodbean upGoodbean = gson.fromJson(json, UpGoodbean.class);
                xiugailisteneter.xiugaiListeneter(upGoodbean);

            }
        });
    }
}