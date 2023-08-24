package com.cetc7.remotecontrol.adpter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.bean.ChatMessage;
import com.cetc7.remotecontrol.common.DateUtils;
import com.cetc7.remotecontrol.common.Utils;

import java.util.List;

public class ChatListAdapter extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<ChatMessage> msgList;
    protected Resources res;

    public ChatListAdapter(Context c, List<ChatMessage> list){
        super();
        this.mInflater = LayoutInflater.from(c);
        this.msgList = list;
        res = c.getResources();
    }

    @Override
    public int getCount() {
        return msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage msg = msgList.get(position);
        ViewHolder_ChatList viewHolder = null;
        if(convertView == null){
            if (msg.isSelfMsg()){
                convertView = mInflater.inflate(R.layout.row_sent_message, null);
            }else{
                convertView = mInflater.inflate(R.layout.row_received_message, null);
            }
            viewHolder = new ViewHolder_ChatList();
            viewHolder.tv_usertel = (TextView) convertView.findViewById(R.id.tv_usertel);
            viewHolder.tv_chatcontent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder_ChatList) convertView.getTag();
        }

        viewHolder.tv_usertel.setText(msg.getTelphone());
        viewHolder.timestamp.setText(DateUtils.getTimestampStr());
        viewHolder.tv_chatcontent.setText(msg.getMsg());
        return convertView;
    }

    static class ViewHolder_ChatList{
        public TextView tv_usertel;
        public TextView tv_chatcontent;
        public TextView timestamp;
    }
}
