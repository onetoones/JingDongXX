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

public class SecondkillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
   private Context context;
   private List<HomepageBean.MiaoshaBean.ListBeanX> list;
    OnClickms onClickms;
    public SecondkillAdapter(Context context, List<HomepageBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnClickms{
        void onClickms(int position);
    }

    public void setMiaoshaadpter(OnClickms onClickms) {
        this.onClickms = onClickms;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.secondkill_layout, null);

        return new ViewHodele(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHodele viewHodele = (ViewHodele) holder;
        HomepageBean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        String[] split = listBeanX.getImages().split("\\|");
        for (int i = 0; i <2; i++) {
            viewHodele.sdv.setImageURI(split[i]);
        }
        viewHodele.js.setText(listBeanX.getSubhead());
        viewHodele.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = list.get(position).getPid();
                onClickms.onClickms(pid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodele extends RecyclerView.ViewHolder{
        private final TextView js;
        private final SimpleDraweeView sdv;
        private final LinearLayout ll;
        public ViewHodele(View itemView) {
            super(itemView);
            js = itemView.findViewById(R.id.second_tv);
            sdv = itemView.findViewById(R.id.second_sim);
            ll = itemView.findViewById(R.id.second_ll);


        }
    }
}
