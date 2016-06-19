package com.lvzhihao.test.mytest.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lvzhihao.test.mytest.Bean.Video;
import com.lvzhihao.test.mytest.R;
import com.lvzhihao.test.mytest.provider.VideoProvider;

import java.util.List;

import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class Tab3Fragment extends TitleBaseFragment {

    private PtrClassicFrameLayout mPtrFrame;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  setHeaderTitle("本地视频");
getTitleHeaderBar().setVisibility(View.GONE);
        View contentView = inflater.inflate(R.layout.fragment_video_local, null);
        ListView listView = (ListView) contentView.findViewById(R.id.rotate_header_list_view);

        mPtrFrame = (PtrClassicFrameLayout) contentView.findViewById(R.id.rotate_header_list_view_frame);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                initData();
//            }
//        });


        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
        return contentView;
    }

    private void initData() {

//        VideoProvider videoProvider = new VideoProvider(getContext());
//        List<Video> list = videoProvider.getList();
//        for (Video video:list){
//            System.out.println("______"+video.toString());
//        }
        System.out.println("______________________刷新了");
    }


}
