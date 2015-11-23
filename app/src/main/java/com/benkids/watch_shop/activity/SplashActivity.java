package com.benkids.watch_shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.benkids.watch_shop.R;
public class SplashActivity extends Activity {
	private SharedPreferences sharedPreferences;
	private Boolean isFirstIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		sharedPreferences=getSharedPreferences("firstIn_spf", MODE_PRIVATE);
		isFirstIn=sharedPreferences.getBoolean("isFirstIn", true);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run(){
				Intent intent=new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		}, 3000);
	}
}
