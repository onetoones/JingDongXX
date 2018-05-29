package com.example.jingdongxx.shopping.model;

import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.model.IModel.IConfirmModel;

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

public class ConfirmModel implements IConfirmModel {
    @Override
    public void confirm(String uid, String price, String token, final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> confirm = OkRetrofiter.getAPI().confirm(uid, price, token);
        confirm.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBean bean) {
                        onNetListner.onSuccess(bean);
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
