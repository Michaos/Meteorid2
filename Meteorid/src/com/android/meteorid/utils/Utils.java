package com.android.meteorid.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

public class Utils {

	@SuppressLint("NewApi")
	public static <P, T extends AsyncTask<P, ?, ?>> void execute(T task, P... params) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
		} else {
			task.execute(params);
		}
	}
}