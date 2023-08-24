package com.cetc7.remotecontrol.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.MainActivity;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.activity.DebugModeActivity;
import com.cetc7.remotecontrol.view.activity.NewMsgActivity;

import org.apache.http.message.BasicNameValuePair;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Call extends Fragment implements View.OnClickListener {

    private Activity ctx;
    private View layout;
    private Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,numX,numJ,dial,delete;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_call,null);
            initViews();
            initData();
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void setOnListener() {
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        numX.setOnClickListener(this);
        numJ.setOnClickListener(this);
        dial.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void initData() {
    }

    private void initViews() {
        editText = (EditText) layout.findViewById(R.id.display);
        num1 = (Button) layout.findViewById(R.id.num1);
        num2 = (Button) layout.findViewById(R.id.num2);
        num3 = (Button) layout.findViewById(R.id.num3);
        num4 = (Button) layout.findViewById(R.id.num4);
        num5 = (Button) layout.findViewById(R.id.num5);
        num6 = (Button) layout.findViewById(R.id.num6);
        num7 = (Button) layout.findViewById(R.id.num7);
        num8 = (Button) layout.findViewById(R.id.num8);
        num9 = (Button) layout.findViewById(R.id.num9);
        num0 = (Button) layout.findViewById(R.id.num0);
        numX = (Button) layout.findViewById(R.id.numX);
        numJ = (Button) layout.findViewById(R.id.numJ);
        dial = (Button) layout.findViewById(R.id.Dial);
        delete = (Button) layout.findViewById(R.id.Delete);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
            case R.id.num7:
            case R.id.num8:
            case R.id.num9:
            case R.id.num0:
            case R.id.numX:
            case R.id.numJ:
                String str = editText.getText().toString();
                str += v.getTag().toString();
                editText.setText(str);
                break;
            case R.id.Dial:
                String str2 = editText.getText().toString();
                if ((str2 !=null) && (!str2.trim().equals(""))){
                    if (str2.trim().equals("*#*#6#*#*")){
                        Utils.start_Activity(ctx, DebugModeActivity.class,
                                new BasicNameValuePair(Constants.NAME,"调试模式"));
                    } else {
                        if (Pattern.compile("^1[3,4,5,6,7,8,9][0-9]{9}$").matcher(str2).matches()){
                            Toast.makeText(ctx,"手机号输入正确", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ctx,"手机号输入错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else{
                    Utils.showShortToast(ctx,"请输入号码");
                }
                break;
            case R.id.Delete:
                String str3 = editText.getText().toString();
                if((str3 != null) && (!str3.trim().equals(""))) {
                    str3 = str3.substring(0, str3.length() - 1);
                    editText.setText(str3);
                }
                break;
            default:
                break;
        }
    }
}
