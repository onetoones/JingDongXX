package com.example.jingdongxx.shopping.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jingdongxx.R;
import com.example.jingdongxx.shopping.adapter.RvAllAdapter;
import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.presenter.AllPresenter;
import com.example.jingdongxx.shopping.view.iview.IAllActivity;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class AllFragment extends Fragment implements IAllActivity{
        private RecyclerView rv;
    private AllPresenter allPresenter;

    @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_all, null);
            rv = view.findViewById(R.id.rv);
            allPresenter = new AllPresenter(this);
            SharedPreferences uids = getActivity().getSharedPreferences("UIDS", Context.MODE_PRIVATE);
            String uid = uids.getString("uid", "");
            allPresenter.alls(uid);

            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            return view;
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        allPresenter.ondestrys();
    }

    @Override
    public void show(Goodbean bean) {
        RvAllAdapter adapter = new RvAllAdapter(getContext(), bean.getData());
        rv.setAdapter(adapter);
    }
}
