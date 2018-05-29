package com.example.jingdongxx.classes.model;

import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.classes.model.imodel.ICataModel;
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

public class CataModel implements ICataModel {
    @Override
    public void getcata(final OnNetListner<CataBean> onNetListner) {
        Observable<CataBean> cata = OkRetrofiter.getAPI().cata();

        cata.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CataBean cataBean) {
                        onNetListner.onSuccess(cataBean);
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
    public void getpro(String cid, final OnNetListner<ProductCataBean> onNetListner) {
        Observable<ProductCataBean> pro = OkRetrofiter.getAPI().pro(cid);
        pro.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductCataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductCataBean productCataBean) {
                        onNetListner.onSuccess(productCataBean);
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
