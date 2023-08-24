package com.cetc7.remotecontrol.view;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.bean.User;
import com.cetc7.remotecontrol.common.Utils;
import net.tsz.afinal.FinalDb;

public class UpdateService extends Service {
	protected FinalDb db;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		db = FinalDb.create(this, Constants.DB_NAME, false);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		initUserList();
		return super.onStartCommand(intent, flags, startId);
	}
	private void initUserList() {
		GloableParams.UserInfos = db.findAll(User.class);
	}

}
