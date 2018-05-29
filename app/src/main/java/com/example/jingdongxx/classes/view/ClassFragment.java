package com.example.jingdongxx.classes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.adapter.CataAdapter;
import com.example.jingdongxx.classes.adapter.ProAdapter;
import com.example.jingdongxx.classes.bean.CataBean;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.classes.presenter.CataPresenter;
import com.example.jingdongxx.classes.view.iview.IClass;
import com.example.jingdongxx.widget.MyExpandableListView;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by
 */

public class ClassFragment extends Fragment implements IClass {
    @BindView(R.id.cata_lv)
    ListView cataLv;
    @BindView(R.id.class_banner)
    Banner classBanner;
    @BindView(R.id.class_elc)
    MyExpandableListView classElc;
    @BindView(R.id.sv)
    ScrollView sv;
    Unbinder unbinder;
    private CataPresenter cataPresenter;
    private CataAdapter adapter;
    private ProAdapter proAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.class_layout, null);
        unbinder = ButterKnife.bind(this, view);
        cataPresenter = new CataPresenter(this);

        cataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CataBean.DataBean bean = (CataBean.DataBean) parent.getItemAtPosition(position);
                adapter.changeItemSelect(position);
                int cid = bean.getCid();

                cataPresenter.getPro(cid+"");
            }
        });
        cataPresenter.cata();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        cataPresenter.onDestro();
    }

    @Override
    public void catashow(List<CataBean.DataBean> beans) {
        adapter = new CataAdapter(getActivity(), beans);
        cataLv.setAdapter(adapter);
        adapter.changeItemSelect(0);

    }

    @Override
    public void proshow(List<ProductCataBean.DataBean> group, List<List<ProductCataBean.DataBean.ListBean>> chiled) {

        proAdapter = new ProAdapter(getActivity(), group, chiled);
        classElc.setAdapter(proAdapter);
        for (int i = 0; i <group.size() ; i++) {
            classElc.expandGroup(i);

        }
    }
}
