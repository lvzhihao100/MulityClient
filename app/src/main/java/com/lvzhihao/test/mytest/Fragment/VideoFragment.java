package com.lvzhihao.test.mytest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lvzhihao.test.mytest.R;
import com.lvzhihao.test.mytest.adapter.VideoFragmentAdapter;

import java.util.ArrayList;

/**
 * Created by vzhihao on 2016/6/17.
 */
public class VideoFragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener{
    // 三个textview
    private TextView tab1Tv, tab2Tv, tab3Tv;
    // 指示器
    private ImageView cursorImg;
    // viewpager
    private ViewPager viewPager;
    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
    private int currentIndex = 0;
    // 指示器的偏移量
    private int offset = 0;
    // 左margin
    private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的三分之一
    private int screen1_3;
    //
    private RelativeLayout.LayoutParams lp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_video, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screen1_3 = screenWidth / 3;
        cursorImg = (ImageView) view.findViewById(R.id.cursor);
        lp = (RelativeLayout.LayoutParams) cursorImg.getLayoutParams();
        viewPager = (ViewPager) view.findViewById(R.id.third_vp);

        tab1Tv = (TextView) view.findViewById(R.id.tab1_tv);
        tab2Tv = (TextView)view. findViewById(R.id.tab2_tv);
        tab3Tv = (TextView)view. findViewById(R.id.tab3_tv);
        //
        tab1Tv.setOnClickListener(this);
        tab2Tv.setOnClickListener(this);
        tab3Tv.setOnClickListener(this);
        initViewPager();
    }

    private void initViewPager() {
        fragmentsList = new ArrayList<>();
        Fragment fragment = new Tab3Fragment();
        fragmentsList.add(fragment);
        fragment = new Tab3Fragment();
        fragmentsList.add(fragment);
        fragment = new Tab3Fragment();
        fragmentsList.add(fragment);
        viewPager.setAdapter(new VideoFragmentAdapter(getActivity().getSupportFragmentManager(), fragmentsList));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (screen1_3 - cursorImg.getLayoutParams().width) / 2;
        lp.leftMargin = position * screen1_3 + offset + positionOffsetPixels / 3;
        cursorImg.setLayoutParams(lp);
        currentIndex = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1_tv:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab2_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3_tv:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
