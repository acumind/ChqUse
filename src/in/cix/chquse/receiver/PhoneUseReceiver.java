package in.cix.chquse.receiver;

import in.cix.chquse.ChqUseApp;
import in.cix.chquse.model.UseStats;
import in.cix.chquse.utils.CixLog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class PhoneUseReceiver extends BroadcastReceiver {

	private static final String TAG = "PhoneUseReceiver";
	private UseStats counter = null;

	public void onReceive(Context context, Intent intent) {
		
		counter = ChqUseApp.app.getCounter();
		
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			CixLog.d(TAG, "Screen OFF");
			counter.setStat(UseStats.OFF);

		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			CixLog.d(TAG, "Screen ON");
			counter.setStat(UseStats.ON);

		} else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
			CixLog.d(TAG, "Screen Unlock");
			counter.setStat(UseStats.UNLOCK);
			ChqUseApp.updateHandler.dispatchMessage(new Message());

		}

	}
}
