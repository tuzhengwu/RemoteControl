package com.cetc7.remotecontrol.communication;

import android.util.Log;

import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.common.Utils;

import java.util.Arrays;

public class ProtocolFrame {
    private static final String TAG  = "ProtocolFrame";
    private static int SetCommandNum = 0;
    private static int QueryCommandNum = 0;
    private static int paramLength = 0;

    public static byte[] getProtocolFrame(short deviceType, byte commandWord, byte keyWord,byte[] paramBuffer){
        byte[] mFrameBuffer = new byte[256];
        Arrays.fill(mFrameBuffer, (byte) 0);
        if (paramBuffer!=null){
            paramLength = paramBuffer.length;
        } else {
            paramLength = 0;
        }

        mFrameBuffer[0] = GloableParams.ProtocolFrame_Head;
        byte[] deviceTypeBytes = Utils.shortToBytes(deviceType);
        mFrameBuffer[1] = deviceTypeBytes[0];
        mFrameBuffer[2] = deviceTypeBytes[1];
        mFrameBuffer[3] = commandWord;
        mFrameBuffer[4] = keyWord;
        byte[] lengthBytes = Utils.shortToBytes((short) paramLength);
        mFrameBuffer[5] = lengthBytes[0];
        mFrameBuffer[6] = lengthBytes[1];
        if (paramBuffer!=null) {
            System.arraycopy(paramBuffer, 0, mFrameBuffer, 7, paramLength);
        }
        if (commandWord==0x80){
            mFrameBuffer[7+paramLength] = (byte) ++SetCommandNum;
        }else if (commandWord==0x82){
            mFrameBuffer[7+paramLength] = (byte) ++QueryCommandNum;
        }
        mFrameBuffer[8+paramLength] = Utils.SumCheck(mFrameBuffer, 1, 7+paramLength);
        mFrameBuffer[9+paramLength] = GloableParams.ProtocolFrame_Tail;

        Log.d(TAG,"mFrameBuffer is "+ Utils.bytesToHex(Utils.SubBytes(mFrameBuffer,0,10+paramLength)));
        return Utils.SubBytes(mFrameBuffer,0,10+paramLength);
    }
}
