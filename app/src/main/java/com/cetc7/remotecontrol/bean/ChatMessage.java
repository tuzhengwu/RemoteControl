package com.cetc7.remotecontrol.bean;

public class ChatMessage {
    private int id;
    private String telphone;
    private String name;
    private String msg;
    private long msgTime;
    private boolean selfMsg;

    public ChatMessage(){
        this.selfMsg = false;
    }

    public ChatMessage(String telphone,String name,String m){
        super();
        this.telphone = telphone;
        this.name = name;
        this.msg = m;
        this.msgTime = System.currentTimeMillis();
        this.selfMsg = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSelfMsg() {
        return selfMsg;
    }

    public void setSelfMsg(boolean selfMsg) {
        this.selfMsg = selfMsg;
    }
}
