package com.example.myapplication.flutterBase;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.facade.Flutter;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;

public class MyFlutterFragment extends Fragment {
    private static final String TAG = "MyFlutterFragment";
    // route
    public static final String AGR_ROUTE = "_route_";
    // params
    public static final String PARAMS = "_params_";
    private String mRoute = "/";
    private String mParams = "";
    private FlutterView mFlutterView;

    public static MyFlutterFragment newInstance(String route,String params){
        Bundle args = new Bundle();
        MyFlutterFragment fragment = new MyFlutterFragment();
        args.putString(MyFlutterFragment.AGR_ROUTE,route);
        args.putString(MyFlutterFragment.PARAMS,params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mRoute = getArguments().getString(AGR_ROUTE);
            mParams = getArguments().getString(PARAMS);
            JSONObject jsonObject = new JSONObject();
            JSONObject pageParamsObject;
            if(!TextUtils.isEmpty(mParams)){
                try {
                    pageParamsObject = new JSONObject(mParams);
                    jsonObject.put("pageParams",pageParamsObject);
                    mRoute = mRoute + "？" + jsonObject.toString();
                    Log.d("ssd",mRoute);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Log.d(TAG,"onCreateView-mRoute:"+mRoute);
        mFlutterView = Flutter.createView(getActivity(),getLifecycle(),mRoute);
        mFlutterView.setZOrderOnTop(true);
        mFlutterView.setZOrderMediaOverlay(false);
        mFlutterView.getHolder().setFormat( Color.parseColor("#00000000"));
        GeneratedPluginRegistrant.registerWith(mFlutterView.getPluginRegistry());
        return mFlutterView;
    }
}

