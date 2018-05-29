package com.example.jingdongxx.my.model;

import com.example.jingdongxx.my.bean.LoginBean;
import com.example.jingdongxx.my.model.imodel.ILogModel;
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

public class LoginModel implements ILogModel {
    @Override
    public void getLog(String name, String pwd, final OnNetListner<LoginBean> onNetListner) {
        Observable<LoginBean> sign = OkRetrofiter.getAPI().sign(name, pwd);
        sign.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        onNetListner.onSuccess(loginBean);
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
