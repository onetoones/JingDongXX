package com.example.jingdongxx.classes.model;

import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.classes.bean.DetailBean;
import com.example.jingdongxx.classes.model.imodel.IDtetailModel;
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

public class DetailModel implements IDtetailModel {
    @Override
    public void getAdd(String uid, String pid,String source,String token,final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> addcata = OkRetrofiter.getAPI().addcata(uid, pid,source,token);
        addcata.observeOn(AndroidSchedulers.mainThread())
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

    @Override
    public void getDetail(String pid,String source, final OnNetListner<DetailBean> onNetListner) {
        Observable<DetailBean> detail = OkRetrofiter.getAPI().detail(pid,source);
        detail.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailBean detailBean) {
                        onNetListner.onSuccess(detailBean);
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
