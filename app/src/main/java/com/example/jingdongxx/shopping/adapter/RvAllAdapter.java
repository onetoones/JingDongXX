package com.example.jingdongxx.shopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.shopping.bean.Goodbean;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class RvAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private Context context;
private List<Goodbean.DataBean> list;

public RvAllAdapter(Context context, List<Goodbean.DataBean> list) {
        this.context = context;
        this.list = list;
        }

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Goodbean.DataBean dataBean = list.get(position);
        myViewHolder.tvTitle.setText(dataBean.getTitle());
        int status = dataBean.getStatus();
        myViewHolder.tvBt.setText("查看订单");
        myViewHolder.tvStatus.setTextColor(Color.parseColor("#000000"));
        if (status == 0) {
        myViewHolder.tvStatus.setText("待支付");
        myViewHolder.tvBt.setText("取消订单");
        myViewHolder.tvStatus.setTextColor(Color.parseColor("#ff0000"));
        } else if (status == 1) {
        myViewHolder.tvStatus.setText("已取消");
        } else if (status == 2) {
        myViewHolder.tvStatus.setText("已支付");
        }

        myViewHolder.tvPrice.setText("价格：" + dataBean.getPrice());
        myViewHolder.tvPrice.setTextColor(Color.parseColor("#ff0000"));
        myViewHolder.tvTime.setText(dataBean.getCreatetime());


        }

@Override
public int getItemCount() {
        return list.size();
        }

class MyViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvStatus;
    private final TextView tvPrice;
    private final TextView tvTime;
    private final TextView tvBt;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.textView);
        tvStatus = itemView.findViewById(R.id.textView2);
        tvPrice = itemView.findViewById(R.id.textView3);
        tvTime = itemView.findViewById(R.id.textView4);
        tvBt = itemView.findViewById(R.id.textView5);

    }
}
}

