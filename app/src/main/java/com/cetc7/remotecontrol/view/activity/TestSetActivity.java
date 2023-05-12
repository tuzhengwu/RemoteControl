package com.cetc7.remotecontrol.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
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

import java.util.Arrays;

import static com.cetc7.remotecontrol.MainActivity.socket;

public class TestSetActivity extends BaseActivity implements OnClickListener {

    private TextView txt_title,txt_modset_workmode,txt_modset_sendrate,txt_modset_centerfreq,
            txt_modset_outdbm,txt_demset_workmode,txt_demset_recvrate,txt_demset_centerfreq,
            txt_antimodset_workmode,txt_antimodset_rate,txt_antimodset_centerfreq,
            txt_antimodset_outdbm,txt_antimodset_timeslot,txt_antimodset_carrierwave,
            txt_antidemset_workmode,txt_antidemset_rate,txt_antidemset_centerfreq;
    private CheckBox check_carrier,check_singlecarrier,check_modtest,check_spectruminversion,
            check_demtest,check_errorzero,check_framnumzero,check_anti_carrier,check_anti_singlecarrier,
            check_anti_modtest,check_anti_spectruminversion,check_anti_demtest,check_anti_errorzero,
            check_anti_framnumzero;
    private Button btn_broadmodset,btn_broaddemset,btn_antimodset,btn_antidemset;
    private byte[] paramBytes,frameBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_set);
        findViewById();
        setOnListener();
    }

    private void setOnListener() {
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.re_modset_workmode).setOnClickListener(this);
        findViewById(R.id.re_modset_sendrate).setOnClickListener(this);
        findViewById(R.id.re_modset_centerfreq).setOnClickListener(this);
        findViewById(R.id.re_modset_outdbm).setOnClickListener(this);
        findViewById(R.id.re_demset_workmode).setOnClickListener(this);
        findViewById(R.id.re_demset_recvrate).setOnClickListener(this);
        findViewById(R.id.re_demset_centerfreq).setOnClickListener(this);
        findViewById(R.id.re_antimodset_workmode).setOnClickListener(this);
        findViewById(R.id.re_antimodset_rate).setOnClickListener(this);
        findViewById(R.id.re_antimodset_centerfreq).setOnClickListener(this);
        findViewById(R.id.re_antimodset_outdbm).setOnClickListener(this);
        findViewById(R.id.re_antimodset_timeslot).setOnClickListener(this);
        findViewById(R.id.re_antimodset_carrierwave).setOnClickListener(this);
        findViewById(R.id.re_antidemset_workmode).setOnClickListener(this);
        findViewById(R.id.re_antidemset_rate).setOnClickListener(this);
        findViewById(R.id.re_antidemset_centerfreq).setOnClickListener(this);

        check_carrier.setOnClickListener(this);
        check_singlecarrier.setOnClickListener(this);
        check_modtest.setOnClickListener(this);
        check_spectruminversion.setOnClickListener(this);
        check_demtest.setOnClickListener(this);
        check_errorzero.setOnClickListener(this);
        check_framnumzero.setOnClickListener(this);
        check_anti_carrier.setOnClickListener(this);
        check_anti_singlecarrier.setOnClickListener(this);
        check_anti_modtest.setOnClickListener(this);
        check_anti_spectruminversion.setOnClickListener(this);
        check_anti_demtest.setOnClickListener(this);
        check_anti_errorzero.setOnClickListener(this);
        check_anti_framnumzero.setOnClickListener(this);

        btn_broadmodset.setOnClickListener(this);
        btn_broaddemset.setOnClickListener(this);
        btn_antimodset.setOnClickListener(this);
        btn_antidemset.setOnClickListener(this);
    }

    private void findViewById() {
        findViewById(R.id.img_back).setVisibility(View.VISIBLE);
        findViewById(R.id.txt_right).setVisibility(View.GONE);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_title.setText(R.string.test_mode);

        check_carrier = (CheckBox) findViewById(R.id.check_carrier);
        check_singlecarrier = (CheckBox) findViewById(R.id.check_singlecarrier);
        check_modtest = (CheckBox) findViewById(R.id.check_modtest);
        check_spectruminversion = (CheckBox) findViewById(R.id.check_spectruminversion);
        check_demtest = (CheckBox) findViewById(R.id.check_demtest);
        check_errorzero = (CheckBox) findViewById(R.id.check_errorzero);
        check_framnumzero = (CheckBox) findViewById(R.id.check_framnumzero);
        check_anti_carrier = (CheckBox) findViewById(R.id.check_anti_carrier);
        check_anti_singlecarrier = (CheckBox) findViewById(R.id.check_anti_singlecarrier);
        check_anti_modtest = (CheckBox) findViewById(R.id.check_anti_modtest);
        check_anti_spectruminversion = (CheckBox) findViewById(R.id.check_anti_spectruminversion);
        check_anti_demtest = (CheckBox) findViewById(R.id.check_anti_demtest);
        check_anti_errorzero = (CheckBox) findViewById(R.id.check_anti_errorzero);
        check_anti_framnumzero = (CheckBox) findViewById(R.id.check_anti_framnumzero);

        btn_broadmodset = (Button)findViewById(R.id.btn_broadmodset);
        btn_broaddemset = (Button)findViewById(R.id.btn_broaddemset);
        btn_antimodset = (Button)findViewById(R.id.btn_antimodset);
        btn_antidemset = (Button)findViewById(R.id.btn_antidemset);

        txt_modset_workmode = (TextView) findViewById(R.id.txt_modset_workmode);
        txt_modset_sendrate = (TextView) findViewById(R.id.txt_modset_sendrate);
        txt_modset_centerfreq = (TextView) findViewById(R.id.txt_modset_centerfreq);
        txt_modset_outdbm = (TextView) findViewById(R.id.txt_modset_outdbm);
        txt_demset_workmode = (TextView) findViewById(R.id.txt_demset_workmode);
        txt_demset_recvrate = (TextView) findViewById(R.id.txt_demset_recvrate);
        txt_demset_centerfreq = (TextView) findViewById(R.id.txt_demset_centerfreq);
        txt_antimodset_workmode = (TextView) findViewById(R.id.txt_antimodset_workmode);
        txt_antimodset_rate = (TextView) findViewById(R.id.txt_antimodset_rate);
        txt_antimodset_centerfreq = (TextView) findViewById(R.id.txt_antimodset_centerfreq);
        txt_antimodset_outdbm = (TextView) findViewById(R.id.txt_antimodset_outdbm);
        txt_antimodset_timeslot = (TextView) findViewById(R.id.txt_antimodset_timeslot);
        txt_antimodset_carrierwave = (TextView) findViewById(R.id.txt_antimodset_carrierwave);
        txt_antidemset_workmode = (TextView) findViewById(R.id.txt_antidemset_workmode);
        txt_antidemset_rate = (TextView) findViewById(R.id.txt_antidemset_rate);
        txt_antidemset_centerfreq = (TextView) findViewById(R.id.txt_antidemset_centerfreq);
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
                Utils.finish(TestSetActivity.this);
                break;
            case R.id.re_modset_workmode:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_modset_workmode,R.id.re_modset_workmode,GloableParams.ListString_Test_modset_workmode,
                        new BasicNameValuePair(Constants.NAME,"宽带调制工作模式"));
                break;
            case R.id.re_modset_sendrate:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_modset_sendrate,R.id.re_modset_sendrate,GloableParams.ListString_Test_modset_sendrate,
                        new BasicNameValuePair(Constants.NAME,"宽带调制发信息速率"));
                break;
            case R.id.re_modset_centerfreq:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_modset_centerfreq,R.id.re_modset_centerfreq,
                        new BasicNameValuePair(Constants.NAME,"宽带调制中心频率"));
                break;
            case R.id.re_modset_outdbm:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_modset_outdbm,R.id.re_modset_outdbm,
                        new BasicNameValuePair(Constants.NAME,"宽带调制输出电平"));
                break;
            case R.id.check_carrier:
                if (check_carrier.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckcarrier,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestModsetCheckcarrier,0);
                    check_carrier.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckcarrier,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestModsetCheckcarrier,1);
                    check_carrier.setChecked(true);
                }
                break;
            case R.id.check_singlecarrier:
                if (check_singlecarrier.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetChecksinglecarrier,false);
                    check_singlecarrier.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetChecksinglecarrier,true);
                    check_singlecarrier.setChecked(true);
                }
                break;
            case R.id.check_modtest:
                if (check_modtest.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckmodtest,false);
                    check_modtest.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckmodtest,true);
                    check_modtest.setChecked(true);
                }
                break;
            case R.id.check_spectruminversion:
                if (check_spectruminversion.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckspectruminversion,false);
                    check_spectruminversion.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestModsetCheckspectruminversion,true);
                    check_spectruminversion.setChecked(true);
                }
                break;
            case R.id.re_demset_workmode:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_demset_workmode,R.id.re_demset_workmode,GloableParams.ListString_Test_demset_workmode,
                        new BasicNameValuePair(Constants.NAME,"宽带解调工作模式"));
                break;
            case R.id.re_demset_recvrate:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_demset_recvrate,R.id.re_demset_recvrate,GloableParams.ListString_Test_demset_recvrate,
                        new BasicNameValuePair(Constants.NAME,"宽带解调收信息速率"));
                break;
            case R.id.re_demset_centerfreq:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_demset_centerfreq,R.id.re_demset_centerfreq,
                        new BasicNameValuePair(Constants.NAME,"宽带解调中心频率"));
                break;
            case R.id.check_demtest:
                if (check_demtest.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetChecktest,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetChecktest,0);
                    check_demtest.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetChecktest,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetChecktest,1);
                    check_demtest.setChecked(true);
                }
                break;
            case R.id.check_errorzero:
                if (check_errorzero.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetCheckerrorzero,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetCheckerrorzero,0);
                    check_errorzero.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetCheckerrorzero,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetCheckerrorzero,1);
                    check_errorzero.setChecked(true);
                }
                break;
            case R.id.check_framnumzero:
                if (check_framnumzero.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetCheckframnumzero,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetCheckframnumzero,0);
                    check_framnumzero.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestDemsetCheckframnumzero,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetCheckframnumzero,1);
                    check_framnumzero.setChecked(true);
                }
                break;
            case R.id.re_antimodset_workmode:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_antimodset_workmode,R.id.re_antimodset_workmode,
                        GloableParams.ListString_Test_antimodset_workmode,
                        new BasicNameValuePair(Constants.NAME,"抗强调制工作模式"));
                break;
            case R.id.re_antimodset_rate:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_antimodset_rate,R.id.re_antimodset_rate,
                        GloableParams.ListString_Test_antimodset_rate,
                        new BasicNameValuePair(Constants.NAME,"抗强调制信息速率"));
                break;
            case R.id.re_antimodset_centerfreq:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_antimodset_centerfreq,R.id.re_antimodset_centerfreq,
                        new BasicNameValuePair(Constants.NAME,"抗强调制中心频率"));
                break;
            case R.id.re_antimodset_outdbm:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_antimodset_outdbm,R.id.re_antimodset_outdbm,
                        new BasicNameValuePair(Constants.NAME,"抗强调制输出电平"));
                break;
            case R.id.check_anti_carrier:
                if (check_anti_carrier.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckanticarrier,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckanticarrier,0);
                    check_anti_carrier.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckanticarrier,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckanticarrier,1);
                    check_anti_carrier.setChecked(true);
                }
                break;
            case R.id.check_anti_singlecarrier:
                if (check_anti_singlecarrier.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantisinglecarrier,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantisinglecarrier,0);
                    check_anti_singlecarrier.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantisinglecarrier,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantisinglecarrier,1);
                    check_anti_singlecarrier.setChecked(true);
                }
                break;
            case R.id.check_anti_modtest:
                if (check_anti_modtest.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantimodtest,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantimodtest,0);
                    check_anti_modtest.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantimodtest,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantimodtest,1);
                    check_anti_modtest.setChecked(true);
                }
                break;
            case R.id.check_anti_spectruminversion:
                if (check_anti_spectruminversion.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantispectruminversion,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantispectruminversion,0);
                    check_anti_spectruminversion.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiModsetCheckantispectruminversion,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModsetCheckantispectruminversion,1);
                    check_anti_spectruminversion.setChecked(true);
                }
                break;
            case R.id.re_antimodset_timeslot:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_antimodset_timeslot,R.id.re_antimodset_timeslot,
                        new BasicNameValuePair(Constants.NAME,"抗强调制时隙号"));
                break;
            case R.id.re_antimodset_carrierwave:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_antimodset_carrierwave,R.id.re_antimodset_carrierwave,
                        new BasicNameValuePair(Constants.NAME,"抗强调制载波号"));
                break;
            case R.id.re_antidemset_workmode:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_antidemset_workmode,R.id.re_antidemset_workmode,
                        GloableParams.ListString_Test_antidemset_workmode,
                        new BasicNameValuePair(Constants.NAME,"抗强解调工作模式"));
                break;
            case R.id.re_antidemset_rate:
                Utils.start_ActivityForResult(TestSetActivity.this,ListViewActivity.class,
                        R.id.re_antidemset_rate,R.id.re_antidemset_rate,
                        GloableParams.ListString_Test_antidemset_rate,
                        new BasicNameValuePair(Constants.NAME,"抗强解调信息速率"));
                break;
            case R.id.re_antidemset_centerfreq:
                Utils.start_ActivityForResult(TestSetActivity.this,ParamSetActivity.class,
                        R.id.re_antidemset_centerfreq,R.id.re_antidemset_centerfreq,
                        new BasicNameValuePair(Constants.NAME,"抗强调制中心频率"));
                break;
            case R.id.check_anti_demtest:
                if (check_anti_demtest.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckTest,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckTest,0);
                    check_anti_demtest.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckTest,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckTest,1);
                    check_anti_demtest.setChecked(true);
                }
                break;
            case R.id.check_anti_errorzero:
                if (check_anti_errorzero.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckErrorzero,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckErrorzero,0);
                    check_anti_errorzero.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckErrorzero,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckErrorzero,1);
                    check_anti_errorzero.setChecked(true);
                }
                break;
            case R.id.check_anti_framnumzero:
                if (check_anti_framnumzero.isChecked()){
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckFramnumzero,false);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckFramnumzero,0);
                    check_anti_framnumzero.setChecked(false);
                } else {
                    Utils.putBooleanValue(TestSetActivity.this,Constants.TestAntiDemSetCheckFramnumzero,true);
                    Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetCheckFramnumzero,1);
                    check_anti_framnumzero.setChecked(true);
                }
                break;
            case R.id.btn_broadmodset:
                byte test_modset_workmode = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestModsetWorkmode);
                byte test_modset_sendrate = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestModsetSendrate);
                String test_modset_centerfreq = Utils.getValue(TestSetActivity.this,
                        Constants.TestModsetCenterfreq);
                String test_modset_outdbm = Utils.getValue(TestSetActivity.this,
                        Constants.TestModsetOutdbm);
                paramBytes = new byte[12];
                Arrays.fill(paramBytes, (byte) 0);
                paramBytes[0] = test_modset_workmode;
                paramBytes[1] = test_modset_sendrate;
                System.arraycopy(Utils.intToBytes(Integer.parseInt(test_modset_centerfreq)),
                        0,paramBytes,2,4);
                System.arraycopy(Utils.shortToBytes(Short.parseShort(test_modset_outdbm)),
                        0,paramBytes,6,2);
                paramBytes[8] = (byte) (Utils.getIntValue(TestSetActivity.this,Constants.TestModsetCheckcarrier));
                paramBytes[9] = (byte) (Utils.getIntValue(TestSetActivity.this,Constants.TestModsetChecksinglecarrier));
                paramBytes[10] = (byte) (Utils.getIntValue(TestSetActivity.this,Constants.TestModsetCheckmodtest));
                paramBytes[11] = (byte) (Utils.getIntValue(TestSetActivity.this,Constants.TestModsetCheckspectruminversion));
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Test_BroadModSet,
                        paramBytes);
                socket.sendMessage(frameBytes);
                break;
            case R.id.btn_broaddemset:
                byte test_demset_workmode = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestDemsetWorkmode);
                byte test_demset_recvrate = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestDemsetRecvrate);
                String test_demset_centerfreq = Utils.getValue(TestSetActivity.this,
                        Constants.TestDemsetCenterfreq);
                paramBytes = new byte[15];
                Arrays.fill(paramBytes, (byte) 0);
                paramBytes[0] = test_demset_workmode;
                paramBytes[1] = test_demset_recvrate;
                System.arraycopy(Utils.intToBytes(Integer.parseInt(test_demset_centerfreq)),
                        0,paramBytes,2,4);
                paramBytes[6] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestDemsetChecktest));
                paramBytes[13] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestDemsetCheckerrorzero));
                paramBytes[14] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestDemsetCheckframnumzero));
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Test_BroadDemSet,
                        paramBytes);
                socket.sendMessage(frameBytes);
                break;
            case R.id.btn_antimodset:
                byte test_antimodset_workmode = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModSetWorkmode);
                byte test_antimodset_recvrate = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModSetRate);
                String test_antimodset_centerfreq = Utils.getValue(TestSetActivity.this,
                        Constants.TestAntiModSetCenterfreq);
                String test_antimodset_outdbm = Utils.getValue(TestSetActivity.this,
                        Constants.TestAntiModSetOutdbm);
                String test_antimodset_timeslot = Utils.getValue(TestSetActivity.this,
                        Constants.TestAntiModSetTimeslot);
                String test_antimodset_carrierwave = Utils.getValue(TestSetActivity.this,
                        Constants.TestAntiModSetCarrierwave);
                paramBytes = new byte[14];
                Arrays.fill(paramBytes, (byte) 0);
                paramBytes[0] = test_antimodset_workmode;
                paramBytes[1] = test_antimodset_recvrate;
                System.arraycopy(Utils.intToBytes(Integer.parseInt(test_antimodset_centerfreq)),
                        0,paramBytes,2,4);
                System.arraycopy(Utils.intToBytes(Integer.parseInt(test_antimodset_outdbm)),
                        0,paramBytes,6,2);
                paramBytes[8] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModsetCheckanticarrier));
                paramBytes[9] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModsetCheckantisinglecarrier));
                paramBytes[10] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModsetCheckantimodtest));
                paramBytes[11] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiModsetCheckantispectruminversion));
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Test_AntiModSet,
                        paramBytes);
                socket.sendMessage(frameBytes);
                break;
            case R.id.btn_antidemset:
                byte test_antidemset_workmode = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiDemSetWorkmode);
                byte test_antidemset_rate = (byte) Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiDemSetRate);
                String test_antidemset_centerfreq = Utils.getValue(TestSetActivity.this,
                        Constants.TestAntiDemSetCenterfreq);
                paramBytes = new byte[9];
                Arrays.fill(paramBytes, (byte) 0);
                paramBytes[0] = test_antidemset_workmode;
                paramBytes[1] = test_antidemset_rate;
                System.arraycopy(Utils.intToBytes(Integer.parseInt(test_antidemset_centerfreq)),
                        0,paramBytes,2,4);
                paramBytes[6] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiDemSetCheckTest));
                paramBytes[7] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiDemSetCheckErrorzero));
                paramBytes[8] = (byte) (Utils.getIntValue(TestSetActivity.this,
                        Constants.TestAntiDemSetCheckFramnumzero));
                frameBytes = ProtocolFrame.getProtocolFrame(GloableParams.DeviceType_MainContol,
                        GloableParams.Command_Set,GloableParams.Key_Test_AntiDemSet,
                        paramBytes);
                socket.sendMessage(frameBytes);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case R.id.re_modset_workmode:
                int test_modset_workmode = data.getExtras().getInt("list_item");
                String test_modset_workmode_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestModsetWorkmode,test_modset_workmode);
                Utils.putValue(TestSetActivity.this,Constants.TestModsetWorkmode,test_modset_workmode_str);
                txt_modset_workmode.setText(test_modset_workmode_str);
                break;
            case R.id.re_modset_sendrate:
                int test_modset_sendrate = data.getExtras().getInt("list_item");
                String test_modset_sendrate_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestModsetSendrate,test_modset_sendrate);
                Utils.putValue(TestSetActivity.this,Constants.TestModsetSendrate,test_modset_sendrate_str);
                txt_modset_sendrate.setText(test_modset_sendrate_str);
                break;
            case R.id.re_modset_centerfreq:
                String test_modset_centerfreq = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestModsetCenterfreq,test_modset_centerfreq);
                txt_modset_centerfreq.setText(test_modset_centerfreq);
                break;
            case R.id.re_modset_outdbm:
                String test_modset_outdbm = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestModsetOutdbm,test_modset_outdbm);
                txt_modset_outdbm.setText(test_modset_outdbm);
                break;
            case R.id.re_demset_workmode:
                int test_demset_workmode = data.getExtras().getInt("list_item");
                String test_demset_workmode_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetWorkmode,test_demset_workmode);
                Utils.putValue(TestSetActivity.this,Constants.TestDemsetWorkmode,test_demset_workmode_str);
                txt_demset_workmode.setText(test_demset_workmode_str);
                break;
            case R.id.re_demset_recvrate:
                int test_demset_recvrate = data.getExtras().getInt("list_item");
                String test_demset_recvrate_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestDemsetRecvrate,test_demset_recvrate);
                Utils.putValue(TestSetActivity.this,Constants.TestDemsetRecvrate,test_demset_recvrate_str);
                txt_demset_recvrate.setText(test_demset_recvrate_str);
                break;
            case R.id.re_demset_centerfreq:
                String test_demset_centerfreq = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestDemsetCenterfreq,test_demset_centerfreq);
                txt_demset_centerfreq.setText(test_demset_centerfreq);
                break;
            case R.id.re_antimodset_workmode:
                int test_antimodset_workmode = data.getExtras().getInt("list_item");
                String test_antimodset_workmode_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModSetWorkmode,test_antimodset_workmode);
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetWorkmode,test_antimodset_workmode_str);
                txt_antimodset_workmode.setText(test_antimodset_workmode_str);
                break;
            case R.id.re_antimodset_rate:
                int test_antimodset_rate = data.getExtras().getInt("list_item");
                String test_antimodset_rate_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestAntiModSetRate,test_antimodset_rate);
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetRate,test_antimodset_rate_str);
                txt_antimodset_rate.setText(test_antimodset_rate_str);
                break;
            case R.id.re_antimodset_centerfreq:
                String test_antimodset_centerfreq = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetCenterfreq,test_antimodset_centerfreq);
                txt_antimodset_centerfreq.setText(test_antimodset_centerfreq);
                break;
            case R.id.re_antimodset_outdbm:
                String test_antimodset_outdbm = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetOutdbm,test_antimodset_outdbm);
                txt_antimodset_outdbm.setText(test_antimodset_outdbm);
                break;
            case R.id.re_antimodset_timeslot:
                String test_antimodset_timeslot = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetTimeslot,test_antimodset_timeslot);
                txt_antimodset_timeslot.setText(test_antimodset_timeslot);
                break;
            case R.id.re_antimodset_carrierwave:
                String test_antimodset_carrierwave = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestAntiModSetCarrierwave,test_antimodset_carrierwave);
                txt_antimodset_carrierwave.setText(test_antimodset_carrierwave);
                break;
            case R.id.re_antidemset_workmode:
                int test_antidemset_workmode = data.getExtras().getInt("list_item");
                String test_antidemset_workmode_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetWorkmode,test_antidemset_workmode);
                Utils.putValue(TestSetActivity.this,Constants.TestAntiDemSetWorkmode,test_antidemset_workmode_str);
                txt_antidemset_workmode.setText(test_antidemset_workmode_str);
                break;
            case R.id.re_antidemset_rate:
                int test_antidemset_rate = data.getExtras().getInt("list_item");
                String test_antidemset_rate_str = data.getExtras().getString("list_item_string");
                Utils.putIntValue(TestSetActivity.this,Constants.TestAntiDemSetRate,test_antidemset_rate);
                Utils.putValue(TestSetActivity.this,Constants.TestAntiDemSetRate,test_antidemset_rate_str);
                txt_antidemset_rate.setText(test_antidemset_rate_str);
                break;
            case R.id.re_antidemset_centerfreq:
                String test_antidemset_centerfreq = data.getExtras().getString("param");
                Utils.putValue(TestSetActivity.this,Constants.TestAntiDemSetCenterfreq,test_antidemset_centerfreq);
                txt_antidemset_centerfreq.setText(test_antidemset_centerfreq);
                break;
            default:
                break;
        }
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
                            case GloableParams.Key_Test_BroadModSet:
                            case GloableParams.Key_Test_BroadDemSet:
                            case GloableParams.Key_Test_AntiModSet:
                            case GloableParams.Key_Test_AntiDemSet:
                                switch (paramBuffer[0]){
                                    case GloableParams.Param_SetResponse_Failed:
                                        Utils.showShortToast(TestSetActivity.this,getString(R.string.param_set_failed));
                                        break;
                                    case GloableParams.Param_SetResponse_Success:
                                        Utils.showShortToast(TestSetActivity.this,getString(R.string.param_set_success));
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
