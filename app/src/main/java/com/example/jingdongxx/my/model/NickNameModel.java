package com.example.jingdongxx.my.model;

import com.example.jingdongxx.my.model.imodel.INickNameModel;
import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

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

public class NickNameModel implements INickNameModel {
    @Override
    public void getname(String uid, String name, final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> nicknames = OkRetrofiter.getAPI().nicknames(uid, name);

        nicknames.observeOn(AndroidSchedulers.mainThread())
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
