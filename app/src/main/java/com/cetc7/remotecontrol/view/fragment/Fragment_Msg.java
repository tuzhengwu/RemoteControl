package com.cetc7.remotecontrol.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.MainActivity;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.adpter.NewMsgAdpter;
import com.cetc7.remotecontrol.bean.ChatConversation;
import com.cetc7.remotecontrol.bean.ChatMessage;
import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.chat.ChatActivity;
import com.cetc7.remotecontrol.common.NetUtil;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import androidx.fragment.app.Fragment;

//消息
public class Fragment_Msg extends Fragment implements OnClickListener,
		OnItemClickListener {
	private Activity ctx;
	private View layout;
	public RelativeLayout errorItem;
	public TextView errorText;
	private ListView lvContact;
	private NewMsgAdpter adpter;
	private List<ChatConversation> conversationList = new ArrayList<>();
	private MainActivity parentActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (layout == null) {
			ctx = this.getActivity();
			parentActivity = (MainActivity) getActivity();
			layout = ctx.getLayoutInflater().inflate(R.layout.fragment_msg,
					null);
			lvContact = (ListView) layout.findViewById(R.id.listview);
			errorItem = (RelativeLayout) layout
					.findViewById(R.id.rl_error_item);
			errorText = (TextView) errorItem
					.findViewById(R.id.tv_connect_errormsg);
			setOnListener();
		} else {
			ViewGroup parent = (ViewGroup) layout.getParent();
			if (parent != null) {
				parent.removeView(layout);
			}
		}
		return layout;
	}

	@Override
	public void onResume() {
		super.onResume();
		conversationList.clear();
		initViews();
	}

	/**
	 * 刷新页面
	 */
	public void refresh() {
		conversationList.clear();
		initViews();
	}

	private void initViews() {
		conversationList.addAll(loadConversationsWithRecentChat());
		if (conversationList != null && conversationList.size() > 0) {
			layout.findViewById(R.id.txt_nochat).setVisibility(View.GONE);
			adpter = new NewMsgAdpter(getActivity(), conversationList);
			lvContact.setAdapter(adpter);
		} else {
			layout.findViewById(R.id.txt_nochat).setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 获取所有会话
	 * 
	 * @param
	 * @return +
	 */
	private List<ChatConversation> loadConversationsWithRecentChat() {
		// 获取所有会话，包括陌生人
		Hashtable<String, ChatConversation> conversations = getAllConversations();
		List<ChatConversation> list = new ArrayList<>();
		// 过滤掉messages seize为0的conversation
		for (ChatConversation conversation : conversations.values()) {
			if (conversation.getmChatList().size() != 0)
				list.add(conversation);
		}
		// 排序
		sortConversationByLastChatTime(list);
		return list;
	}

	private Hashtable<String, ChatConversation> getAllConversations() {
		FinalDb db = FinalDb
				.create(getActivity(), Constants.DB_NAME,false);
		List<ChatConversation> list = db.findAll(ChatConversation.class);
		Hashtable<String, ChatConversation> conversations = new Hashtable();
		for (ChatConversation chatConversation:list){
			conversations.put(chatConversation.getUserName(),chatConversation);
		}
		return conversations;
	}

	/**
	 * 根据最后一条消息的时间排序
	 * 
	 * @param
	 */
	private void sortConversationByLastChatTime(
			List<ChatConversation> conversationList) {
		Collections.sort(conversationList, (con1, con2) -> {

			ChatMessage con2LastMessage = con2.getLastMessage();
			ChatMessage con1LastMessage = con1.getLastMessage();
			if (con2LastMessage.getMsgTime() == con1LastMessage
					.getMsgTime()) {
				return 0;
			} else if (con2LastMessage.getMsgTime() > con1LastMessage
					.getMsgTime()) {
				return 1;
			} else {
				return -1;
			}
		});
	}

	private void setOnListener() {
		lvContact.setOnItemClickListener(this);
		errorItem.setOnClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
			ChatConversation conversation = conversationList.get(position);
			Intent intent = new Intent(getActivity(), ChatActivity.class);
			Hashtable<String, String> ChatRecord = adpter.getChatRecord();
			if (ChatRecord != null) {
					User user = GloableParams.Users.get(conversation
							.getUserName());
					if (user != null) {
						intent.putExtra(Constants.NAME, user.getUserName());// 设置昵称
						intent.putExtra(Constants.User_ID,
								conversation.getUserName());
						getActivity().startActivity(intent);
					} else {
						intent.putExtra(Constants.NAME, "好友");
						intent.putExtra(Constants.User_ID,
								conversation.getUserName());
						getActivity().startActivity(intent);
					}
			}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_error_item:
			NetUtil.openSetNetWork(getActivity());
			break;
		default:
			break;
		}
	}


}
