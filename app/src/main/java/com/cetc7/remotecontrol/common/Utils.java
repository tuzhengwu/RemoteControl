package com.cetc7.remotecontrol.common;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.cetc7.remotecontrol.R;
import org.apache.http.message.BasicNameValuePair;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.core.app.NotificationCompat;


public class Utils {
	private static final String TAG = "Utils";

	public static byte SumCheck(byte[] mFrameBuffer, int start, int end) {
		int sum = 0;
		for (int i = start; i < end + 1; i++){
			sum = sum + mFrameBuffer[i];
		}
		return (byte)(sum&0xff);
	}

	public static byte[] SubBytes(byte[] bytes,int start,int len){
		byte[] mBytes = new byte[len];
		System.arraycopy(bytes,start,mBytes,0,len);
		return mBytes;
	}

	public static int bytesToInt(byte[] bytes){
		return bytes[3]&0xff|//
				(bytes[2]&0xff)<<8|//
				(bytes[1]&0xff)<<16|//
				(bytes[0]&0xff)<<24;//
	}

	public static byte[] intToBytes(int intValue){
		byte[] bytes = new byte[Integer.BYTES];
		bytes[3] = (byte) (intValue&0xff);
		bytes[2] = (byte) ((intValue>> Byte.SIZE)&0xff);
		bytes[1] = (byte) ((intValue>> Byte.SIZE*2)&0xff);
		bytes[0] = (byte) ((intValue>> Byte.SIZE*3)&0xff);
		return bytes;
	}

	public static byte[] byteToBytes(byte byteValue){
		byte[] bytes = new byte[1];
		bytes[0] = (byte) (byteValue&0xff);
		return bytes;
	}

	public static short bytesToShort(byte[] bytes){
		return (short) (bytes[1]&0xff|(bytes[0]&0xff)<<8);
	}

	public static byte[] shortToBytes(short shortValue){
		byte[] bytes = new byte[Short.BYTES];
		bytes[1] = (byte) (shortValue&0xff);
		bytes[0] = (byte) ((shortValue>>Byte.SIZE)&0xff);
		return bytes;
	}

	public static String bytesToHex(byte [] bytes){
		String hex = "";
		for(int i = 0; i < bytes.length; i++){
			String temp = Integer.toHexString(bytes[i] & 0xFF);
			if(temp.length() == 1){
				temp = "0" + temp;
			}
			hex = hex + " "+ temp;
		}
		return hex;
	}

