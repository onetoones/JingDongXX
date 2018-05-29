package com.example.jingdongxx.my.presenter;

import android.text.TextUtils;

import com.example.jingdongxx.my.bean.LoginBean;
import com.example.jingdongxx.my.model.imodel.ILogModel;
import com.example.jingdongxx.my.model.LoginModel;
import com.example.jingdongxx.my.view.iview.ILoginActivity;
import com.example.jingdongxx.net.OnNetListner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 不将就 on 2018/5/10.
 */

public class LoginPresenter {
    private ILoginActivity activity;
    private final ILogModel model;

    public LoginPresenter(ILoginActivity activity) {
        this.activity = activity;
        model = new LoginModel();
    }

    public void log() {
        String mobile = activity.getName();
        String passworld = activity.getmm();
        ;
        if (checkAccount(mobile) && checkmm(passworld)) {
            model.getLog(mobile, passworld, new OnNetListner<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    activity.TZ(loginBean);
                }

                @Override
                public void onFailed(Exception e) {

                }
            });

        }
    }

    private boolean checkmm(String passworld) {
        if (TextUtils.isEmpty(passworld)) {
            activity.show("请输入密码");
            return false;
        }
        if (passworld.length() != 6) {
            activity.show("请输入六位数密码");
            return false;
        }
        return true;
    }

    private boolean checkAccount(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            activity.show("请输入账号");
            return false;
        }
        if (!isMobileNO(mobile)) {
            activity.show("请输入正确手机号");
            return false;
        }
        return true;
    }


    /*
判断是否是手机号
 */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}