package com.gianxd.audiodev.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gianxd.audiodev.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class LiveStreamActivity extends  AppCompatActivity  {

	private LinearLayout up;
	private LinearLayout player;
	private TextView archo_name;
	private ImageView albumArt;
	private TextView songTitle;
	private TextView songArtist;
	private LinearLayout playback2;
	private ImageView skipBackward;
	private ImageView playPause;
	private ImageView skipForward;
	
	private SharedPreferences savedData;
	private FirebaseDatabase fDatabase;
	private FirebaseAuth fAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_streaming);
		initialize(savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle savedInstanceState) {
		up = (LinearLayout) findViewById(R.id.up);
		player = (LinearLayout) findViewById(R.id.player);
		archo_name = (TextView) findViewById(R.id.archo_name);
		albumArt = (ImageView) findViewById(R.id.albumArt);
		songTitle = (TextView) findViewById(R.id.songTitle);
		songArtist = (TextView) findViewById(R.id.songArtist);
		playback2 = (LinearLayout) findViewById(R.id.playback2);
		skipBackward = (ImageView) findViewById(R.id.skipBackward);
		playPause = (ImageView) findViewById(R.id.playPause);
		skipForward = (ImageView) findViewById(R.id.skipForward);
		savedData = getSharedPreferences("savedData", Activity.MODE_PRIVATE);
		fDatabase = FirebaseDatabase.getInstance();
		fAuth = FirebaseAuth.getInstance();
	}
	
	private void initializeLogic() {
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _startupUI () {
		
	}
	
	
	
}
