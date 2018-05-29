package com.example.jingdongxx.shopping.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.shopping.bean.Goodbean;
import com.example.jingdongxx.shopping.view.Mymode;
import com.example.jingdongxx.shopping.view.Presenter;
import com.example.jingdongxx.shopping.view.iview.Iview;

import java.util.List;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class Myadapter  extends RecyclerView.Adapter <Myadapter.Myhodler>{
    private Context context;
    private List<Goodbean.DataBean> list;
    private Iview iview;

    public Myadapter(Context context, List<Goodbean.DataBean> list,Iview iview) {
        this.context = context;
        this.list = list;
        this.iview=iview;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Goodbean.DataBean> getList() {
        return list;
    }

    public void setList(List<Goodbean.DataBean> list) {
        this.list = list;
    }

    @Override
    public Myhodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.xrc, null);
        Myhodler myhodler=new Myhodler(view);
        return myhodler;
    }

    @Override
    public void onBindViewHolder(Myhodler holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.prive.setText("价格"+list.get(position).getPrice()+"");
        holder.time.setText("创建时间："+list.get(position).getCreatetime()+"");

        if (list.get(position).getStatus()==0.0){
            holder.zhi.setTextColor(Color.RED);
            holder.zhi.setText("待支付");
            holder.dan.setText("取消订单");
        }
        if (list.get(position).getStatus()==1.0){
            holder.zhi.setText("已支付");
            holder.dan.setText("查看订单");
        }
        if (list.get(position).getStatus()==2.0){
            holder.zhi.setTextColor(Color.YELLOW);
            holder.zhi.setText("已取消");
            holder.dan.setText("删除订单");
        }
        //如果是待支付的话点击改变取消订单
        if (holder.zhi.getText().toString().equals("待支付")) {
            holder.dan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹出框
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("提示");
                    builder.setMessage("是否取消?");
                    AlertDialog alertDialog = builder.create();
                    builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //获取点击时 订单的id
                            double orderid = list.get(position).getOrderid();
                            Presenter presenter=new Presenter();
                            presenter.upgood(context,iview,new Mymode(),(int)orderid);
                        }
                    });

                    builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();


                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myhodler extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView prive;
        private TextView time;
        private TextView zhi;
        private Button dan;
        public Myhodler(View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.prive=itemView.findViewById(R.id.prive);
            this.time=itemView.findViewById(R.id.time);
            this.zhi=itemView.findViewById(R.id.zhi);
            this.dan=itemView.findViewById(R.id.dan);
        }
    }
}