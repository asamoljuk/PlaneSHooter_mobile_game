package com.sandipbhattacharya.planeshooter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class GameOver extends Activity {

    TextView tvScore, tvPersonalBest;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int score = Objects.requireNonNull(getIntent().getExtras()).getInt("score");
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int scoreSP = pref.getInt("scoreSP", 0);
        SharedPreferences.Editor editor = pref.edit();
        if(score > scoreSP){
            scoreSP = score;
            editor.putInt("scoreSP", scoreSP);
            editor.apply();
        }
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        tvScore.setText(""+score);
        tvPersonalBest.setText(""+scoreSP);
    }
    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void exit(View view){
        finish();
    }
}
