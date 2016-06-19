package com.lvzhihao.test.mytest.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lvzhihao.test.mytest.R;

import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by vzhihao on 2016/6/18.
 */
public class WebFragment extends TitleBaseFragment {

    private WebView mWebView;
    private PtrClassicFrameLayout ptrClassicFrameLayout;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getTitleHeaderBar().setVisibility(View.GONE);
        final View contentView = inflater.inflate(R.layout.fragment_web, null);
        mWebView = (WebView) contentView.findViewById(R.id.rotate_header_web_view);
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                ptrClassicFrameLayout.refreshComplete();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) contentView.findViewById(R.id.rotate_header_web_view_frame);
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
        // the following are default settings
        ptrClassicFrameLayout.setResistance(1.7f);
        ptrClassicFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrClassicFrameLayout.setDurationToClose(200);
        ptrClassicFrameLayout.setDurationToCloseHeader(1000);
        // default is false
        ptrClassicFrameLayout.setPullToRefresh(false);
        // default is true
        ptrClassicFrameLayout.setKeepHeaderWhenRefresh(true);
        ptrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh();
            }
        }, 100);
        return contentView;
    }

    private void updateData() {
        if (mWebView.getUrl()==null){
            mWebView.loadUrl("https://www.baidu.com/");
        }else {
            mWebView.loadUrl(mWebView.getUrl());
        }
    }
}
