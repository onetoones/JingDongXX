package com.example.jingdongxx.shopping.model;

import com.example.jingdongxx.net.OkRetrofiter;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.bean.QueryBean;
import com.example.jingdongxx.shopping.model.IModel.IQueryModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by
 */

public class QueryModel implements IQueryModel {
    @Override
    public void getQuery(String uid, String source, String token, final OnNetListner<QueryBean> onNetListner) {
        Observable<QueryBean> querys = OkRetrofiter.getAPI().querys(uid, source, token);

        querys.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<QueryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryBean queryBean) {
                        onNetListner.onSuccess(queryBean);
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
    public void delete(String uid, String pid, String token, final OnNetListner<MyBean> onNetListner) {
        Observable<MyBean> delete = OkRetrofiter.getAPI().delete(uid, pid, token);
        delete.observeOn(AndroidSchedulers.mainThread())
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
