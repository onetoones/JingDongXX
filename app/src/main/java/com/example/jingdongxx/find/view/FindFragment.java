package com.example.jingdongxx.find.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jingdongxx.R;
import com.example.jingdongxx.find.adapter.FindAdapter;
import com.example.jingdongxx.find.bean.ParagraphBean;
import com.example.jingdongxx.find.presenter.ParagraphPresenter;

import java.util.List;


public class FindFragment extends Fragment implements IFindActivity {
    private View view;
    private RecyclerView mFindRcv;
    private ParagraphPresenter paragraphPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.finds_layout, null);
        paragraphPresenter = new ParagraphPresenter(this);
        paragraphPresenter.finda("android", "100", "1");
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFindRcv = (RecyclerView) view.findViewById(R.id.find_rcv);
    }

    @Override
    public void show(ParagraphBean bean) {
        List<ParagraphBean.DataBean> data = bean.getData();
        FindAdapter findAdapter = new FindAdapter(getActivity(), data);
        mFindRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFindRcv.setAdapter(findAdapter);
    }
}
