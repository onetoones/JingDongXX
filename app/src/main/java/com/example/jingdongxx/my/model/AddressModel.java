package com.example.jingdongxx.my.model;

import com.example.jingdongxx.my.model.imodel.IAddressModel;
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

public class AddressModel implements IAddressModel {
    @Override
    public void address(String uid, String addr, String mobile, String name, String token, final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> ress = OkRetrofiter.getAPI().ress(uid, addr, mobile, name, token);

        ress.observeOn(AndroidSchedulers.mainThread())
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
