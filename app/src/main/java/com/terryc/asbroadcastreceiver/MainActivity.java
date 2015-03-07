package com.terryc.asbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	Button btn1, btn2, btn3;
	static final String INTERNAL_ACTION_1 = "com.terryc.asboradcastreceiver.Internal_1";
	static final String INTERNAL_ACTION_2 = "com.terryc.asboradcastreceiver.Internal_2";
	static final String INTERNAL_ACTION_3 = "com.terryc.asboradcastreceiver.Internal_3";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn1.setOnClickListener(new PrivateClickEvent());
		btn2.setOnClickListener(new PrivateClickEvent());
		btn3.setOnClickListener(new PrivateClickEvent());
		registerReceiver(receiver1, new IntentFilter(INTERNAL_ACTION_1));
	}

	class PrivateClickEvent implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Log.e("btn click", v.toString());
			if (v == btn1) {
				Intent intent = new Intent(INTERNAL_ACTION_1);
				sendBroadcast(intent);
			} else if (v == btn2) {
				Log.e("btn click", "22222");
				Intent intent2 = new Intent(INTERNAL_ACTION_2);
				sendBroadcast(intent2);
			} else if (v == btn3) {
				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction(Intent.ACTION_ALL_APPS);
				intentFilter.addAction(INTERNAL_ACTION_3);
				registerReceiver(receiver2, intentFilter);

				Intent intent = new Intent(INTERNAL_ACTION_3);
				intent.putExtra("name", "hello hello");
				intent.putExtra("ccc", "ddddeeefff");
				sendBroadcast(intent);
			}
		}
	}

	private BroadcastReceiver receiver1 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Toast.makeText(context, "ttt action is " + action, Toast.LENGTH_LONG).show();
		}
	};

	private BroadcastReceiver receiver2 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(Intent.ACTION_ALL_APPS.equals(action)){

				Log.e("here ", "111111111111");
			}else if(INTERNAL_ACTION_3.equals(action)){

			}

			Bundle bundle = intent.getExtras();
			Object[] bKeySet = bundle.keySet().toArray();
			for(int i = 0; i < bKeySet.length; i++){
				String keyName = bKeySet[i].toString();
				Log.e("terry " + action + keyName, bundle.getString(keyName));
			}
		}
	};


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
