package com.cetc7.remotecontrol.bean;

import java.util.Collections;
import java.util.List;

public class ChatConversation {
    private static final String TAG = "ChatConversation";
    private int id;
    List<ChatMessage> mChatList;
    private String userName;

    public ChatConversation(String username,List<ChatMessage> chatlist){
        this.userName = username;
        if (this.mChatList == null){
            this.mChatList = Collections.synchronizedList(chatlist);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChatMessage> getmChatList() {
        return mChatList;
    }

    public void setmChatList(List<ChatMessage> mChatList) {
        this.mChatList = mChatList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addChatMessage(ChatMessage msg){
        mChatList.add(msg);
    }

    public int getChatMessageCount(){
        return this.mChatList.size();
    }

    public ChatMessage getLastMessage(){
        return this.mChatList.size() == 0 ? null : this.mChatList.get(this.mChatList.size()-1);
    }
}
