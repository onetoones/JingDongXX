package com.example.jingdongxx.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.my.bean.SeeorderBean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class SeeOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SeeorderBean.DataBean> list;

    public SeeOrderAdapter(Context context, List<SeeorderBean.DataBean> list) {
        this.context = context;
        this.list = list;
        Log.e("list",list+"");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.seeoder_item, null);


        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyView myView = (MyView) holder;
        SeeorderBean.DataBean bean = list.get(position);

        myView.name.setText(bean.getName());
        myView.dz.setText(bean.getAddr());
        myView.phone.setText(bean.getMobile()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyView extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView dz;
        private final TextView phone;

        public MyView(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.seeitem_name);
            dz = itemView.findViewById(R.id.seeitem_dz);
            phone = itemView.findViewById(R.id.seeitem_phone);
        }
    }
}
