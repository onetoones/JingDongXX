package com.example.jingdongxx.find.model;

import com.example.jingdongxx.find.bean.ParagraphBean;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public interface IParagraphModel {
    void finds(String source, String appVersion, String page, OnNetListner<ParagraphBean> onNetListner);
}
