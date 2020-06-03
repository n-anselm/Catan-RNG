package com.anselmdevelopment.catanrng;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.appizona.yehiahd.fastsave.FastSave;

public class SettingsActivity extends AppCompatActivity {

    TextView infoTitle;
    TextView infoText;
    TextView ok;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    Switch vibrateSwitch;
    Switch animateSwitch;
//    Switch dNumberSwitch;
    Switch exclude7Switch;
    public boolean isVibrate;
    public boolean isAnimate;
//    public boolean isDuplicate;
    public boolean isExlude7;
    public boolean infoVisible;
    public static final String RADIO1 = "radio1";
    public static final String RADIO2 = "radio2";
    public static final String RADIO3 = "radio3";
    public static final String VIBRATE = "vibrate";
    public static final String ANIMATE = "animate";
//    public static final String DUPLICATE = "duplicate";
    public static final String EXCLUDE7 = "exclude7Switch";
    ConstraintLayout constraintLayoutInfo;

    @Override
    public void onBackPressed() {
        if (infoVisible) {
            hideInfo();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave
        setContentView(R.layout.activity_settings);

        radioButton1 = findViewById(R.id.radiobutton1);
        radioButton2 = findViewById(R.id.radiobutton2);
        radioButton3 = findViewById(R.id.radiobutton3);
        vibrateSwitch = findViewById(R.id.switch_vibrate);
        animateSwitch = findViewById(R.id.switch_animate);
//        dNumberSwitch = findViewById(R.id.switch_duplicate);
        exclude7Switch = findViewById(R.id.switch_exclude7);
        constraintLayoutInfo = findViewById(R.id.constraintlayout_info);
        infoTitle = findViewById(R.id.tv_infotitle);
        infoText = findViewById(R.id.tv_infotext);
        ok = findViewById(R.id.tv_ok);

        isVibrate = FastSave.getInstance().getBoolean(VIBRATE, true);
        isAnimate = FastSave.getInstance().getBoolean(ANIMATE, true);
//        isDuplicate = FastSave.getInstance().getBoolean(DUPLICATE, true);
        isExlude7 = FastSave.getInstance().getBoolean(EXCLUDE7, false);

        infoVisible = false;

        if (FastSave.getInstance().getBoolean(RADIO1, true)) {
            radioButton1.setChecked(true);
        } else if (FastSave.getInstance().getBoolean(RADIO2, false)) {
            radioButton2.setChecked(true);
        } else if (FastSave.getInstance().getBoolean(RADIO3, false)) {
            radioButton3.setChecked(true);
        }

        if (isVibrate) {
            vibrateSwitch.setChecked(true);
        } else {
            vibrateSwitch.setChecked(false);
        }

        if (isAnimate) {
            animateSwitch.setChecked(true);
        } else {
            animateSwitch.setChecked(false);
        }

//        if (isDuplicate) {
//            dNumberSwitch.setChecked(true);
//        } else {
//            dNumberSwitch.setChecked(false);
//        }

        if (isExlude7) {
            exclude7Switch.setChecked(true);
        } else {
            exclude7Switch.setChecked(false);
        }

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastSave.getInstance().saveBoolean(RADIO1, true);
                FastSave.getInstance().saveBoolean(RADIO2, false);
                FastSave.getInstance().saveBoolean(RADIO3, false);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastSave.getInstance().saveBoolean(RADIO1, false);
                FastSave.getInstance().saveBoolean(RADIO2, true);
                FastSave.getInstance().saveBoolean(RADIO3, false);
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastSave.getInstance().saveBoolean(RADIO1, false);
                FastSave.getInstance().saveBoolean(RADIO2, false);
                FastSave.getInstance().saveBoolean(RADIO3, true);
            }
        });

        // Set a click listener on the vibrate switch
        vibrateSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrateSwitch.isChecked()) { // If the vibrate switch is set to "ON" position
                    isVibrate = true; // Set boolean "isVibrate" to true
                    FastSave.getInstance().saveBoolean(VIBRATE, isVibrate); // Save the switch position
                } else {
                    isVibrate = false; // Else set "isVibrate" to false
                    FastSave.getInstance().saveBoolean(VIBRATE, isVibrate); // Save the switch position
                }
            }
        });

        animateSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animateSwitch.isChecked()) {
                    isAnimate = true;
                    FastSave.getInstance().saveBoolean(ANIMATE, isAnimate);
                } else {
                    isAnimate = false;
                    FastSave.getInstance().saveBoolean(ANIMATE, isAnimate);
                }
            }
        });

//        dNumberSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dNumberSwitch.isChecked()) {
//                    isDuplicate = true;
//                    FastSave.getInstance().saveBoolean(DUPLICATE, isDuplicate);
//                } else {
//                    isDuplicate = false;
//                    FastSave.getInstance().saveBoolean(DUPLICATE, isDuplicate);
//                }
//            }
//        });

        exclude7Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exclude7Switch.isChecked()) {
                    isExlude7 = true;
                    FastSave.getInstance().saveBoolean(EXCLUDE7, isExlude7);
                } else {
                    isExlude7 = false;
                    FastSave.getInstance().saveBoolean(EXCLUDE7, isExlude7);
                }
            }
        });

        ImageView backArrow = findViewById(R.id.iv_backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView info = findViewById(R.id.imageview_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInfoWithDelay();
            }
        });

        LinearLayout top = findViewById(R.id.linearlayout_top3);
        LinearLayout left = findViewById(R.id.linearlayout_left3);
        LinearLayout right = findViewById(R.id.linearlayout_right3);
        LinearLayout bottom = findViewById(R.id.linearlayout_bottom3);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInfo();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInfo();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInfo();
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInfo();
            }
        });
    }

    public void showInfo() {
        constraintLayoutInfo.setVisibility(View.VISIBLE);
        infoVisible = true;
    }

    public void hideInfo() {
        constraintLayoutInfo.setVisibility(View.GONE);
        infoVisible = false;
    }

    public void hideInfoWithDelay() {
        int num = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayoutInfo.setVisibility(View.GONE);
            }
        }, num * 100);
        infoVisible = false;
    }
}
