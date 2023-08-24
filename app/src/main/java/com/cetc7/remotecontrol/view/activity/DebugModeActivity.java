package com.cetc7.remotecontrol.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import androidx.annotation.Nullable;

public class DebugModeActivity extends Activity implements View.OnClickListener {
    private TextView txt_title;
    private ImageView img_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugmode);
        initView();
        setListener();
    }

    private void setListener() {
        img_back.setOnClickListener(this);
    }

    private void initView() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        String Name = getIntent().getStringExtra(Constants.NAME);
        txt_title.setText(Name);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(DebugModeActivity.this);
                break;
            default:
                break;
        }
    }
}
