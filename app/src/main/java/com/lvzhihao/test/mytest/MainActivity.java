package com.lvzhihao.test.mytest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.lvzhihao.test.mytest.Fragment.BaseFragment;
import com.lvzhihao.test.mytest.Fragment.VideoFragment;
import com.lvzhihao.test.mytest.Fragment.WebFragment;
import com.lvzhihao.test.mytest.utils.ArrayUtils;
import com.lvzhihao.test.mytest.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;
    private String[] texts;
    private ListView listView;
    private ArrayList<Fragment> fragmentList;
    private VideoFragment videoFragment;
    private WebFragment webFragment;
    private SupportMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        toolbar.setTitle("MultyClient");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        texts = ArrayUtils.getMenuItemTexts();
        for (int i = 0; i< texts.length; i++) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id", texts[i]);
            data.add(stringObjectHashMap);
        }
        SimpleAdapter simple = new SimpleAdapter(this, data,
                R.layout.menu_list, new String[] { "id" },
                new int[] { R.id.text1 });
        listView.setAdapter(simple);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_UP){
                    return false;
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toolbar.setTitle(texts[position]);
                switch (position){
                    case 0:FragmentUtil.switchFragment(videoFragment);
                        break;
                    case 3:FragmentUtil.switchFragment(webFragment);
                        break;
                    case 4:FragmentUtil.switchFragment(map);
                        break;
                }
            }
        });
        initFragment();
    }

    private void initFragment() {
        fragmentList = new ArrayList<Fragment>();
        videoFragment = new VideoFragment();
        webFragment = new WebFragment();
        Intent intent = getIntent();
        MapStatus.Builder builder = new MapStatus.Builder();
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
            // 当用intent参数时，设置中心点为指定点
            Bundle b = intent.getExtras();
            LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
            builder.target(p);
        }
        builder.overlook(-20).zoom(15);
        BaiduMapOptions bo = new BaiduMapOptions().mapStatus(builder.build())
                .compassEnabled(false).zoomControlsEnabled(false);
        map = SupportMapFragment.newInstance(bo);
        fragmentList.add(videoFragment);
        fragmentList.add(map);
        fragmentList.add(webFragment);
        FragmentUtil.initFragment(this,videoFragment);

    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        frameLayout = (FrameLayout) findViewById(R.id.fl_main);
        listView = (ListView) findViewById(R.id.lv_menu);
    }
}
