package com.cetc7.remotecontrol.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.adpter.ChatListAdapter;
import com.cetc7.remotecontrol.bean.ChatMessage;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.BaseActivity;
import com.cetc7.remotecontrol.view.activity.FriendMsgActivity;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//聊天页面
public class ChatActivity extends BaseActivity implements OnClickListener {

	private ListView listView;
	private EditText mEditTextContent;
	private Button buttonSend;
	public static ChatActivity activityInstance = null;
	// 给谁发送消息
	private String Name;
	private String toChatUsername;
	private TextView txt_title;
	private ImageView img_right;

	private ChatListAdapter mChatListAdapter;
	private List<ChatMessage> mChatList = new ArrayList<ChatMessage>();
	private String[] msgArray = new String[]{};
	private String[] dataArray = new String[]{};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		initView();
		setUpView();
		setListener();
	}

	@Override
	public void processMessage(Message msg) {

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Utils.finish(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 绑定控件id
	 */
	@Override
	protected void initControl() {

	}

	/**
	 * initView
	 */
	protected void initView() {
		txt_title = (TextView) findViewById(R.id.txt_title);
		img_right = (ImageView) findViewById(R.id.img_right);
		listView = (ListView) findViewById(R.id.list);
		mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
		buttonSend = findViewById(R.id.btn_send);
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initData() {
		mChatListAdapter = new ChatListAdapter(this,mChatList);
		listView.setAdapter(mChatListAdapter);
	}

	@SuppressLint("InvalidWakeLockTag")
	private void setUpView() {
		activityInstance = this;
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		Name = getIntent().getStringExtra(Constants.NAME);
		img_right.setVisibility(View.VISIBLE);
		toChatUsername = getIntent().getStringExtra(Constants.User_ID);
		img_right.setImageResource(R.drawable.icon_chat_user);
		if (TextUtils.isEmpty(Name)) {
//			initUserInfo();
		} else {
			txt_title.setText(Name);
		}
	}

	protected void setListener() {
		findViewById(R.id.img_back).setVisibility(View.VISIBLE);
		findViewById(R.id.img_back).setOnClickListener(this);
		img_right.setOnClickListener(this);
		buttonSend.setOnClickListener(this);
	}

	/**
	 * onActivityResult
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		listView.setSelection(listView.getCount());
	}

	/**
	 * 消息图标点击事件
	 *
	 * @param view
	 */
	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.img_back:
			Utils.finish(ChatActivity.this);
			break;
		case R.id.img_right:
				Utils.start_Activity(this, FriendMsgActivity.class,
						new BasicNameValuePair(Constants.User_ID,
								toChatUsername), new BasicNameValuePair(
								Constants.NAME, Name));
			break;
		case R.id.btn_send:
			// 点击发送按钮(发文字和表情)
			String s = mEditTextContent.getText().toString();
			sendText(s);
			break;
		default:
			break;
		}
	}

	/**
	 * 发送文本消息
	 *
	 * @param content
	 *            message content
	 * @param
	 *
	 */
	private void sendText(String content) {
		if (content.length() > 0) {
			ChatMessage message = new ChatMessage();
			message.setSelfMsg(true);
			message.setMsg(content);
			message.setMsgTime(System.currentTimeMillis());
			message.setName(Utils.getValue(ChatActivity.this,Constants.User_Name));
			message.setTelphone(Utils.getValue(ChatActivity.this,Constants.User_Phone));

			mChatList.add(message);

			mChatListAdapter.notifyDataSetChanged();
			mEditTextContent.setText("");
			listView.setSelection(listView.getCount()-1);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityInstance = null;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	/**
	 * 返回
	 *
	 * @param view
	 */
	public void back(View view) {
		finish();
	}

}
