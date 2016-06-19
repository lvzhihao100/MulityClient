package com.lvzhihao.test.mytest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by vzhihao on 2016/6/17.
 */
public class VideoFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public VideoFragmentAdapter(FragmentManager fm, List<Fragment>list) {
        super(fm);
        this.list=list;
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
