package com.example.jingdongxx.find.presenter;

import com.example.jingdongxx.find.bean.ParagraphBean;
import com.example.jingdongxx.find.model.IParagraphModel;
import com.example.jingdongxx.find.model.ParagraphModel;
import com.example.jingdongxx.find.view.IFindActivity;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class ParagraphPresenter {
    private IFindActivity activity;
    private final IParagraphModel model;

    public ParagraphPresenter(IFindActivity activity) {
        this.activity = activity;
        model = new ParagraphModel();
    }

    public void onDestroys() {
        if (activity != null) {
            activity = null;
        }
    }

    public void finda(String source, String appVersion, String page) {
        model.finds(source, appVersion, page, new OnNetListner<ParagraphBean>() {
            @Override
            public void onSuccess(ParagraphBean bean) {
                if (activity != null) {
                    activity.show(bean);
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
