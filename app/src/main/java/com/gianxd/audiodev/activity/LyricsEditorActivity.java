package com.gianxd.audiodev.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gianxd.audiodev.R;

import java.util.ArrayList;
import java.util.HashMap;


public class LyricsEditorActivity extends  AppCompatActivity  { 

	private ArrayList<HashMap<String, Object>> musicData = new ArrayList<>();
	
	private LinearLayout toolbar;
	private EditText lyrics;
	private ImageView back;
	private TextView bruh;
	private ImageView save;
	
	private SharedPreferences savedData;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_lyrics_editor);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		toolbar = (LinearLayout) findViewById(R.id.toolbar);
		lyrics = (EditText) findViewById(R.id.lyrics);
		back = (ImageView) findViewById(R.id.back);
		bruh = (TextView) findViewById(R.id.bruh);
		save = (ImageView) findViewById(R.id.save);
		savedData = getSharedPreferences("savedData", Activity.MODE_PRIVATE);
		
		lyrics.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (lyrics.getText().toString().length() < 1) {
					save.setEnabled(false);
					save.setAlpha((float)(0.5d));
				}
				else {
					save.setEnabled(true);
					save.setAlpha((float)(1.0d));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.graphics.drawable.RippleDrawable rippleButton = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#BDBDBD") }), null, null);
				back.setBackground(rippleButton);
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.graphics.drawable.RippleDrawable rippleButton = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#BDBDBD") }), null, null);
				save.setBackground(rippleButton);
				try {
					musicData.get((int)Double.parseDouble(getIntent().getStringExtra("songPosition"))).put("songLyrics", lyrics.getText().toString());
					savedData.edit().putString("savedMusicData", new Gson().toJson(musicData)).apply();
					com.gianxd.musicdev.MusicDevUtil.showMessage(getApplicationContext(), "Lyrics saved successfully.");
					finish();
				} catch (Exception e) {
					com.gianxd.musicdev.MusicDevUtil.showMessage(getApplicationContext(), "Error saving lyrics.");
				}
			}
		});
	}
	
	private void initializeLogic() {
		bruh.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto_medium.ttf"), Typeface.NORMAL);
		musicData = new Gson().fromJson(savedData.getString("savedMusicData", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		if (Build.VERSION.SDK_INT >= 23) {
			toolbar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
			getWindow().setNavigationBarColor(Color.parseColor("#EEEEEE"));
		}
		else {
			getWindow().setStatusBarColor(Color.parseColor("#000000"));
			getWindow().setNavigationBarColor(Color.parseColor("#000000"));
		}
		if (musicData.get((int)Double.parseDouble(savedData.getString("savedSongPosition", "0"))).containsKey("songLyrics")) {
					if (musicData.get((int)Double.parseDouble(savedData.getString("savedSongPosition", "0"))).get("songLyrics").toString().length() == 0) {
							// lyrics is added but empty cheems.
					} else {
						    lyrics.setText(musicData.get((int)Double.parseDouble(savedData.getString("savedSongPosition", "0"))).get("songLyrics").toString());
				        }
		} else {
				// no lyrics found cheems.
		}
		save.setEnabled(false);
		save.setAlpha((float)(0.5d));
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
}
