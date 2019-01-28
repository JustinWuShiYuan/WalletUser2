package com.tong.gao.walletuser.utils;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.tong.gao.walletuser.AppApplication;
import com.tong.gao.walletuser.interfaces.CountDownCallBack;


public class UIUtils {


	private static CountDownTimer countDownTimer;

	/**
	 * 上下文的获取
	 * 
	 * @return
	 */
	public static Context getContext()
	{
		return AppApplication.getMyContext();
	}

	/**
	 * 获取资源
	 * 
	 * @return
	 */
	public static Resources getResources()
	{
		return getContext().getResources();
	}

	public static long getMainThreadId()
	{
		return AppApplication.getMainThreadId();
	}

	public static Handler getMainThreadHandler()
	{
		return AppApplication.getMainThreadHandler();
	}

	/**
	 * 主线程中执行 任务
	 * 
	 * @param task
	 */
	public static void runOnUiThread(Runnable task) {
		long currentThreadId = android.os.Process.myTid();
		long mainThreadId = getMainThreadId();

		if (currentThreadId == mainThreadId) {
			// 如果在主线程中执行
			task.run();
		} else {
			// 需要转的主线程执行
			getMainThreadHandler().post(task);
		}
	}

	/**
	 * 
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (dip * density + 0.5f);
	}

	public static float dip2px(float dip) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (float) (dip * density + 0.5f);
	}

	public static int px2dip(int px) {
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (px / density + 0.5f);
	}

	public static String getString(int resId)
	{
		return getResources().getString(resId);
	}

	public static String getPackageName()
	{
		return getContext().getPackageName();
	}

	public static String[] getStringArray(int resId)
	{
		return getResources().getStringArray(resId);
	}

	public static int getColor(int resId)
	{
		return getResources().getColor(resId);
	}


	public static void copyTextToClipboard(final String text, final Context context) {

		UIUtils.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
				ClipData clipData = ClipData.newPlainText("playerId", text);
				clipboardManager.setPrimaryClip(clipData);
				ToastUtils.showNomalShortToast("复制成功");
			}
		});
	}


	public static void startCountDown(long continueTime, final TextView tvDisplayTime, final CountDownCallBack callback) {
		if (countDownTimer == null) {
			countDownTimer = new CountDownTimer(continueTime, 1000) {
				@Override
				public void onTick(long millisUntilFinished) {
					tvDisplayTime.setText(TextUtils.parseTime1(millisUntilFinished));
				}

				@Override
				public void onFinish() {
					callback.countDownFinish();
				}
			}.start();
		}
	}

	public static void endCountDown() {
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer = null;
		}
	}
}
