package com.example.jingdongxx.net;

import com.example.jingdongxx.find.bean.ParagraphBean;
import com.example.jingdongxx.home.bean.SearchBean;
import com.example.jingdongxx.my.bean.SeeorderBean;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.DetailBean;
import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.my.bean.LoginBean;
import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.bean.QueryBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by
 */

public interface RetrofitAPI {
    //首页
    @GET("ad/getAd")
    Observable<HomepageBean> homepage();

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> sign(@Field("mobile") String mobile, @Field("password") String pwd);

    //注册
    @POST("user/reg")
    @FormUrlEncoded
    Observable<MyBean> regis(@Field("mobile") String mobile, @Field("password") String pwd);

    //商品分类左侧
    @GET("product/getCatagory")
    Observable<CataBean> cata();

    //商品分类右侧
    @POST("product/getProductCatagory")
    @FormUrlEncoded
    Observable<ProductCataBean> pro(@Field("cid") String cid);

    //列表详情页
    @POST("product/getProducts")
    @FormUrlEncoded
    Observable<GoListBean> golist(@Field("pscid") String pscid);

    //详情页
    @POST("product/getProductDetail")
    @FormUrlEncoded
    Observable<DetailBean> detail(@Field("pid") String pid, @Field("source") String source);

    //加入购物车
    @POST("product/addCart")
    @FormUrlEncoded
    Observable<MyBean> addcata(@Field("uid") String uid, @Field("pid") String pid, @Field("source") String source, @Field("token") String token);

    //查询购物车
    @POST("product/getCarts")
    @FormUrlEncoded
    Observable<QueryBean> querys(@Field("uid") String uid, @Field("source") String source, @Field("token") String token);

    //删除购物车
    @POST("product/deleteCart")
    @FormUrlEncoded
    Observable<MyBean> delete(@Field("uid") String uid, @Field("pid") String pid, @Field("token") String token);

    //关键字搜索
    @POST("product/searchProducts")
    @FormUrlEncoded
    Observable<SearchBean> searchs(@Field("keywords") String keywords, @Field("source") String source);

    //创建订单
    @POST("product/createOrder")
    @FormUrlEncoded
    Observable<MyBean> confirm(@Field("uid") String uid, @Field("price") String price, @Field("token") String token);

    //订单列表
    @POST("product/getOrders")
    @FormUrlEncoded
    Observable<Goodbean> all(@Field("uid") String uid);

    //修改订单
    @POST("product/updateOrder")
    @FormUrlEncoded
    Observable<MyBean> modify(@Field("uid") String uid, @Field("status") String status, @Field("orderId") String orderId);

    //添加收货地址
    @POST("user/addAddr")
    @FormUrlEncoded
    Observable<MyBean> ress(@Field("uid") String uid, @Field("addr") String addr, @Field("mobile") String mobile, @Field("name") String name, @Field("token") String token);

    //查看订单地址
    @POST("user/getAddrs")
    @FormUrlEncoded
    Observable<SeeorderBean> seeorder(@Field("uid") String uid);

    //修改昵称
    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<MyBean> nicknames(@Field("uid") String uid, @Field("nickname") String nickname);

    //段子
    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<ParagraphBean> find(@Field("source") String source, @Field("appVersion") String appVersion, @Field("page") String page);

}
