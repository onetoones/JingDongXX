package com.example.jingdongxx.shopping.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.shopping.bean.MessgeEvent;
import com.example.jingdongxx.shopping.bean.QueryBean;
import com.example.jingdongxx.shopping.bean.QueryEventBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by
 */

public class QueryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<QueryBean.DataBean> group;
    private List<List<QueryBean.DataBean.ListBean>> chiled;
    OnClinks onClink;

    //接口回调
    public interface OnClinks {
        void onclikId(int pid);
    }

    public void setOnClink(OnClinks onClink) {
        this.onClink = onClink;
    }


    public QueryAdapter(Context context, List<QueryBean.DataBean> group, List<List<QueryBean.DataBean.ListBean>> chiled) {
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
        return chiled.get(groupPosition).size();
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final groupViewHodel hodel;
        if (convertView == null) {
            hodel = new groupViewHodel();
            view = View.inflate(context, R.layout.querygroup, null);
            hodel.check = view.findViewById(R.id.query_check);
            hodel.tv = view.findViewById(R.id.query_tv);
            view.setTag(hodel);
        } else {
            view = convertView;
            hodel = (groupViewHodel) view.getTag();
        }
        final QueryBean.DataBean dataBean = group.get(groupPosition);
        hodel.tv.setText(dataBean.getSellerName());
        hodel.check.setChecked(dataBean.isCheck());
        hodel.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setCheck(hodel.check.isChecked());
                changeChildState(groupPosition, hodel.check.isChecked());
                EventBus.getDefault().post(js());
                changeMianQXstatus(checkGroupAll());
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, final View convertView, ViewGroup parent) {
        View inflate;
        final chiledViewHodel hodel;
        if (convertView == null) {
            hodel = new chiledViewHodel();
            inflate = View.inflate(context, R.layout.querychiled_item, null);
            hodel.chi_check = inflate.findViewById(R.id.chi_check);
            hodel.chi_add = inflate.findViewById(R.id.chi_add);
            hodel.chi_jian = inflate.findViewById(R.id.chi_jian);
            hodel.chi_del = inflate.findViewById(R.id.chi_del);
            hodel.chi_im = inflate.findViewById(R.id.chi_im);
            hodel.chi_num = inflate.findViewById(R.id.chi_num);
            hodel.chi_price = inflate.findViewById(R.id.chi_price);
            hodel.chi_title = inflate.findViewById(R.id.chi_title);
            hodel.chi_yanse = inflate.findViewById(R.id.chi_yanse);
            inflate.setTag(hodel);
        } else {
            inflate = convertView;
            hodel = (chiledViewHodel) inflate.getTag();
        }
        final QueryBean.DataBean.ListBean listBean = chiled.get(groupPosition).get(childPosition);
        int price = (int) listBean.getPrice();
        hodel.chi_check.setChecked(listBean.isChecked());
        hodel.chi_price.setText("¥" + price);
        hodel.chi_title.setText(listBean.getTitle());
        String subhead = listBean.getSubhead();
        String substring = subhead.substring(1, 10);
        hodel.chi_yanse.setText(substring + "....");
        String[] split = listBean.getImages().split("\\|");
        hodel.chi_im.setImageURI(Uri.parse(split[0]));
        //给子条目设置点击事件
        hodel.chi_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setChecked(hodel.chi_check.isChecked());
                EventBus.getDefault().post(js());
                if (hodel.chi_check.isChecked()) {
                    if (ischeckAllchild(groupPosition)) {
                        changeGroupstatus(groupPosition, true);
                        changeMianQXstatus(checkGroupAll());
                    }
                } else {
                    changeGroupstatus(groupPosition, false);
                    changeMianQXstatus(checkGroupAll());
                }
                notifyDataSetChanged();
            }
        });
        //加加功能
        hodel.chi_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cou = listBean.getCou();
                hodel.chi_num.setText(++cou + "");
                listBean.setCou(cou);
                if (hodel.chi_check.isChecked()) {
                    EventBus.getDefault().post(js());
                }
            }
        });
        //减减供能
        hodel.chi_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cou = listBean.getCou();
                if (cou == 1) {
                    Toast.makeText(context, "最小数量为1", Toast.LENGTH_LONG).show();
                    return;
                }
                hodel.chi_num.setText(--cou + "");
                listBean.setCou(cou);
                if (hodel.chi_check.isChecked()) {
                    EventBus.getDefault().post(js());
                }
            }
        });
        //删除
        hodel.chi_del.setOnClickListener(new View.OnClickListener() {
            private AlertDialog dialog;

            @Override
            public void onClick(View v) {
                final List<QueryBean.DataBean.ListBean> listBeans = chiled.get(childPosition);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示");
                builder.setMessage("确认删除？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*QueryBean.DataBean.ListBean remove = listBeans.remove(childPosition);*/
                        int pid = chiled.get(groupPosition).get(childPosition).getPid();
                        Log.e("pid------", pid + "");
                        onClink.onclikId(pid);
                        if (listBeans.size()== 0) {
                            chiled.remove(groupPosition);
                            group.remove(groupPosition);
                        }
                        EventBus.getDefault().post(js());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

        return inflate;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class groupViewHodel {
        CheckBox check;
        TextView tv;
    }

    class chiledViewHodel {
        CheckBox chi_check;
        TextView chi_title;
        SimpleDraweeView chi_im;
        TextView chi_price, chi_yanse, chi_num, chi_del;
        ImageView chi_add, chi_jian;
    }

    //改变子条目的选中状态
    private void changeChildState(int groupPosition, boolean checked) {

        List<QueryBean.DataBean.ListBean> listBeans = chiled.get(groupPosition);

        for (int i = 0; i < listBeans.size(); i++) {
            listBeans.get(i).setChecked(checked);
        }

    }

    //计算价钱
    private QueryEventBean js() {
        int cou = 0;
        int jiage = 0;
        for (int i = 0; i < group.size(); i++) {
            for (int j = 0; j < chiled.get(i).size(); j++) {
                QueryBean.DataBean.ListBean listBean = chiled.get(i).get(j);
                if (listBean.isChecked()) {
                    cou += listBean.getCou();
                    jiage += (int) listBean.getPrice() * listBean.getCou();
                }
            }
        }
        QueryEventBean bean = new QueryEventBean();
        bean.setCount(cou);
        bean.setPrice(jiage);
        return bean;
    }

    //判断是否全选状态
    private boolean checkGroupAll() {
        for (int i = 0; i < group.size(); i++) {
            if (!group.get(i).isCheck()) {
                return false;
            }
        }
        return true;
    }

    //改变全选状态
    private void changeMianQXstatus(boolean flag) {
        MessgeEvent messgeEvent = new MessgeEvent();
        messgeEvent.setCheck(flag);
        EventBus.getDefault().post(messgeEvent);
    }

    //判断二级列是否选中
    private boolean ischeckAllchild(int groups) {
        List<QueryBean.DataBean.ListBean> listBeans = chiled.get(groups);
        for (int i = 0; i < listBeans.size(); i++) {
            QueryBean.DataBean.ListBean listBean = listBeans.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    //改变一级列表的状态
    private void changeGroupstatus(int groups, boolean flag) {
        QueryBean.DataBean dataBean = group.get(groups);
        dataBean.setCheck(flag);
    }

    public void qx(boolean flag) {
        for (int i = 0; i < group.size(); i++) {
            changeGroupstatus(i, flag);
            changeChildState(i, flag);
        }
        EventBus.getDefault().post(js());
        notifyDataSetChanged();
    }
}