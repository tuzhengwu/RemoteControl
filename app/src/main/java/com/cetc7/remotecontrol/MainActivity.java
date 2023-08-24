package com.cetc7.remotecontrol;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.communication.UDPSocket;
import com.cetc7.remotecontrol.view.activity.NewFriendActivity;
import com.cetc7.remotecontrol.view.activity.NewMsgActivity;
import com.cetc7.remotecontrol.view.fragment.Fragment_Call;
import com.cetc7.remotecontrol.view.fragment.Fragment_Friends;
import com.cetc7.remotecontrol.view.fragment.Fragment_Main;
import com.cetc7.remotecontrol.view.fragment.Fragment_Msg;
import com.cetc7.remotecontrol.view.fragment.Fragment_Setting;

import org.apache.http.message.BasicNameValuePair;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    protected static final String TAG = "MainActivity";

    private TextView txt_title;
    private ImageView img_right;
    private TextView unreaMsgdLabel;// 未读消息textview
    private TextView unreadAddressLable;// 未读通讯录textview
    private TextView unreadFindLable;// 发现

    private Fragment[] fragments;
    private Fragment_Main homefragment;
    private Fragment_Msg msgfragment;
    private Fragment_Friends contactlistfragment;
    private Fragment_Call callfragment;
    private Fragment_Setting settingfragment;

    private ImageView[] imagebuttons;
    private TextView[] textviews;

    private int index;
    private int currentTabIndex;// 当前fragment的index

    public static UDPSocket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initViews();
        initTabView();
        setOnlistener();
        initUDPSocket();
    }

    private void initUDPSocket() {
        socket = new UDPSocket(this);
        socket.startUDPSocket();
    }


    private void setOnlistener() {
        img_right.setOnClickListener(this);
    }

    private void initTabView() {
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        unreadFindLable = (TextView) findViewById(R.id.unread_find_number);
        unreaMsgdLabel = (TextView) findViewById(R.id.unread_msg_number);

        homefragment = new Fragment_Main();
        msgfragment = new Fragment_Msg();
        contactlistfragment = new Fragment_Friends();
        callfragment = new Fragment_Call();
        settingfragment = new Fragment_Setting();
        fragments = new Fragment[]{homefragment,
                msgfragment, contactlistfragment, callfragment, settingfragment};

        imagebuttons = new ImageView[5];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_main);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_message);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_contact_list);
        imagebuttons[3] = (ImageView) findViewById(R.id.ib_call);
        imagebuttons[4] = (ImageView) findViewById(R.id.ib_setting);
        imagebuttons[0].setSelected(true);

        textviews = new TextView[5];
        textviews[0] = (TextView) findViewById(R.id.tv_main);
        textviews[1] = (TextView) findViewById(R.id.tv_weixin);
        textviews[2] = (TextView) findViewById(R.id.tv_contact_list);
        textviews[3] = (TextView) findViewById(R.id.tv_call);
        textviews[4] = (TextView) findViewById(R.id.tv_setting);
        textviews[0].setTextColor(0xFF45C01A);
        txt_title.setText(R.string.main);

        //添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homefragment)
                .add(R.id.fragment_container, msgfragment)
                .add(R.id.fragment_container, contactlistfragment)
                .add(R.id.fragment_container, callfragment)
                .add(R.id.fragment_container, settingfragment)
                .hide(msgfragment).hide(contactlistfragment)
                .hide(callfragment).hide(settingfragment)
                .show(homefragment).commit();
    }

    public void onTabClicked(View view){
        img_right.setVisibility(View.GONE);
        switch (view.getId()){
            case R.id.re_main:
                index = 0;
                txt_title.setText(R.string.main);
                break;
            case R.id.re_message:
                img_right.setVisibility(View.VISIBLE);
                index = 1;
                if(msgfragment!=null){
                    msgfragment.refresh();
                }
                txt_title.setText(R.string.chat);
                img_right.setImageResource(R.drawable.icon_add);
                break;
            case R.id.re_contact_list:
                index = 2;
                txt_title.setText(R.string.contacts);
                img_right.setVisibility(View.VISIBLE);
                if (contactlistfragment!=null){
                    contactlistfragment.refresh();
                }
                img_right.setImageResource(R.drawable.icon_titleaddfriend);
                break;
            case R.id.re_call:
                index = 3;
                txt_title.setText(R.string.call);
                break;
            case R.id.re_setting:
                index = 4;
                txt_title.setText(R.string.settings);
                break;
        }
        if (currentTabIndex != index){
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if(!fragments[index].isAdded()){
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        //把当前tab设置为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
    }

    private void initViews() {
        img_right.setVisibility(View.VISIBLE);
        img_right.setImageResource(R.drawable.icon_add);
    }

    private void findViewById() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        img_right = (ImageView) findViewById(R.id.img_right);
    }

    private int keyBackClickCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            switch (keyBackClickCount++){
                case 0:
                    Toast.makeText(this,"再次按返回键退出", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    finish();
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_right:
                if (index == 1) {
                    Utils.start_Activity(MainActivity.this, NewMsgActivity.class,
                            new BasicNameValuePair(Constants.NAME,"新消息"));
                } else if (index == 2){
                    Utils.start_Activity(MainActivity.this, NewFriendActivity.class,
                            new BasicNameValuePair(Constants.NAME,"添加朋友"));
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.stopUDPSocket();
    }
}