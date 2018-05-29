package com.example.jingdongxx.shopping.model;

import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.model.IModel.IAllModel;

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

public class AllModel implements IAllModel {
    @Override
    public void getAll(String uid, final OnNetListner<Goodbean> onNetListner) {
        Observable<Goodbean> all = OkRetrofiter.getAPI().all(uid);
        all.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Goodbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Goodbean orderBean) {
                        onNetListner.onSuccess(orderBean);
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
