package com.anselmdevelopment.catanrng;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverviewActivity extends AppCompatActivity {

    private FrameLayout mBackArrow;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_overview);

        mBackArrow = findViewById(R.id.overview_back_arrow);

        // Sets onClickListener on the back arrow
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
