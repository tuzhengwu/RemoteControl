package com.cetc7.remotecontrol.view.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.BaseActivity;

//公共页面
public class PublicActivity extends BaseActivity implements OnClickListener {
	private TextView txt_title;
	private ImageView img_back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_param_set);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void processMessage(Message msg) {

	}

	@Override
	protected void initControl() {
		txt_title = (TextView) findViewById(R.id.txt_title);
		String Name = getIntent().getStringExtra(Constants.NAME);
		txt_title.setText(Name);
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setVisibility(View.VISIBLE);
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
	}

	@Override
	protected void setListener() {
		img_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			Utils.finish(PublicActivity.this);
			break;
		default:
			break;
		}
	}

}
