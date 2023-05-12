package com.cetc7.remotecontrol.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.common.Utils;

import androidx.fragment.app.Fragment;


//通讯录

public class Fragment_Friends extends Fragment implements OnClickListener{
	private Activity ctx;
	private View layout, layout_head;
	private ListView lvContact;
//	private SideBar indexBar;
	private TextView mDialogText;
	private WindowManager mWindowManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (layout == null) {
			ctx = this.getActivity();
			layout = ctx.getLayoutInflater().inflate(R.layout.fragment_friends,
					null);
			mWindowManager = (WindowManager) ctx
					.getSystemService(Context.WINDOW_SERVICE);
			initViews();
//			initData();
//			setOnListener();
		} else {
			ViewGroup parent = (ViewGroup) layout.getParent();
			if (parent != null) {
				parent.removeView(layout);
			}
		}
		return layout;
	}

	private void initViews() {
		lvContact = (ListView) layout.findViewById(R.id.lvContact);

//		mDialogText = (TextView) LayoutInflater.from(getActivity()).inflate(
//				R.layout.list_position, null);
//		mDialogText.setVisibility(View.INVISIBLE);
//		indexBar = (SideBar) layout.findViewById(R.id.sideBar);
//		indexBar.setListView(lvContact);
//		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
//				WindowManager.LayoutParams.TYPE_APPLICATION,
//				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
//						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//				PixelFormat.TRANSLUCENT);
//		mWindowManager.addView(mDialogText, lp);
//		indexBar.setTextView(mDialogText);
//		layout_head = ctx.getLayoutInflater().inflate(
//				R.layout.layout_head_friend, null);
//		lvContact.addHeaderView(layout_head);

	}

	@Override
	public void onDestroy() {
		mWindowManager.removeView(mDialogText);
		super.onDestroy();
	}


	/**
	 * 刷新页面
	 */
//	public void refresh() {
//		initData();
//	}

//	private void initData() {
//		if (GloableParams.UserInfos != null) {
//			lvContact.setAdapter(new ContactAdapter(getActivity(),
//					GloableParams.UserInfos));
//		} else {
//			FinalDb db = FinalDb
//					.create(getActivity(), Constants.DB_NAME, false);
//			GloableParams.UserInfos = db.findAllByWhere(User.class, "type='N'");
//			lvContact.setAdapter(new ContactAdapter(getActivity(),
//					GloableParams.UserInfos));
//			for (User user : GloableParams.UserInfos) {
//				GloableParams.Users.put(user.getTelephone(), user);
//			}
//			 Intent intent = new Intent(getActivity(), UpdateService.class);
//			 getActivity().startService(intent);
//		}
//	}

//	private void setOnListener() {
//		lvContact.setOnItemClickListener(this);
//		layout_head.findViewById(R.id.layout_addfriend)
//				.setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_search).setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_group).setOnClickListener(this);
//		layout_head.findViewById(R.id.layout_public).setOnClickListener(this);
//	}

	@Override
	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.layout_search:// 搜索好友及公众号
//			Utils.start_Activity(getActivity(), SearchActivity.class);
//			break;
//		case R.id.layout_addfriend:// 添加好友
//			Utils.start_Activity(getActivity(), NewFriendsListActivity.class);
//			break;
//		case R.id.layout_group:// 群聊
//			Utils.start_Activity(getActivity(), GroupListActivity.class);
//			break;
//		case R.id.layout_public:// 公众号
//			Utils.start_Activity(getActivity(), PublishUserListActivity.class);
//			break;
//		default:
//			break;
//		}
	}
}
