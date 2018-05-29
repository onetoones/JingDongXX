package com.example.jingdongxx.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.view.DetailActivity;
import com.example.jingdongxx.home.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> list;

    public SearchAdapter(Context context, List<SearchBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_adapter, null);

        return new Viewhodels(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewhodels hodel = (Viewhodels) holder;
        SearchBean.DataBean dataBean = list.get(position);
        hodel.name.setText(dataBean.getSubhead());
        hodel.price.setText(dataBean.getPrice() + "");
        String[] split1 = dataBean.getImages().split("\\|");
        Uri parse = Uri.parse(split1[0]);
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
        return list == null ? 0 : list.size();
    }

    class Viewhodels extends RecyclerView.ViewHolder {

        private final SimpleDraweeView im;
        private final TextView name;
        private final TextView price;
        private final LinearLayout ll;

        public Viewhodels(View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.search_im);
            name = itemView.findViewById(R.id.search_name);
            price = itemView.findViewById(R.id.search_price);
            ll = itemView.findViewById(R.id.search_ll);
        }
    }

}
