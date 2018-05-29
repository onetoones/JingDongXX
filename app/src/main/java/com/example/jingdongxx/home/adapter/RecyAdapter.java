package com.example.jingdongxx.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.CataBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CataBean.DataBean> list;
    private Context context;

    public RecyAdapter(List<CataBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.itemfragmmnet, null);

        return new MyHodel(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHodel myHodel = (MyHodel) holder;
        String[] split = list.get(position).getIcon().split("\\|");
        Uri parse = Uri.parse(split[0]);
        myHodel.im.setImageURI(parse);
        myHodel.tv.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHodel extends RecyclerView.ViewHolder {

        private final SimpleDraweeView im;
        private final TextView tv;

        public MyHodel(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.itemonf_sim);
            tv = itemView.findViewById(R.id.itemonef_tv);

        }
    }
}
