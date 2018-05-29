package com.example.jingdongxx.classes.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.CataBean;

import java.util.List;

/**
 * Created by 不将就 on 2018/5/11.
 */

public class CataAdapter extends BaseAdapter {
    private Context context;
    private List<CataBean.DataBean> list;

    public CataAdapter(Context context, List<CataBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel hodel;
        View view;
        if (convertView == null) {
            hodel = new ViewHodel();
            view = View.inflate(context, R.layout.cata_item, null);
            hodel.tv = view.findViewById(R.id.cata_tv);
            hodel.ll = view.findViewById(R.id.ca_ll);

            view.setTag(hodel);
        } else {
            view = convertView;
            hodel = (ViewHodel) view.getTag();
        }
        CataBean.DataBean dataBean = list.get(position);
        hodel.tv.setText(dataBean.getName());
        if (dataBean.isChecked()) {
            hodel.ll.setBackgroundColor(Color.parseColor("" +
                    "#33000000"));
        } else {
            hodel.ll.setBackgroundColor(Color.WHITE);
        }

        return view;
    }

    class ViewHodel {
        TextView tv;
        LinearLayout ll;
    }
    public void changeItemSelect(int position){
        for (int i = 0; i < list.size(); i++) {
            CataBean.DataBean dataBean = list.get(i);
            dataBean.setChecked(false) ;
        }
        CataBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }
}
