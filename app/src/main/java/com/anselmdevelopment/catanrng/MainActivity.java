package com.anselmdevelopment.catanrng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appizona.yehiahd.fastsave.FastSave;

import java.util.ArrayList;
import java.util.Random;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;

import static com.anselmdevelopment.catanrng.SettingsActivity.ANIMATE;
import static com.anselmdevelopment.catanrng.SettingsActivity.DUPLICATE;
import static com.anselmdevelopment.catanrng.SettingsActivity.EXCLUDE7;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO1;
import static com.anselmdevelopment.catanrng.SettingsActivity.RADIO2;
import static com.anselmdevelopment.catanrng.SettingsActivity.VIBRATE;
import static com.anselmdevelopment.catanrng.SettingsActivity.SHAKE;

public class MainActivity extends AppCompatActivity {

    int number;
    int animation;
    public static final ArrayList<String> history = new ArrayList<>();
    public static String hist = null;
    public boolean isSpinMethod1;
    public boolean isSpinMethod2;
    public boolean isVibrate;
    public boolean isShake;
    public boolean isDuplicateNumberFilter;
    public boolean menuVisible;
    public boolean historyVisible;
    private TextView mRandomNumber;
    private TextView mGenerateButton;
    private ShakeDetector shakeDetector; // Do not delete
    ConstraintLayout constraintLayoutMenu;
    ConstraintLayout constraintLayoutHistory;

