package com.cetc7.remotecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.common.Utils;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initData();
        mHandler.sendEmptyMessageDelayed(0, 600);
    }

    private void initData() {
        GloableParams.UserInfos = new ArrayList<User>();
        GloableParams.Users = new HashMap<String, User>();
        FinalDb db = FinalDb.create(this, Constants.DB_NAME, false);
        GloableParams.UserInfos = db.findAll(User.class);
        for (User user : GloableParams.UserInfos) {
            GloableParams.Users.put(user.getTelephone(), user);
            Log.d("SplashActivity","user:"+user);
        }
        Log.d("SplashActivity","GloableParams.UserInfos:"+GloableParams.UserInfos.size());
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent();
            intent.setClass(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            finish();
        }
    };
}
