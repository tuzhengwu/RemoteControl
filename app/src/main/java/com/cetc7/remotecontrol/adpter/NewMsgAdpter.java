package com.cetc7.remotecontrol.adpter;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.bean.ChatConversation;
import com.cetc7.remotecontrol.common.DateUtils;
import com.cetc7.remotecontrol.common.ViewHolder;

public class NewMsgAdpter extends BaseAdapter {
	protected Context context;
	private List<ChatConversation> conversationList;
	private int deleteID;
	private String ChatID;
	private String userid;
	private Hashtable<String, String> ChatRecord = new Hashtable<String, String>();

	public NewMsgAdpter(Context ctx, List<ChatConversation> objects) {
		context = ctx;
		conversationList = objects;
	}

	public Hashtable<String, String> getChatRecord() {
		return ChatRecord;
	}

	@Override
	public int getCount() {
		return conversationList.size();
	}

	@Override
	public Object getItem(int position) {
		return conversationList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ChatConversation mChatConversation = conversationList.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.layout_item_msg, parent, false);
		}
		ImageView img_avar = ViewHolder.get(convertView,
				R.id.contactitem_avatar_iv);
		TextView txt_name = ViewHolder.get(convertView, R.id.txt_name);
//		TextView txt_state = ViewHolder.get(convertView, R.id.txt_state);
		TextView txt_content = ViewHolder.get(convertView, R.id.txt_content);
		TextView txt_time = ViewHolder.get(convertView, R.id.txt_time);
//		TextView unreadLabel = ViewHolder.get(convertView,
//				R.id.unread_msg_number);
		txt_name.setText(mChatConversation.getUserName());
		txt_content.setText(mChatConversation.getLastMessage().getMsg());
		txt_time.setText(DateUtils.getTimestampString(new Date(mChatConversation.getLastMessage().getMsgTime())));
		return convertView;
	}

	String getStrng(Context context, int resId) {
		return context.getResources().getString(resId);
	}
}
