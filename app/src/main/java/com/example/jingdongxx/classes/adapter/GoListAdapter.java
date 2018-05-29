package com.example.jingdongxx.classes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.GoListBean;
import com.example.jingdongxx.classes.view.DetailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 *
 */

public class GoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoListBean.DataBean> list;

    public GoListAdapter(Context context, List<GoListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.golistadapter_layout, null);

        return new Viewhodels(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewhodels hodel = (Viewhodels) holder;
        GoListBean.DataBean dataBean = list.get(position);
        hodel.name.setText(dataBean.getSubhead());
        hodel.price.setText(dataBean.getPrice() + "");
        String[] split = dataBean.getImages().split("\\|");
        Uri parse = Uri.parse(split[0]);
        hodel.im.setImageURI(parse);
        hodel.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pid", list.get(position).getPid() + "");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewhodels extends RecyclerView.ViewHolder {

        private final SimpleDraweeView im;
        private final TextView name;
        private final TextView price;
        private final LinearLayout ll;

        public Viewhodels(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.li_im);
            name = itemView.findViewById(R.id.li_name);
            price = itemView.findViewById(R.id.li_price);
            ll = itemView.findViewById(R.id.list_ll);
        }
    }


}
