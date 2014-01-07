package in.cix.chquse.utils;

import in.cix.chquse.ChqUseApp;
import android.util.Log;

public class CixLog {


		public static void d( String TAG, String text ) {
			if (ChqUseApp.DEV_MODE) {
				Log.d(TAG, text);
			}
		}
		
		public static void i( String TAG, String text ) {
			if (ChqUseApp.DEV_MODE) {
				Log.i(TAG, text);
			}
		}
		
		public static void e( String TAG, String text ) {
			if (ChqUseApp.DEV_MODE) {
				Log.e(TAG, text);
			}
		}
		
		public static void v( String TAG, String text ) {
			if (ChqUseApp.DEV_MODE) {
				Log.v(TAG, text);
			}
		}
		
	

}
