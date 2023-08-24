package com.cetc7.remotecontrol.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.chat.ChatActivity;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.BaseActivity;

import net.tsz.afinal.FinalDb;


//好友详情
public class FriendMsgActivity extends BaseActivity implements OnClickListener {
	private TextView txt_title, txt_right, tv_name, tv_phone;
	private ImageView img_back;
	private String Name, UserId,Phone;
	private Button btn_sendmsg,btn_call,btn_video;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_friendmsg);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void processMessage(Message msg) {

	}

	@Override
	protected void initControl() {
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_title.setText("");
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setVisibility(View.VISIBLE);
		txt_right = (TextView)findViewById(R.id.txt_right);
		txt_right.setText("删除");
		txt_right.setVisibility(View.VISIBLE);
		btn_sendmsg = (Button) findViewById(R.id.btn_sendmsg);
		btn_sendmsg.setTag("1");
		btn_call = (Button) findViewById(R.id.btn_call);
		btn_video = (Button)findViewById(R.id.btn_video);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
		Name = getIntent().getStringExtra(Constants.NAME);
		UserId = getIntent().getStringExtra(Constants.User_ID);
		if (TextUtils.isEmpty(UserId)) {
			finish();
		} else {
			User user = GloableParams.Users.get(UserId);
			Log.d("tu","user:"+user);
			Log.d("tu","GloableParams.Users:"+GloableParams.Users.size());
			if (user!=null) {
				tv_name.setText(user.getUserName());
				tv_phone.setText(user.getTelephone());
			}
		}
	}

	@Override
	protected void setListener() {
		img_back.setOnClickListener(this);
		txt_right.setOnClickListener(this);
		btn_sendmsg.setOnClickListener(this);
		btn_call.setOnClickListener(this);
		btn_video.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.img_back:
				Utils.finish(FriendMsgActivity.this);
				break;
			case R.id.txt_right://删除联系人
				FinalDb db = FinalDb.create(this,Constants.DB_NAME,false);
				User user = GloableParams.Users.get(UserId);
				db.delete(user);
				GloableParams.UserInfos.remove(user);
				GloableParams.Users.remove(user.getTelephone());
				Utils.finish(FriendMsgActivity.this);
				break;
			case R.id.btn_sendmsg:
				if ("1".equals(v.getTag().toString())) {
					Intent intent = new Intent(this, ChatActivity.class);
					intent.putExtra(Constants.NAME, Name);
					intent.putExtra(Constants.User_ID, UserId);
					startActivity(intent);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
				} else {
					// TODO 添加好友
				}
				break;
			case R.id.btn_call:
				break;
			case R.id.btn_video:
				break;
		default:
			break;
		}
	}

}
