package com.cetc7.remotecontrol.view.activity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.MainActivity;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.communication.ProtocolFrame;
import com.cetc7.remotecontrol.view.BaseActivity;

public class ProtocolGetActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "ProtocolGetActivity";

    private TextView txt_title,txt_earth_longitude,txt_earth_latitude,txt_satellite_longitude,
            txt_rev_rate, txt_send_rate,txt_ip_rev_bytes,txt_ip_send_bytes,txt_run_status,
            txt_mode,txt_software_version, txt_work_status,txt_satellite_num,txt_port_num,
            txt_mtu,txt_channel_num,txt_channel_unit, txt_channel_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_get);
        findViewById();
        setOnListener();
        byte[] frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                GloableParams.Command_Query,GloableParams.Key_Protocol_Location_Set,null);
        Log.d(TAG,"onCreate frameBytes : "+Utils.bytesToHex(frameBytes));
        Log.d(TAG,"onCreate MainActivity.socket : "+(MainActivity.socket !=null));
        MainActivity.socket.sendMessage(frameBytes);
    }

    private void findViewById() {
        findViewById(R.id.img_back).setVisibility(View.VISIBLE);
        findViewById(R.id.txt_right).setVisibility(View.GONE);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText(R.string.protocol_get);
    }

    private void setOnListener() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void processMessage(Message msg) {
        Bundle b = msg.getData();
        short deviceType = b.getShort("DEVICETYPE");
        byte commandWord = b.getByte("COMMANDWORD");
        byte keyWord = b.getByte("KEYWORD");
        byte[] paramBuffer = b.getByteArray("PARAMBUFFER");
        byte frameNum = b.getByte("FRAMENUM");
        switch (deviceType){
            case GloableParams.DeviceType_MainContol:
                switch (commandWord){
                    case GloableParams.Command_SetResponse:
                        break;
                    case GloableParams.Command_QueryResponse:
                        switch (keyWord){
                            case GloableParams.Key_Protocol_AccessControl_Query:
                                int recvRate = Utils.bytesToInt(Utils.SubBytes(paramBuffer,0,4));
                                txt_rev_rate.setText(recvRate+"bps");
                                int sendRate = Utils.bytesToInt(Utils.SubBytes(paramBuffer,4,4));
                                txt_send_rate.setText(sendRate+"bps");
                                int ipSendBytes = Utils.bytesToInt(Utils.SubBytes(paramBuffer,8,4));
                                txt_ip_send_bytes.setText(ipSendBytes+"B");
                                int ipRecvBytes = Utils.bytesToInt(Utils.SubBytes(paramBuffer,12,4));
                                txt_ip_rev_bytes.setText(ipRecvBytes+"B");
                                switch (paramBuffer[16]){
                                    case 0:
                                        txt_run_status.setText("正常");
                                        break;
                                    case 1:
                                        txt_run_status.setText("异常");
                                        break;
                                    default:
                                        break;
                                }
                                switch (paramBuffer[17]){
                                    case 0x00:
                                        txt_mode.setText("待机");
                                        break;
                                    case 0x20:
                                        txt_mode.setText("星状网");
                                        break;
                                    case 0x21:
                                        txt_mode.setText("星状网误码测试");
                                        break;
                                    case 0x30:
                                        txt_mode.setText("网状网");
                                        break;
                                    case 0x31:
                                        txt_mode.setText("网状网误码测试");
                                        break;
                                    case 0x40:
                                        txt_mode.setText("星组网");
                                        break;
                                    case 0x41:
                                        txt_mode.setText("星组网误码测试");
                                        break;
                                    case 0x50:
                                        txt_mode.setText("抗强组网");
                                        break;
                                    case 0x51:
                                        txt_mode.setText("抗强组网误码测试");
                                        break;
                                    case 0x60:
                                        txt_mode.setText("宽带组网");
                                        break;
                                    case 0x61:
                                        txt_mode.setText("宽带组网误码测试");
                                        break;
                                    case 0x70:
                                        txt_mode.setText("高通量组网");
                                        break;
                                    case 0x71:
                                        txt_mode.setText("高通量组网误码测试");
                                        break;
                                    default:
                                        break;
                                }
                                txt_software_version.setText("V"+(char)paramBuffer[18]+"." +
                                        (char)paramBuffer[19]+"."+(char)paramBuffer[20]+"."+
                                        (char)paramBuffer[21]);
                                short workStatus = Utils.bytesToShort(Utils.SubBytes(paramBuffer,22,2));
                                switch (workStatus){
                                    case 0:
                                        txt_work_status.setText("初始化");
                                        break;
                                    case 1:
                                        txt_work_status.setText("下行同步");
                                        break;
                                    case 2:
                                        txt_work_status.setText("上行同步");
                                        break;
                                    case 3:
                                        txt_work_status.setText("入网注册");
                                        break;
                                    case 4:
                                        txt_work_status.setText("认证鉴权");
                                        break;
                                    case 5:
                                        txt_work_status.setText("业务参数下发");
                                        break;
                                    case 6:
                                        txt_work_status.setText("在线保持");
                                        break;
                                    default:
                                        break;
                                }
                                txt_satellite_num.setText(""+paramBuffer[24]);
                                txt_port_num.setText(""+paramBuffer[25]);
                                short mtu = Utils.bytesToShort(Utils.SubBytes(paramBuffer,26,2));
                                txt_mtu.setText(""+mtu);
                                int channelNum = Utils.bytesToInt(Utils.SubBytes(paramBuffer,28,4));
                                txt_channel_num.setText(""+channelNum);
                                int channelAddr = Utils.bytesToInt(Utils.SubBytes(paramBuffer,32,4));
                                txt_channel_unit.setText(""+channelAddr);
                                switch (paramBuffer[36]){
                                    case 1:
                                        txt_channel_status.setText("闲（0~25%）");
                                        break;
                                    case 2:
                                        txt_channel_status.setText("中（25%~50%）");
                                        break;
                                    case 3:
                                        txt_channel_status.setText("忙（50%~75%）");
                                        break;
                                    case 4:
                                        txt_channel_status.setText("满（75%~100%）");
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case GloableParams.Key_Protocol_Location_Query:
                                String earthLongitude = new String(Utils.SubBytes(paramBuffer,0,13));
                                String earthLatitude = new String(Utils.SubBytes(paramBuffer,13,13));
                                String satlliteLongitude = new String(Utils.SubBytes(paramBuffer,26,13));
                                txt_earth_longitude.setText(earthLongitude);
                                txt_earth_latitude.setText(earthLatitude);
                                txt_satellite_longitude.setText(satlliteLongitude);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(ProtocolGetActivity.this);
                break;
            default:
                break;
        }
    }

    /**
     * 绑定控件id
     */
    @Override
    protected void initControl() {
        txt_earth_longitude = (TextView)findViewById(R.id.txt_earth_longitude);
        txt_earth_latitude = (TextView)findViewById(R.id.txt_earth_latitude);
        txt_satellite_longitude = (TextView)findViewById(R.id.txt_satellite_longitude);
        txt_rev_rate = (TextView)findViewById(R.id.txt_rev_rate);
        txt_send_rate = (TextView)findViewById(R.id.txt_send_rate);
        txt_ip_rev_bytes = (TextView)findViewById(R.id.txt_ip_rev_bytes);
        txt_ip_send_bytes = (TextView)findViewById(R.id.txt_ip_send_bytes);
        txt_run_status = (TextView)findViewById(R.id.txt_run_status);
        txt_mode = (TextView)findViewById(R.id.txt_mode);
        txt_software_version = (TextView)findViewById(R.id.txt_software_version);
        txt_work_status = (TextView)findViewById(R.id.txt_work_status);
        txt_satellite_num = (TextView)findViewById(R.id.txt_satellite_num);
        txt_port_num = (TextView)findViewById(R.id.txt_port_num);
        txt_mtu = (TextView)findViewById(R.id.txt_mtu);
        txt_channel_num = (TextView)findViewById(R.id.txt_channel_num);
        txt_channel_unit = (TextView)findViewById(R.id.txt_channel_unit);
        txt_channel_status = (TextView)findViewById(R.id.txt_channel_status);
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {

    }

}
