package com.example.jingdongxx.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomepageBean.TuijianBean.ListBean> list;
    private Context context;
    OnClickfl onClickfl;
    public interface OnClickfl{
        void onClickxq(int position);
    }

    public void setOnclickSpflAdpter(OnClickfl onClickfl) {
        this.onClickfl = onClickfl;
    }

    public RecommendAdapter(List<HomepageBean.TuijianBean.ListBean> list, Context context) {

        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.recommend_layout, null);


        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final HomepageBean.TuijianBean.ListBean listBean = list.get(position);
        String[] split = listBean.getImages().split("\\|");
        myViewHolder.sdv.setImageURI(split[0]);
        myViewHolder.tv.setText(listBean.getTitle());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = list.get(position).getPid();
                onClickfl.onClickxq(pid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv;
        private final TextView tv;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.recommend_sim);
            tv = itemView.findViewById(R.id.recommend_tv);
            ll = itemView.findViewById(R.id.recommend_ll);
        }
    }
}
