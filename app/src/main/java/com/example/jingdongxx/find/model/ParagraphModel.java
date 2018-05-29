package com.example.jingdongxx.find.model;

import com.example.jingdongxx.find.bean.ParagraphBean;
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

public class ParagraphModel implements IParagraphModel {
    @Override
    public void finds(String source, String appVersion, String page, final OnNetListner<ParagraphBean> onNetListner) {
        Observable<ParagraphBean> beanObservable = OkRetrofiter.getAPI().find(source, appVersion, page);
        beanObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ParagraphBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ParagraphBean paragraphBean) {
                        onNetListner.onSuccess(paragraphBean);
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
