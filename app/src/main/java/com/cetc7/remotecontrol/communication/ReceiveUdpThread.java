package com.cetc7.remotecontrol.communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveUdpThread extends Thread{
    private String TAG = "ReceiveUdpThread";
    private DatagramSocket mDatagramSocket = null;
    private DatagramPacket mDatagramPacket = null;

    @Override
    public void run() {
        while (true){
            try {
                mDatagramSocket = new DatagramSocket(5000);
                byte[] recvBytes = new byte[128];
                mDatagramPacket = new DatagramPacket(recvBytes, recvBytes.length);
                while (true){
                    mDatagramSocket.receive(mDatagramPacket);
                    ProtocolParse.parseProtocol(recvBytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(mDatagramSocket != null) {
                    mDatagramSocket.close();
                }
                mDatagramPacket = null;
            }
        }
    }
}
