package com.example.jingdongxx.my.presenter;

import com.example.jingdongxx.my.model.AddressModel;
import com.example.jingdongxx.my.model.imodel.IAddressModel;
import com.example.jingdongxx.my.view.iview.IAddressActivity;
import com.example.jingdongxx.my.view.iview.IBaseView;
import com.example.jingdongxx.net.OnNetListner;
import com.example.jingdongxx.parentclass.MyBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class AddressPresenter extends BasePresenter{

    private final IAddressModel model;

    IBaseView activity = getActivity();
    public AddressPresenter() {
        model = new AddressModel();
    }
   public void ondestr(){
       if (activity!=null){
            activity=null;
        }

   }
   public void getress(String uid, String addr, String mobile, String name, String token){
       model.address(uid, addr, mobile, name, token, new OnNetListner<MyBean>() {
           @Override
           public void onSuccess(MyBean bean) {
               if (activity!=null){
                   activity.show(bean);
               }
           }

           @Override
           public void onFailed(Exception e) {

           }
       });

   }
}
