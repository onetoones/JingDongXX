package com.example.jingdongxx.home.presenter;

import android.util.Log;

import com.example.jingdongxx.home.bean.SearchBean;
import com.example.jingdongxx.home.model.SearchModel;
import com.example.jingdongxx.home.model.imodel.ISearchModel;
import com.example.jingdongxx.home.view.iview.ISearch;
import com.example.jingdongxx.net.OnNetListner;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class SeaarchPresenter {
    private ISearch activity;
    private final ISearchModel model;

    public SeaarchPresenter(ISearch activity) {
        this.activity = activity;
        model = new SearchModel();
    }
    public void onDestro() {
        if (activity != null) {
            activity = null;
        }
    }

    public void getsea( String keywords,String source){
        model.getSearch(keywords, source, new OnNetListner<SearchBean>() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                if (activity!=null){
                    activity.shows(searchBean);
                    Log.e("listzzzzz",searchBean.getMsg()+"");
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
