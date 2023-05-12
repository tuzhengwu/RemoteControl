package com.cetc7.remotecontrol.view.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.*;
import android.widget.TextView;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.BaseActivity;

public class ProtocolReportActivity extends BaseActivity implements OnClickListener  {

    private TextView txt_title,txt_report_broadmod_workmode,txt_report_broadmod_version,
            txt_report_broaddem_workmode, txt_report_broaddem_version,txt_report_broaddem_sync,
            txt_report_broaddem_code, txt_report_broaddem_dbm,txt_report_broaddem_snr,
            txt_report_broaddem_freq, txt_report_antimod_workmode,txt_report_antimod_version,
            txt_report_antidem_workmode, txt_report_antidem_version,txt_report_antidem_sync,
            txt_report_antidem_dbm, txt_report_antidem_snr,txt_report_antidem_freq,
            txt_report_netcontrol_workmode, txt_report_netcontrol_channelunit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_report);
        findViewById();
        setOnListener();
    }

    private void setOnListener() {
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    private void findViewById() {
        findViewById(R.id.img_back).setVisibility(View.VISIBLE);
        findViewById(R.id.txt_right).setVisibility(View.GONE);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText(R.string.protocol_report);
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
                    case GloableParams.Command_Report:
                        switch (keyWord){
                            case GloableParams.Key_Protocol_BroadMod_Report:
                                switch (paramBuffer[0]){
                                    case 0x00:
                                        txt_report_broadmod_workmode.setText("信令模式");
                                        break;
                                    case 0x01:
                                        txt_report_broadmod_workmode.setText("TDMA模式");
                                        break;
                                    case 0x02:
                                        txt_report_broadmod_workmode.setText("FDMA模式");
                                        break;
                                    case 0x03:
                                        txt_report_broadmod_workmode.setText("CDMA模式");
                                        break;
                                    case 0x04:
                                        txt_report_broadmod_workmode.setText("抗旋翼模式");
                                        break;
                                    case 0x05:
                                        txt_report_broadmod_workmode.setText("馈电模式");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_broadmod_version.setText("V"+paramBuffer[1]+"."+
                                        paramBuffer[2]+"."+paramBuffer[3]+"."+paramBuffer[4]);
                                break;
                            case GloableParams.Key_Protocol_BroadDem_Report:
                                switch (paramBuffer[0]){
                                    case 0x00:
                                        txt_report_broaddem_workmode.setText("信令模式");
                                        break;
                                    case 0x01:
                                        txt_report_broaddem_workmode.setText("用户VTDM模式");
                                        break;
                                    case 0x02:
                                        txt_report_broaddem_workmode.setText("用户抗旋翼模式");
                                        break;
                                    case 0x03:
                                        txt_report_broaddem_workmode.setText("用户抗干扰模式");
                                        break;
                                    case 0x04:
                                        txt_report_broaddem_workmode.setText("馈电模式");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_broaddem_version.setText("V"+paramBuffer[1]+"."+
                                        paramBuffer[2]+"."+paramBuffer[3]+"."+paramBuffer[4]);
                                switch (paramBuffer[5]){
                                    case 0x00:
                                        txt_report_broaddem_sync.setText("失步");
                                        txt_report_broaddem_dbm.setText("***");
                                        txt_report_broaddem_snr.setText("***");
                                        break;
                                    case 0x01:
                                        txt_report_broaddem_sync.setText("同步");
                                        txt_report_broaddem_dbm.setText(Utils.bytesToShort(
                                                Utils.SubBytes(paramBuffer,7,2))+"dbm");
                                        txt_report_broaddem_snr.setText(Utils.bytesToShort(
                                                Utils.SubBytes(paramBuffer,9,2))+"dB");
                                        break;
                                    default:
                                        break;
                                }
                                switch (paramBuffer[6]){
                                    case 0x00:
                                        txt_report_broaddem_code.setText("失步");
                                        break;
                                    case 0x01:
                                        txt_report_broaddem_code.setText("同步");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_broaddem_freq.setText(Utils.bytesToInt(
                                        Utils.SubBytes(paramBuffer,11,4))+"Hz");
                                break;
                            case GloableParams.Key_Protocol_AntiMod_Report:
                                switch (paramBuffer[0]){
                                    case 0x00:
                                        txt_report_antimod_workmode.setText("LDR模式");
                                        break;
                                    case 0x01:
                                        txt_report_antimod_workmode.setText("MDR模式");
                                        break;
                                    case 0x02:
                                        txt_report_antimod_workmode.setText("HDR模式");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_antimod_version.setText("V"+paramBuffer[1]+"."+
                                        paramBuffer[2]+"."+paramBuffer[3]+"."+paramBuffer[4]);
                                break;
                            case GloableParams.Key_Protocol_AntidDem_Report:
                                switch (paramBuffer[0]){
                                    case 0x00:
                                        txt_report_antidem_workmode.setText("SDR模式");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_antidem_version.setText("V"+paramBuffer[1]+"."+
                                        paramBuffer[2]+"."+paramBuffer[3]+"."+paramBuffer[4]);
                                switch (paramBuffer[5]){
                                    case 0x00:
                                        txt_report_antidem_sync.setText("失步");
                                        txt_report_antidem_dbm.setText("***");
                                        txt_report_antidem_snr.setText("***");
                                        break;
                                    case 0x01:
                                        txt_report_antidem_sync.setText("同步");
                                        txt_report_antidem_dbm.setText(Utils.bytesToShort(
                                                Utils.SubBytes(paramBuffer,6,2))+"dbm");
                                        txt_report_antidem_snr.setText(Utils.bytesToShort(
                                                Utils.SubBytes(paramBuffer,8,2))+"dbm");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_antidem_freq.setText(Utils.bytesToInt(
                                        Utils.SubBytes(paramBuffer,10,4))+"Hz");
                                break;
                            case GloableParams.Key_Protocol_NetControl_Report:
                                switch (paramBuffer[0]){
                                    case 0x00:
                                        txt_report_netcontrol_workmode.setText("调制：信令模式");
                                        break;
                                    case 0x01:
                                        txt_report_netcontrol_workmode.setText("调制：TDMA模式");
                                        break;
                                    case 0x02:
                                        txt_report_netcontrol_workmode.setText("调制：FDMA模式");
                                        break;
                                    case 0x03:
                                        txt_report_netcontrol_workmode.setText("调制：CDMA模式");
                                        break;
                                    case 0x04:
                                        txt_report_netcontrol_workmode.setText("调制：抗旋翼模式");
                                        break;
                                    case 0x05:
                                        txt_report_netcontrol_workmode.setText("调制：馈电模式");
                                        break;
                                    case 0x06:
                                        txt_report_netcontrol_workmode.setText("解调：信令模式");
                                        break;
                                    case 0x07:
                                        txt_report_netcontrol_workmode.setText("解调：VTDM模式");
                                        break;
                                    case 0x08:
                                        txt_report_netcontrol_workmode.setText("解调：抗旋翼模式");
                                        break;
                                    case 0x09:
                                        txt_report_netcontrol_workmode.setText("解调：抗干扰模式");
                                        break;
                                    case 0x0A:
                                        txt_report_netcontrol_workmode.setText("解调：馈电模式");
                                        break;
                                    default:
                                        break;
                                }
                                txt_report_netcontrol_channelunit.setText(Utils.bytesToInt(
                                        Utils.SubBytes(paramBuffer,1,4)));
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

    /**
     * 绑定控件id
     */
    @Override
    protected void initControl() {
        txt_report_broadmod_workmode = (TextView) findViewById(R.id.txt_report_broadmod_workmode);
        txt_report_broadmod_version = (TextView) findViewById(R.id.txt_report_broadmod_version);
        txt_report_broaddem_workmode = (TextView) findViewById(R.id.txt_report_broaddem_workmode);
        txt_report_broaddem_version = (TextView) findViewById(R.id.txt_report_broaddem_version);
        txt_report_broaddem_sync = (TextView) findViewById(R.id.txt_report_broaddem_sync);
        txt_report_broaddem_code = (TextView) findViewById(R.id.txt_report_broaddem_code);
        txt_report_broaddem_dbm = (TextView) findViewById(R.id.txt_report_broaddem_dbm);
        txt_report_broaddem_snr = (TextView) findViewById(R.id.txt_report_broaddem_snr);
        txt_report_broaddem_freq = (TextView) findViewById(R.id.txt_report_broaddem_freq);
        txt_report_antimod_workmode = (TextView) findViewById(R.id.txt_report_antimod_workmode);
        txt_report_antimod_version = (TextView) findViewById(R.id.txt_report_antimod_version);
        txt_report_antidem_workmode = (TextView) findViewById(R.id.txt_report_antidem_workmode);
        txt_report_antidem_version = (TextView) findViewById(R.id.txt_report_antidem_version);
        txt_report_antidem_sync = (TextView) findViewById(R.id.txt_report_antidem_sync);
        txt_report_antidem_dbm = (TextView) findViewById(R.id.txt_report_antidem_dbm);
        txt_report_antidem_snr = (TextView) findViewById(R.id.txt_report_antidem_snr);
        txt_report_antidem_freq = (TextView) findViewById(R.id.txt_report_antidem_freq);
        txt_report_netcontrol_workmode = (TextView) findViewById(R.id.txt_report_netcontrol_workmode);
        txt_report_netcontrol_channelunit = (TextView) findViewById(R.id.txt_report_netcontrol_channelunit);
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                Utils.finish(ProtocolReportActivity.this);
                break;
            default:
                break;
        }
    }
}
