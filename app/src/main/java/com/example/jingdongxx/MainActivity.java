package com.example.jingdongxx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.jingdongxx.classes.view.ClassFragment;
import com.example.jingdongxx.find.view.FindFragment;
import com.example.jingdongxx.home.view.HomeFragment;
import com.example.jingdongxx.my.view.MyFragment;
import com.example.jingdongxx.shopping.view.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_vp)
    CustomViewPager mainVp;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.classs)
    ImageView classs;
    @BindView(R.id.find)
    ImageView find;
    @BindView(R.id.shopping)
    ImageView shopping;
    @BindView(R.id.my)
    ImageView my;
    @BindView(R.id.radio)
    RadioGroup radio;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new ClassFragment());
        list.add(new FindFragment());
        list.add(new ShoppingFragment());
        list.add(new MyFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        mainVp.setAdapter(adapter);
        mainVp.setScanScroll(false);
        mainVp.setOffscreenPageLimit(0);
    }

  @Override
    protected void onResume() {
      super.onResume();
        int flag = getIntent().getIntExtra("flag",0);
        if (flag==3){
            mainVp.setCurrentItem(3);
            home.setImageResource(R.mipmap.ac0);
            classs.setImageResource(R.mipmap.abw);
            find.setImageResource(R.mipmap.aby);
            shopping.setImageResource(R.mipmap.abv);
            my.setImageResource(R.mipmap.ac2);

        }


    }



    @OnClick({R.id.home, R.id.classs, R.id.find, R.id.shopping, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                mainVp.setCurrentItem(0);
                home.setImageResource(R.mipmap.ac1);
                classs.setImageResource(R.mipmap.abw);
                find.setImageResource(R.mipmap.aby);
                shopping.setImageResource(R.mipmap.abu);
                my.setImageResource(R.mipmap.ac2);
                break;
            case R.id.classs:
                mainVp.setCurrentItem(1);
                home.setImageResource(R.mipmap.ac0);
                classs.setImageResource(R.mipmap.abx);
                find.setImageResource(R.mipmap.aby);
                shopping.setImageResource(R.mipmap.abu);
                my.setImageResource(R.mipmap.ac2);
                break;
            case R.id.find:
                mainVp.setCurrentItem(2);
                home.setImageResource(R.mipmap.ac0);
                classs.setImageResource(R.mipmap.abw);
                find.setImageResource(R.mipmap.abz);
                shopping.setImageResource(R.mipmap.abu);
                my.setImageResource(R.mipmap.ac2);
                break;
            case R.id.shopping:
                mainVp.setCurrentItem(3);
                home.setImageResource(R.mipmap.ac0);
                classs.setImageResource(R.mipmap.abw);
                find.setImageResource(R.mipmap.aby);
                shopping.setImageResource(R.mipmap.abv);
                my.setImageResource(R.mipmap.ac2);
                break;
            case R.id.my:
                mainVp.setCurrentItem(4);
                home.setImageResource(R.mipmap.ac0);
                classs.setImageResource(R.mipmap.abw);
                find.setImageResource(R.mipmap.aby);
                shopping.setImageResource(R.mipmap.abu);
                my.setImageResource(R.mipmap.ac3);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
