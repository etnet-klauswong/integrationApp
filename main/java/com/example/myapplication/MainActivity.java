package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;


import com.example.myapplication.flutterBase.MyFlutterActivity;
import com.example.myapplication.flutterBase.MyFlutterFragment;
import com.example.myapplication.fragmentAdapter.FragmentAdapter;
import com.example.myapplication.fragmentAdapter.Fragment_One;
import com.example.myapplication.fragmentAdapter.Fragment_Three;
import com.example.myapplication.fragmentAdapter.Fragment_Two;

import io.flutter.facade.Flutter;


public class MainActivity extends AppCompatActivity {
    View flutterView1;
    View flutterView2;
    View flutterView3;
    String routeName = "loginPage";
    // ViewPager
    private ViewPager mViewPager;
    // ButtonGroup
    private RadioGroup radioGroup;

    // Fragment Adapter -> Control Fragment
    // TODO: You Could not use this FragmentAdapter
    private FragmentAdapter<Fragment> mFragmentAdapter;

    // TODO: Instance a Object Fragment 
    private Fragment_One fragment_one;
    private Fragment_Two fragment_two;
    private Fragment_Three fragment_three;
    // whether init FlutterView
    private boolean isFirstInitFlutterView = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFormat( PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
//        Intent customFlutter = new FlutterActivity.IntentBuilder()
//                .initialRoute("someOtherRoute")
//                .build(currentActivity);
//        startActivity(customFlutter);
        // TODO: Test for - Add Flutter as a Fragment in a ViewPager
        init();
    }

    private void init(){
        initView(); // AndroidUI
        initFragment();
        initViewPagerAdapter();
        initListener();
    }

    private void initView(){
        radioGroup = findViewById(R.id.rg_foot_bar);
        mViewPager = findViewById(R.id.viewpager);
    }

    private void initFragment(){
        fragment_one = Fragment_One.newInstance();
        fragment_two = Fragment_Two.newInstance();
        fragment_three = Fragment_Three.newInstance();
    }

//    Add Flutter as a Fragment in a ViewPager
    public void initViewPagerAdapter(){
        mFragmentAdapter = new FragmentAdapter<>(getSupportFragmentManager());
        mFragmentAdapter.addFragment(fragment_one);
        mFragmentAdapter.addFragment(fragment_two);
        mFragmentAdapter.addFragment(fragment_three);
//        Intent customFlutter = new IntentBuilder()
//                .initialRoute("someOtherRoute")
//                .build(currentActivity);
//        startActivity(customFlutter);

//        mFragmentAdapter.updateFragment(0, MyFlutterFragment.newInstance("loginPage","ssssss"));
//        mFragmentAdapter.updateFragment(1, MyFlutterFragment.newInstance("quotesPage_withNssCore","ssssss"));
//        mFragmentAdapter.updateFragment(2, MyFlutterFragment.newInstance("quotesPage_withJava","ssssss"));
        mViewPager.setOffscreenPageLimit(mFragmentAdapter.getCount());
        mViewPager.setAdapter(mFragmentAdapter);
//        mViewPager.setCurrentItem(0);
//        mFragmentAdapter.notifyDataSetChanged();
        tryTestView("smallCanvas_areaChart", 100,100,100,900,flutterView1);
//        tryTestView("smallCanvas_volChart", 100,100,300,900,flutterView2);
//        tryTestView("smallCanvas_areaChart", 100,100,600,900,flutterView3);
//        tryTestActivity();
    }


    // TODO: Add Flutter as a Fragment in a ViewPager
    private int currIndex = 0;
    private void initListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_button_one:
                        currIndex = 0;
                        routeName = "loginPage";
                        getFlutterFragment(routeName);
                        break;
                    case R.id.radio_button_two:
                        currIndex = 1;
                        routeName = "quotesPage_withNssCore";
                        getFlutterFragment(routeName);
                        break;
                    case R.id.radio_button_three:
                        currIndex = 2;
                        routeName = "quotesPage_withJava";
                        getFlutterFragment(routeName);
                        break;
                }
                mViewPager.setCurrentItem(currIndex);
            }
        });
    }

    public void tryTestView(String RootName, int layout_LeftMargin, int layout_RightMargin, int layout_TopMargin, int layout_BottomMargin, View tempView){
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.leftMargin = layout_LeftMargin;
        layout.rightMargin = layout_RightMargin;
        layout.topMargin = layout_TopMargin;
        layout.bottomMargin = layout_BottomMargin;
        tempView = getFlutterView(tempView, MainActivity.this, RootName);
        addContentView( tempView, layout);
    }
//    "advertisementWidget"
    public void tryTestActivity(){
        getFlutterActivity(" ");
    }

    // TODO: getFlutterView func - the usage of getFlutterView func :
    //  flutterView = getFlutterView(flutterView, MainActivity.this, "loginPage");
    //  setContentView(flutterView); or addContentView(flutterView,layout);
    // Avaiable rootName -  1.  advertisementWidget   2.  loginPage     3.  canvasCharts (someErrors)   4.  quotesPage_withNssCore  5.  quotesPage_withJava
    public View getFlutterView(@Nullable View FlutterView, @Nullable Activity Activity, @Nullable String RootName){
        FlutterView = Flutter.createView(
                Activity,
                getLifecycle(),
                RootName);
        return  FlutterView; // Return a View
    }

    // TODO: getFlutterActivity func - the usage of getFlutterActivity func :
    //  getFlutterActivity("advertisementWidget");
    public void getFlutterActivity(@Nullable String RootName){
        // Avaiable rootName -  1.  advertisementWidget   2.  loginPage     3.  canvasCharts (someErrors)   4.  quotesPage_withNssCore  5.  quotesPage_withJava
        Intent intent = new Intent(this, MyFlutterActivity.class);
        intent.putExtra( "_route_",RootName);
        startActivity(intent);
    }

    // TODO: getFlutterFragment func - the usage of getFlutterFragment func :
    //  getFlutterFragment(routeName);;
    private void getFlutterFragment(String selectedRoute){
        mFragmentAdapter.updateFragment(currIndex, MyFlutterFragment.newInstance(selectedRoute,"ssssss"));
        mFragmentAdapter.notifyDataSetChanged();
    }
}
