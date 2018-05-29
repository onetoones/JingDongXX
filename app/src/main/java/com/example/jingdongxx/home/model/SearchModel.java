package com.example.jingdongxx.home.model;

import android.util.Log;

import com.example.jingdongxx.home.bean.SearchBean;
import com.example.jingdongxx.home.model.imodel.ISearchModel;
import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class SearchModel implements ISearchModel {
    @Override
    public void getSearch(String keywords,String source,final OnNetListner<SearchBean> onNetListner) {
        Observable<SearchBean> searchs = OkRetrofiter.getAPI().searchs(keywords, source);
        searchs.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        onNetListner.onSuccess(searchBean);
                        Log.e("成功",searchBean+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListner.onFailed((Exception) e);
                        Log.e("失败","");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("=========","一次");
                    }
                });
    }
}