    @Override
    public void onBackPressed() {
        if (menuVisible) {
            constraintLayoutMenu.setVisibility(View.GONE);
            menuVisible = false;
        } else if (historyVisible) {
            constraintLayoutHistory.setVisibility(View.GONE);
            historyVisible = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        setContentView(R.layout.activity_main);

        mRandomNumber = findViewById(R.id.random_number);
        mGenerateButton = findViewById(R.id.spin_button);
        constraintLayoutHistory = findViewById(R.id.constraintlayout_history);
        animation = 1;
        menuVisible = false;
        historyVisible = false;

        final ImageView optionsMenu = findViewById(R.id.options_menu);
        optionsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                constraintLayoutMenu = findViewById(R.id.constraintlayout_menu);
                constraintLayoutMenu.setVisibility(View.VISIBLE);
                menuVisible = true;

                TextView tvgameOverView = findViewById(R.id.tv_gameoverview);
                TextView tvhistory = findViewById(R.id.tv_history);
                TextView tvsettings = findViewById(R.id.tv_settings);
                TextView tvabout = findViewById(R.id.tv_about);

                LinearLayout topll = findViewById(R.id.linearlayout_top);
                LinearLayout leftll = findViewById(R.id.linearlayout_left);
                LinearLayout rightll = findViewById(R.id.linearlayout_right);
                LinearLayout bottomll = findViewById(R.id.linearlayout_bottom);

                tvgameOverView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, GameOverviewActivity.class));
                        hideMenuWithDelay();
                    }
                });

                tvhistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();

                        final TextView tvHistoryNumbers = findViewById(R.id.tv_infotext);
                        TextView ok = findViewById(R.id.tv_ok);
                        TextView clear = findViewById(R.id.tv_clear);

                        LinearLayout top2 = findViewById(R.id.linearlayout_top2);
                        LinearLayout left2 = findViewById(R.id.linearlayout_left2);
                        LinearLayout right2 = findViewById(R.id.linearlayout_right2);
                        LinearLayout bottom2 = findViewById(R.id.linearlayout_bottom2);

                        if (history.isEmpty()) {
                            hist = "No history";
                        } else {
                            /* Put the values of the ArrayList history into the String hist and replace
                            the [ and ] with nothing so they don't appear in the history dialog */
                            hist = history.toString().replace("[", "").replace("]", "");
                        }

                        tvHistoryNumbers.setText(hist);
                        constraintLayoutHistory.setVisibility(View.VISIBLE);
                        historyVisible = true;

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int num = 1;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        constraintLayoutHistory.setVisibility(View.GONE);
                                    }
                                }, num * 100);
                            }
                        });

                        clear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                history.clear();
                                tvHistoryNumbers.setText("No history");
                            }
                        });

                        top2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        left2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        right2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });

                        bottom2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                hideHistory();
                            }
                        });
                    }
                });

                tvsettings.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                        hideMenuWithDelay();
                    }
                });

                tvabout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        hideMenuWithDelay();
                    }
                });

                topll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                leftll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                rightll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });

                bottomll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideMenu();
                    }
                });
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
                    if (FastSave.getInstance().getBoolean(ANIMATE, true)) {
                        animate();
                    }
                    generateRandomNumber();
                    vibrate();
                }
            }
        });

        // Set onClickListener on the spin button_background
        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastSave.getInstance().getBoolean(ANIMATE, true)) {
                    animate();
                }
                generateRandomNumber();
            }
        });
    }

    public void hideMenu() {
        constraintLayoutMenu.setVisibility(View.GONE);
        menuVisible = false;
    }

    public void hideMenuWithDelay() {
        final ConstraintLayout constraintLayoutMenu = findViewById(R.id.constraintlayout_menu);
        int num = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayoutMenu.setVisibility(View.GONE);
            }
        }, num * 250);
        menuVisible = false;
    }

    public void hideHistory() {
        constraintLayoutHistory.setVisibility(View.GONE);
        historyVisible = false;
    }

    public void animate() {
        int delay = 1;
        if (animation == 1) {
            mRandomNumber.setText("5");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("2");
                }
            }, delay + 75);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("10");
                }
            }, delay + 150);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("1");
                }
            }, delay + 225);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("4");
                }
            }, delay + 300);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("12");
                }
            }, delay + 375);
            animation = 2;
        } else if (animation == 2) {
            mRandomNumber.setText("11");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("6");
                }
            }, delay + 75);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("9");
                }
            }, delay + 150);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("7");
                }
            }, delay + 225);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("1");
                }
            }, delay + 300);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("3");
                }
            }, delay + 375);
            animation = 3;
        } else if (animation == 3) {
            mRandomNumber.setText("7");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("8");
                }
            }, delay + 75);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("12");
                }
            }, delay + 150);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("4");
                }
            }, delay + 225);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("6");
                }
            }, delay + 300);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRandomNumber.setText("9");
                }
            }, delay + 375);
            animation = 1;
        }
    }

    // Generate a random number according to generate method 1
    public int generateMethod1() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }

    // Generate a random number according to generate method 2
    public int generateMethod2() {
        int min = 2;
        int max = 12;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        history.add(String.valueOf(randomNumber));
        return randomNumber;
    }

    public int addIntsMethod1() {
        int number1 = generateMethod1();
        int number2 = generateMethod1();
        int sum = number1 + number2;
        history.add(String.valueOf(sum));
        return sum;
    }

    public void generateRandomNumber() {
        isVibrate = FastSave.getInstance().getBoolean(VIBRATE, true);
        isSpinMethod1 = FastSave.getInstance().getBoolean(RADIO1, true);
        isSpinMethod2 = FastSave.getInstance().getBoolean(RADIO2, false);
        isDuplicateNumberFilter = FastSave.getInstance().getBoolean(DUPLICATE, true);
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
                int rn = generateMethod2();
                if (rn == number) {
                    int r1 = generateMethod2();
                    if (r1 == number) {
                        int n1 = generateMethod2();
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
                int random = generateMethod2();
                setRandomNumberText(random);
            }
        }
    }

    public void setRandomNumberText(int i) {
        final int x = i;
        if (FastSave.getInstance().getBoolean(EXCLUDE7, false) && x == 7) {
            generateRandomNumber();
        } else {
            if (FastSave.getInstance().getBoolean(ANIMATE, true)) {
                int delay = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mRandomNumber.setText(Integer.toString(x));
                    }
                }, delay + 500);
            } else {
                mRandomNumber.setText(Integer.toString(x));
            }
        }
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
