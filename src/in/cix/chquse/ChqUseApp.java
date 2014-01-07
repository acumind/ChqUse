package in.cix.chquse;

import in.cix.chquse.model.UseStats;
import in.cix.chquse.utils.CixLog;
import android.app.Application;
import android.os.Handler;

public class ChqUseApp extends Application {
	
	private static final String TAG = "ChqUseApp";
	public static ChqUseApp app = null;
	public static boolean DEV_MODE = true;
	private static UseStats counter = null;
	public static final String PREF = "counter";
	public static final String PREF_KEY_ON_OFF = "ONOFF";
	public static final String PREF_KEY_LOCK_UNLOCK = "LOCKUNLOCK";
	public static Handler  updateHandler = null;
	
	
	public UseStats getCounter() {
		if(counter == null) {
			counter = new UseStats();
		}
		return counter;
		
	}
	
	
	@Override
	public void onCreate() {

		
		app = this;
		checkAndSetBuildMode();
		super.onCreate();
	}
	
	
	private void checkAndSetBuildMode() {
		if(BuildConfig.DEBUG)
			DEV_MODE = true;
		else
			DEV_MODE = false;
		
		CixLog.i(TAG, "App Build Mode = " + (DEV_MODE?"Debug":"Release"));
		
	}
	
	

}
