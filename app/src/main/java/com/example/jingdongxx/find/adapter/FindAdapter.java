package com.example.jingdongxx.find.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.find.bean.ParagraphBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ParagraphBean.DataBean> list;

    public FindAdapter(Context context, List<ParagraphBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.find_item, null);

        return new MyView(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyView myView = (MyView) holder;
        ParagraphBean.DataBean dataBean = list.get(position);
        myView.name.setText(dataBean.getUser().getNickname());
        myView.times.setText(dataBean.getCreateTime()+"");
        myView.nr.setText(dataBean.getContent());
   if (dataBean.getImgUrls()==null){

   }else
   {
       String[] split = dataBean.getImgUrls().split("\\|");
       Uri parses = Uri.parse(split[0]);
       myView.tpnr.setImageURI(parses);
   }

        if (dataBean.getUser().getIcon()==null){

        }else {

            Uri parse = Uri.parse(dataBean.getUser().getIcon());
            myView.tx.setImageURI(parse);
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
    class MyView extends RecyclerView.ViewHolder{

        private final SimpleDraweeView tx;
        private final SimpleDraweeView tpnr;
        private final TextView name;
        private final TextView times;
        private final TextView nr;

        public MyView(View itemView) {
            super(itemView);
            tx = itemView.findViewById(R.id.find_tx);
            tpnr = itemView.findViewById(R.id.find_tpnr);
            name = itemView.findViewById(R.id.find_name);
            times = itemView.findViewById(R.id.find_time);
            nr = itemView.findViewById(R.id.find_nr);
        }
    }

}
