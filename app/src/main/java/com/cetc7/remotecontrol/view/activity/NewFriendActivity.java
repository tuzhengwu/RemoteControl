package com.cetc7.remotecontrol.view.activity;

import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.UpdateService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import net.tsz.afinal.FinalDb;

public class NewFriendActivity extends Activity implements OnClickListener {

    private TextView txt_title,txt_left,txt_right;
    private EditText edit_name,edit_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfriend);
        initView();
        setListener();
    }

    private void setListener() {
        txt_left.setOnClickListener(this);
        txt_right.setOnClickListener(this);
    }

    private void initView() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        String Name = getIntent().getStringExtra(Constants.NAME);
        txt_title.setText(Name);
        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_left.setText("取消");
        txt_left.setVisibility(View.VISIBLE);
        txt_right = (TextView) findViewById(R.id.txt_right);
        txt_right.setText("确定");
        txt_right.setVisibility(View.VISIBLE);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_left:
                Utils.finish(NewFriendActivity.this);
                break;
            case R.id.txt_right:
                if (edit_name.getText()!=null&&edit_phone.getText()!=null) {
                    String str_name = edit_name.getText().toString().trim();
                    String str_phone = edit_phone.getText().toString().trim();
                    FinalDb db = FinalDb.create(this,Constants.DB_NAME,false);
                    User user = new User();
                    user.setTelephone(str_phone);
                    user.setUserName(str_name);
                    db.save(user);

                    GloableParams.UserInfos.add(user);
                    GloableParams.Users.put(user.getTelephone(),user);

//                    Intent intent = new Intent(this, UpdateService.class);
//                    this.startService(intent);

                    Utils.finish(NewFriendActivity.this);
                } else {
                    Utils.showShortToast(NewFriendActivity.this,"输入不能为空");
                }
                break;
            default:
                break;
        }
    }
}
