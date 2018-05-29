package com.example.jingdongxx.my.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jingdongxx.my.presenter.BasePresenter;
import com.example.jingdongxx.my.view.iview.IBaseView;

import butterknife.ButterKnife;

/**
 * @Creation date
 * @name
 * @Class action
 */

public abstract class BaseActivity<B extends BasePresenter> extends AppCompatActivity implements IBaseView{

    public B b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        b = setPresenter();
        if (b!=null){
            b.BasePresenter(this);
        }
        initData();
    }

    protected abstract int setLayout();

    protected abstract B setPresenter();

    abstract void initView();
    abstract void initData();

}
