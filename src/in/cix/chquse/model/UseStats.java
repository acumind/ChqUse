package in.cix.chquse.model;

import android.content.Context;
import android.content.SharedPreferences;
import in.cix.chquse.ChqUseApp;
import in.cix.chquse.utils.CixLog;

public class UseStats {

	public static final int ON = 0;
	public static final int OFF = 1;
	public static final int LOCK = 2;
	public static final int UNLOCK = 3;
	private static final String TAG = "UseStats";

	private Boolean isLocked = false;
	private Boolean isUnLocked = false;
	private Boolean isScreenOn = false;
	private Boolean isScreenOff = false;

	private int LockUnlockCount = 0;
	private int ScreenOnOffCount = 0;

	public UseStats() {

	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsUnLocked() {
		return isUnLocked;
	}

	public void setIsUnLocked(Boolean isUnLocked) {
		this.isUnLocked = isUnLocked;
	}

	public Boolean getIsScreenOn() {
		return isScreenOn;
	}

	public void setIsScreenOn(Boolean isScreenOn) {
		this.isScreenOn = isScreenOn;
	}

	public Boolean getIsScreenOff() {
		return isScreenOff;
	}

	public void setIsScreenOff(Boolean isScreenOff) {
		this.isScreenOff = isScreenOff;
	}

	public int getLockUnlockCount() {
		return LockUnlockCount;
	}

	public void setLockUnlockCount(int lockUnlockCount) {
		LockUnlockCount = lockUnlockCount;
	}

	public int getScreenOnOffCount() {
		return ScreenOnOffCount;
	}

	public void setScreenOnOffCount(int screenOnOffCount) {
		ScreenOnOffCount = screenOnOffCount;
	}

	public void setStat(int state) {

		switch (state) {
		case ON: {
			setIsScreenOn(true);
			setIsScreenOff(false);
			break;
		}
		case OFF: {
			updateCounter(ChqUseApp.PREF_KEY_ON_OFF);
			/*setIsScreenOff(true);
			if (getIsScreenOn()) {
				updateCounter(ChqUseApp.PREF_KEY_ON_OFF);
				setIsScreenOn(false);
			}*/
			break;
		}
		case LOCK: {
			setIsLocked(true);
			setIsUnLocked(false);
			break;
		}
		case UNLOCK: {
			//setIsUnLocked(true);
			updateCounter(ChqUseApp.PREF_KEY_LOCK_UNLOCK);
			break;
		}

		default:
			CixLog.e(TAG, "Unreachable device state");

		}

	}

	private void updateCounter(String key) {
		SharedPreferences pref = ChqUseApp.app.getSharedPreferences(
				ChqUseApp.PREF, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();

		int counter = pref.getInt(key, 0);
		prefEditor.putInt(key, ++counter);
		prefEditor.commit();
		
		if( key.trim().equalsIgnoreCase(ChqUseApp.PREF_KEY_ON_OFF)) {
			setScreenOnOffCount(counter);			
		}else if(key.trim().equalsIgnoreCase(ChqUseApp.PREF_KEY_LOCK_UNLOCK)){
			setLockUnlockCount(counter);
		}
		
	}

	public void reset() {
		SharedPreferences pref = ChqUseApp.app.getSharedPreferences(
				ChqUseApp.PREF, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = pref.edit();

		prefEditor.putInt(ChqUseApp.PREF_KEY_ON_OFF, 0);
		setScreenOnOffCount(0);
		prefEditor.putInt(ChqUseApp.PREF_KEY_LOCK_UNLOCK, 0);
		setLockUnlockCount(0);
		prefEditor.commit();

		
	}
	
	
	

}
