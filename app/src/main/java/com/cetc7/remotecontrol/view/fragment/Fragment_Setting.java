package com.cetc7.remotecontrol.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;

import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.activity.ProtocolGetActivity;
import com.cetc7.remotecontrol.view.activity.ProtocolReportActivity;
import com.cetc7.remotecontrol.view.activity.ProtocolSetActivity;
import com.cetc7.remotecontrol.view.activity.RoutingSetActivity;
import com.cetc7.remotecontrol.view.activity.TestSetActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Setting extends Fragment implements OnClickListener {

    private Activity ctx;
    private View layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null){
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_setting,null);
            initViews();
            initData();
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent!=null){
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void setOnListener() {
        layout.findViewById(R.id.txt_protocol_set).setOnClickListener(this);
        layout.findViewById(R.id.txt_protocol_get).setOnClickListener(this);
        layout.findViewById(R.id.txt_protocol_report).setOnClickListener(this);
        layout.findViewById(R.id.txt_test).setOnClickListener(this);
        layout.findViewById(R.id.txt_routing).setOnClickListener(this);
        layout.findViewById(R.id.txt_upgrade).setOnClickListener(this);
    }

    private void initData() {
    }

    private void initViews() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_protocol_set:
                Utils.start_Activity(getActivity(), ProtocolSetActivity.class);
                break;
            case R.id.txt_protocol_get:
                Utils.start_Activity(getActivity(), ProtocolGetActivity.class);
                break;
            case R.id.txt_protocol_report:
                Utils.start_Activity(getActivity(), ProtocolReportActivity.class);
                break;
            case R.id.txt_test:
                Utils.start_Activity(getActivity(), TestSetActivity.class);
                break;
            case R.id.txt_test_report:
                Utils.start_Activity(getActivity(), ProtocolReportActivity.class);
            case R.id.txt_routing:
                Utils.start_Activity(getActivity(), RoutingSetActivity.class);
                break;
            case R.id.txt_upgrade:
                break;
            default:
                break;
        }
    }
}
