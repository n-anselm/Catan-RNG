package com.anselmdevelopment.catanrng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.appizona.yehiahd.fastsave.FastSave;

import java.util.Random;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;

import static com.anselmdevelopment.catanrng.SettingsActivity.DUPLICATE;
//import static com.anselmdevelopment.catanrng.SettingsActivity.EXCLUDE7;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO1;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO2;
import static com.anselmdevelopment.catanrng.SettingsActivity.VIBRATE;
import static com.anselmdevelopment.catanrng.SettingsActivity.SHAKE;

public class MainActivity extends AppCompatActivity {

    int number;
    public boolean isSpinMethod1;
    public boolean isSpinMethod2;
    public boolean isVibrate;
    public boolean isShake;
    public boolean isDuplicateNumberFilter;
    //    public boolean isExclude7;
    private TextView mRandomNumber;
    private TextView mSpinButton;
    private ShakeDetector shakeDetector; // Do not delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_main);

        mRandomNumber = findViewById(R.id.random_number);
        mSpinButton = findViewById(R.id.spin_button);

        final ImageView optionsMenu = findViewById(R.id.options_menu);
        // Sets onClickListener on the options ImageView
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create instance of PopupMenu
                Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.popupMenuStyle);
                final PopupMenu popupMenu = new PopupMenu(wrapper, optionsMenu);

                // Inflate the popup menu using xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                // Register popup with OnMenuItemClickListener
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.action_gameoverview) {
                            Intent gameOverviewActivity = new Intent(MainActivity.this, GameOverviewActivity.class);
                            startActivity(gameOverviewActivity);
                            return true;
                        } else if (id == R.id.action_settings) {
                            Intent settingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
                            startActivity(settingsActivity);
                        } else if (id == R.id.action_about) {
                            Intent aboutAppActivity = new Intent(MainActivity.this, AboutActivity.class);
                            startActivity(aboutAppActivity);
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        // Settings for shake
        ShakeOptions shakeOptions = new ShakeOptions()
                .background(false)
                .interval(650)
                .shakeCount(2)
                .sensibility(2.0f);

        this.shakeDetector = new ShakeDetector(shakeOptions).start(this, new ShakeCallback() {
            @Override
            public void onShake() {
                isShake = FastSave.getInstance().getBoolean(SHAKE, false); // Get boolean stored with FastSave
                if (isShake) {
                    spin();
                    vibrate();
                }
            }
        });

        // Set onClickListener on the spin button_background
        mSpinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin();
            }
        });
    }

    // Generate a random number according to spin method 1
    public int spinMethod1() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    // Generate a random number according to spin method 2
    public int spinMethod2() {
        int min = 2;
        int max = 12;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    public int addIntsMethod1() {
        int number1 = spinMethod1();
        int number2 = spinMethod1();
        int sum = number1 + number2;
        return sum;
    }

    public void spin() {
        isVibrate = FastSave.getInstance().getBoolean(VIBRATE, false);
        isSpinMethod1 = FastSave.getInstance().getBoolean(RADIO1, true);
        isSpinMethod2 = FastSave.getInstance().getBoolean(RADIO2, false);
        isDuplicateNumberFilter = FastSave.getInstance().getBoolean(DUPLICATE, true);
//        isExclude7 = FastSave.getInstance().getBoolean(EXCLUDE7, false);
        if (isVibrate) {
            vibrate();
        }
        // Spin method 1
        if (isSpinMethod1) { // If method 1 is selected
            if (isDuplicateNumberFilter) {
                int sum = addIntsMethod1();
                if (sum == number) { // 1st filter
                    int sum2 = addIntsMethod1();
                    if (sum2 == number) { // 2nd filter
                        int sum3 = addIntsMethod1();
                        setRandomNumberText(sum3);
                        number = sum3;
                    } else {
                        setRandomNumberText(sum2);
                        number = sum2;
                    }
                } else {
                    setRandomNumberText(sum);
                    number = sum;
                }
            } else {
                int sum = addIntsMethod1();
                setRandomNumberText(sum);
                number = sum;
            }
            // Spin method 2
        } else if (isSpinMethod2) { // If method 2 is selected
            if (isDuplicateNumberFilter) {
                int rn = spinMethod2();
                if (rn == number) {
                    int r1 = spinMethod2();
                    if (r1 == number) {
                        int n1 = spinMethod2();
                        setRandomNumberText(n1);
                        number = n1;
                    } else {
                        setRandomNumberText(r1);
                        number = r1;
                    }
                } else {
                    setRandomNumberText(rn);
                    number = rn;
                }
            } else {
                int random = spinMethod2();
                setRandomNumberText(random);
            }
        }
    }

    public void setRandomNumberText(int i) {
        mRandomNumber.setText(Integer.toString(i));
    }

    public void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 70 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(70); // Deprecated in API 26
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    // Hides the status bar
    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}