package com.example.jingdongxx.home.model;

import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.home.model.imodel.IHomePageModel;
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

public class HomePageModel implements IHomePageModel {
    @Override
    public void gethome(final OnNetListner<HomepageBean> onNetListner) {
        Observable<HomepageBean> homepage = OkRetrofiter.getAPI().homepage();

        homepage.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomepageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomepageBean homepageBean) {
                        onNetListner.onSuccess(homepageBean);
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
