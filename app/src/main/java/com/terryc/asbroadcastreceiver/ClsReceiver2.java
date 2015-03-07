package com.terryc.asbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by terry-developer on 3/7/15.
 */
public class ClsReceiver2 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("111", "aaa");
		String action = intent.getAction();
		Log.e("111", "aaa");
		Toast.makeText(context, "tttclsReceiver2 action is " + action, Toast.LENGTH_LONG).show();
	}
}
