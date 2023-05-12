package com.cetc7.remotecontrol.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.EditText;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;

public class ParamSetActivity extends Activity implements OnClickListener {
    private TextView txt_title,txt_left,txt_right;
    private EditText edit_param;
    private int rid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_set);
        initView();
        setListener();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_left:
                Utils.finish(ParamSetActivity.this);
                break;
            case R.id.txt_right:
                if (edit_param.getText()!=null) {
                    String str_edit = edit_param.getText().toString().trim();
                    Intent intent = new Intent();
                    intent.putExtra("param",str_edit);
                    setResult(rid,intent);
                    Utils.finish(ParamSetActivity.this);
                } else {
                    Utils.showShortToast(ParamSetActivity.this,"输入不能为空");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 绑定控件id
     */
    private void initView() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        String Name = getIntent().getStringExtra(Constants.NAME);
        rid = getIntent().getExtras().getInt("rid");
        txt_title.setText(Name);
        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_left.setText("取消");
        txt_left.setVisibility(View.VISIBLE);
        txt_right = (TextView) findViewById(R.id.txt_right);
        txt_right.setText("确定");
        txt_right.setVisibility(View.VISIBLE);
        edit_param = (EditText) findViewById(R.id.edit_param);
    }



    /**
     * 设置监听
     */
    private void setListener() {
        txt_left.setOnClickListener(this);
        txt_right.setOnClickListener(this);
    }
}
