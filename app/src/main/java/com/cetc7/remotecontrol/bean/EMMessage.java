//package com.cetc7.remotecontrol.bean;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//import java.util.Hashtable;
//
//public class EMMessage implements Parcelable, Cloneable {
//    private static final String TAG = "msg";
//    EMMessage.Type type;
//    public EMMessage.Direct direct;
//    public EMMessage.Status status;
//    String fromtel;
//    String totel;
//    String message;
//    String msgId;
//    public boolean isAcked;
//    public boolean isDelivered;
//    long msgTime;
//    public transient int progress;
//    Hashtable<String, Object> attributes;
//    transient boolean unread;
//    transient boolean offline;
//    boolean isListened;
//    static final String ATTR_ENCRYPTED = "isencrypted";
//    public static final Creator<EMMessage> CREATOR = new Creator<EMMessage>() {
//        public EMMessage createFromParcel(Parcel var1) {
//            return new EMMessage(var1, (EMMessage)null);
//        }
//
//        public EMMessage[] newArray(int var1) {
//            return new EMMessage[var1];
//        }
//    };
//
//    EMMessage(EMMessage.Type var1) {
//        this.status = EMMessage.Status.CREATE;
//        this.isAcked = false;
//        this.isDelivered = false;
//        this.progress = 0;
//        this.attributes = new Hashtable();
//        this.unread = true;
//        this.offline = false;
//        this.type = var1;
//        this.msgTime = System.currentTimeMillis();
//    }
//
//    public EMMessage.Type getType() {
//        return this.type;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//    public long getMsgTime() {
//        return this.msgTime;
//    }
//
//    public void setMsgTime(long var1) {
//        this.msgTime = var1;
//    }
//
//    public static EMMessage createSendMessage(EMMessage.Type var0) {
//        EMMessage var1 = new EMMessage(var0);
//        var1.direct = EMMessage.Direct.SEND;
//        EMContact var2 = EMSessionManager.getInstance().currentUser;
//        if (var2 == null) {
//            String var3 = EMSessionManager.getInstance().getLastLoginUser();
//            var2 = EMContactManager.getInstance().getContactByUserName(var3);
//        }
//
//        var1.from = var2;
//        var1.setMsgId(EMMessageUtils.getUniqueMessageId());
//        return var1;
//    }
//
//    public static EMMessage createReceiveMessage(EMMessage.Type var0) {
//        EMMessage var1 = new EMMessage(var0);
//        var1.direct = EMMessage.Direct.RECEIVE;
//        var1.to = EMSessionManager.getInstance().currentUser;
//        return var1;
//    }
//
//    public void addBody(MessageBody var1) {
//        this.body = var1;
//    }
//
//    public String getFrom() {
//        return this.from == null ? null : this.from.username;
//    }
//
//    public void setFrom(String var1) {
//        EMContact var2 = new EMContact();
//        var2.setUsername(var1);
//        this.from = var2;
//    }
//
//    public void setTo(String var1) {
//        EMContact var2 = new EMContact();
//        var2.setUsername(var1);
//        this.to = var2;
//    }
//
//    public String getTo() {
//        return this.to == null ? null : this.to.username;
//    }
//
//    public String getMsgId() {
//        return this.msgId;
//    }
//
//    public void setMsgId(String var1) {
//        this.msgId = var1;
//    }
//
//    public void setReceipt(String var1) {
//        EMContactManager var2 = EMContactManager.getInstance();
//        EMContact var3 = null;
//        if (var1.contains("@")) {
//            EMLog.e("msg", "error wrong uesrname format:" + var1);
//        } else {
//            var3 = var2.getContactByUserName(var1);
//        }
//
//        if (var3 == null) {
//            var3 = new EMContact(var1);
//            var2.addContactInternal(var3);
//        }
//
//        this.to = var3;
//    }
//
//    public String toString() {
//        StringBuffer var1 = new StringBuffer();
//        var1.append("msg{from:" + this.from.username);
//        var1.append(", to:" + this.to.username);
//        var1.append(" body:" + this.body.toString());
//        return var1.toString();
//    }
//
//    public void setAttribute(String var1, boolean var2) {
//        if (this.attributes == null) {
//            this.attributes = new Hashtable();
//        }
//
//        this.attributes.put(var1, new Boolean(var2));
//    }
//
//    public void setAttribute(String var1, int var2) {
//        if (this.attributes == null) {
//            this.attributes = new Hashtable();
//        }
//
//        this.attributes.put(var1, new Integer(var2));
//    }
//
//    public void setAttribute(String var1, String var2) {
//        if (this.attributes == null) {
//            this.attributes = new Hashtable();
//        }
//
//        this.attributes.put(var1, var2);
//    }
//
//    public boolean getBooleanAttribute(String var1) throws EaseMobException {
//        Boolean var2 = null;
//        if (this.attributes != null) {
//            var2 = (Boolean)this.attributes.get(var1);
//        }
//
//        if (var2 == null) {
//            throw new EaseMobException("attribute " + var1 + " not found");
//        } else {
//            return var2;
//        }
//    }
//
//    public boolean getBooleanAttribute(String var1, boolean var2) {
//        if (this.attributes == null) {
//            return var2;
//        } else {
//            Boolean var3 = (Boolean)this.attributes.get(var1);
//            return var3 == null ? var2 : var3;
//        }
//    }
//
//    public int getIntAttribute(String var1, int var2) {
//        Integer var3 = null;
//        if (this.attributes != null) {
//            var3 = (Integer)this.attributes.get(var1);
//        }
//
//        return var3 == null ? var2 : var3;
//    }
//
//    public int getIntAttribute(String var1) throws EaseMobException {
//        Integer var2 = null;
//        if (this.attributes != null) {
//            var2 = (Integer)this.attributes.get(var1);
//        }
//
//        if (var2 == null) {
//            throw new EaseMobException("attribute " + var1 + " not found");
//        } else {
//            return var2;
//        }
//    }
//
//    public String getStringAttribute(String var1) throws EaseMobException {
//        String var2 = null;
//        if (this.attributes != null) {
//            var2 = (String)this.attributes.get(var1);
//        }
//
//        if (var2 == null) {
//            throw new EaseMobException("attribute " + var1 + " not found");
//        } else {
//            return var2;
//        }
//    }
//
//    public String getStringAttribute(String var1, String var2) {
//        String var3 = null;
//        if (this.attributes != null) {
//            var3 = (String)this.attributes.get(var1);
//        }
//
//        return var3 == null ? var2 : var3;
//    }
//
//    public EMMessage.ChatType getChatType() {
//        return this.chatType;
//    }
//
//    public void setChatType(EMMessage.ChatType var1) {
//        this.chatType = var1;
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//    public void writeToParcel(Parcel var1, int var2) {
//        var1.writeString(this.type.name());
//        var1.writeString(this.direct.name());
//        var1.writeString(this.msgId);
//        var1.writeLong(this.msgTime);
//        var1.writeMap(this.attributes);
//        var1.writeParcelable(this.from, var2);
//        var1.writeParcelable(this.to, var2);
//        var1.writeParcelable(this.body, var2);
//    }
//
//    private EMMessage(Parcel var1) {
//        this.status = EMMessage.Status.CREATE;
//        this.isAcked = false;
//        this.isDelivered = false;
//        this.chatType = EMMessage.ChatType.Chat;
//        this.progress = 0;
//        this.attributes = new Hashtable();
//        this.unread = true;
//        this.offline = false;
//        this.type = EMMessage.Type.valueOf(var1.readString());
//        this.direct = EMMessage.Direct.valueOf(var1.readString());
//        this.msgId = var1.readString();
//        this.msgTime = var1.readLong();
//        this.attributes = new Hashtable();
//        var1.readMap(this.attributes, (ClassLoader)null);
//        this.from = (EMContact)var1.readParcelable(EMMessage.class.getClassLoader());
//        this.to = (EMContact)var1.readParcelable(EMMessage.class.getClassLoader());
//        this.body = (MessageBody)var1.readParcelable(EMMessage.class.getClassLoader());
//    }
//
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    public boolean isAcked() {
//        return this.isAcked;
//    }
//
//    public void setAcked(boolean var1) {
//        this.isAcked = var1;
//    }
//
//    public boolean isDelivered() {
//        return this.isDelivered;
//    }
//
//    public void setDelivered(boolean var1) {
//        this.isDelivered = var1;
//    }
//
//    public boolean isUnread() {
//        return this.unread;
//    }
//
//    public void setUnread(boolean var1) {
//        this.unread = var1;
//    }
//
//    public void setType(EMMessage.Type var1) {
//        this.type = var1;
//    }
//
//    public boolean isListened() {
//        return this.isListened;
//    }
//
//    public void setListened(boolean var1) {
//        this.isListened = var1;
//    }
//
//
//    public static enum Direct {
//        SEND,
//        RECEIVE;
//
//        private Direct() {
//        }
//    }
//
//    public static enum Status {
//        SUCCESS,
//        FAIL,
//        INPROGRESS,
//        CREATE;
//
//        private Status() {
//        }
//    }
//
//    public static enum Type {
//        TXT,
//        IMAGE,
//        VIDEO,
//        LOCATION,
//        VOICE,
//        FILE,
//        CMD;
//
//        private Type() {
//        }
//    }
//}
