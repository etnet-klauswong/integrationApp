package com.example.myapplication;

import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

/**
 * FullScreen Activity
 */
public class MyFlutterActivity extends AppCompatActivity implements MethodChannel.MethodCallHandler {
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";
    private FlutterView flutterView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceStae){
        super.onCreate(savedInstanceStae);
        getWindow().setFormat( PixelFormat.TRANSLUCENT);
        String route = getIntent().getStringExtra("_route_");
        String stockParams = getIntent().getStringExtra("_stockParams_");
        JSONObject jsonObject = new JSONObject();
        try{
//            jsonObject.put("stockPrice",stockPrice);
            jsonObject.put("stockParams",stockParams);
        } catch(JSONException e){
            e.printStackTrace();

        }
        // Create FlutterView
        flutterView = Flutter.createView(this,getLifecycle(),route + "?" + jsonObject.toString());
        System.out.println("Json Object");
        System.out.println(jsonObject.toString());
        // Set Content View
        setContentView(flutterView);
        // Register the Plugin
        registerMethodChannel();

    }


    @Override
    public void onBackPressed(){
        if(flutterView != null){
            flutterView.popRoute();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void registerMethodChannel(){
        new MethodChannel(flutterView,TOAST_CHANNEL).setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch(methodCall.method){
            default:
                result.notImplemented();
        }
    }
}