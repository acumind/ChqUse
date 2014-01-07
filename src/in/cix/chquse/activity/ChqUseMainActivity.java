package in.cix.chquse.activity;

import in.cix.chquse.ChqUseApp;
import in.cix.chquse.R;
import in.cix.chquse.model.UseStats;
import in.cix.chquse.service.ChqUseService;
import in.cix.chquse.utils.CixLog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ChqUseMainActivity extends Activity {


	private static final String TAG = "ChqUseMainActivity";
	private UseStats counter = null;
	private TextView onOffValueView = null;
	private TextView lockUnlockValueView = null;

	class uHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			CixLog.d(TAG,"Update Message Handling");
			refereshCounters();
			super.handleMessage(msg);
		}
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chquse_main);

		ChqUseApp.updateHandler = new uHandler();
		counter = ChqUseApp.app.getCounter();
		populateViews();
		refereshCounters();
		

		
	}


	@Override
	protected void onResume() {
	
		startService(new Intent(getApplicationContext(), ChqUseService.class)); 
		refereshCounters();
		super.onResume();
	}


	public void refereshCounters() {
		CixLog.d(TAG, "Updating Counters");
		onOffValueView.setText(counter.getScreenOnOffCount() +"");
		lockUnlockValueView.setText(counter.getLockUnlockCount() + "");
		
	}
	public void resetCounters() {
		counter.reset();
		refereshCounters();
		
	}

	private void populateViews() {
		onOffValueView = (TextView) findViewById(R.id.screenOnOffValue);
		lockUnlockValueView = (TextView) findViewById(R.id.lockUnlockValue);

		
	}

	public void onButtonClick(View btn){
		
		switch (btn.getId()) {
		case R.id.btnRefresh:
			CixLog.d(TAG, "onBtnRefresh");
			refereshCounters();
				
			break;
		case R.id.btnReset:
			CixLog.d(TAG, "onBtnReset");
			resetCounters();
				
			break;

		default:
			break;
		}
		
		
	}
	
	@Override
	protected void onDestroy() {
		
		//Intent i= new Intent(getApplicationContext(), ChqUseService.class);
		//stopService(i); 
		
		super.onDestroy();
	}

	


}
