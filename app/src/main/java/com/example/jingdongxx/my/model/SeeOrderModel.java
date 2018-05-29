package com.example.jingdongxx.my.model;

import android.util.Log;

import com.example.jingdongxx.my.bean.SeeorderBean;
import com.example.jingdongxx.my.model.imodel.ISeeModel;
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

public class SeeOrderModel implements ISeeModel {
    @Override
    public void see(String uid, final OnNetListner<SeeorderBean> onNetListner) {
        Observable<SeeorderBean> seeorder = OkRetrofiter.getAPI().seeorder(uid);
        seeorder.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SeeorderBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SeeorderBean seeorderBean) {
                        onNetListner.onSuccess(seeorderBean);
                        Log.e("seeorderBean",seeorderBean.getMsg()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListner.onFailed((Exception) e);
                    }
                    @Override
                    public void onComplete() {
                        Log.e("-------",1+"");
                    }
                });
    }
}
