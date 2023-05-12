package com.cetc7.remotecontrol.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.communication.ProtocolFrame;
import com.cetc7.remotecontrol.view.BaseActivity;
import org.apache.http.message.BasicNameValuePair;
import static com.cetc7.remotecontrol.MainActivity.socket;

public class ProtocolSetActivity extends BaseActivity implements OnClickListener {

    private String TAG = "ProtocolSetActivity";

    private TextView txt_title,txt_earthlongitude,txt_earthLatitude,txt_satellitelogitude,
            txt_work_mode,txt_manage_net,txt_mac_addr,txt_channel_unit;
    private Button btn_location,btn_workmode,btn_managenet,btn_macaddr,btn_channelunit;
    private CheckBox checkBox;
    private byte[] paramBytes,frameBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_set);
        findViewById();
        setOnListener();
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
                        switch (keyWord){
                            case GloableParams.Key_Protocol_Location_Set:
                            case GloableParams.Key_Protocol_WorkMode_Set:
                            case GloableParams.Key_Protocol_IpAddr_Set:
                            case GloableParams.Key_Protocol_MacAddr_Set:
                            case GloableParams.Key_Protocol_ChannelUnit_Set:
                                switch (paramBuffer[0]){
                                    case GloableParams.Param_SetResponse_Failed:
                                        Utils.showShortToast(ProtocolSetActivity.this,getString(R.string.param_set_failed));
                                        break;
                                    case GloableParams.Param_SetResponse_Success:
                                        Utils.showShortToast(ProtocolSetActivity.this,getString(R.string.param_set_success));
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
                break;
            default:
                break;
        }
    }

    private void setOnListener() {
        findViewById(R.id.re_earthlongitude).setOnClickListener(this);
        findViewById(R.id.re_earthLatitude).setOnClickListener(this);
        findViewById(R.id.re_satellitelogitude).setOnClickListener(this);
        findViewById(R.id.re_work_mode).setOnClickListener(this);
        findViewById(R.id.re_manage_net).setOnClickListener(this);
        findViewById(R.id.re_mac_addr).setOnClickListener(this);
        findViewById(R.id.re_channel_unit).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
        checkBox.setOnClickListener(this);
        btn_workmode.setOnClickListener(this);
        btn_location.setOnClickListener(this);
        btn_managenet.setOnClickListener(this);
        btn_macaddr.setOnClickListener(this);
        btn_channelunit.setOnClickListener(this);

    }

    private void findViewById() {
        findViewById(R.id.img_back).setVisibility(View.VISIBLE);
        findViewById(R.id.txt_right).setVisibility(View.GONE);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText(R.string.protocol_set);
        checkBox = (CheckBox) findViewById(R.id.check_top);
        txt_earthlongitude = (TextView) findViewById(R.id.txt_earthlongitude);
        txt_earthLatitude = (TextView) findViewById(R.id.txt_earthLatitude);
        txt_satellitelogitude = (TextView) findViewById(R.id.txt_satellitelogitude);
        txt_work_mode = (TextView) findViewById(R.id.txt_work_mode);
        txt_manage_net = (TextView) findViewById(R.id.txt_manage_net);
        txt_mac_addr = (TextView) findViewById(R.id.txt_mac_addr);
        txt_channel_unit = (TextView) findViewById(R.id.txt_channel_unit);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_workmode = (Button) findViewById(R.id.btn_workmode);
        btn_managenet = (Button) findViewById(R.id.btn_managenet);
        btn_macaddr = (Button) findViewById(R.id.btn_macaddr);
        btn_channelunit = (Button) findViewById(R.id.btn_channelunit);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check_top:
                if(checkBox.isChecked()){
                    frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                            GloableParams.Command_Set,GloableParams.Key_Protocol_TestSwitch_Set,
                            Utils.byteToBytes(GloableParams.Param_Protocol_TestSwitch_Open));
                    socket.sendMessage(frameBytes);
                    Log.d(TAG,"check_top sendMessage : "+Utils.bytesToHex(frameBytes));
                    checkBox.setChecked(false);
                }else{
                    frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                            GloableParams.Command_Set,GloableParams.Key_Protocol_TestSwitch_Set,
                            Utils.byteToBytes(GloableParams.Param_Protocol_TestSwitch_Close));
                    socket.sendMessage(frameBytes);
                    Log.d(TAG,"check_top sendMessage : "+Utils.bytesToHex(frameBytes));
                    checkBox.setChecked(true);
                }
                break;
            case R.id.img_back:
                Utils.finish(ProtocolSetActivity.this);
                break;
            case R.id.btn_workmode:
                int signal_mode = Utils.getIntValue(ProtocolSetActivity.this,Constants.SignalMode);
                paramBytes = Utils.shortToBytes((short) signal_mode);
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Protocol_WorkMode_Set,paramBytes);
                socket.sendMessage(frameBytes);
                Log.d(TAG,"btn_workmode sendMessage : "+Utils.bytesToHex(frameBytes));
                break;
            case R.id.btn_location:
                String str_earth_longitude = Utils.getValue(ProtocolSetActivity.this,Constants.EarthLogitude);
                String str_earth_latitude = Utils.getValue(ProtocolSetActivity.this,Constants.EarthLatitude);
                String str_satellite_longitude = Utils.getValue(ProtocolSetActivity.this,Constants.SatelliteLogitude);
                paramBytes = (str_earth_longitude+str_earth_latitude+str_satellite_longitude).getBytes();
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Protocol_Location_Set,paramBytes);
                socket.sendMessage(frameBytes);
                Log.d(TAG,"btn_location sendMessage : "+Utils.bytesToHex(frameBytes));
                break;
            case R.id.btn_managenet:
                paramBytes = Utils.getValue(ProtocolSetActivity.this,Constants.ManageNet).getBytes();
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Protocol_IpAddr_Set,paramBytes);
                socket.sendMessage(frameBytes);
                Log.d(TAG,"btn_managenet sendMessage : "+Utils.bytesToHex(frameBytes));
                break;
            case R.id.btn_macaddr:
                paramBytes = Utils.getValue(ProtocolSetActivity.this,Constants.MacAddr).getBytes();
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Protocol_MacAddr_Set,paramBytes);
                socket.sendMessage(frameBytes);
                Log.d(TAG,"btn_macaddr sendMessage : "+Utils.bytesToHex(frameBytes));
                break;
            case R.id.btn_channelunit:
                paramBytes = Utils.getValue(ProtocolSetActivity.this,Constants.ChannelUnit).getBytes();
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Protocol_ChannelUnit_Set,paramBytes);
                socket.sendMessage(frameBytes);
                Log.d(TAG,"btn_channelunit sendMessage : "+Utils.bytesToHex(frameBytes));
                break;
            case R.id.re_earthlongitude:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_earthlongitude,R.id.re_earthlongitude,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.earth_longitude)));
                break;
            case R.id.re_earthLatitude:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_earthLatitude,R.id.re_earthLatitude,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.earth_latitude)));
                break;
            case R.id.re_satellitelogitude:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_satellitelogitude,R.id.re_satellitelogitude,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.satellite_longitude)));
                break;
            case R.id.re_work_mode:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ListViewActivity.class,
                        R.id.re_work_mode,R.id.re_work_mode,GloableParams.ListString_Signal_Mode,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.work_mode)));
                break;
            case R.id.re_manage_net:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_manage_net,R.id.re_manage_net,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.manage_net)));
                break;
            case R.id.re_mac_addr:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_mac_addr,R.id.re_mac_addr,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.mac_addr)));
                break;
            case R.id.re_channel_unit:
                Utils.start_ActivityForResult(ProtocolSetActivity.this,ParamSetActivity.class,
                        R.id.re_channel_unit,R.id.re_channel_unit,
                        new BasicNameValuePair(Constants.NAME,getString(R.string.channel_unit)));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult requestCode:"+requestCode+",resultCode"+resultCode);
        switch (resultCode){
            case R.id.re_earthlongitude:
                String str_earth_longitude = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.EarthLogitude,str_earth_longitude);
                txt_earthlongitude.setText(str_earth_longitude);
                break;
            case R.id.re_earthLatitude:
                String str_earth_latitude = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.EarthLatitude,str_earth_latitude);
                txt_earthLatitude.setText(str_earth_latitude);
                break;
            case R.id.re_satellitelogitude:
                String str_satellite_longitude = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.SatelliteLogitude,str_satellite_longitude);
                txt_satellitelogitude.setText(str_satellite_longitude);
                break;
            case R.id.re_work_mode:
                int list_item = data.getIntExtra("list_item",0);
                String list_item_string = data.getStringExtra("list_item_string");
                Utils.putIntValue(ProtocolSetActivity.this,Constants.SignalMode,list_item);
                Utils.putValue(ProtocolSetActivity.this,Constants.SignalModeStr,list_item_string);
                txt_work_mode.setText(list_item_string);
                break;
            case R.id.re_manage_net:
                String str_managenet = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.ManageNet,str_managenet);
                txt_manage_net.setText(str_managenet);
                break;
            case R.id.re_mac_addr:
                String str_macaddr = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.MacAddr,str_macaddr);
                txt_mac_addr.setText(str_macaddr);
                break;
            case R.id.re_channel_unit:
                String str_channelunit = data.getStringExtra("param");
                Utils.putValue(ProtocolSetActivity.this,Constants.ChannelUnit,str_channelunit);
                txt_channel_unit.setText(str_channelunit);
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
