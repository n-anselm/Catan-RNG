package com.anselmdevelopment.catanrng;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;

public class SettingsActivity extends AppCompatActivity {

    RadioButton radioButton1;
    RadioButton radioButton2;
    Switch vibrateSwitch;
    Switch shakeSwitch;
    Switch dNumberSwitch;
    Switch exclude7Switch;
    public boolean isRadio1;
    public boolean isRadio2;
    public boolean isVibrate;
    public boolean isShake;
    public boolean isDuplicate;
    //    public boolean isExlude7;
    public static final String RADIO1 = "radio1";
    public static final String RADIO2 = "radio2";
    public static final String SHAKE = "shake";
    public static final String VIBRATE = "vibrate";
    public static final String DUPLICATE = "duplicate";
//    public static final String EXCLUDE7 = "exclude7Switch";

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_settings);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        vibrateSwitch = findViewById(R.id.switch_vibrate);
        shakeSwitch = findViewById(R.id.switch_shake);
        dNumberSwitch = findViewById(R.id.switch_duplicate);
//        exclude7Switch = findViewById(R.id.switch_exclude7);

        isRadio1 = FastSave.getInstance().getBoolean(RADIO1, true);
        isRadio2 = FastSave.getInstance().getBoolean(RADIO2, false);
        isVibrate = FastSave.getInstance().getBoolean(VIBRATE, false);
        isShake = FastSave.getInstance().getBoolean(SHAKE, false);
        isDuplicate = FastSave.getInstance().getBoolean(DUPLICATE, true);
//        isExlude7 = FastSave.getInstance().getBoolean(EXCLUDE7, false);

        if (isRadio1) {
            radioButton1.setChecked(true);
        } else if (isRadio2) {
            radioButton2.setChecked(true);
        }
        if (isVibrate) {
            vibrateSwitch.setChecked(true);
        } else {
            vibrateSwitch.setChecked(false);
        }
        if (isShake) {
            shakeSwitch.setChecked(true);
        } else {
            shakeSwitch.setChecked(false);
        }
        if (isDuplicate) {
            dNumberSwitch.setChecked(true);
        } else {
            dNumberSwitch.setChecked(false);
        }
//        if (isExlude7) {
//            exclude7Switch.setChecked(true);
//        } else {
//            exclude7Switch.setChecked(false);
//        }

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRadio1 = true;
                isRadio2 = false;
                FastSave.getInstance().saveBoolean(RADIO1, isRadio1);
                FastSave.getInstance().saveBoolean(RADIO2, isRadio2);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRadio2 = true;
                isRadio1 = false;
                FastSave.getInstance().saveBoolean(RADIO2, isRadio2);
                FastSave.getInstance().saveBoolean(RADIO1, isRadio1);
            }
        });

        // Set a click listener on the shake switch
        vibrateSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrateSwitch.isChecked()) { // If the shake switch is set to "ON" position
                    isVibrate = true; // Set boolean "isShake" to true
                    FastSave.getInstance().saveBoolean(VIBRATE, isVibrate); // Save the switch position
                } else {
                    isVibrate = false; // Else set "isShake" to false
                    FastSave.getInstance().saveBoolean(VIBRATE, isVibrate); // Save the switch position
                }
            }
        });

        shakeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shakeSwitch.isChecked()) {
                    isShake = true;
                    FastSave.getInstance().saveBoolean(SHAKE, isShake);
                } else {
                    isShake = false;
                    FastSave.getInstance().saveBoolean(SHAKE, isShake);
                }
            }
        });

        dNumberSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dNumberSwitch.isChecked()) {
                    isDuplicate = true;
                    FastSave.getInstance().saveBoolean(DUPLICATE, isDuplicate);
                } else {
                    isDuplicate = false;
                    FastSave.getInstance().saveBoolean(DUPLICATE, isDuplicate);
                }
            }
        });

//        exclude7Switch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (exclude7Switch.isChecked()) {
//                    isExlude7 = true;
//                    FastSave.getInstance().saveBoolean(EXCLUDE7, isExlude7);
//                } else {
//                    isExlude7 = false;
//                    FastSave.getInstance().saveBoolean(EXCLUDE7, isExlude7);
//                }
//            }
//        });

        FrameLayout backArrow = findViewById(R.id.settings_back_arrow);
        // Sets onClickListener on the back arrow
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FrameLayout info1 = findViewById(R.id.framelayout_info1);
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("1st Spin Method")
                        .setMessage(R.string.info_method1)
                        .setPositiveButton("Ok", null)
                        .create().show();
            }
        });

        FrameLayout info2 = findViewById(R.id.framelayout_info2);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("2nd Spin Method")
                        .setMessage(R.string.info_method2)
                        .setPositiveButton("Ok", null)
                        .create().show();
            }
        });

        FrameLayout vibrateInfo = findViewById(R.id.framelayout_info_vibrate);
        vibrateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Vibrate")
                        .setMessage(R.string.info_vibrate)
                        .setPositiveButton("Ok", null)
                        .create().show();
            }
        });

        FrameLayout shakeInfo = findViewById(R.id.framelayout_info_shake);
        shakeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Shake")
                        .setMessage(R.string.info_shake)
                        .setPositiveButton("Ok", null)
                        .create().show();
            }
        });

        FrameLayout dnfInfo = findViewById(R.id.framelayout_info_filter);
        dnfInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Duplicate number filter")
                        .setMessage(R.string.info_duplicate_number_filter)
                        .setPositiveButton("Ok", null)
                        .create().show();
            }
        });

//        FrameLayout exclude7Info = findViewById(R.id.framelayout_info_exclude7);
//        exclude7Info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
//                builder.setTitle("Exclude 7")
//                        .setMessage("Enabling this will exclude the number 7 from spin results.")
//                        .setPositiveButton("Ok", null)
//                        .create().show();
//            }
//        });
    }
}
