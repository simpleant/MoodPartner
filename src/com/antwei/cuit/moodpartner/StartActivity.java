package com.antwei.cuit.moodpartner;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class StartActivity extends Activity {
	
	private MediaPlayer startMusic;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		
		initPrograme();
		startMusic.start();
		startChat();
		
		
	}

	private void initPrograme() {
		// TODO Auto-generated method stub
		startMusic = MediaPlayer.create(getBaseContext(), R.raw.start);
		intent = new Intent();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		startMusic.release();
		finish();
	}

	private void startChat() {
		 new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					try{
						sleep(3000);
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						intent.setClass(getBaseContext(), ChatActivity.class);
						startActivity(intent);
					}
				}
				
			}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
