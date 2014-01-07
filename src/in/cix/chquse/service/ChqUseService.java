package in.cix.chquse.service;

import in.cix.chquse.receiver.PhoneUseReceiver;
import in.cix.chquse.utils.CixLog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ChqUseService extends Service {
	
	private static final String TAG = "ChqUseService";
	private BroadcastReceiver mReceiver;
	private Boolean isReceiverRegistered = false;


	@Override
	public void onCreate() {
		
		super.onCreate();
		
		CixLog.v(TAG, "onCreate");

	}

	@Override
	public void onDestroy() {
		CixLog.v(TAG, "onDestroy");
		
		unRegisterForPhoneUse();
		super.onDestroy();
	}

	private void unRegisterForPhoneUse() {
		 CixLog.v(TAG,"unregistering broadcard receiver");
		unregisterReceiver(mReceiver);
		setIsReceiverRegistered(false);
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 CixLog.v(TAG,"onStartCommand");
		
		if(getIsReceiverRegistered() == false) {
			registerForPhoneUse();
		}
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


	private void registerForPhoneUse() {
		
		 CixLog.v(TAG,"registering broadcard receiver");
		 IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

		 filter.addAction(Intent.ACTION_SCREEN_OFF);
		 filter.addAction(Intent.ACTION_USER_PRESENT);

		 mReceiver = new PhoneUseReceiver();
		 registerReceiver(mReceiver, filter);
		 
		 setIsReceiverRegistered(true);
		
	}

	public Boolean getIsReceiverRegistered() {
		return isReceiverRegistered;
	}

	public void setIsReceiverRegistered(Boolean isReceiverRegistered) {
		this.isReceiverRegistered = isReceiverRegistered;
	}

	
}
