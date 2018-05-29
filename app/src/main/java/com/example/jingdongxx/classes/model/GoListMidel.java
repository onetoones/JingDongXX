package com.example.jingdongxx.classes.model;

import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.classes.model.imodel.IGoListModel;
import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 */

public class GoListMidel implements IGoListModel {

    @Override
    public void getList(String pscid, final OnNetListner<GoListBean> onNetListner) {
        Observable<GoListBean> golist = OkRetrofiter.getAPI().golist(pscid);
        golist.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GoListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoListBean goListBean) {
                        onNetListner.onSuccess(goListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListner.onFailed((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
