package com.example.jingdongxx.net;

/**
 * Created by 不将就 on 2018/5/9.
 */

public interface OnNetListner<T> {
void onSuccess(T t);
void onFailed(Exception e);

}
