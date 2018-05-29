package com.example.jingdongxx.classes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.bean.ProductCataBean;
import com.example.jingdongxx.classes.view.GoListActivity;

import java.util.List;

/**
 * Created by 不将就 on 2018/5/11.
 */

public class ProAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<ProductCataBean.DataBean> group;
    private List<List<ProductCataBean.DataBean.ListBean>> chiled;

    public ProAdapter(Context context, List<ProductCataBean.DataBean> group, List<List<ProductCataBean.DataBean.ListBean>> chiled) {
        this.context = context;
        this.group = group;
        this.chiled = chiled;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chiled.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHodel groupViewHodel;
        View view;
        if (convertView==null){
            groupViewHodel = new GroupViewHodel();
             view = View.inflate(context, R.layout.group_layout, null);
            groupViewHodel.tv = view.findViewById(R.id.gr_tv);
            view.setTag(groupViewHodel);

        }else {
            view = convertView;
            groupViewHodel = (GroupViewHodel) view.getTag();
        }
        ProductCataBean.DataBean dataBean = group.get(groupPosition);
        groupViewHodel.tv.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChiledViewHodel hodel;
        View views;
        if (convertView==null){
             hodel = new ChiledViewHodel();
            views = View.inflate(context, R.layout.chiled_layout, null);
            hodel.gv = views.findViewById(R.id.ch_gv);
            views.setTag(hodel);

        }else {
            views = convertView;
            hodel = (ChiledViewHodel) views.getTag();
        }
        final List<ProductCataBean.DataBean.ListBean> listBeans = chiled.get(groupPosition);

        GAAdapter gaAdapter = new GAAdapter(context, listBeans);
        hodel.gv.setAdapter(gaAdapter);
        hodel.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, GoListActivity.class);
                intent.putExtra("pscid",listBeans.get(position).getPscid()+"");
                context.startActivity(intent);
            }
        });
        return views;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHodel{
        TextView tv;
    }
    class ChiledViewHodel{
        GridView gv;
    }
}
