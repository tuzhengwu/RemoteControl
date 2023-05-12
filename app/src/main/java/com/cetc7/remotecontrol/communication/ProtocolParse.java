package com.cetc7.remotecontrol.communication;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.BaseActivity;

public class ProtocolParse {
    public static String TAG = "ProtocolParse";


    public static boolean parseProtocol(byte[] protocolFrame){
        Log.d(TAG,"protocolFrame length is " + protocolFrame.length);
        if (protocolFrame.length>0&&(protocolFrame[5]<<8|protocolFrame[6])==protocolFrame.length){
            if (protocolFrame[0]== GloableParams.ProtocolFrame_Head&&protocolFrame[protocolFrame.length-1]==GloableParams.ProtocolFrame_Tail){
                if (Utils.SumCheck(protocolFrame,1,protocolFrame.length-3)==protocolFrame[protocolFrame.length-2]){
                    short deviceType = (short) ((protocolFrame[1]<<8)|(protocolFrame[2]&0xff));
                    byte commandWord = (byte) (protocolFrame[3]&0xff);
                    byte keyWord = (byte) (protocolFrame[4]&0xff);
                    byte[] paramBuffer = Utils.SubBytes(protocolFrame,7,protocolFrame.length-10);
                    byte frameNum = (byte) (protocolFrame[protocolFrame.length-3]&0xff);

                    Message msg = new Message();
                    Bundle b = new Bundle();
                    b.putShort("DEVICETYPE",deviceType);
                    b.putByte("COMMANDWORD",commandWord);
                    b.putByte("KEYWORD",keyWord);
                    b.putByteArray("PARAMBUFFER",paramBuffer);
                    b.putByte("FRAMENUM",frameNum);
                    msg.setData(b);
                    BaseActivity.sendMessage(msg);
                    return true;
                }else{
                    Log.d(TAG,"protocolFrame SumCheck is error");
                    return false;
                }
            } else {
                Log.d(TAG,"protocolFrame headFlag or tailFlag is error");
                return false;
            }
        }else{
            Log.d(TAG,"protocolFrame length is error");
            return false;
        }
    }
}
