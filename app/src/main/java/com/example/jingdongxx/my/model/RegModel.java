package com.example.jingdongxx.my.model;

import com.example.jingdongxx.my.model.imodel.IRegModel;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 不将就 on 2018/5/10.
 */

public class RegModel implements IRegModel {
    @Override
    public void getreg(String name, String pwd, final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> sign = OkRetrofiter.getAPI().regis(name,pwd);
        sign.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        onNetListner.onSuccess(myBean);
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
