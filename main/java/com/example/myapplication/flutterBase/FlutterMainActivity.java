package com.example.myapplication.flutterBase;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.facade.*;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterMain;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

/**
 * Android ->Flutter（FlutterActivity as Container）
 */

public class FlutterMainActivity extends FlutterActivity implements MethodChannel.MethodCallHandler{
    private static final String TAG = "FlutterMainActivity";
    private String initRouteStr = "quotesPage_withNssCore";
    private static final String TOAST_CHANNEL = "com.test.native_flutter/toast";

    @Override
    protected void onCreate(Bundle savedInstance){
        // Make the background loading window to be Transparent
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // Initiates the Dart VM
        FlutterMain.startInitialization(getApplicationContext());
//        FlutterMain.ensureInitializationComplete(getApplicationContext());
        super.onCreate(savedInstance);
        // Register with the Plugin
        GeneratedPluginRegistrant.registerWith(this);
        registerCustomPlugin(this);
    }



    private void registerCustomPlugin(PluginRegistry register){
        registerMethodChannel();
    }

    private void registerMethodChannel(){
    }

    @Override
    public FlutterView createFlutterView(Context context){
        getIntentData();
        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        // Create FlutterNativeView
        FlutterNativeView nativeView = this.createFlutterNativeView();
        //create FlutterView
        FlutterView flutterView = new FlutterView(FlutterMainActivity.this,(AttributeSet)null,nativeView);
        // Send the params of Router to flutterView
        flutterView.setInitialRoute(initRouteStr);
        // Set the Layout Params ot of flutterView
        flutterView.setLayoutParams(matchParent);
        // put FlutterView into ContentView,Set the content
        flutterView.setZOrderOnTop(true);
        flutterView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        this.setContentView(flutterView);
        return flutterView;
    }
    /**
     * get info of paramster
     * send to flutterVIew
     */
    private void getIntentData(){
        String route = getIntent().getStringExtra("_route_"); // get the data
        String params = getIntent().getStringExtra("_params_");
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("pageParams",params);
        } catch (JSONException e){
            e.printStackTrace();
        }
        // String = routParams + extraParams
        initRouteStr = route + "?" + jsonObject.toString();
        Log.d(TAG,"onCreate-route:" + route + "-params" + params);
        Log.d(TAG,"pnCreate-routeStr:" + initRouteStr);
    }
    /**
     * @param methodCall
     * @param result
     */
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch(methodCall.method){
            case "showToast"://调用原生的toast
                String content = methodCall.argument("content");
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
                break;
            default:
                result.notImplemented();
        }
    }
}