	public static void sendUdp(String msg,int port) {//给本地端口发送指令
		try {
			Log.i(TAG, "data is " + msg);
			InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
			DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes("UTF-8"),
					msg.getBytes("UTF-8").length, inetAddress,port);
			DatagramSocket datagramSocket = new DatagramSocket();
			datagramSocket.send(datagramPacket);
			datagramPacket = null;
			datagramSocket.close();
			Log.i(TAG, "send out data is " + msg);
			msg = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = null;
			Log.i(TAG,"lose");
		}
	}


	public static void showLongToast(Context context, String pMsg) {
		Toast.makeText(context, pMsg, Toast.LENGTH_LONG).show();
	}

	public static void showShortToast(Context context, String pMsg) {
		Toast.makeText(context, pMsg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 关闭 Activity
	 * 
	 * @param activity
	 */
	public static void finish(Activity activity) {
		activity.finish();
		activity.overridePendingTransition(R.anim.push_right_in,
				R.anim.push_right_out);
	}

	/**
	 * 打开Activity
	 * 
	 * @param activity
	 * @param cls
	 * @param name
	 */
	public static void start_Activity(Activity activity, Class<?> cls,
			BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (name != null)
			for (int i = 0; i < name.length; i++) {
				intent.putExtra(name[i].getName(), name[i].getValue());
			}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}

	public static void start_Activity(Activity activity, Class<?> cls,byte key,
									  BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtra("key",key);
		if (name != null)
			for (int i = 0; i < name.length; i++) {
				intent.putExtra(name[i].getName(), name[i].getValue());
			}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}

	public static void start_ActivityForResult(Activity activity, Class<?> cls, int requestCode,
											   int rid,
											   BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtra("rid",rid);
		if (name != null)
			for (int i = 0; i < name.length; i++) {
				intent.putExtra(name[i].getName(), name[i].getValue());
			}
		activity.startActivityForResult(intent,requestCode);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}

	public static void start_ActivityForResult(Activity activity, Class<?> cls, int requestCode,
											   int key,ArrayList<String> list_string,
											   BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtra("rid",key);
		intent.putStringArrayListExtra("list_string",list_string);
		if (name != null)
			for (int i = 0; i < name.length; i++) {
				intent.putExtra(name[i].getName(), name[i].getValue());
			}
		activity.startActivityForResult(intent,requestCode);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}

	public static void start_ActivityForResult(Activity activity, Class<?> cls,int requestCode,
											   byte key, BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (name != null)
			for (int i = 0; i < name.length; i++) {
				intent.putExtra(name[i].getName(), name[i].getValue());
			}
		intent.putExtra("key",key);
		activity.startActivityForResult(intent,requestCode);
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}

	/**
	 * 判断是否有网络
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
			return false;
		} else {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity == null) {
				Log.w("Utility", "couldn't get connectivity manager");
			} else {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].isAvailable()) {
							Log.d("Utility", "network is available");
							return true;
						}
					}
				}
			}
		}
		Log.d("Utility", "network is not available");
		return false;
	}

	/**
	 * 发送文字通知
	 * 
	 * @param context
	 * @param Msg
	 * @param Title
	 * @param content
	 * @param i
	 */
	@SuppressWarnings("deprecation")
	public static void sendText(Context context, String Msg, String Title,
			String content, Intent i) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentTitle(Title);
		builder.setContentText(content);
		builder.setAutoCancel(true);
		Notification notification = builder.build();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
//		Notification notification = new Notification(R.drawable.ic_launcher,
//				Msg, System.currentTimeMillis());
//		notification.flags = Notification.FLAG_AUTO_CANCEL;
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(contentIntent);

//		notification.setLatestEventInfo(context, Title, content, contentIntent);
//		mn.notify(0, notification);
		notificationManager.notify(0x1,notification);
	}

	/**
	 * 移除SharedPreference
	 * 
	 * @param context
	 * @param key
	 */
	public static final void RemoveValue(Context context, String key) {
		Editor editor = getSharedPreference(context).edit();
		editor.remove(key);
		boolean result = editor.commit();
		if (!result) {
			Log.e("移除Shared", "save " + key + " failed");
		}
	}

	private static final SharedPreferences getSharedPreference(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 获取SharedPreference 值
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static final String getValue(Context context, String key) {
		return getSharedPreference(context).getString(key, "");
	}

	public static final Boolean getBooleanValue(Context context, String key) {
		return getSharedPreference(context).getBoolean(key, false);
	}

	public static final void putBooleanValue(Context context, String key,
			boolean bl) {
		Editor edit = getSharedPreference(context).edit();
		edit.putBoolean(key, bl);
		edit.commit();
	}

	public static final int getIntValue(Context context, String key) {
		return getSharedPreference(context).getInt(key, 0);
	}

	public static final long getLongValue(Context context, String key,
			long default_data) {
		return getSharedPreference(context).getLong(key, default_data);
	}

	public static final boolean putLongValue(Context context, String key,
			Long value) {
		Editor editor = getSharedPreference(context).edit();
		editor.putLong(key, value);
		return editor.commit();
	}

	public static final Boolean hasValue(Context context, String key) {
		return getSharedPreference(context).contains(key);
	}

	/**
	 * 设置SharedPreference 值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static final boolean putValue(Context context, String key,
			String value) {
		value = value == null ? "" : value;
		Editor editor = getSharedPreference(context).edit();
		editor.putString(key, value);
		boolean result = editor.commit();
		if (!result) {
			return false;
		}
		return true;
	}

	/**
	 * 设置SharedPreference 值
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static final boolean putIntValue(Context context, String key,
			int value) {
		Editor editor = getSharedPreference(context).edit();
		editor.putInt(key, value);
		boolean result = editor.commit();
		if (!result) {
			return false;
		}
		return true;
	}

	public static Date stringToDate(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = null;
		try {
			// Fri Feb 24 00:00:00 CST 2012
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	/**
	 * 验证手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private static float sDensity = 0;

	/**
	 * DP转换为像素
	 * 
	 * @param context
	 * @param nDip
	 * @return
	 */
	public static int dipToPixel(Context context, int nDip) {
		if (sDensity == 0) {
			final WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			wm.getDefaultDisplay().getMetrics(dm);
			sDensity = dm.density;
		}
		return (int) (sDensity * nDip);
	}

}
