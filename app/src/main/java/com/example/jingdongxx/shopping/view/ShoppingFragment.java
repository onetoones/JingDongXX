package com.example.jingdongxx.shopping.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.parentclass.MyBean;
import com.example.jingdongxx.shopping.adapter.QueryAdapter;
import com.example.jingdongxx.shopping.bean.MessgeEvent;
import com.example.jingdongxx.shopping.bean.QueryBean;
import com.example.jingdongxx.shopping.bean.QueryEventBean;
import com.example.jingdongxx.shopping.presenter.QueryPresenter;
import com.example.jingdongxx.shopping.view.iview.IShopping;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by
 */

public class ShoppingFragment extends Fragment implements IShopping {
    @BindView(R.id.shop_elv)
    ExpandableListView shopElv;
    @BindView(R.id.shop_quanxuan)
    CheckBox shopQuanxuan;
    @BindView(R.id.shop_zongjia)
    TextView shopZongjia;
    @BindView(R.id.shop_count)
    TextView shopCount;
    @BindView(R.id.lls)
    LinearLayout lls;
    Unbinder unbinder;
    @BindView(R.id.shopiing_scroll)
    SwipeRefreshLayout shopiingScroll;
    @BindView(R.id.shopping_btn)
    Button shoppingBtn;

    private QueryPresenter presenter;
    private QueryAdapter queryAdapter;
    private View view;
    private SharedPreferences sp;
    private String token;
    private int price;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        presenter = new QueryPresenter(this);
        sp = getActivity().getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        uid = sp.getString("uid", "");
        if (this.token.equals("")) {
            View view = inflater.inflate(R.layout.ceshi_itme_jlj, null);
            return view;
        } else {
            View view = inflater.inflate(R.layout.shopping_layout, null);
            unbinder = ButterKnife.bind(this, view);
            presenter.querys(uid, this.token);
            EventBus.getDefault().register(this);
            shopQuanxuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    queryAdapter.qx(shopQuanxuan.isChecked());

                }
            });

            return view;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            presenter.ondestrys();
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void queryshow(List<QueryBean.DataBean> group, List<List<QueryBean.DataBean.ListBean>> chiled) {
        queryAdapter = new QueryAdapter(getActivity(), group, chiled);
        shopElv.setAdapter(queryAdapter);
        for (int i = 0; i < group.size(); i++) {
            shopElv.expandGroup(i);

        }
        //删除接口
        queryAdapter.setOnClink(new QueryAdapter.OnClinks() {
            @Override
            public void onclikId(int pid) {
                presenter.getdelete(uid,pid+"",token);
            }
        });

        shopiingScroll.setColorSchemeColors(Color.GRAY);
        shopiingScroll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shopiingScroll.setRefreshing(false);
                presenter.querys(sp.getString("uid", ""), token);
            }
        });

    }

    @Override
    public void deleteshow(MyBean bean) {
        String code = bean.getCode();
        int i = Integer.parseInt(code);
        if (i==0){
            queryAdapter.notifyDataSetChanged();
            Toast.makeText(getActivity(),bean.getMsg(),Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void Eventjs(QueryEventBean bean) {
        price = bean.getPrice();
        shopZongjia.setText("总计 : ¥ " + bean.getPrice());
        shopCount.setText("共" + bean.getCount() + "件商品");

    }

    @Subscribe
    public void Eventqx(MessgeEvent event) {
        shopQuanxuan.setChecked(event.isCheck());
    }

    @OnClick(R.id.shopping_btn)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ConfirmActivity.class);
        intent.putExtra("money",price+"");
        startActivity(intent);

    }
}
