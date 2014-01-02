package com.jmgff.xu.doctortotal.view;

import java.util.Timer;
import java.util.TimerTask;

import com.jmgff.xu.doctortotal.R;
import com.jmgff.xu.doctortotal.database.DatabaseHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class SplashActivity extends Activity {

	protected static final int LODING_OK = 1;
	protected static final int LODING_ERROR = 0;
	private Timer timer;
	private Context mContext;
	public TextView tvLoding;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case LODING_OK:
				tvLoding.setText(R.string.loding_ok);
				timer.schedule(new LodingOKTimerTask(), 1400);

				break;
			case LODING_ERROR:
				timer.schedule(new LodingErrorTimerTask(), 2000);
				tvLoding.setText(R.string.loding_error);
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mContext = this;
		timer = new Timer();

		tvLoding = (TextView) findViewById(R.id.tv_activity_splash_loding);

		new Thread() {
			public void run() {
				DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
				try {
					sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = mHandler.obtainMessage();
				if (databaseHelper.initDatabase()) {
					msg.what = LODING_OK;
				} else {
					msg.what = LODING_ERROR;
				}

				mHandler.sendMessage(msg);
			};
		}.start();

	}

	private class LodingOKTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			startActivity(new Intent(getApplicationContext(),
					MainActivity.class));

			SplashActivity.this.finish();
		}

	}

	private class LodingErrorTimerTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			SplashActivity.this.finish();
		}

	}

}
