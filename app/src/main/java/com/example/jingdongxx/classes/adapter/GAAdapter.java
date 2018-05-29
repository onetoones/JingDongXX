package com.example.jingdongxx.classes.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.ProductCataBean;



import java.util.List;

/**
 * 姓名：
 * 日期：
 * 类作用：
 */

public class GAAdapter extends BaseAdapter {
    private Context context;
    private List<ProductCataBean.DataBean.ListBean> listBeans;

    public GAAdapter(Context context, List<ProductCataBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHodel hodel;
        if (convertView == null) {
             hodel = new ViewHodel();
            view = View.inflate(context, R.layout.gv_layout, null);
            hodel.iv = view.findViewById(R.id.gv_im);
            hodel.tv = view.findViewById(R.id.gv_tv);
            hodel.ll = view.findViewById(R.id.gv_ll);
            view.setTag(hodel);
        }else {
            view = convertView;
            hodel= (ViewHodel) view.getTag();
        }
        ProductCataBean.DataBean.ListBean listBean = listBeans.get(position);
        String[] split = listBean.getIcon().split("\\|");
        Glide.with(context).load(split[0]).into(hodel.iv);
        hodel.tv.setText(listBean.getName());
        return view;
    }

    class ViewHodel {
        ImageView iv;
        TextView tv;
        LinearLayout ll;

    }
}
