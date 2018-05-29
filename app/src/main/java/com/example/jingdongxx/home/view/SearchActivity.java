package com.example.jingdongxx.home.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.home.adapter.SearchAdapter;
import com.example.jingdongxx.home.bean.SearchBean;
import com.example.jingdongxx.home.presenter.SeaarchPresenter;
import com.example.jingdongxx.home.view.iview.ISearch;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements ISearch {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_im)
    ImageView searchIm;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;
    private SeaarchPresenter seaarchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        ButterKnife.bind(this);
        seaarchPresenter = new SeaarchPresenter(this);
    }

    @OnClick(R.id.search_im)
    public void onViewClicked() {
      /*  SharedPreferences sp = getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "");*/

        String trim = searchEt.getText().toString();
        seaarchPresenter.getsea(trim ,"android");

    }

    @Override
    public void shows(SearchBean bean) {
        List<SearchBean.DataBean> data = bean.getData();

        SearchAdapter searchAdapter = new SearchAdapter(this, data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        searchRv.setLayoutManager(linearLayoutManager);
        searchRv.setAdapter(searchAdapter);

    }
}
