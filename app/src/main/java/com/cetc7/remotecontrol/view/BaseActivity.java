package com.cetc7.remotecontrol.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.cetc7.remotecontrol.common.Utils;
import com.cetc7.remotecontrol.view.activity.ProtocolGetActivity;
//import com.cetc7.remotecontrol.dialog.FlippingLoadingDialog;
//import com.cetc7.remotecontrol.net.NetClient;

import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;

import androidx.annotation.NonNull;

public abstract class BaseActivity extends Activity {
	protected Activity context;
//	protected NetClient netClient;
//	protected FlippingLoadingDialog mLoadingDialog;
	protected static LinkedList<BaseActivity> queue = new LinkedList<BaseActivity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
//		App.getInstance2().addActivity(this);
//		netClient = new NetClient(this);
		initControl();
		initView();
		initData();
		setListener();
		if(!queue.contains(this))
			queue.add(this);
	}

	public static BaseActivity getActivity(int index){
		if (index < 0 || index >= queue.size())
			throw new IllegalArgumentException("out of queue");
		return queue.get(index);
	}

	public static BaseActivity getCurrentActivity(){
		return queue.getLast();
	}

	public void makeTextShort(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	public void makeTextLong(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	public abstract void processMessage(Message msg);

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		queue.removeLast();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Utils.finish(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 绑定控件id
	 */
	protected abstract void initControl();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 设置监听
	 */
	protected abstract void setListener();


	/**
	 * 打开 Activity
	 * 
	 * @param activity
	 * @param cls
	 * @param name
	 */
	public void start_Activity(Activity activity, Class<?> cls,
			BasicNameValuePair... name) {
		Utils.start_Activity(activity, cls, name);
	}

	/**
	 * 关闭 Activity
	 * 
	 * @param activity
	 */
	public void finish(Activity activity) {
		Utils.finish(activity);
	}

	/**
	 * 判断是否有网络连接
	 */
	public boolean isNetworkAvailable(Context context) {
		return Utils.isNetworkAvailable(context);
	}

//	public FlippingLoadingDialog getLoadingDialog(String msg) {
//		if (mLoadingDialog == null)
//			mLoadingDialog = new FlippingLoadingDialog(this, msg);
//		return mLoadingDialog;
//	}
	public static void sendMessage(Message msg){
		handler.sendMessage(msg);
	}

	private static Handler handler = new Handler(Looper.getMainLooper()){
		@Override
		public void handleMessage(@NonNull Message msg) {
			super.handleMessage(msg);
			if(queue.size() > 0)
				queue.getLast().processMessage(msg);
		}
	};
}