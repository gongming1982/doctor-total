package com.jmgff.xu.doctortotal.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jmgff.xu.doctortotal.R;

public class GMUtil {

	public static int GET_SCREEN_WIDTH(Activity a) {
		int screenWidth = 0;
		DisplayMetrics dm = new DisplayMetrics();

		a.getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenWidth = dm.widthPixels;

		return screenWidth;
	}

	public static int GET_SCREEN_HEIGHT(Activity a) {
		DisplayMetrics dm = new DisplayMetrics();

		a.getWindowManager().getDefaultDisplay().getMetrics(dm);

		return dm.heightPixels;
	}

	public static void showToast(Context context, String message, int yOffset,
			int duration) {

		Toast toast = new Toast(context);
		toast.setDuration(duration);
		LayoutInflater inflater = LayoutInflater.from(context);
		View toastView = inflater.inflate(R.layout.toast, null);

		TextView tv = (TextView) toastView.findViewById(R.id.toast_message);
		tv.setText(message);

		toast.setView(toastView);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, yOffset);

		toast.show();
	}
}
