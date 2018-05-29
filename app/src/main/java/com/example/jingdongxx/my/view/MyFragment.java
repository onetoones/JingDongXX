package com.example.jingdongxx.my.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingdongxx.R;
import com.example.jingdongxx.classes.view.DetailActivity;
import com.example.jingdongxx.home.adapter.RecommendAdapter;
import com.example.jingdongxx.home.bean.HomepageBean;
import com.example.jingdongxx.my.presenter.BottomPrensenter;
import com.example.jingdongxx.my.presenter.NickNamePresenter;
import com.example.jingdongxx.my.view.iview.IMfragment;
import com.example.jingdongxx.parentclass.MyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MyFragment extends Fragment implements IMfragment {
    @BindView(R.id.sctx)
    SimpleDraweeView sctx;
    @BindView(R.id.my_name)
    TextView names;
    @BindView(R.id.my_dl)
    RelativeLayout dl;
    @BindView(R.id.iv_dfk)
    ImageView ivDfk;
    @BindView(R.id.iv_sh)
    ImageView ivSh;
    @BindView(R.id.iv_dpj)
    ImageView ivDpj;
    Unbinder unbinder;
    @BindView(R.id.shopping_rc)
    RecyclerView shoppingRc;
    @BindView(R.id.my_sz)
    ImageView mySz;

    private BottomPrensenter bottomPrensenter;
    private EditText editText;
    private String etname;
    private NickNamePresenter nickNamePresenter;
    private SharedPreferences sp;
    private String token;
    private String uid;
    private int i;
    private String names1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_layout, null);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("UIDS", Context.MODE_PRIVATE);
        /*token = sp.getString("token", "");*/
        uid = sp.getString("uid", "");
        //修改昵称
        names1 = sp.getString("names", "");
        names.setText(names1);

        nickNamePresenter = new NickNamePresenter(this);
        bottomPrensenter = new BottomPrensenter(this);
        bottomPrensenter.geths();
        shoppingRc.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        bottomPrensenter.onDes();
        nickNamePresenter.ondestr();
    }

    @Override
    public void show(HomepageBean bean) {
        List<HomepageBean.TuijianBean.ListBean> list = bean.getTuijian().getList();
        RecommendAdapter recommendAdapter = new RecommendAdapter(list, getActivity());
        shoppingRc.setAdapter(recommendAdapter);
        recommendAdapter.setOnclickSpflAdpter(new RecommendAdapter.OnClickfl() {
            @Override
            public void onClickxq(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("pid", position + "");
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.my_sz, R.id.sctx, R.id.my_name, R.id.my_dl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_sz:
                startActivity(new Intent(getActivity(), SetupActivity.class));
                break;
            case R.id.sctx:
                break;
            case R.id.my_name:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                editText = new EditText(getActivity());
                builder.setTitle("设置")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(editText)
                        .setNegativeButton("", null);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etname = editText.getText().toString();
                        if (etname.length()!=0){
                        if(i ==0) {
                            names.setText(etname);
                            nickNamePresenter.getnick(uid, etname);
                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_LONG).show();}
                        }else {

                            Toast.makeText(getActivity(), "名字不能为空", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                builder.show();
                break;
            case R.id.my_dl:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void nicknameshow(MyBean bean) {
        String code = bean.getCode();
        i = Integer.parseInt(code);

    }


}
