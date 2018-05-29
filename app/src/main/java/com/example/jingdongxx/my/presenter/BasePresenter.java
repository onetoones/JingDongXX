package com.example.jingdongxx.my.presenter;

import com.example.jingdongxx.my.view.iview.IBaseView;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class BasePresenter<B extends IBaseView> {
    B b;
    public void BasePresenter(B b) {
        this.b = b;
    }
    public B getActivity(){
        return b;
    }
}
